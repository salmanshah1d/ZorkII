package com.bayviewglen.zork;

import java.util.ArrayList;

public class Deslaurier extends NonPlayableCharacter{
	private String characterName = "Deslaurier";
	private int characterHealth = 500;
	private int characterHealthMax = 500;
	private int characterArmour = 5; 
	Weapon deslaurierWeapon = new Weapon("Sword of Eclipse", 50.0, 1000, 50, 6);
	private int characterPower = deslaurierWeapon.getPower();
	ArrayList<Item> deslauerierInventory = new ArrayList<Item>();
	//private int characterPower;
	//private int characterArmor;
	
	
	
	public void talk(){
		boolean stillTalking = true;
		System.out.println("Deslaurier: Welcome my son. I have what you seek. A 5 in AP-ComSci-A. (Select the number of the option you wish to say.)");
		while(stillTalking){
		System.out.println("1: You held the treasure all along? Why not just give it to me when we met?");
		System.out.println("2: I have collected all five gems. Now give me what I earned.");
		System.out.println("3: Think game is pretty good, I think you should give it a 100%.");
		System.out.println("4: You are a very handsome and Intelligent man.");
		System.out.println("5: So be it. Let us fight.");
		String response  = scanner.nextLine();
		if(response.equals("1")==false||response.equals("2")==false||response.equals("3")==false||response.equals("4")==false||response.equals("5")==false||response.equals("6")==false){
			System.out.println("That is not a valid option.");
		}else if(response.equals("1")){
			System.out.println("Deslaurier: It was funny watching you struggle. HAHAHAHAHA.");
		}else if(response.equals("2")){
				System.out.println("Deslauerier: Not so fast my son. If you want the treasure, you must fight for it.");
		}else if(response.equals("3")){
			System.out.println("Deslaurier: You are correct. I should give this game a 100%.");
		}else if(response.equals("4")){
			System.out.println("Deslaurier: Why thank you. You are very kind.");
		}else{
			System.out.println("Deslaurier: Prepare to suffer the wrath of Kevin!");
			stillTalking = false;
		}
		}
	}
	
	public Deslaurier(){
		super();
		//String description, double mass, int cost ,int power, int critChance
		
	}
	
	
	
/*	public void displayInventory(){
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
	
	}*/
}
