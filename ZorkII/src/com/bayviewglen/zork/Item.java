package com.bayviewglen.zork;

public class Item {
	private String description;
	private int mass;

	//create getters and setters
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public void setMass(int mass) {
		this.mass = mass;
	}

	public int getMass() {
		return mass;
	}

	public Item(String description, int mass) {
		super();
		this.description = description;
		this.mass = mass;
	}
	public Item(String description) {
		super();
		this.description = description;
		this.mass = 0;
	}

}
