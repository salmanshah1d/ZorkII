package com.bayviewglen.zork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.omg.CORBA.SystemException;

/**
 * Class Game - the main class of the "Zork" game.
 *
 * Author: Michael Kolling Version: 1.1 Date: March 2000
 * 
 * This class is the main class of the "Zork" application. Zork is a very
 * simple, text based adventure game. Users can walk around some scenery. That's
 * all. It should really be extended to make it more interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * routine.
 * 
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates the commands that
 * the parser returns.
 */

class Game {

	public Character mainCharacter = new Character();
	private Parser parser;
	private Room currentRoom;
	private Sword mainSword;
	private Inventory characterInventory = new Inventory();
	private Inventory roomInventory;
	boolean finished = false;
	// This is a MASTER object that contains all of the rooms and is easily
	// accessible.
	// The key will be the name of the room -> no spaces (Use all caps and
	// underscore -> Great Room would have a key of GREAT_ROOM
	// In a hashmap keys are case sensitive.
	// masterRoomMap.get("GREAT_ROOM") will return the Room Object that is the
	// Great Room (assuming you have one).
	private HashMap<String, Room> masterRoomMap;

	private void initRooms(String fileName) throws Exception {
		masterRoomMap = new HashMap<String, Room>();
		Scanner roomScanner;
		try {
			HashMap<String, HashMap<String, String>> exits = new HashMap<String, HashMap<String, String>>();
			roomScanner = new Scanner(new File(fileName));
			while (roomScanner.hasNext()) {
				Room room = new Room(characterInventory);
				// Read the Name
				String roomName = roomScanner.nextLine();
				room.setRoomName(roomName.split(":")[1].trim());
				// Read the Description
				String roomDescription = roomScanner.nextLine();
				room.setDescription(roomDescription.split(":")[1].replaceAll("<br>", "\n").trim());
				// Read the Exits
				String roomExits = roomScanner.nextLine();
				// An array of strings in the format E-RoomName
				String[] rooms = roomExits.split(":")[1].split(",");
				HashMap<String, String> temp = new HashMap<String, String>();
				for (String s : rooms) {
					temp.put(s.split("-")[0].trim(), s.split("-")[1]);
				}

				exits.put(roomName.substring(10).trim().toUpperCase().replaceAll(" ", "_"), temp);

				// adds room items Inventory
				String[] roomItems = roomScanner.nextLine().split(":")[1].split(",");
				roomInventory = new Inventory();
				// An array of strings in the format ItemName-ItemWeight
				for (int s = 0; s < roomItems.length; s++) {
					if (roomItems[s].equals("None-0")) {
						s += 1;
					} else if (roomItems[s].substring(0, roomItems[s].length() - 2).equals("Sword")) {
						roomInventory.addItem(new Weapon(roomItems[s].split("-")[0].trim(),
								Integer.parseInt(roomItems[s].split("-")[1].trim()), 25));
					} else {
						roomInventory.addItem(new Item(roomItems[s].split("-")[0].trim(),
								Integer.parseInt(roomItems[s].split("-")[1].trim())));
					}
				}
				room.setRoomInventory(roomInventory);

				// adds room items ArrayList
				String[] roomEnemies = roomScanner.nextLine().split(":")[1].split(",");
				// An array of strings in the format ItemName-ItemWeight
				ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
				for (int t = 0; t < roomEnemies.length; t++) {
					if (roomEnemies[t].equals("None-0")) {
						t += 1;
					} else {
						if (roomEnemies[t].split("-")[0].trim().equals("Yute")) {
							enemyList.add(new Yute(roomEnemies[t].split("-")[1].trim()));
						} else if (roomEnemies[t].split("-")[0].trim().equals("WasteMansYute")) {
							enemyList.add(new WasteMansYute(roomEnemies[t].split("-")[1].trim()));
						} else {
							enemyList.add(new HypeBeastYute(roomEnemies[t].split("-")[1].trim()));
						}
					}
				}
				room.setRoomEnemies(enemyList);

				// This puts the room we created (Without the exits in the
				// masterMap)
				masterRoomMap.put(roomName.toUpperCase().substring(10).trim().replaceAll(" ", "_"), room);

			}

			for (String key : masterRoomMap.keySet()) {
				Room roomTemp = masterRoomMap.get(key);
				HashMap<String, String> tempExits = exits.get(key);
				for (String s : tempExits.keySet()) {
					// s = direction
					// value is the room.
					String roomName2 = tempExits.get(s.trim());
					Room exitRoom = masterRoomMap.get(roomName2.toUpperCase().replaceAll(" ", "_"));
					roomTemp.setExit(s.trim().charAt(0), exitRoom);
				}
			}
			roomScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the game and initialise its internal map.
	 */
	public Game() {
		try {
			initRooms("data/rooms.dat");
			currentRoom = masterRoomMap.get("ANCIENT_KEY_HOLDER_ROOM");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parser = new Parser();
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.

		finished = false;
		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}

		System.out.println("Thank you for playing. Good bye.");
		// >>>>>>> branch 'master' of https://github.com/salmanshah1d/ZorkII.git
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Hello! What's your name? ");
		String name = keyboard.nextLine();

		name = textCheck(name);
		System.out.println("(Heads up: press enter after each line when you're done reading.)");
		// intro(name);
		System.out.println(currentRoom.longDescription());
	}

	private String textCheck(String text) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		boolean valid = true;
		Scanner keyboard = new Scanner(System.in);
		for (int i = 0; i < text.length() && valid == true; i++) {
			if (alphabet.indexOf(text.charAt(i)) < 0) {
				valid = false;
			}
		}
		while (valid == false || text.length() == 0) {
			System.out.print("Please enter a legit name boi: ");
			text = keyboard.nextLine();
			valid = true;
			for (int i = 0; i < text.length() && valid == true; i++) {
				if (alphabet.indexOf(text.charAt(i)) < 0) {
					valid = false;
				}
			}
		}
		return text;
		// >>>>>>> branch 'master' of https://github.com/salmanshah1d/ZorkII.git
	}

	private void intro(String name) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nHello, " + name
				+ ", you find yourself in the Shah Mosque in Isfahan, Iran, in the south side of Naghsh-e Jahan Square.");
		scanner.nextLine();
		System.out.print(
				"As a Pakistani tourist from China, when walking through a spice market in the blistering heat of the Iranian sun, you");
		scanner.nextLine();
		System.out.print("mistakenly stumbled into an alley where you learned an ancient family secret.");
		scanner.nextLine();
		System.out.print(
				"There, you encountered a local Iranian man named Kevin DLau whom'st tells you that your family originated in Iran, 800 years ago.");
		scanner.nextLine();
		System.out.print(
				"He explains that your ancestor, Mehdi Bao Tran hid a total of 5 gems, each with a different quality, in the Shah mosque.");
		scanner.nextLine();
		System.out.print(
				"He explains that fate led you to the dark alley on that very day. You are meant to collect the gems.");
		scanner.nextLine();
		System.out.print(
				"When you have all 5 gems, you will achieve the unthinkable. You will have wealth, health, peace, happiness, and most importantly...");
		scanner.nextLine();
		System.out.print("You ");
		delay(0.25);
		System.out.print("will ");
		delay(0.25);
		System.out.print("get ");
		delay(0.25);
		System.out.print("a ");
		delay(0.25);
		System.out.print("5 ");
		delay(0.25);
		System.out.print("in ");
		delay(0.25);
		System.out.println("AP Computer Science A!");
		scanner.nextLine();
		System.out.print("In the Shah mosque, you shall find these gems, but not without significant obstacles.");
		scanner.nextLine();
		System.out.print("May ");
		delay(1);
		System.out.print("your ");
		delay(1);
		System.out.print("adventure ");
		delay(1);
		System.out.print("begin. ");
		scanner.nextLine();
		// >>>>>>> branch 'master' of https://github.com/salmanshah1d/ZorkII.git
	}

	private void delay(double num) {
		num *= 1000;
		try {
			Thread.sleep((int) num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Given a command, process (that is: execute) the command. If this command
	 * ends the game, true is returned, otherwise false is returned.
	 */
	private boolean processCommand(Command command) {
		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help"))
			printHelp();
		else if (commandWord.equals("go"))
			goRoom(command);
		else if (commandWord.equals("take"))
			takeItem(command);
		else if (commandWord.equals("attack"))
			attackEnemy(command);
		/*
		 * else if (commandWord.equals("talk")) talk(command);
		 */
		else if (commandWord.equals("quit")) {
			if (command.hasSecondWord())
				System.out.println("Quit what?");
			else
				return true; // signal that we want to quit
		} else if (commandWord.equals("eat")) {
			System.out.println("Do you really think you should be eating at a time like this?");
		} else if (commandWord.equals("use")) {
			if (command.hasSecondWord() == false)
				System.out.println("Use what?");
			else
				use(command.getSecondWord());
		}
		return false;
	}

	// implementations of user commands:

	/*
	 * private void talk(Command command) { if
	 * (currentRoom.getNonPlayableCharacter.getName().equals(command)){
	 * currentRoom.getNonPlayableCharacter.talk(); } else {
	 * System.out.println(command + " is not in this room."); } }
	 */

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		System.out.println("You are lost. You are alone. You wander.");
		// >>>>>>> branch 'master' of https://github.com/salmanshah1d/ZorkII.git
		System.out.println("Your command words are:");
		parser.showCommands();
	}

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();

		// Try to leave current room.
		Room nextRoom = currentRoom.nextRoom(direction);

		if (nextRoom == null)
			System.out.println("There is no door!");
		else {
			currentRoom = nextRoom;
			System.out.println(currentRoom.longDescription());
		}
	}

	private void takeItem(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know what to pick up...
			System.out.println("Take what?");
			return;
		}

		String object = command.getSecondWord();

		int itemIndex = -1;

		for (int i = 0; i < currentRoom.getRoomInventory().getNumItems(); i++) {
			if (currentRoom.getRoomInventory().getItem(i).getDescription().equals(object)) {
				itemIndex = i;
			}
		}
		if (itemIndex == -1) {
			System.out.println("There is no such item...");
			return;
		} else if (currentRoom.getRoomInventory().getItem(itemIndex).getDescription().equals("sword")) {
			mainSword = new Sword();
			characterInventory.addItem(currentRoom.getRoomInventory().getItem(itemIndex));
			currentRoom.getRoomInventory().removeItem(currentRoom.getRoomInventory().getItem(itemIndex));
			System.out.println("Done. Now?");
		} else {
			characterInventory.addItem(currentRoom.getRoomInventory().getItem(itemIndex));
			currentRoom.getRoomInventory().removeItem(currentRoom.getRoomInventory().getItem(itemIndex));
			System.out.println("Done. Now?");
		}
	}

	private void attackEnemy(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know who to attack
			System.out.println("Attack who?");
			return;
		}
		String enemy = command.getSecondWord();
		int enemyIndex = -1;

		for (int i = 0; i < currentRoom.getRoomEnemies().size(); i++) {
			if (currentRoom.getRoomEnemies().get(i).getCharacterName().equals(enemy)) {
				enemyIndex = i;
			}
		}

		if (enemyIndex == -1) {
			System.out.println("There is no such enemy...");
			return;
		} else {
			currentRoom.getRoomEnemies().get(enemyIndex).setCharacterHealth(
					currentRoom.getRoomEnemies().get(enemyIndex).getCharacterHealth() - mainSword.getPower());
			mainCharacter.setCharacterHealth(mainCharacter.getCharacterHealth()
					- currentRoom.getRoomEnemies().get(enemyIndex).getWeapon().getPower());
			System.out.println(currentRoom.getRoomEnemies().get(enemyIndex).getCharacterName() + " attacked you back!");
			if (currentRoom.getRoomEnemies().get(enemyIndex).getCharacterHealth() < 0) {
				System.out.println(currentRoom.getRoomEnemies().get(enemyIndex).getCharacterName() + " is dead.");
				currentRoom.getRoomEnemies().remove(enemyIndex);
			} else if (mainCharacter.getCharacterHealth() < 0) {
				finished = true;
			} else { //indicates that game continues
				System.out.print("Your Health: " + mainCharacter.getCharacterHealth() + " |");
				for (int i = 0; i < mainCharacter.getCharacterHealth() / 5; i++) {
					System.out.print("-");
				}
				for (int j = mainCharacter.getCharacterHealth() / 5; j < 20; j++) {
					System.out.print(" ");
				}
				System.out.println("|");
				System.out.println(currentRoom.getRoomEnemies().get(enemyIndex).getCharacterName() + "'s Health: "
						+ currentRoom.getRoomEnemies().get(enemyIndex).getCharacterHealth());
			}
		}
	}

	public void use(String secondWord) {
		// checks to see if the item the player wants to use in their inventory
		int inventorySize = characterInventory.getNumItems();
		for (int i = 0; i < inventorySize; i++) {
			Item theItem = characterInventory.getInventory().get(i);
			String itemName = theItem.getDescription();

			if (itemName.equals(secondWord)) {

				// checks the type of the item
				if (theItem instanceof Food) {
					mainCharacter.setCharacterHealth(
							((Food) theItem).getHealthRestored() + mainCharacter.getCharacterHealth());
					if (mainCharacter.getCharacterHealth() > mainCharacter.getCharacterHealthMax())
						mainCharacter.setCharacterHealth(mainCharacter.getCharacterHealthMax());
				} else if (theItem instanceof WeaponAttachment) {

				}
			}

		}

	}
}