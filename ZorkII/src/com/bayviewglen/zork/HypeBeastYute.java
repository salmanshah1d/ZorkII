package com.bayviewglen.zork;

public class HypeBeastYute extends Enemy {
	static String description = "Hype Beast";
	// creates a weapon to be used in combat
	static Weapon hypeBeastYuteShank = new GreasyShank();
	// sets character attributes
	// private int characterHealth = 100;
	// private int characterHealthMax = 100;

	public void setEnemyInventory(Inventory hypeBeastYuteInventory) {
		this.enemyInventory = hypeBeastYuteInventory;
		hypeBeastYuteInventory.addItem(new SupremeShank());
	}

	public HypeBeastYute(String name) {
		super(description, name, 100, hypeBeastYuteShank);
	}
	
	public void deathPhrase(){
		System.out.println(this.getCharacterName() + ": *While dying* My only regret. Is that I will miss this weeks supreme drop.");
	}
}