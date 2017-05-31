package com.bayviewglen.zork;

import java.util.ArrayList;

public class RyanMak extends NonPlayableCharacter{
	private String characterName = "Ryan";
	private int characterHealth = 150;
	private int characterHealthMax = 150;
	Weapon ryanMakWeapon = new GreasyShank();
	private int characterPower = ryanMakWeapon.getPower();
	ArrayList<Item> ryanMakInventory = new ArrayList<Item>();
	//private int characterPower;
	//private int characterArmor;
	
	
	
	public void talk(){
		boolean stillTalking = true;
		System.out.println("Ryan: Oh hi. Can you help me with my ComSci? (Select the number of the option you wish to say.)");
		while(stillTalking){
		System.out.println("1: What are you doing inside a temple?");
		System.out.println("2: What do you need help for in ComSci?");
		System.out.println("3: I think you should just drop the course.");
		System.out.println("4: You have a nice butt.");
		System.out.println("5: Show me your wares.");
		System.out.println("6: Goodbye.");
		String response  = scanner.nextLine();
		if(response.equals("1")==false||response.equals("2")==false||response.equals("3")==false||response.equals("4")==false||response.equals("5")==false||response.equals("6")==false){
			System.out.println("That is not a valid option.");
		}else if(response.equals("1")){
			System.out.println("Ryan: I'm here to find the lost treasure. A five in AP ComSci. Alhtough I can't figure out how to get out of this room.");
		}else if(response.equals("2")){
				System.out.println("Ryan: I can't get my Cross Country Assignment to work. What does System.out.println() do?");
		}else if(response.equals("3")){
			System.out.println("Ryan: Ya I think your right.");
		}else if(response.equals("4")){
			System.out.println("Ryan: WHY DOES EVERYONE KEEP SAYING THAT!!!!???");
		}else if (response.equals("5")){
			this.displayInventory();
		}else{
			System.out.println("Daniel: Cya.");
			stillTalking = false;
		}
		}
	}
	
	public RyanMak(){
		super();
		//creates the inventory for the character
		ryanMakInventory.add(new Food("Spicy Noodles", 0.5, 5, 5 ));
		ryanMakInventory.add(new Pockets("Pockets", 0.0,30, 35));
		ryanMakInventory.add(new DirtyShank());
		//String description, double mass, int cost ,int power, int critChance
		
	}
	
	
	
	public void displayInventory(){
	//	shayanSnInventoryNames =new ArrayList<String>();
		if(ryanMakInventory.size() > 0){
		System.out.println("Ryan Mak: I don't have much, but take a look.");
		System.out.println();
		for (int i = 0; i<ryanMakInventory.size(); i++){
			System.out.print(ryanMakInventory.get(i).getDescription()+ ": " + ryanMakInventory.get(i).getCost() + " ");
		}
	}else {
		System.out.println("I have nothing more to sell.");
	}
	
	}
}
