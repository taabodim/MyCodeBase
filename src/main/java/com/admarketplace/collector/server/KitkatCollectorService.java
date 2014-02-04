package com.admarketplace.collector.server;

/**
 * Created with IntelliJ IDEA.
 * User: AP0025
 * Date: 11/22/13
 * Time: 8:56 AM
 * To change this template use File | Settings | File Templates.
 */

import com.admarketplace.isg.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KitkatCollectorService implements CollectorService {
    public final static Logger logger = LoggerFactory.getLogger(KitkatCollectorService.class);
    private static AmqpTemplate template;
    private static ApplicationContext context;
    public String queueName;


    public static void main(String args[]) {
        if (context == null)
            context = new ClassPathXmlApplicationContext("ctracker-c2collector-server-kitkat.xml");
        KitkatCollectorService service = (KitkatCollectorService) context.getBean("kitkatCollectorService");
        service.initialize();
        service.start();

    }

    @Override
    public void initialize() {
        logger.info(this.getClass().getCanonicalName() + " initializing.... ");



    }

    @Override
    public void start() {

//        synchronousSendAndReceive("this is a test");
        asynchronousSendAndReceive("this is a kitkat test");


    }

    private void asynchronousSendAndReceive(String msg) {
        for (int i = 0; i < 100; i++) {
            send(msg);
        }
    }

    private void send(String msg) {
        template.convertAndSend(queueName, msg);
        logger.info("sent to this queue {"+queueName+"} " + "msg {"+msg+"}....");
    }

    private void synchronousSendAndReceive(String msg) {

        template.convertAndSend(queueName, msg);
        logger.info("sent to this queue {"+queueName+"} " + "msg {"+msg+"}....");

        String foo = (String) template.receiveAndConvert(queueName);
        logger.info("received this :" + foo);
         Util.echo("received this :" + foo);
    }


    @Override
    public void stop() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {

        return queueName;
    }

    public  AmqpTemplate getTemplate() {
        return template;
    }

    public void setTemplate(AmqpTemplate template) {
        KitkatCollectorService.template = template;
    }
}