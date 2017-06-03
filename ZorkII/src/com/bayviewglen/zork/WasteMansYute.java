package com.bayviewglen.zork;

public class WasteMansYute extends Enemy{
	static String description = "Waste Mans";
	//creates a weapon to be used in combat
	static Weapon wasteMansYuteShank = new GreasyShank();
	//sets character attributes
	//private int characterHealth = 75;
	//private int characterHealthMax = 75;

	public void setEnemyInventory(Inventory wasteMansYuteInventory) {
		this.enemyInventory = wasteMansYuteInventory;
		wasteMansYuteInventory.addItem(new GreasyShank());
	}

	public WasteMansYute(String name){
		super(description, name, 75, wasteMansYuteShank);
	}
	public void deathPhrase(){
		System.out.println(this.getCharacterName() +": *While dying* All mans reach... the afterlife.");
	}
}
