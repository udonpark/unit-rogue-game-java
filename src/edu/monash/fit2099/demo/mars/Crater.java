package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Crater extends Ground {
	private int age;

	public Crater() {
		super('o');
		age = 0;
	}

	@Override
	public void tick(Location location){
		age++;
		if(age == 10){
			displayChar = '#';
		}else if (age == 20){
			displayChar = '&';
		}
	}
	
	@Override
	public boolean canActorEnter(Actor a) {
		return a.hasCapability(DemoCapabilities.SPACETRAVELLER);
	}
}