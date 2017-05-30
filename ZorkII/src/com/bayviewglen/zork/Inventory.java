package com.bayviewglen.zork;

import java.util.ArrayList;

public class Inventory {
	private int currentIndex;
	ArrayList<Item> inventory;
	private int weight = 0;
	private double maxWeight = 50.0; // the max weight a character can make

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
	public String print() {
		String words = "";
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) != null) {
				words += inventory.get(i).display() + ", ";
			}
		}
		words = words.substring(0, words.length() - 2);
		return words;
	}

	// get the max weight you can carry
	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public Item getItem(int index) {
		return inventory.get(index);
	}

	public void setSword(Sword sword, Character mainCharacter){
		mainCharacter.setSword(sword);
	}
}