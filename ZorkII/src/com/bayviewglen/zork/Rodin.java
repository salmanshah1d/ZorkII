package com.bayviewglen.zork;

import java.util.ArrayList;
import java.util.Scanner;

public class Rodin extends NonPlayableCharacter{
	private static String characterName = "Rodin";
	private int characterHealth = 80;
	private int characterHealthMax = 80;
	private Weapon Weapon = new DirtyShank();
	private int characterPower = Weapon.getPower();
	public int getCharacterHealth() {
		return characterHealth;
	}

	public void setCharacterHealth(int characterHealth) {
		this.characterHealth = characterHealth;
	}

	public Weapon getWeapon() {
		return Weapon;
	}

	public void setWeapon(Weapon weapon) {
		Weapon = weapon;
	}
	ArrayList<Item> rodinInventory = new ArrayList<Item>();
	//private int characterPower;
	//private int characterArmor;
	
	
	
	public void talk(Inventory inv){
		boolean stillTalking = true;
		System.out.println("Rodin: AAAAAHHHHHHHHHHHHHHHHHH! (Select the number of the option you wish to say.)");
		while(stillTalking){
		System.out.println();
		System.out.println("1: What are you doing inside a temple?");
		System.out.println("2: What is wrong with you?");
		System.out.println("3: How long have you been down here?");
		System.out.println("4: Show me your wares.");
		System.out.println("5: Take a look at what I have.");
		System.out.println("6: I'm going to kill you now. Goodbye.");
		String response  = scanner.nextLine();
		if(response.equals("1")==false&&response.equals("2")==false&&response.equals("3")==false&&response.equals("4")==false&&response.equals("5")==false&&response.equals("6")==false){
			System.out.println("That is not a valid option.");
		}else if(response.equals("1")){
			System.out.println("Rodin: WWWWWWWWAAAAAHHHHHHH!");
		}else if(response.equals("2")){
			System.out.println("Rodin: GGGGGGGGGAAAAAHHHHHHH!");
		}else if(response.equals("3")){
			System.out.println("Rodin: SO LONG MAN!");
		}else if (response.equals("4")){
			this.displayInventory();
			this.buy(inv);
		}else if (response.equals("5")){
			inv.showInventory();
			this.sell(inv);
		}else{
			System.out.println("Rodin: NO PLZ STAHP!");
			stillTalking = false;
		}
		}
	}
	
	public Rodin(){
		super(characterName);
		//creates the inventory for the character
		rodinInventory.add(new Food("Half-eatenSandwich", 0.5, 0, 60));
		rodinInventory.add(new Pockets("LargePockets", 0.0, 50, 50));
		rodinInventory.add(new WeaponAttachment("WeaponPoison(WA)", 0.1, 55, 0, 5, "poisoned"));
		rodinInventory.add(new ArmourAttachment("SmallUnderShirt(AA)", 0.3, 20, 1, "insulated"));
		//String description, double mass, int cost ,int power, int critChance
		rodinInventory.add(new DirtyShank());
		
	}
	
	
	
	public void displayInventory(){
	//	shayanSnInventoryNames =new ArrayList<String>();
		if(rodinInventory.size() > 0){
		System.out.print("YOU WANT?!");
		System.out.println("(Type the name of the item you wish to buy. Or type exit.)");
		System.out.println();
		for (int i = 0; i<rodinInventory.size(); i++){
			System.out.print(rodinInventory.get(i).getDescription()+ ": " + rodinInventory.get(i).getCost() + ", ");
		}
	}else {
		System.out.println("I ain't got nutin.");
	}
	
	}
	public void buy(Inventory inv){
		boolean stillBuying = true;
		while(stillBuying){
			String wishToBuy = scanner.nextLine();
			if(wishToBuy.equals("exit"))
				stillBuying = false;
			else{
				boolean isBuyable = false;
				for (int i = 0; i<rodinInventory.size(); i++){
					if(wishToBuy.equalsIgnoreCase(rodinInventory.get(i).getDescription())){
							if(inv.getWallet()<rodinInventory.get(i).getCost()){
								System.out.println("You do not have enough to buy this item.");
							}else{
								inv.addItem(rodinInventory.get(i));
								System.out.println("You have bought " + rodinInventory.get(i).getDescription() + ".");
								inv.setWallet(inv.getWallet() - rodinInventory.get(i).getCost());
								System.out.println("You now have " + inv.getWallet() + "Iranian Rials.");
								rodinInventory.remove(i);
								isBuyable = true;
								break;	
							}
					}
				}if(isBuyable == false)
					System.out.println("That is not a valid option.");
			}stillBuying = false;
		}
	}public void sell(Inventory inv){
		boolean stillSelling = true;
		while(stillSelling){
			String wishToSell = scanner.nextLine();
			if(wishToSell.equals("exit"))
				stillSelling = false;
			else{
				boolean isSellable= false;
				for (int j=0; j<inv.getNumItems();j++){
					if(wishToSell.equalsIgnoreCase(inv.getItem(j).getDescription())){
						rodinInventory.add(inv.getItem(j));
						inv.setWallet(inv.getWallet()+inv.getItem(j).getCost());
						inv.removeItem(inv.getItem(j));
						System.out.println("You now have " + inv.getWallet() + "Iranian Rials.");
						isSellable = true;
						break;	
					}
				}if(isSellable== false)
					System.out.println("That is not a valid option.");
			}stillSelling = false;
		}
	}
	public void deathPhrase(){
		System.out.println(characterName + ": *While dying* WHY U DO DIS?");
	}
	
	public ArrayList<Item> getNpcInventory() {
		return rodinInventory;
	}

	public void setNpcInventory(ArrayList<Item> shayanSnInventory) {
		this.rodinInventory = shayanSnInventory;
	}

}
