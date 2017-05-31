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
}
