package com.bayviewglen.zork;

import java.util.ArrayList;

public class Yute extends Enemy{

	//creates a weapon to be used in combat
	Weapon yuteShank = new DirtyShank();
	//sets character attributes
	private int characterHealth = 50;
	private int characterHealthMax = 50;
	private int characterPower = yuteShank.getPower();
	//ArrayList<Item> yuteInventory = new ArrayList<Item>();
	

	
	/*public ArrayList<Item> getYuteInventory() {
		return yuteInventory;
	}*/

	public void setEnemyInventory(ArrayList<Item> yuteInventory) {
		this.enemyInventory = yuteInventory;
		yuteInventory.add(new DirtyShank());
	}

	public Yute(){
		super();
	}
	public Yute(String name){
		super(name);
	}
	
}
