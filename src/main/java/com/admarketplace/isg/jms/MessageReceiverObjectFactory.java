package com.admarketplace.isg.jms;

import org.apache.commons.pool.PoolableObjectFactory;


public class MessageReceiverObjectFactory implements
		PoolableObjectFactory<MessageReceiver> {

	@Override
	public MessageReceiver makeObject() throws Exception {
		// TODO Auto-generated method stub
		return new MessageReceiver();
	}

	@Override
	public void destroyObject(MessageReceiver obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateObject(MessageReceiver obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void activateObject(MessageReceiver obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passivateObject(MessageReceiver obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
