package com.bayviewglen.zork;

public class Item {
	private String description;
	private double mass;
	//I've changed the cost to an int, tell me if you want to change it back.
	//private double cost;
	private int cost;
	private int power;
	
	Item(String objectInp) {
		description = objectInp.toLowerCase();
	}
	
	Item(String objectInp, double weightInp) {
		description = objectInp.toLowerCase();
		mass = weightInp;
		if (objectInp.equals("Sword")){
			power = 50;
		}
	}
	
	Item(String objectInp, double weightInp, int costInp) {
		description = objectInp;
		mass = weightInp;
		cost = costInp;
	}

	public String getDescription(){
		return description;
	}
	
	public double getMass(){
		return mass;
	}
	
	public int getCost(){
		return cost;
	}
	
	public void setDescription(String descrInp){
		this.description = descrInp;	}
	
	public void setMass(double massInp){
		this.mass = massInp;	}
	
//	public void setCost(double costInp){
	public void setCOst(int costInp){
	this.cost = costInp;
	}
	
	public String display() {
		return(description + " (" + mass + ")");
		
	}
	public int getPower() {
		return power;
	}	
}