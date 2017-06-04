package com.bayviewglen.zork;

public class Armour extends Item{
	
	private int protection;
	
	//Creates the characters' armour
	public Armour(){
		super("Armour", 0.0);
		this.protection = 5;
	}

	public int getProtection() {
		return protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}

}