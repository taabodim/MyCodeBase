package com.admarketplace.guava.examples;

import com.admarketplace.isg.util.Util;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class TestGuavaEventBus {
	EventBus eventBus = new EventBus();

	public static void main(String args[]) {
		Util.printProcessId();
		// Objects.toStringHelper(self)
		TestGuavaEventBus test = new TestGuavaEventBus();
		test.registerSubscriber();
		for (int i = 0; i < 10; i++)
			test.buySomething();

	}

	private void registerSubscriber() {
		PurchaseSubscriber purchaseSubscriber = new PurchaseSubscriber();
		eventBus.register(purchaseSubscriber);

		HousePurchaseSubscriber HousePurchaseSubscriberobj = new HousePurchaseSubscriber();
		eventBus.register(HousePurchaseSubscriberobj);
		
		CarPurchaseSubscriber CarPurchaseSubscriberobj = new CarPurchaseSubscriber();
		eventBus.register(CarPurchaseSubscriberobj);
	}

	public void buySomething() {
		eventBus.post(new PurchaseEvent("course", "200000$"));
		eventBus.post(new CarPurchaseEvent("mazda car", "200000$"));
		eventBus.post(new HousePurchaseEvent("3 storey House", "200000$"));
	}

}

class PurchaseSubscriber {

	@Subscribe
	public void handlePurchaseEvent(PurchaseEvent event) {
		Util.echo("PurchaseSubscriber : event coming through event.item " + event.item + "event.price" + event.price);

	}
}

class CarPurchaseSubscriber {
	
	@Subscribe
	public void handleCarPurchaseEvent(CarPurchaseEvent event) {
		Util.echo("CarPurchaseSubscriber : event coming through event.item " + event.item + "event.price" + event.price);

	}
}

// Would only be notified of credit purchases
class HousePurchaseSubscriber {

	@Subscribe
	public void handleHousePurchaseEvent(HousePurchaseEvent event) {
		Util.echo("HousePurchaseSubscriber : event coming through event.item " + event.item + "event.price" + event.price);

	}
}

class PurchaseEvent {
	public String item;
	public String price;

	public PurchaseEvent(String item, String price) {
		this.item = item;
		this.price = price;
	}

}

class CarPurchaseEvent {
	public String item;
	public String price;

	public CarPurchaseEvent(String item, String price) {
		this.item = item;
		this.price = price;
	}

}

class HousePurchaseEvent {
	public String item;
	public String price;

	public HousePurchaseEvent(String item, String price) {
		this.item = item;
		this.price = price;
	}

}
