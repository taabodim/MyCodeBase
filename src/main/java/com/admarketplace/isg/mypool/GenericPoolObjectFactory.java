package com.admarketplace.isg.mypool;

public interface GenericPoolObjectFactory<T> {
	public T createObject() throws Exception;

	public void destroyObject(T obj);
}
