package com.bayviewglen.zork;

import java.util.ArrayList;

public class HypeBeastYute extends Enemy{
	
	//creates a weapon to be used in combat
		Weapon wasteMansYuteShank = new GreasyShank();
		//sets character attributes
		private int characterHealth = 100;
		private int characterPower = wasteMansYuteShank.getPower();

		public void setEnemyInventory(ArrayList<Item> hypeBeastYuteInventory) {
			this.enemyInventory = hypeBeastYuteInventory;
			hypeBeastYuteInventory.add(new GreasyShank());
		}

		public HypeBeastYute(){
			super();
		}
		public HypeBeastYute(String name){
			super(name);
		}
}
