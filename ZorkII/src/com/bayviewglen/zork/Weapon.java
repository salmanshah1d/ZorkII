package com.bayviewglen.zork;

public class Weapon extends Item{
	
	private int Power;
	
	public Weapon(String description, double mass, int cost ,int power){
		super(description, mass, cost);
		this.Power = power;
	}

	public int getPower() {
		return Power;
	}

	public void setPower(int power) {
		Power = power;
	}

}
