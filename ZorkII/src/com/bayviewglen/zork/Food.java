package com.bayviewglen.zork;

public class Food extends Item{
	
	private int healthRestored;
	
	public Food(String description, Double mass, int cost, int healthReturned){
		super(description,mass,cost);
		this.healthRestored = healthReturned;
	}

	public int getHealthRestored() {
		return healthRestored;
	}

	public void setHealthRestored(int healthRestored) {
		this.healthRestored = healthRestored;
	}

}
