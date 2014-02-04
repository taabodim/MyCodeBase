package com.admarketplace.isg.profiling.example;

import com.admarketplace.isg.myEventBus.*;
import com.admarketplace.isg.util.Util;

public class ProfileMyEventBus {

	public static void main(String args[]) throws Exception {
		Util.printProcessId();
		Thread.sleep(10000);

		ProfileMyEventBus test = new ProfileMyEventBus();
		Event parkYourCarEvent = new ParkYouCarEvent("park your cars");
		Event huuricaneIsComing = new HurricaneEvent("hurricane is coming");
		myEventBus.register(test);
		while (true) {
			test.tellPeople(parkYourCarEvent);
			test.tellPeople(huuricaneIsComing);
		}
	}

	private static MyEventBus myEventBus = new MyEventBus();

	@MyEventSubscriber
	public void parkTheCar(ParkYouCarEvent obj) {
		String parkTheCarMsg ="parkTheCar :" + obj.msg;
		Util.echo(parkTheCarMsg);
		parkTheCarMsg=null;
	}

	@MyEventSubscriber
	public void huuricaneAlert(HurricaneEvent obj) {
		String msg ="huuricaneAlert :" + obj.msg;
		Util.echo(msg);
		msg=null;
	}

	@MyEventSubscriber
	public void AllEvents(Event obj) {
		String msg ="AllEvents :" + obj.msg;
		Util.echo(msg);
		msg=null;
	}

	@MyEventPublisher
	public void tellPeople(Event event) throws Exception {

		myEventBus.post(event);
	}

}
