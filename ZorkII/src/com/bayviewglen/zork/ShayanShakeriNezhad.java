package com.bayviewglen.zork;

import java.util.ArrayList;
import java.util.Scanner;

public class ShayanShakeriNezhad extends NonPlayableCharacter{
	
	private static String characterName = "ShayanShakeri";
	private int characterHealth = 1000;
	private int characterHealthMax = 1000;
	Weapon shayanSnKeyBlade = new Weapon("Sour Key-Blade", 5.0, 1000, 80, 4);
	private int characterPower = shayanSnKeyBlade.getPower();
//	ArrayList<String> shayanSnInventoryNames = new ArrayList<String>();
	ArrayList<Item> shayanSnInventory = new ArrayList<Item>();
	//private int characterPower;
	//private int characterArmor;
	
	
	
	public void talk(Inventory inv){
		boolean stillTalking = true;
		System.out.println("ShayanSn: Welcome to the tuck shop, what can I do for you. (Select the number of the option you wish to say.)");
		while(stillTalking){
		System.out.println();
		System.out.println("1: What are you doing inside a temple?");
		System.out.println("2: Show me your wares.");
		System.out.println("3: Take a look at my stuff.");
		System.out.println("4: Goodbye.");
		String response  = scanner.nextLine();
		if(response.equals("1")==false&&response.equals("2")==false&&response.equals("3")==false&&response.equals("4")==false){
			System.out.println("That is not a valid option.");
		}else if(response.equals("1")){
			System.out.println("I am looking for customers to buy my wares so I can raise money for Bayview Glen.");
		}else if (response.equals("2")){
			this.displayInventory();
			this.buy(inv);
		}else if(response.equals("3")){
			inv.showInventory();
			if(inv.getInventory().size() == 0)
				System.out.println("You have noting to sell.");
			else{
				this.sell(inv);
			}
			}else{
			System.out.println("Come again.");
			stillTalking = false;
		}
		}
	}
	
	public ShayanShakeriNezhad(){
		super(characterName);
		//creates the inventory for the character
		shayanSnInventory.add(new Food("skittles", 0.4, 25, 30));
		shayanSnInventory.add(new Food("skittles", 0.4, 25, 30));
		shayanSnInventory.add(new Food("skittles", 0.4, 25, 30));
		shayanSnInventory.add(new Food("SourKey", 0.2, 10, 10));
		shayanSnInventory.add(new Food("SourKey", 0.2, 10, 10));
		shayanSnInventory.add(new Food("SourKey", 0.2, 10, 10));
		shayanSnInventory.add(new Food("SourKey", 0.2, 10, 10));
		shayanSnInventory.add(new Food("FunDip", 0.5, 35, 80));
		shayanSnInventory.add(new Food("FunDip", 0.5, 35, 80));
		shayanSnInventory.add(new Food("KinderEgg", 0.1, 80, 100000000));
		//String description, double mass, int cost ,int power, int critChance
		shayanSnInventory.add(new Weapon("SourKey-Blade", 5.0, 1000, 80, 4));
		
	}
	
	
	
	public void displayInventory(){
	//	shayanSnInventoryNames =new ArrayList<String>();
		if(shayanSnInventory.size() > 0){
		System.out.println("ShayanSn:Here are my wares.");
		System.out.println("(Type the name of the item you wish to buy. Or type exit.)");

		System.out.println();
		for (int i = 0; i<shayanSnInventory.size(); i++){
			System.out.print(shayanSnInventory.get(i).getDescription()+ ": " + shayanSnInventory.get(i).getCost() + ", ");
		}
	}else {
		System.out.println("I have nothing more to sell.");
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
				for (int i = 0; i<shayanSnInventory.size(); i++){
					if(wishToBuy.equalsIgnoreCase(shayanSnInventory.get(i).getDescription())){
							if(inv.getWallet()<shayanSnInventory.get(i).getCost()){
								System.out.println("You do not have enough to buy this item.");
							}else{
								inv.addItem(shayanSnInventory.get(i));
								System.out.println("You have bought " + shayanSnInventory.get(i).getDescription() + ".");
								inv.setWallet(inv.getWallet() - shayanSnInventory.get(i).getCost());
								System.out.println("You now have " + inv.getWallet() + "Iranian Rials.");
								shayanSnInventory.remove(i);
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
						shayanSnInventory.add(inv.getItem(j));
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
}
