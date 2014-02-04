package com.admarketplace.isg.mypool;

import com.admarketplace.isg.util.Util;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MySimpleGenericPool<T extends Resource> implements GenericPool<T> {
	private int capacity;
	private AtomicBoolean poolIsOpen = new AtomicBoolean();
	private PoolObjectFactory<T> factory;
	private ReentrantLock lock = new ReentrantLock();
	private final Condition ObjectsAreAvailable = lock.newCondition();

	public MySimpleGenericPool(PoolObjectFactory<T> myFactory, int capacity) throws Exception {

		this.capacity = capacity;
		poolIsOpen.set(true);

		factory = myFactory;

		for (int i = 0; i < capacity; i++) {
			add(factory.createObject());
		}
	}

	public ConcurrentHashMap<Integer, T> poolOfObjects = new ConcurrentHashMap<Integer, T>(capacity);

	@Override
	public void open() {
		poolIsOpen.set(true);
	}

	@Override
	public boolean isOpen() {
		return poolIsOpen.get();
	}

	@Override
	public synchronized void close() {
		Enumeration<Integer> keys = poolOfObjects.keys();
		while (keys.hasMoreElements()) {
			Integer key = keys.nextElement();
			T object = poolOfObjects.get(key);
			if (!object.isAvailable()) {
				try {
					this.wait(); // wait until its released
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			} else {
				factory.destroyObject(object);
			}

		}
		poolIsOpen.set(false);
	}

	@Override
	public synchronized void closeNow() {
		Enumeration<Integer> keys = poolOfObjects.keys();
		while (keys.hasMoreElements()) {
			Integer key = keys.nextElement();
			T object = poolOfObjects.get(key);
			factory.destroyObject(object);

		}

		poolIsOpen.set(false);

	}

	@Override
	public boolean add(T mongo) {

		if (poolIsOpen.get()) {
			Integer hashCode = (Integer) mongo.hashCode();
			if (poolOfObjects.containsKey(hashCode)) {
				echo("pool already has the object..discarding the object");
				return false;
			} else {
				echo("adding an object to the pool");
				poolOfObjects.put(hashCode, mongo);
				lock.lock();
				try {
					ObjectsAreAvailable.signalAll();
				} finally {
					lock.unlock();
				}
			}
		}

		return false;
	}

	@Override
	public boolean remove(T conn) {

		if (poolIsOpen.get()) {
			Integer hashCode = (Integer) conn.hashCode();
			if (!poolOfObjects.containsKey(hashCode))
				return false;
			else {
				while (true) {
					if (poolOfObjects.get(poolOfObjects.get(hashCode)).isAvailable()) {
						poolOfObjects.remove(hashCode);
						return true;
					} else {// resource in use

						try {
							synchronized (ObjectsAreAvailable) {
								ObjectsAreAvailable.await();
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
							return false;
						}

					}
				}// while(true)
			}

		}

		return false;
	}

	@Override
	public boolean removeNow(T conn) {

		if (poolIsOpen.get()) {
			Integer hashCode = (Integer) conn.hashCode();
			if (!poolOfObjects.containsKey(hashCode))
				return false;
			else {
				poolOfObjects.remove(hashCode);
				return true;

			}
		}

		return false;
	}

	@Override
	public T acquire() throws InterruptedException {
		T myAcquiredObject = null;
		try {
		lock.lock();
		
			while (myAcquiredObject == null && poolIsOpen.get()) {

				if (poolOfObjects.isEmpty()) {
					echo("pool is empty.");

					ObjectsAreAvailable.await();
				} else {
					
					myAcquiredObject = fetchMeAnObjectFromPool(); // and
																	// wait

				}
			}
		} finally {
			lock.unlock();
		}

		return myAcquiredObject;
	}

	private void echo(String string) {
		System.out.println(string);
	}

	@Override
	public T acquire(long timeout, TimeUnit unit) throws InterruptedException {
		if (lock.tryLock(timeout, unit)) {
			try {
				if (poolIsOpen.get()) {
					if (poolOfObjects.isEmpty()) {
						return null;
					} else {
						return fetchMeAnObjectFromPool(timeout, unit);
					}
				} else {
					return null;// pool is closed
				}

			} finally {
				lock.unlock();
			}
		}
		return null;

	}

	private T fetchMeAnObjectFromPool() throws InterruptedException {
		return fetchMeAnObjectFromPool(0, TimeUnit.MINUTES);
	}

	private T fetchMeAnObjectFromPool(long timeout, TimeUnit timeUnit) throws InterruptedException {

		T object = null;
		if (lock.tryLock(timeout, timeUnit)) {
			try {

				Enumeration<Integer> keys = poolOfObjects.keys();
				while (keys.hasMoreElements()) {
					Integer key = keys.nextElement();
					
					if (poolOfObjects.get(key).isAvailable()) {
						echo("fetching an object from the pool.");
						poolOfObjects.get(key).setAvailable(false);
						poolOfObjects.get(key).numberOfAcquired.incrementAndGet();
						object = poolOfObjects.get(key);
						return object;
					}
					ObjectsAreAvailable.await(timeout, timeUnit);
				}

			} finally {
				lock.unlock();
			}
		}
		return object;
	}

	@Override
	public void release(T conn) {

		Util.echo("releasing the object back to the pool");
		conn.setAvailable(true);
		// conn.numberOfAcquired.decrementAndGet();
		lock.lock();
		try {
			ObjectsAreAvailable.signalAll();
		} finally {
			lock.unlock();
		}

	}

	public int getCapacity() {

		return capacity;
	}

}
