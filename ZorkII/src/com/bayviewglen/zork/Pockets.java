package com.bayviewglen.zork;

public class Pockets extends Item{

	private double spaceAdded;

	
	public Pockets(String description, Double mass, int cost,double pocketSpace){
		super(description,mass,cost);
	
	}


	public double getSpaceAdded() {
		return spaceAdded;
	}


	public void setSpaceAdded(int spaceAdded) {
		this.spaceAdded = spaceAdded;
	}
	
}
