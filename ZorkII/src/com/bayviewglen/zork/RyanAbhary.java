package com.bayviewglen.zork;

import java.util.ArrayList;

public class RyanAbhary extends NonPlayableCharacter{
	private static String characterName = "Ryan Abhary";
	private int characterHealth = 50;
	private int characterHealthMax = 50;
	Weapon ryanAbharyWeapon = new GreasyShank();
	private int characterPower = ryanAbharyWeapon.getPower();
	ArrayList<Item> ryanMakInventory = new ArrayList<Item>();
	//private int characterPower;
	//private int characterArmor;
	
	
	
	public void talk(Inventory inv){
		boolean stillTalking = true;
		System.out.println("RyanAbhary: Hey what's up. The ceiling LOL. (Select the number of the option you wish to say.)");
		while(stillTalking){
		System.out.println();
		System.out.println("1: What are you doing inside a temple?");
		System.out.println("2: Tell me a joke.");
		System.out.println("3: What do you do in your spare time?");
		System.out.println("4: Goodbye.");
		String response  = scanner.nextLine();
		if(response.equals("1")==false&&response.equals("2")==false&&response.equals("3")==false&&response.equals("4")==false&&response.equals("5")){
			System.out.println("That is not a valid option.");
		}else if(response.equals("1")){
			System.out.println("RyanAbhary: Looking for friends.");
		}else if(response.equals("2")){
			int badJokeIndex = (int) Math.random()*5;
				System.out.println("RyanAbhary: I can't get my Cross Country Assignment to work. What does System.out.println() do?");
		}else if(response.equals("3")){
			System.out.println("RyanAbhary: Look at clothes that I can't buy online.");
		}else{
			System.out.println("Ryan: CY@.");
			stillTalking = false;
		}
		}
	}
	
	public RyanAbhary(){
		super(characterName);
		//creates the inventory for the character
		//ryanMakInventory.add(new Food("Spicy Noodles", 0.5, 5, 5 ));
		//ryanMakInventory.add(new Pockets("Pockets", 0.0,30, 35));
		//ryanMakInventory.add(new DirtyShank());
		//String description, double mass, int cost ,int power, int critChance
		
	}
	
	
/*	
	public void displayInventory(){
	//	shayanSnInventoryNames =new ArrayList<String>();
		if(ryanMakInventory.size() > 0){
		System.out.println("Ryan Mak: I don't have much, but take a look.");
		System.out.println("(Type the name of the item you wish to buy. Or type exit.)");
		System.out.println();
		for (int i = 0; i<ryanMakInventory.size(); i++){
			System.out.print(ryanMakInventory.get(i).getDescription()+ ": " + ryanMakInventory.get(i).getCost() + ", ");
		}
	}else {
		System.out.println("I have nothing more to sell.");
	}
	
	}*/


}
