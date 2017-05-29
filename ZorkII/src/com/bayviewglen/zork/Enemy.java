package com.bayviewglen.zork;

import java.util.ArrayList;

public class Enemy extends Character{
	ArrayList<Item> enemyInventory = new ArrayList<Item>();
	
	public ArrayList<Item> getEnemyInventory() {
		return enemyInventory;
	}

	public void setEnemyInventory(ArrayList<Item> enemyInventory) {
		this.enemyInventory = enemyInventory;
	}

	public Enemy(){
		super();
}
	public Enemy(String name){
		super(name);
}
}
