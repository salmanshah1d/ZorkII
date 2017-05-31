package com.bayviewglen.zork;

import java.util.ArrayList;

public class Yute extends Enemy{
	static String description = "Yute";
	//creates a weapon to be used in combat
	static Weapon yuteShank = new DirtyShank();
	//sets character attributes
	private int characterPower = yuteShank.getPower();
	//private int characterHealth = 100;
	//private int characterHealthMax = 100;
	//ArrayList<Item> yuteInventory = new ArrayList<Item>();
	

	
	/*public ArrayList<Item> getYuteInventory() {
		return yuteInventory;
	}*/

	public void setEnemyInventory(Inventory yuteInventory) {
		this.enemyInventory = yuteInventory;
		yuteInventory.addItem(new DirtyShank());
	}
	
	public Yute(String name){
		super(description, name, 50, yuteShank);
	}
}
