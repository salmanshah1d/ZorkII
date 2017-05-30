package com.bayviewglen.zork;

public class Item {
	private String description;

	private double mass;
	private double cost;
	
	Item(String objectInp, double weightInp) {
		description = objectInp.toLowerCase();
		mass = weightInp;
	}
	
	Item(String objectInp, double weightInp, int costInp) {
		description = objectInp.toLowerCase();
		mass = weightInp;
		cost = costInp;
	}

	public String getDescription(){
		return description;
	}
	
	public double getMass(){
		return mass;
	}
	
	public double getCost(){
		return cost;
	}
	
	public void setDescription(String descrInp){
		this.description = descrInp;	}
	
	public void setMass(double massInp){
		this.mass = massInp;	}
	
	public void setCost(double costInp){
		this.cost = costInp;
	}
	
	public String display() {
		return(description + " (" + mass + ")");
	}	
}

