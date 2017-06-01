package com.bayviewglen.zork;

import java.util.ArrayList;

public class Inventory {
	private int currentIndex;
	ArrayList<Item> inventory;
	private int weight = 0;
	private double maxWeight = 50.0; // the max weight a character can make
	private int wallet = 9999999;

	public int getWallet() {
		return wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public Inventory() {
		inventory = new ArrayList<Item>(); // construct the object
	}

	// how to add an item to a inventory
	public void addItem(Item item) {
		if ((weight + item.getMass()) < maxWeight) { // IT IS A + NOT A "+="
			inventory.add(item);
			weight += item.getMass();

		} else {
			System.out.println("It is too heavy to be carried!");
		}
	}

	public void addItem(Weapon weapon) {
		if ((weight + weapon.getMass()) < maxWeight) { // IT IS A + NOT A "+="
			inventory.add(weapon);
			weight += weapon.getMass();

		} else {
			System.out.println("It is too heavy to be carried!");
		}
	}

	// how to remove an item
	public void removeItem(Item item) {
		currentIndex = inventory.indexOf(item);
		inventory.remove(currentIndex);
		weight -= item.getMass();
	}

	// the weight that you currently are carrying
	public int getWeight() {
		return weight;
	}

	// getter for the inventory arraylist
	public ArrayList<Item> getInventory() {
		return inventory;
	}

	// getter for the number of inventory
	public int getNumItems() {
		return inventory.size();
	}

	// print out the inventory you have
	// print out the inventory you have
	public String print() {
		String words = "";
		if (inventory.size() == 1) {
			if (inventory.get(0).getDescription().charAt(0) == 'a' || inventory.get(0).getDescription().charAt(0) == 'e'
					|| inventory.get(0).getDescription().charAt(0) == 'i'
					|| inventory.get(0).getDescription().charAt(0) == 'o'
					|| inventory.get(0).getDescription().charAt(0) == 'u') {
				words += "an " + inventory.get(0).display();
			} else {
				words += "a " + inventory.get(0).display();
			}
		} else if (inventory.size() == 2) {
			if ((inventory.get(0).getDescription().charAt(0) == 'a'
					|| inventory.get(0).getDescription().charAt(0) == 'e'
					|| inventory.get(0).getDescription().charAt(0) == 'i'
					|| inventory.get(0).getDescription().charAt(0) == 'o'
					|| inventory.get(0).getDescription().charAt(0) == 'u')
					&& (inventory.get(1).getDescription().charAt(0) == 'a'
							|| inventory.get(1).getDescription().charAt(0) == 'e'
							|| inventory.get(1).getDescription().charAt(0) == 'i'
							|| inventory.get(1).getDescription().charAt(0) == 'o'
							|| inventory.get(1).getDescription().charAt(0) == 'u')) {
				words += "an " + inventory.get(0).display() + " and an " + inventory.get(1).display();
			} else if (inventory.get(0).getDescription().charAt(0) == 'a'
					|| inventory.get(0).getDescription().charAt(0) == 'e'
					|| inventory.get(0).getDescription().charAt(0) == 'i'
					|| inventory.get(0).getDescription().charAt(0) == 'o'
					|| inventory.get(0).getDescription().charAt(0) == 'u') {
				words += "an " + inventory.get(0).display() + " and a " + inventory.get(1).display();
			} else if (inventory.get(1).getDescription().charAt(0) == 'a'
					|| inventory.get(1).getDescription().charAt(0) == 'e'
					|| inventory.get(1).getDescription().charAt(0) == 'i'
					|| inventory.get(1).getDescription().charAt(0) == 'o'
					|| inventory.get(1).getDescription().charAt(0) == 'u') {
				words += "a " + inventory.get(0).display() + " and an " + inventory.get(1).display();
			} else {
				words += "a " + inventory.get(0).display() + " and a " + inventory.get(1).display();
			}
		} else {
			for (int i = 0; i < inventory.size() - 1; i++) {
				if (inventory.get(i) != null) {
					words += "a " + inventory.get(i).display() + ", ";
				}
			}
			words += "and " + inventory.get(inventory.size() - 1).display();
		}
		return words;
	}

	public void showInventory() {
		if (inventory.size() > 0) {
			for (int i = 0; i < inventory.size(); i++) {
				System.out.print(inventory.get(i).getDescription() + ": " + inventory.get(i).getCost() + ", ");
			}
		} else {
			System.out.println("There is nothing in your inventory.");
		}

	}

	// get the max weight you can carry
	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public Item getItem(int index) {
		if (inventory.get(index).getDescription().equals("Sword")) {
			return (Weapon) inventory.get(index);
		} else {
			return inventory.get(index);
		}
	}
}
