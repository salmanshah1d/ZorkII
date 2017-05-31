package com.bayviewglen.zork;

import java.util.ArrayList;
import java.util.Scanner;

public class Rodin extends NonPlayableCharacter{
	private String characterName = "Rodin";
	private int characterHealth = 80;
	private int characterHealthMax = 80;
	Weapon rodinShank = new DirtyShank();
	private int characterPower = rodinShank.getPower();
	ArrayList<Item> rodinInventory = new ArrayList<Item>();
	//private int characterPower;
	//private int characterArmor;
	
	
	
	public void talk(){
		boolean stillTalking = true;
		System.out.println("Rodin: AAAAAHHHHHHHHHHHHHHHHHH! (Select the number of the option you wish to say.)");
		while(stillTalking){
		System.out.println("1: What are you doing inside a temple?");
		System.out.println("2: What is wrong with you?");
		System.out.println("3: How long have you been down here?");
		System.out.println("4: Show me your wares.");
		System.out.println("5: I'm going to kill you now. Goodbye.");
		String response  = scanner.nextLine();
		if(response.equals("1")==false||response.equals("2")==false||response.equals("3")==false||response.equals("4")==false||response.equals("5")==false){
			System.out.println("That is not a valid option.");
		}else if(response.equals("1")){
			System.out.println("Rodin: WWWWWWWWAAAAAHHHHHHH!");
		}else if(response.equals("2")){
			System.out.println("Rodin: GGGGGGGGGAAAAAHHHHHHH!");
		}else if(response.equals("3")){
			System.out.println("Rodin: SO LONG MAN!");
		}else if (response.equals("4")){
			this.displayInventory();
		}else{
			System.out.println("Rodin: NO PLZ STAHP!");
			stillTalking = false;
		}
		}
	}
	
	public Rodin(){
		super();
		//creates the inventory for the character
		rodinInventory.add(new Food("Half-eaten sandwich", 0.5, 0, 60));
		rodinInventory.add(new Pockets("Large Pockets", 0.0, 50, 50));
		//String description, double mass, int cost ,int power, int critChance
		rodinInventory.add(new DirtyShank());
		
	}
	
	public void displayInventory(){
	//	shayanSnInventoryNames =new ArrayList<String>();
		if(rodinInventory.size() > 0){
		System.out.print("YOU WANT?!");
		System.out.println();
		for (int i = 0; i<rodinInventory.size(); i++){
			System.out.print(rodinInventory.get(i).getDescription()+ ": " + rodinInventory.get(i).getCost() + " ");
		}
	}else {
		System.out.println("I ain't got nutin.");
	}
	
	}

}
