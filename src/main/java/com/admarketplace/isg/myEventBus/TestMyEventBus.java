package com.admarketplace.isg.myEventBus;

import com.admarketplace.isg.util.Util;

public class TestMyEventBus {

	public static void main(String args[]) throws Exception {
		Util.printProcessId();
		
		TestMyEventBus test = new TestMyEventBus();
		Event parkYourCarEvent = new ParkYouCarEvent("park your cars");
		Event huuricaneIsComing = new HurricaneEvent("hurricane is coming");
		test.tellPeople(parkYourCarEvent);
		test.tellPeople(huuricaneIsComing);
	}

	@MyEventSubscriber
	public void parkTheCar(ParkYouCarEvent obj) {
		Util.echo("parkTheCar :"+obj.msg);
	}
	
	@MyEventSubscriber
	public void huuricaneAlert(HurricaneEvent obj) {
		Util.echo("huuricaneAlert :"+obj.msg);
	}
	
	@MyEventSubscriber
	public void AllEvents(Event obj) {
		Util.echo("AllEvents :"+obj.msg);
	}
	

	@MyEventPublisher
	public void tellPeople(Event event) throws Exception {

		MyEventBus myEventBus = new MyEventBus();
		myEventBus.register(this);
		myEventBus.post(event);
	}

	
	
}
