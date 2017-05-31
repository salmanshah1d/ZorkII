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
	private HashMap<String, Room> exits; // stores exits of this room.
	private Inventory roomInventory;
	private Inventory characterInventory;
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
		return "\nRoom: " + roomName + "\n\n" + description + enemyString() + npString() + characterItemsString()
				+ itemString() + "\n" + exitString();
	}

	private String npString() {
		if (npc != null) {
			return ("You see " + npc.getName() + " in this room. To interact with him, write \"talk <name>\".\n");
		} else {
			return "";
		}
	}

	private String enemyString() {
		String returnString = "";
		String enemies = "";
		if (roomEnemies.size() != 0) {
			returnString += "\nYou encounter ";
			returnString += roomEnemies.get(0).getDescription() + " " + roomEnemies.get(0).getCharacterName();
			enemies += roomEnemies.get(0).getCharacterName();
			for (int i = 1; i < roomEnemies.size() - 1; i++) {
				returnString += ", " + roomEnemies.get(i).getDescription() + " "
						+ roomEnemies.get(i).getCharacterName();
				enemies += ", " + roomEnemies.get(i).getCharacterName();
			}
			if (roomEnemies.size() > 1) {
				returnString += roomEnemies.get(roomEnemies.size() - 1).getDescription() + " "
						+ roomEnemies.get(roomEnemies.size() - 1).getCharacterName();
				enemies += roomEnemies.get(roomEnemies.size() - 1).getCharacterName();
			}
			returnString += ". You must defeat " + enemies
					+ " to pick up any items in this room. To attack him, type \"attack <name>\".\n";
			return (returnString);
		} else {
			return ("");
		}
	}

	private String characterItemsString() {
		if (characterInventory.getNumItems() != 0) {
			return ("\nYou presently have " + characterInventory.print() + ".");
		} else {
			return ("");
		}
	}

	/**
	 * Return a string describing the room's items
	 */

	private String itemString() {
		if (characterInventory.getNumItems() != 0) {
			return ("\nYou presently have " + characterInventory.print() + ".");
		} else {
			return ("");
		}
	}

	private String exitString() {
		String returnString = "Room Exits:";
		Set keys = exits.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext();)
			returnString += " " + iter.next();
		return returnString;
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
}