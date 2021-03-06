package com.bayviewglen.zork;

public class HypeBeastYute extends Enemy {
	static String description = "Hype Beast";
	// creates a weapon to be used in combat
	static Weapon hypeBeastYuteShank = new SupremeShank();
	private static int CHARACTERHEALTH = 100;

	public void setEnemyInventory(Inventory hypeBeastYuteInventory) {
		this.enemyInventory = hypeBeastYuteInventory;
		hypeBeastYuteInventory.addItem(new SupremeShank());
	}

	public HypeBeastYute(String name) {
		super(description, name, CHARACTERHEALTH, hypeBeastYuteShank);

	}

	public void deathPhrase() {
		System.out.println(
				this.getCharacterName() + ": *While dying* My only regret: I will miss this week's Supreme drop.");
	}
}