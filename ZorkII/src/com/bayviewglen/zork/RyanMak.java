package com.bayviewglen.zork;

import java.util.ArrayList;

public class RyanMak extends NonPlayableCharacter{
	private static String characterName = "Ryan Mak";
	private int characterHealth = 150;
	private Weapon Weapon = new GreasyShank();
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
	ArrayList<Item> ryanMakInventory = new ArrayList<Item>();
	//private int characterPower;
	//private int characterArmor;
	
	
	
	public void talk(Inventory inv){
		boolean stillTalking = true;
		System.out.println("Ryan: Oh hi. Can you help me with my ComSci? (Select the number of the option you wish to say.)");
		while(stillTalking){
		System.out.println();
		System.out.println("1: What are you doing inside a temple?");
		System.out.println("2: What do you need help for in ComSci?");
		System.out.println("3: I think you should just drop the course.");
		System.out.println("4: You have a nice butt.");
		System.out.println("5: Show me your wares.");
		System.out.println("6: Take a look at what I have.");
		System.out.println("7: Goodbye.");
		String response  = scanner.nextLine();
		if(response.equals("1")==false&&response.equals("2")==false&&response.equals("3")==false&&response.equals("4")==false&&response.equals("5")==false&&response.equals("6")==false&&response.equals("7")==false){
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
			this.buy(inv);
		}else if (response.equals("6")){
			inv.showInventory();
			this.sell(inv);
		}else{
			System.out.println("Ryan: CY@.");
			stillTalking = false;
		}
		}
	}
	
	public RyanMak(){
		super(characterName);
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
		System.out.println("(Type the name of the item you wish to buy. Or type exit.)");
		System.out.println();
		for (int i = 0; i<ryanMakInventory.size(); i++){
			System.out.print(ryanMakInventory.get(i).getDescription()+ ": " + ryanMakInventory.get(i).getCost() + ", ");
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
				for (int i = 0; i<ryanMakInventory.size(); i++){
					if(wishToBuy.equalsIgnoreCase(ryanMakInventory.get(i).getDescription())){
							if(inv.getWallet()<ryanMakInventory.get(i).getCost()){
								System.out.println("You do not have enough to buy this item.");
							}else{
								inv.addItem(ryanMakInventory.get(i));
								System.out.println("You have bought " + ryanMakInventory.get(i).getDescription() + ".");
								inv.setWallet(inv.getWallet() - ryanMakInventory.get(i).getCost());
								System.out.println("You now have " + inv.getWallet() + "Iranian Rials.");
								ryanMakInventory.remove(i);
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
						ryanMakInventory.add(inv.getItem(j));
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
	}	public void deathPhrase(){
		System.out.println("characterName + *: While dying* I should have dropped ComSci.");
	}
	public ArrayList<Item> getNpcInventory() {
		return ryanMakInventory;
	}

	public void setNpcInventory(ArrayList<Item> shayanSnInventory) {
		this.ryanMakInventory = shayanSnInventory;
	}
}
