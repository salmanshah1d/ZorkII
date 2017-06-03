package com.bayviewglen.zork;

public class Food extends Item{
	
	private int healthRestored;
//	private String foodName;
	
	public Food(String description, Double mass, int cost,int healthReturned){
		super(description,mass,cost);
		this.healthRestored = healthReturned;
		//this.foodName = word;
	}


/*	public String getFoodName() {
		return foodName;
	}


	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

*/
	public int getHealthRestored() {
		return healthRestored;
	}

	public void setHealthRestored(int healthRestored) {
		this.healthRestored = healthRestored;
	}

}
