package com.bayviewglen.zork;

import java.util.ArrayList;

public class Andrei extends NonPlayableCharacter{
	private String characterName = "Andrei";
	private int characterHealth = 400;
	private int characterHealthMax = 400;
	Weapon andreiWeapon = new Weapon("Audacity Sound File ", 1.0, 0, 20, 5);
	private int characterPower = andreiWeapon.getPower();
	ArrayList<Item> andreiInventory = new ArrayList<Item>();
	//private int characterPower;
	//private int characterArmor;
	
	
	
	public void talk(){
		boolean stillTalking = true;
		System.out.println("Andrei: Oh hi there, didn't see you. Do you want to buy some ugly green GRAD sweaters. (Select the number of the option you wish to say.)");
		while(stillTalking){
		System.out.println("1: What are you doing inside a temple?");
		System.out.println("2: Show me your wares.");
		System.out.println("3: Goodbye.");
		String response  = scanner.nextLine();
		if(response.equals("1")==false||response.equals("2")==false||response.equals("3")==false){
			System.out.println("That is not a valid option.");
		}else if(response.equals("1")){
			System.out.println("Andrei: I am trying to find ideas for my ComSci project inside this superior game.");
		}else if (response.equals("2")){
			this.displayInventory();
		}else{
			System.out.println("Andrei: CIAO.");
			stillTalking = false;
		}
		}
	}
	
	public Andrei(){
		super();
		//creates the inventory for the character
		andreiInventory.add(new ArmourAttachment("Ugly green sweater(AA)", 1.0, 30, 5, "Ugly Green"));
		andreiInventory.add(new WeaponAttachment("Reposted meme(WA)", 0.1, 20, 5, 1, "Reposted"));
		andreiInventory.add(new Pockets("Small Pockets", 0.0, 10, 20));
		andreiInventory.add(new Weapon("Audacity Sound File ", 1.0, 0, 20, 5));
		//String description, double mass, int cost ,int power, int critChance
		
	}
	
	
	
	public void displayInventory(){
	//	shayanSnInventoryNames =new ArrayList<String>();
		if(andreiInventory.size() > 0){
		System.out.println("Andrei: Check em out ");
		System.out.println();
		for (int i = 0; i<andreiInventory.size(); i++){
			System.out.print(andreiInventory.get(i).getDescription()+ ": " + andreiInventory.get(i).getCost() + " ");
		}
	}else {
		System.out.println("I have nothing more to sell.");
	}
	
	}
}
