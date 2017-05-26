package com.bayviewglen.zork;

import java.util.ArrayList;

public class WasteMansYute extends Enemy{
	
	Weapon wasteMansYuteShank = new DirtyShank();
	private int characterHealth = 100;
	private int characterPower = wasteMansYuteShank.getPower();

	public void setEnemyInventory(ArrayList<Item> wasteMansYuteInventory) {
		this.enemyInventory = wasteMansYuteInventory;
		wasteMansYuteInventory.add(new SupremeShank());
	}

	public WasteMansYute(){
		super();
	}
	public WasteMansYute(String name){
		super(name);
	}
}
