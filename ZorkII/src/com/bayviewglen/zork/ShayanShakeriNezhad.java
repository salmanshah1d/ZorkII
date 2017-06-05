package com.bayviewglen.zork;

import java.util.ArrayList;

public class ShayanShakeriNezhad extends NonPlayableCharacter {

	private static String characterName = "ShayanShakeri";
	private int characterHealth = 300;

	public int getCharacterHealth() {
		return characterHealth;
	}

	public void setCharacterHealth(int characterHealth) {
		this.characterHealth = characterHealth;
	}
	
	//string name, double weight, int cost, int power, int CritChance
	private Weapon Weapon = new Weapon("Sour Key-Blade", 5.0, 400, 40, 4);

	public Weapon getWeapon() {
		return Weapon;
	}

	public void setWeapon(Weapon weapon) {
		Weapon = weapon;
	}

	ArrayList<Item> shayanSnInventory = new ArrayList<Item>();

	public void talk(Inventory inv) {
		boolean stillTalking = true;
		System.out.println(
				"ShayanShakeri: Welcome to the tuck shop, what can I do for you? (Select the number of the option you wish to say.)");
		while (stillTalking) {
			System.out.println();
			System.out.println("1: What are you doing inside a temple?");
			System.out.println("2: Show me your wares.");
			System.out.println("3: Take a look at my stuff.");
			System.out.println("4: Goodbye.");
			String response = scanner.nextLine();
			if (response.equals("1") == false && response.equals("2") == false && response.equals("3") == false
					&& response.equals("4") == false) {
				System.out.println("That is not a valid option.");
			} else if (response.equals("1")) {
				System.out.println(
						"ShayanShakeri: I am looking for customers to buy my wares so I can raise money for Bayview Glen.");
			} else if (response.equals("2")) {
				this.displayInventory(inv);
				this.buy(inv);
			} else if (response.equals("3")) {
				inv.showInventory();
				if (inv.getInventory().size() == 0)
					System.out.println("You have noting to sell.");
				else {
					this.sell(inv);
				}
			} else {
				System.out.println("Come again.");
				stillTalking = false;
			}
		}
	}

	public ShayanShakeriNezhad() {
		super(characterName);
		// creates the inventory for the character
		// String description, Double mass, int cost, int healthReturned
		shayanSnInventory.add(new Food("Skittles", 0.4, 25, 30));
		shayanSnInventory.add(new Food("Skittles", 0.4, 25, 30));
		shayanSnInventory.add(new Food("Skittles", 0.4, 25, 30));
		shayanSnInventory.add(new Food("SourKey", 0.2, 10, 10));
		shayanSnInventory.add(new Food("SourKey", 0.2, 10, 10));
		shayanSnInventory.add(new Food("SourKey", 0.2, 10, 10));
		shayanSnInventory.add(new Food("SourKey", 0.2, 10, 10));
		shayanSnInventory.add(new Food("KinderEgg", 0.1, 80, 100000000));
		// String description, double mass, int cost, int power, int critChance
		shayanSnInventory.add(new Weapon("SourKey-Blade", 5.0, 400, 40, 4));

	}

	public void displayInventory(Inventory inv) {
		// shayanSnInventoryNames =new ArrayList<String>();
		if (shayanSnInventory.size() > 0) {
			System.out.println("ShayanShakeri: Here are my wares.");
			System.out.println("(Type the name of the item you wish to buy. Or type exit.)");

			System.out.println();
			System.out.println("You have " + inv.getWallet() + " Iranian Rials.");
			for (int i = 0; i < shayanSnInventory.size(); i++) {
				if (i == shayanSnInventory.size() - 1) {
					System.out.print(
							shayanSnInventory.get(i).getDescription() + ": " + shayanSnInventory.get(i).getCost());
				} else {
					System.out.print(shayanSnInventory.get(i).getDescription() + ": "
							+ shayanSnInventory.get(i).getCost() + ", ");
				}
			}
			System.out.println();
		} else {
			System.out.println("I have nothing more to sell.");
		}

	}

	public void buy(Inventory inv) {
		boolean stillBuying = true;
		while (stillBuying) {
			String wishToBuy = scanner.nextLine();
			if (wishToBuy.equals("exit"))
				stillBuying = false;
			else {
				boolean isBuyable = false;
				for (int i = 0; i < shayanSnInventory.size(); i++) {
					if (wishToBuy.equalsIgnoreCase(shayanSnInventory.get(i).getDescription())) {
						if (inv.getWallet() < shayanSnInventory.get(i).getCost()) {
							System.out.println("You do not have enough to buy this item.");
						} else {
							inv.addItem(shayanSnInventory.get(i));
							System.out.println("You have bought " + shayanSnInventory.get(i).getDescription() + ".");
							inv.setWallet(inv.getWallet() - shayanSnInventory.get(i).getCost());
							System.out.println("You now have " + inv.getWallet() + " Iranian Rials.");
							shayanSnInventory.remove(i);
							isBuyable = true;
							break;
						}
					}
				}
				if (isBuyable == false)
					System.out.println("That is not a valid option.");
			}
			stillBuying = false;
		}
	}

	public void sell(Inventory inv) {
		boolean stillSelling = true;
		while (stillSelling) {
			String wishToSell = scanner.nextLine();
			if (wishToSell.equals("exit"))
				stillSelling = false;
			else {
				boolean isSellable = false;
				for (int j = 0; j < inv.getNumItems(); j++) {
					if (wishToSell.equalsIgnoreCase(inv.getItem(j).getDescription())) {
						shayanSnInventory.add(inv.getItem(j));
						inv.setWallet(inv.getWallet() + inv.getItem(j).getCost());
						inv.removeItem(inv.getItem(j));
						System.out.println("You now have " + inv.getWallet() + " Iranian Rials.");
						isSellable = true;
						break;
					}
				}
				if (isSellable == false)
					System.out.println("That is not a valid option.");
			}
			stillSelling = false;
		}
	}

	public void deathPhrase() {
		System.out.println(characterName + ": *While dying* I will  be back.");
	}

	public ArrayList<Item> getNpcInventory() {
		return shayanSnInventory;
	}

	public void setNpcInventory(ArrayList<Item> shayanSnInventory) {
		this.shayanSnInventory = shayanSnInventory;
	}
}
