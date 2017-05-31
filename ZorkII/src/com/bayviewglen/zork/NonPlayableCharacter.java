package com.bayviewglen.zork;

import java.util.ArrayList;
import java.util.Scanner;

public class NonPlayableCharacter extends Character{
	
	Scanner scanner = new Scanner(System.in);

	ArrayList<Item> npcInventory = new ArrayList<Item>();
	
	public ArrayList<Item> getNpcInventory() {
		return npcInventory;
	}
	public void setNpcInventory(ArrayList<Item> npcInventory) {
		this.npcInventory = npcInventory;
	}
	public NonPlayableCharacter(){
		super();
}
	public NonPlayableCharacter(String name){
		super(name);
}
}
