package com.bayviewglen.zork;

<<<<<<< HEAD
public class NonPlayableCharacter {
	private String name;
	
	public NonPlayableCharacter(String nameInp){
		this.name = nameInp;
	}
	
	public String getName(){
		return this.name;
	}
=======
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
>>>>>>> refs/remotes/origin/master
}
