package com.bayviewglen.zork;

public class Item {
	private String description;
	private int mass;
	private int cost;
	
	Item(String objectInp, int weightInp) {
		description = objectInp;
		mass = weightInp;
	}
	
	Item(String objectInp, int weightInp, int costInp) {
		description = objectInp;
		mass = weightInp;
		cost = costInp;
	}

	public String getDescription(){
		return description;
	}
	
	public int getMass(){
		return mass;
	}
	
	public int getCost(){
		return cost;
	}
	
	public void setDescription(String descrInp){
		this.description = descrInp;	}
	
	public void setMass(int massInp){
		this.cost = massInp;	}
	
	public void setCost(int costInp){
		this.cost = costInp;
	}
	
	public String display() {
		return(description + " (" + mass + ")");
	}	
	
	public String toString(){
		return("(" + description + ", " + mass + ")");
	}
}

