package com.bayviewglen.zork;

public class CodeTestingFile {

	public static void main(String[] args) {
		Inventory roomConditions = new Inventory();
		Inventory characterInventory = new Inventory();
		
		roomConditions.addItem(new Item("Key"));
		characterInventory.addItem(new Item("Boi", 20.0));
		characterInventory.addItem(new Item("Key", 20.0));
		
		System.out.println(checkConditions(roomConditions, characterInventory));
	}

	public static boolean checkConditions(Inventory roomConditions, Inventory characterInventory) {
		boolean equal = false;
		for (int i = 0; i < roomConditions.getNumItems(); i++) {
			equal = false;
			for (int j = 0; j < characterInventory.getNumItems(); j++) {
				if (roomConditions.getItem(i).getDescription()
						.equals(characterInventory.getItem(j).getDescription())) {
					equal = true;
				}
			}
			if (equal == false) {
				return false;
			}
		}
		return equal;
	}
}