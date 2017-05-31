package com.bayviewglen.zork;

import java.util.ArrayList;
import java.util.Scanner;
// test
public class ShayanShakeriNezhad extends NonPlayableCharacter{
	
	private String characterName = "ShayanSn";
	private int characterHealth = 1000;
	private int characterHealthMax = 1000;
	Weapon shayanSnKeyBlade = new Weapon("Sour Key-Blade", 5.0, 1000, 80, 4);
	private int characterPower = shayanSnKeyBlade.getPower();
//	ArrayList<String> shayanSnInventoryNames = new ArrayList<String>();
	ArrayList<Item> shayanSnInventory = new ArrayList<Item>();
	//private int characterPower;
	//private int characterArmor;
	
	
	
	public void talk(){
		boolean stillTalking = true;
		System.out.println("ShayanSn: Welcome to the tuck shop, what can I do for you. (Select the number of the option you wish to say.)");
		while(stillTalking){
		System.out.println("1: What are you doing inside a temple?");
		System.out.println("2: Show me your wares.");
		System.out.println("3: Goodbye.");
		String response  = scanner.nextLine();
		if(response.equals("1")==false||response.equals("2")==false||response.equals("3")==false){
			System.out.println("That is not a valid option.");
		}else if(response.equals("1")){
			System.out.println("I am looking for customers to buy my wares so I can raise money for Bayview Glen.");
		}else if (response.equals("2")){
			this.displayInventory();
		}else{
			System.out.println("Come again.");
			stillTalking = false;
		}
		}
	}
	
	public ShayanShakeriNezhad(){
		super();
		//creates the inventory for the character
		shayanSnInventory.add(new Food("skittles", 0.4, 25, 30));
		shayanSnInventory.add(new Food("skittles", 0.4, 25, 30));
		shayanSnInventory.add(new Food("skittles", 0.4, 25, 30));
		shayanSnInventory.add(new Food("Sour Key", 0.2, 10, 10));
		shayanSnInventory.add(new Food("Sour Key", 0.2, 10, 10));
		shayanSnInventory.add(new Food("Sour Key", 0.2, 10, 10));
		shayanSnInventory.add(new Food("Sour Key", 0.2, 10, 10));
		shayanSnInventory.add(new Food("Fun Dip", 0.5, 35, 80));
		shayanSnInventory.add(new Food("Fun Dip", 0.5, 35, 80));
		shayanSnInventory.add(new Food("Kinder Egg", 0.1, 80, 100000000));
		//String description, double mass, int cost ,int power, int critChance
		shayanSnInventory.add(new Weapon("Sour Key-Blade", 5.0, 1000, 80, 4));
		
	}
	
	
	
	public void displayInventory(){
	//	shayanSnInventoryNames =new ArrayList<String>();
		if(shayanSnInventory.size() > 0){
		System.out.println("Here are my wares.");
		System.out.println();
		for (int i = 0; i<shayanSnInventory.size(); i++){
			System.out.print(shayanSnInventory.get(i).getDescription()+ ": " + shayanSnInventory.get(i).getCost() + " ");
		}
	}else {
		System.out.println("I have nothing more to sell.");
	}
	
	}
}
