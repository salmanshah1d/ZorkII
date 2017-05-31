package com.bayviewglen.zork;

import java.util.ArrayList;

public class HypeBeastYute extends Enemy {
	static String description = "Hype Beast";
	// creates a weapon to be used in combat
	static Weapon hypeBeastYuteShank = new GreasyShank();
	// sets character attributes
	// private int characterHealth = 100;
	// private int characterHealthMax = 100;
	private int characterPower = hypeBeastYuteShank.getPower();

	public void setEnemyInventory(Inventory hypeBeastYuteInventory) {
		this.enemyInventory = hypeBeastYuteInventory;
		hypeBeastYuteInventory.addItem(new GreasyShank());
	}

	public HypeBeastYute(String name) {
		super(description, name, 100, hypeBeastYuteShank);
	}
}
