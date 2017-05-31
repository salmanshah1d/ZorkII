package com.bayviewglen.zork;

import java.util.ArrayList;

public class Daniel extends NonPlayableCharacter{
	private String characterName = "Daniel";
	private int characterHealth = 150;
	private int characterHealthMax = 150;
	Weapon danielWeapon = new Weapon("Shoe ", 1.0, 10, 30, 3);
	private int characterPower = danielWeapon.getPower();
	ArrayList<Item> danielInventory = new ArrayList<Item>();
	//private int characterPower;
	//private int characterArmor;
	
	
	
	public void talk(){
		boolean stillTalking = true;
		System.out.println("Daniel: What do you want? (Select the number of the option you wish to say.)");
		while(stillTalking){
		System.out.println("1: What are you doing inside a temple?");
		System.out.println("2: What is you opinion on this game?");
		System.out.println("3: What is you opinion on Canadian Prime Minister Justin Trudea?");
		System.out.println("4: What is you opinion on fidget spinners?");
		System.out.println("5: Show me your wares.");
		System.out.println("6: Goodbye.");
		String response  = scanner.nextLine();
		if(response.equals("1")==false||response.equals("2")==false||response.equals("3")==false||response.equals("4")==false||response.equals("5")==false||response.equals("6")==false){
			System.out.println("That is not a valid option.");
		}else if(response.equals("1")){
			System.out.println("Daniel: I was told I should go outside more. I ended up here.");
		}else if(response.equals("2")){
				System.out.println("Daniel: I hate it.");
		}else if(response.equals("3")){
			System.out.println("Daniel: I hate him.");
		}else if(response.equals("4")){
			System.out.println("Daniel: I hate them.");
		}else if (response.equals("5")){
			this.displayInventory();
		}else{
			System.out.println("Daniel: Cya.");
			stillTalking = false;
		}
		}
	}
	
	public Daniel(){
		super();
		//creates the inventory for the character
		danielInventory.add(new ArmourAttachment("Cotton Fluff(AA)", 2.0, 50, 5, "fluffy"));
		danielInventory.add(new WeaponAttachment("Electro Magnet(WA)", 1.0,50, 12, 2, "Electro-Magnetic"));
		danielInventory.add(new Weapon("Shoe ", 1.0, 10, 30, 3));
		//String description, double mass, int cost ,int power, int critChance
		
	}
	
	
	
	public void displayInventory(){
	//	shayanSnInventoryNames =new ArrayList<String>();
		if(danielInventory.size() > 0){
		System.out.println("Daniel: Heres what I have.");
		System.out.println();
		for (int i = 0; i<danielInventory.size(); i++){
			System.out.print(danielInventory.get(i).getDescription()+ ": " + danielInventory.get(i).getCost() + " ");
		}
	}else {
		System.out.println("I have nothing more to sell.");
	}
	
	}
}
