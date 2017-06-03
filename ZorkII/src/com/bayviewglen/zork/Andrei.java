package com.bayviewglen.zork;

import java.util.ArrayList;

import com.bayviewglen.zork.Inventory;
import com.bayviewglen.zork.Item;
import com.bayviewglen.zork.NonPlayableCharacter;

public class Andrei extends NonPlayableCharacter {
	private static String characterName = "Andrei";
	private int characterHealth = 200;

	public int getCharacterHealth() {
		return characterHealth;
	}

	public void setCharacterHealth(int characterHealth) {
		this.characterHealth = characterHealth;
	}

	private Weapon Weapon = new Weapon("Audacity Sound File ", 1.0, 0, 20, 5);

	public Weapon getWeapon() {
		return Weapon;
	}

	public void setWeapon(Weapon weapon) {
		Weapon = weapon;
	}

	ArrayList<Item> andreiInventory = new ArrayList<Item>();
	// private int characterPower;
	// private int characterArmor;

	public void talk(Inventory inv) {
		boolean stillTalking = true;
		System.out.println(
				"Andrei: Oh hi there, didn't see you. Do you want to buy some ugly green GRAD sweaters. (Select the number of the option you wish to say.)");
		while (stillTalking) {
			System.out.println();
			System.out.println("1: What are you doing inside a temple?");
			System.out.println("2: Show me your wares.");
			System.out.println("3: Take a look at what I have.");
			System.out.println("4: Goodbye.");
			String response = scanner.nextLine();
			if (response.equals("1") == false && response.equals("2") == false && response.equals("3") == false
					&& response.equals("4") == false) {
				System.out.println("That is not a valid option.");
			} else if (response.equals("1")) {
				System.out
						.println("Andrei: I am trying to find ideas for my ComSci project inside this superior game.");
			} else if (response.equals("2")) {
				this.displayInventory();
				this.buy(inv);
			} else if (response.equals("3")) {
				inv.showInventory();
				this.sell(inv);
			} else {
				System.out.println("Andrei: CIAO.");
				stillTalking = false;
			}
		}
	}

	public Andrei() {
		super(characterName);
		// creates the inventory for the character
		andreiInventory.add(new ArmourAttachment("Uglygreensweater(AA)", 1.0, 30, 5, "Ugly Green"));
		andreiInventory.add(new WeaponAttachment("Repostedmeme(WA)", 0.1, 20, 5, 1, "Reposted"));
		andreiInventory.add(new Pockets("SmallPockets", 0.0, 10, 20));
		andreiInventory.add(new Weapon("AudacitySoundFile", 1.0, 0, 20, 5));
		// String description, double mass, int cost ,int power, int critChance

	}

	public void displayInventory() {
		// shayanSnInventoryNames =new ArrayList<String>();
		if (andreiInventory.size() > 0) {
			System.out.println("Andrei: Check em out ");
			System.out.println("(Type the name of the item you wish to buy. Or type exit.)");
			System.out.println();
			for (int i = 0; i < andreiInventory.size(); i++) {
				System.out.print(
						andreiInventory.get(i).getDescription() + ": " + andreiInventory.get(i).getCost() + ", ");
			}
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
				for (int i = 0; i < andreiInventory.size(); i++) {
					if (wishToBuy.equalsIgnoreCase(andreiInventory.get(i).getDescription())) {
						if (inv.getWallet() < andreiInventory.get(i).getCost()) {
							System.out.println("You do not have enough to buy this item.");
						} else {
							inv.addItem(andreiInventory.get(i));
							System.out.println("You have bought " + andreiInventory.get(i).getDescription() + ".");
							inv.setWallet(inv.getWallet() - andreiInventory.get(i).getCost());
							System.out.println("You now have " + inv.getWallet() + "Iranian Rials.");
							andreiInventory.remove(i);
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
						andreiInventory.add(inv.getItem(j));
						inv.setWallet(inv.getWallet() + inv.getItem(j).getCost());
						inv.removeItem(inv.getItem(j));
						System.out.println("You now have " + inv.getWallet() + "Iranian Rials.");
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
		System.out.println(characterName + ": *While dying* I see the light. It's green.");
	}

	public ArrayList<Item> getNpcInventory() {
		return andreiInventory;
	}

	public void setNpcInventory(ArrayList<Item> shayanSnInventory) {
		this.andreiInventory = shayanSnInventory;
	}
}
