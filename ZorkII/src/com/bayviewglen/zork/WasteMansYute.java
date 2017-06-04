package com.bayviewglen.zork;

public class WasteMansYute extends Enemy{
	private static final int wasteMansYutePower = 75;
	static String description = "Waste Mans";
	static Weapon wasteMansYuteShank = new GreasyShank();

	public void setEnemyInventory(Inventory wasteMansYuteInventory) {
		this.enemyInventory = wasteMansYuteInventory;
		wasteMansYuteInventory.addItem(new GreasyShank());
	}

	public WasteMansYute(String name){
		super(description, name, wasteMansYutePower, wasteMansYuteShank);
	}
	public void deathPhrase(){
		System.out.println(this.getCharacterName() +": *While dying* All mans reach... the afterlife.");
	}
}