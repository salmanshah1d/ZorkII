package com.bayviewglen.zork;

import java.util.ArrayList;
import java.util.Scanner;

public class ShayanSalesi extends NonPlayableCharacter{

	
	private String characterName = "ShayanSl";
	private int characterHealth = 150;
	private int characterHealthMax = 150;
	Weapon shayanSlWeapon = new SupremeShank();
	private int characterPower = shayanSlWeapon.getPower();
//	ArrayList<String> shayanSnInventoryNames = new ArrayList<String>();
	ArrayList<Item> shayanSlInventory = new ArrayList<Item>();
	//private int characterPower;
	//private int characterArmor;
	
	
	
	public void talk(){
		boolean stillTalking = true;
		System.out.println("ShayanSl: Hello, I am Shayan Salesi, CEO of Saturn Capital. Please buy some Saturn Capital shares. (Select the number of the option you wish to say.)");
		while(stillTalking){
		System.out.println("1: What are you doing inside a temple?");
		System.out.println("2: What is Saturn Capital?");
		System.out.println("3: Show me your wares.");
		System.out.println("4: Goodbye.");
		String response  = scanner.nextLine();
		if(response.equals("1")==false||response.equals("2")==false||response.equals("3")==false||response.equals("4")==false){
			System.out.println("That is not a valid option.");
		}
		else if(response.equals("1")){
			System.out.println("I wasn't able to find any investors anywhere else in the world, so I decided to try my luck here. Wold you lik to invest?");
		}else if(response.equals("2")){
			System.out.println("Saturn Capital is a capital investment firm created by myself. You purchase my stocks, and I use that money to increase the companies capital, increasing the price of your stock.");
		}
		else if (response.equals("3")){
			this.displayInventory();
		}else{
			System.out.println("Lets talk soon.");
			stillTalking = false;
		}
		}
	}
	
	public ShayanSalesi(){
		super();
		//creates the inventory for the character
		shayanSlInventory.add(new Item("Saturn Capital Share", 0.0, 40));
		shayanSlInventory.add(new Item("Saturn Capital Share", 0.0, 40));
		shayanSlInventory.add(new Item("Saturn Capital Share", 0.0, 40));
		shayanSlInventory.add(new Item("Saturn Capital Share", 0.0, 40));
		shayanSlInventory.add(new Item("Saturn Capital Share", 0.0, 40));
		//String description, Double mass, int cost, int powerAdded,int critAdded, String word
		shayanSlInventory.add(new WeaponAttachment("Supreme logo(WA)", 0.2, 80, 10, 3, "Supreme"));
		shayanSlInventory.add(new WeaponAttachment("Weapon Grease(WA)", 0.2, 50, 5, 2, "Greasy"));
		//String description, double mass, int cost ,int power, int critChance
		shayanSlInventory.add(new SupremeShank());
		
	}
	
	
	
	public void displayInventory(){
	//	shayanSnInventoryNames =new ArrayList<String>();
		if(shayanSlInventory.size() > 0){
		System.out.println("This is what i got: ");
		for (int i = 0; i<shayanSlInventory.size(); i++){
			System.out.print(shayanSlInventory.get(i).getDescription()+ ": " + shayanSlInventory.get(i).getCost() + " ");
		}
	}else {
		System.out.println("Nothing more to sell.");
	}
	
	}
}
