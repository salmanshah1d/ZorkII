package com.bayviewglen.zork;

import java.util.ArrayList;

public class WasteMansYute extends Enemy{
	
	//creates a weapon to be used in combat
	Weapon wasteMansYuteShank = new GreasyShank();
	//sets character attributes
	private int characterHealth = 75;
	private int characterHealthMax = 75;
	private int characterPower = wasteMansYuteShank.getPower();

	public void setEnemyInventory(ArrayList<Item> wasteMansYuteInventory) {
		this.enemyInventory = wasteMansYuteInventory;
		wasteMansYuteInventory.add(new GreasyShank());
	}

	public WasteMansYute(){
		super();
	}
	public WasteMansYute(String name){
		super(name);
	}
}
