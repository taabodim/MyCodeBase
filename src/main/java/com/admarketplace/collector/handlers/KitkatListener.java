package com.admarketplace.collector.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: AP0025
 * Date: 11/22/13
 * Time: 10:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class KitkatListener {
    public final static Logger logger = LoggerFactory.getLogger(KitkatListener.class);

    public void handleMessage(String message) {
        logger.info(KitkatListener.class + " message recieved " + message);
    }
}
