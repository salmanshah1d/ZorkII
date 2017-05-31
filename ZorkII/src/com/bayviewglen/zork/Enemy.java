package com.bayviewglen.zork;

public class Enemy extends Character {
	public Enemy(String name, int i, Weapon weapon){
		super(name, i, weapon);
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
