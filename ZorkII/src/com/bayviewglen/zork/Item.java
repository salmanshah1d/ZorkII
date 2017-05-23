
package com.bayviewglen.zork;

public class Item {
	private String description;
	private int weight;
	private int cost;
	
	Item(String objectInp, int weightInp) {
		description = objectInp;
		weight = weightInp;
	}
	
	Item(String objectInp, int weightInp, int costInp) {
		description = objectInp;
		weight = weightInp;
		cost = costInp;
	}
	
	public String toString(){
		return("(" + description + ", " + weight + ")");
	}

	public String display() {
		return(weight + "-pound " + description);
	}
}

