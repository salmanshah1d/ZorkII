package com.bayviewglen.zork;

import java.util.ArrayList;

public class WasteMansYute extends Enemy{
	
	//creates a weapon to be used in combat
	static Weapon wasteMansYuteShank = new GreasyShank();
	//sets character attributes
	//private int characterHealth = 75;
	//private int characterHealthMax = 75;
	private int characterPower = wasteMansYuteShank.getPower();

	public void setEnemyInventory(Inventory wasteMansYuteInventory) {
		this.enemyInventory = wasteMansYuteInventory;
		wasteMansYuteInventory.addItem(new GreasyShank());
	}

	public WasteMansYute(String name){
		super(name, 75, wasteMansYuteShank);
	}
}
