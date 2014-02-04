package com.admarketplace.isg.mypool;

public class PoolObjectFactory<T extends Resource> implements GenericPoolObjectFactory<T> {
	
	private Class<T> classOfT;
	
	public PoolObjectFactory(Class<T> class1) {
		classOfT = class1;
	}

	@Override
	public T createObject() throws Exception{

		return  classOfT.newInstance();

	
	}

	@Override
	public void destroyObject(T obj) {
		obj = null;
	}

}
