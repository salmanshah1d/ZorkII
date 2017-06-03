package com.bayviewglen.zork;

public class Enemy extends Character {
	public Enemy(String description, String name, int i, Weapon weapon){
		super(description, name, i, weapon);
	}
	
	Inventory enemyInventory = new Inventory();

	public Inventory getEnemyInventory() {
		return enemyInventory;
	}

	public void setEnemyInventory(Inventory enemyInventory) {
		this.enemyInventory = enemyInventory;
	}

	public Enemy(String name) {
		super(name);
	}
	public void deathPhrase(){
		//System.out.println("*While dying* I see the light. It's green.");
	}
}
