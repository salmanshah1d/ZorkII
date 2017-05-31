package com.bayviewglen.zork;

public class Pockets extends Item{

	private int spaceAdded;

	
	public Pockets(String description, Double mass, int cost,int pocketSpace){
		super(description,mass,cost);
	
	}


	public int getSpaceAdded() {
		return spaceAdded;
	}


	public void setSpaceAdded(int spaceAdded) {
		this.spaceAdded = spaceAdded;
	}
	
}
