package com.bayviewglen.zork;

import java.util.ArrayList;
import java.util.Scanner;

public class NonPlayableCharacter extends Character{
	
	Scanner scanner = new Scanner(System.in);
	//private int characterPower;

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
/*	public int getCharacterPower() {
		return characterPower;
	}
	public void setCharacterPower(int characterPower) {
		this.characterPower = characterPower;
	}*/
	public void talk(Inventory inv){
		
	}
	public void deathPhrase(){
	//	System.out.println("*While dying* I see the light. It's green.");
	}
}
