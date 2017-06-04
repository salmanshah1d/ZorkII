package com.bayviewglen.zork;
/*
 * Class Room - a room in an adventure game. 
 *
 * Author:  Michael Kolling.
 * Version: 1.1
 * Date:    August 2000
 * 
 * This class is part of Zork. Zork is a simple, text based adventure game.
 *
 * "Room" represents one location in the scenery of the game.  It is 
 * connected to at most four other rooms via exits.  The exits are labelled
 * north, east, south, west.  For each direction, the room stores a reference
 * to the neighbouring room, or null if there is no exit in that direction.
 */

import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class Room {
	private String roomName;
	private String description;
	private String conditionMessage;
	private HashMap<String, Room> exits; // stores exits of this room.
	private Inventory roomInventory;
	private Inventory characterInventory;
	private Inventory roomConditions;
	private NonPlayableCharacter npc;
	private ArrayList<Enemy> roomEnemies;

	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 */
	public Room(String description) {
		this.description = description;
		exits = new HashMap<String, Room>();
	}

	public Room(Inventory characterInventoryInput) {
		// default constructor.
		roomName = "DEFAULT ROOM";
		description = "DEFAULT DESCRIPTION";
		exits = new HashMap<String, Room>();
		roomInventory = new Inventory();
		characterInventory = characterInventoryInput;
		roomEnemies = new ArrayList<Enemy>();
		roomConditions = new Inventory();
		conditionMessage = new String();
	}

	public void setExit(char direction, Room r) throws Exception {
		String dir = "";
		switch (direction) {
		case 'E':
			dir = "east";
			break;
		case 'W':
			dir = "west";
			break;
		case 'S':
			dir = "south";
			break;
		case 'N':
			dir = "north";
			break;
		case 'U':
			dir = "up";
			break;
		case 'D':
			dir = "down";
			break;
		default:
			throw new Exception("Invalid Direction");

		}

		exits.put(dir, r);
	}

	/**
	 * Define the exits of this room. Every direction either leads to another
	 * room or is null (no exit there).
	 */
	public void setExits(Room north, Room east, Room south, Room west, Room up, Room down) {
		if (north != null)
			exits.put("north", north);
		if (east != null)
			exits.put("east", east);
		if (south != null)
			exits.put("south", south);
		if (west != null)
			exits.put("west", west);
		if (up != null)
			exits.put("up", up);
		if (up != null)
			exits.put("down", down);

	}

	/**
	 * Return the description of the room (the one that was defined in the
	 * constructor).
	 */
	public String shortDescription() {
		return "Room: " + roomName + "\n\n" + description;
	}

	/**
	 * Return a long description of this room, on the form: You are in the
	 * kitchen. Exits: north west
	 */
	public String longDescription() {
		return "\nYou are in the " + roomName + ".\n" + description + enemyString() + npString()
				+ characterItemsString() + itemString() + gemString() + exitString();
	}

	private String gemString() {
		if (characterInventory.getNumGems() == 1) {
			return ("\nCurrently, you have 1 gem.");
		} else {
			return ("\nCurrently, you have " + characterInventory.getNumGems() + " gems.");
		}
	}

	private String npString() {
		if (npc != null) {
			return ("\nYou see " + npc.getCharacterName()
					+ " in this room. To interact with him, write \"talk <name>\".");
		} else {
			return "";
		}
	}

	private String enemyString() {
		String returnString = "";
		String enemies = "";
		if (roomEnemies.size() != 0) {
			returnString += "You encounter ";
			returnString += roomEnemies.get(0).getDescription() + " " + roomEnemies.get(0).getCharacterName();
			enemies += roomEnemies.get(0).getCharacterName();
			for (int i = 1; i < roomEnemies.size(); i++) {
				returnString += ", " + roomEnemies.get(i).getDescription() + " "
						+ roomEnemies.get(i).getCharacterName();
				enemies += ", " + roomEnemies.get(i).getCharacterName();
			}
			returnString += ". You must defeat " + enemies
					+ " to pick up any items in this room. To attack him, type \"attack <name>\".";

			return ("\n" + returnString);
		} else {
			return ("");
		}
	}

	private String characterItemsString() {
		if (characterInventory.getNumItems() != 0) {
			return ("\nYou presently have " + characterInventory.print() + " and " + characterInventory.getWallet()
					+ " Iranian Rials. Weight: (" + characterInventory.getWeight() + "/"
					+ (int) characterInventory.getMaxWeight() + ")");
		} else {
			return ("");
		}
	}

	/**
	 * Return a string describing the room's items
	 */

	private String itemString() {
		if (roomInventory.getNumItems() != 0) {
			return ("\nIn the " + roomName + ", you find " + roomInventory.print()
					+ ". To take an item, write \"take <item>\".");
		} else {
			return ("");
		}
	}

	@SuppressWarnings("rawtypes")
	private String exitString() {
		String returnString = "\nYou can exit to the: ";
		Set keys = exits.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext();)
			returnString += iter.next() + ", ";
		return returnString.substring(0, returnString.length() - 2);
	}

	/**
	 * Return the room that is reached if we go from this room in direction
	 * "direction". If there is no room in that direction, return null.
	 */
	public Room nextRoom(String direction) {
		return (Room) exits.get(direction);
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public void setRoomInventory(Inventory roomItems) {
		this.roomInventory = roomItems;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRoomEnemies(ArrayList<Enemy> enemyList) {
		this.roomEnemies = enemyList;
	}

	public ArrayList<Enemy> getRoomEnemies() {
		return roomEnemies;
	}

	public Inventory getRoomInventory() {
		return roomInventory;
	}

	public void setNPC(NonPlayableCharacter nonPlayableCharacter) {
		this.npc = nonPlayableCharacter;
	}

	/*
	 * public void setNPCtoNull() { this.npc = null; }
	 */

	public NonPlayableCharacter getNPC() {
		return this.npc;
	}

	public void setConditions(Inventory roomConditionsInp) {
		this.roomConditions = roomConditionsInp;
	}

	public Boolean checkConditions() {
		if (roomConditions == null || roomConditions.getNumItems() == 0) {
			return true;
		} else {
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

	public String conditionMessage() {
		return this.conditionMessage;
	}

	public void setConditionMessage(String conditionMessageInp) {
		conditionMessage = conditionMessageInp;
	}

	public Inventory getConditions() {
		return this.roomConditions;
	}
}