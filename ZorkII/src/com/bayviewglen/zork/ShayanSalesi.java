package com.bayviewglen.zork;

import java.util.ArrayList;
import java.util.Scanner;

public class ShayanSalesi extends NonPlayableCharacter{

	
	private static String characterName = "ShayanSalesi";
	private int characterHealth = 150;
	private int characterHealthMax = 150;
	Weapon shayanSlWeapon = new SupremeShank();
	private int characterPower = shayanSlWeapon.getPower();
//	ArrayList<String> shayanSnInventoryNames = new ArrayList<String>();
	ArrayList<Item> shayanSlInventory = new ArrayList<Item>();
	//private int characterPower;
	//private int characterArmor;
	
	
	
	public void talk(Inventory inv){
		boolean stillTalking = true;
		System.out.println("ShayanSl: Hello, I am Shayan Salesi, CEO of Saturn Capital. Please buy some Saturn Capital shares. (Select the number of the option you wish to say.)");
		while(stillTalking){
		System.out.println();
		System.out.println("1: What are you doing inside a temple?");
		System.out.println("2: What is Saturn Capital?");
		System.out.println("3: I want to invest. Here is some money.");
		System.out.println("4: Show me your wares.");
		System.out.println("5: Take a look at what I have");
		System.out.println("6: Goodbye.");
		String response  = scanner.nextLine();
		if(response.equals("1")==false&&response.equals("2")==false&&response.equals("3")==false&&response.equals("4")==false&&response.equals("5")==false){
			System.out.println("That is not a valid option.");
		}
		else if(response.equals("1")){
			System.out.println("I wasn't able to find any investors anywhere else in the world, so I decided to try my luck here. Would you like to invest?");
		}else if(response.equals("2")){
			System.out.println("Saturn Capital is a capital investment firm created by myself. You purchase my stocks, and I use that money to increase the companies capital, increasing the price of your stock.");
			
		}else if(response.equals("3")){
			System.out.println("Due to unfavorable market conditions and poor investment decisions, I have lost your money. Hahahahah");
			inv.setWallet(inv.getWallet()*80/100);
			System.out.println();
			System.out.println("You now have " + inv.getWallet() + "Iranian rials left.");
		}
		else if (response.equals("4")){
			this.displayInventory();
			this.buy(inv);
		}else if (response.equals("5")){
			inv.showInventory();
			this.sell(inv);
		}else{
			System.out.println("Let's chat soon.");
			stillTalking = false;
		}
		}
	}
	
	public ShayanSalesi(){
		super(characterName);
		//creates the inventory for the character
		System.out.println();
	/*	shayanSlInventory.add(new Item("SaturnCapitalShare", 0.0, 40));
		shayanSlInventory.add(new Item("SaturnCapitalShare", 0.0, 40));
		shayanSlInventory.add(new Item("SaturnCapitalShare", 0.0, 40));
		shayanSlInventory.add(new Item("SaturnCapitalShare", 0.0, 40));*/
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
		System.out.println("(Type the name of the item you wish to buy. Or type exit.)");
		for (int i = 0; i<shayanSlInventory.size(); i++){
			System.out.print(shayanSlInventory.get(i).getDescription()+ ": " + shayanSlInventory.get(i).getCost() + ", ");
		}
	}else {
		System.out.println("Nothing more to sell.");
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
				for (int i = 0; i<shayanSlInventory.size(); i++){
					if(wishToBuy.equalsIgnoreCase(shayanSlInventory.get(i).getDescription())){
							if(inv.getWallet()<shayanSlInventory.get(i).getCost()){
								System.out.println("You do not have enough to buy this item.");
							}else{
								inv.addItem(shayanSlInventory.get(i));
								System.out.println("You have bought " + shayanSlInventory.get(i).getDescription() + ".");
								inv.setWallet(inv.getWallet() - shayanSlInventory.get(i).getCost());
								System.out.println("You now have " + inv.getWallet() + "Iranian Rials.");
								shayanSlInventory.remove(i);
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
						shayanSlInventory.add(inv.getItem(j));
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
