package com.bayviewglen.zork;

import java.util.ArrayList;

public class Daniel extends NonPlayableCharacter{
	private static String characterName = "Daniel";
	private int characterHealth = 150;
	private int characterHealthMax = 150;
	Weapon Weapon = new Weapon("Shoe ", 1.0, 10, 30, 3);
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
	ArrayList<Item> danielInventory = new ArrayList<Item>();
	//private int characterPower;
	//private int characterArmor;
	
	
	
	public void talk(Inventory inv){
		boolean stillTalking = true;
		System.out.println("Daniel: What do you want? (Select the number of the option you wish to say.)");
		while(stillTalking){
			System.out.println();
		System.out.println("1: What are you doing inside a temple?");
		System.out.println("2: What is you opinion on this game?");
		System.out.println("3: What is you opinion on Canadian Prime Minister Justin Trudea?");
		System.out.println("4: What is you opinion on fidget spinners?");
		System.out.println("5: Show me your wares.");
		System.out.println("6: Take a look at what I have.");
		System.out.println("7: Goodbye.");
		String response  = scanner.nextLine()+"";
		if(response.equals("1")==false&&response.equals("2")==false&&response.equals("3")==false&&response.equals("4")==false&&response.equals("5")==false&&response.equals("6")==false&&response.equals("7")==false){
			System.out.println("That is not a valid option.");
			System.out.println();
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
			this.buy(inv);;
		}else if (response.equals("6")){
			inv.showInventory();
			this.sell(inv);;
		}else{
			System.out.println("Daniel: Cya.");
			stillTalking = false;
		}
		}
	}
	
	public Daniel(){
		super(characterName);
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
			System.out.print(danielInventory.get(i).getDescription()+ ": " + danielInventory.get(i).getCost() + ", ");
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
				for (int i = 0; i<danielInventory.size(); i++){
					if(wishToBuy.equalsIgnoreCase(danielInventory.get(i).getDescription())){
							if(inv.getWallet()<danielInventory.get(i).getCost()){
								System.out.println("You do not have enough to buy this item.");
							}else{
								inv.addItem(danielInventory.get(i));
								System.out.println("You have bought " + danielInventory.get(i).getDescription() + ".");
								inv.setWallet(inv.getWallet() - danielInventory.get(i).getCost());
								System.out.println("You now have " + inv.getWallet() + "Iranian Rials.");
								danielInventory.remove(i);
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
						danielInventory.add(inv.getItem(j));
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
		System.out.println(characterName + ": *While dying* I... hate you.");
	}
	public ArrayList<Item> getNpcInventory() {
		return danielInventory;
	}

	public void setNpcInventory(ArrayList<Item> shayanSnInventory) {
		this.danielInventory = shayanSnInventory;
	}
}
