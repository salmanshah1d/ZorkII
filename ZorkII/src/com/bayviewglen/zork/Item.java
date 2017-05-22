package com.bayviewglen.zork;

public class Item {
	private String object;
	private int weight;
	private int cost;
	
	// for most items such as Shayan's tuck shop food items or weapons
	Item(String objectInp, int weightInp) {
		object = objectInp;
		weight = weightInp;
	}
	
	Item(String objectInp, int weightInp, int costInp) {
		object = objectInp;
		weight = weightInp;
		cost = costInp;
	}
	
	public String toString(){
		return("(" + object + ", " + weight + ")");
	}
}
