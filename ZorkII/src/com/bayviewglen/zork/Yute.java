package com.bayviewglen.zork;

public class Yute extends Enemy{
	static String description = "Yute";
	//creates a weapon to be used in combat
	static Weapon yuteShank = new DirtyShank();
	//sets character attributes


	public void setEnemyInventory(Inventory yuteInventory) {
		this.enemyInventory = yuteInventory;
		yuteInventory.addItem(new DirtyShank());
	}
	
	public Yute(String name){
		super(description, name, 50, yuteShank);
		
	}	public void deathPhrase(){
		System.out.println(this.getCharacterName() +": *While dying* AHHHHHHH!");
	}
}
