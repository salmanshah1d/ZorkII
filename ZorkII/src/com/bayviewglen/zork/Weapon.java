package com.bayviewglen.zork;

public class Weapon extends Item{
	
	private int Power;
	private int critChance;

	private static final int CRITICALSTRIKEMULTIPLIER = 3;
	
	public Weapon(String description, double mass, int cost ,int power, int critChance){
		super(description, mass, cost);
		this.Power = power;
		this.critChance = critChance;
	}
	
	public Weapon(String description, double mass, int power){
		super(description,mass);
		this.Power = power;
	}
	
	public Weapon(String description, double mass, int power, int critChance){
		super(description,mass);
		this.Power = power;
		this.critChance = critChance;
	}

	
	public int getPower() {
		return Power;
	}

	public void setPower(int power) {
		Power = power;
	}
	public int getCritChance() {
		return critChance;
	}

	public void setCritChance(int critChance) {
		this.critChance = critChance;
	}

	

}
