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

	public Character mainCharacter = new Character("", 100, 100);
	private Parser parser;
	private Room currentRoom;
	private Sword mainSword;
	private Inventory characterInventory = new Inventory();
	private Inventory roomInventory;
	private Armour mainArmour = new Armour();
	boolean finished;

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
					if (roomItems[s].equals(" None-0")) {
						s += 1;
					} else if (roomItems[s].substring(0, roomItems[s].length() - 2).equals("Sword")) {
						roomInventory.addItem(new Sword());
					} else if (roomItems[s].substring(0, roomItems[s].length() - 2).equals("Armour")) {
						roomInventory.addItem(new Armour());
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
					if (roomEnemies[t].equals(" None-0")) {
						t += 1;
					} else if (roomEnemies[t].split("-")[0].trim().equals("Yute")) {
						enemyList.add(new Yute(roomEnemies[t].split("-")[1].trim()));
					} else if (roomEnemies[t].split("-")[0].trim().equals("WasteMansYute")) {
						enemyList.add(new WasteMansYute(roomEnemies[t].split("-")[1].trim()));
					} else {
						enemyList.add(new HypeBeastYute(roomEnemies[t].split("-")[1].trim()));
					}
				}

				room.setRoomEnemies(enemyList);

				String roomNPC = roomScanner.nextLine().split(":")[1].trim();

				if (roomNPC.equals("ShayanShakeri")) {
					room.setNPC(new ShayanShakeriNezhad());
				}

				if (roomNPC.equals("Salesi")) {
					room.setNPC(new ShayanSalesi());
				}

				if (roomNPC.equals("RyanAbhary")) {
					room.setNPC(new RyanAbhary());
				}

				if (roomNPC.equals("Rodin")) {
					room.setNPC(new Rodin());
				}

				if (roomNPC.equals("Andrei")) {
					room.setNPC(new Andrei());
				}

				if (roomNPC.equals("Daniel")) {
					room.setNPC(new Rodin());
				}

				if (roomNPC.equals("RyanMak")) {
					room.setNPC(new RyanMak());
				}

				if (roomNPC.equals("DesLauriers")) {
					room.setNPC(new DesLauriers());
				}

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
		System.out.print("Welcome to Road to Zion. Please enter your name: ");
		String name = keyboard.nextLine();
		mainCharacter.setCharacterName(textCheck(name));
		System.out.println("Please press enter after each line.");
		keyboard.nextLine();
		intro(name);
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
			System.out.print("Please enter a legit name (one name only): ");
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
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
		System.out.print("\n*After waking up* \n-Wh-");
		System.out.print("\n-Where am I?");
		scanner.nextLine();
		System.out.print("KD: Assalam-u-alaikum. You are in Iran.");
		scanner.nextLine();
		System.out.print("-Oh yeah, that's right. Wait... who are you?");
		scanner.nextLine();
		System.out.print("KD: My name is Kevin DesLauriers. I am an Iranian man.");
		scanner.nextLine();
		System.out.print("-What am I doing here with you?");
		scanner.nextLine();
		System.out.print("KD: Look man you're the one who just woke up in my alley; I don't even know you.");
		scanner.nextLine();
		System.out.print("-Oh I apologize, my name is " + name + ".");
		scanner.nextLine();
		System.out.print("KD: Wait... What's your name?");
		scanner.nextLine();
		System.out.print("-It's " + name + ".");
		scanner.nextLine();
		System.out.print("KD: *eyes widen* And your last name? ");
		System.out.print("Please enter your last name: ");
		String last = textCheck(scanner.nextLine());
		System.out.print("KD: " + name + " " + last + "?! How is this possible?!");
		scanner.nextLine();
		System.out.print("KD: My god. " + name + " " + last + " in my midst. I am truly honored.");
		scanner.nextLine();
		System.out.print("-What dude?");
		scanner.nextLine();
		System.out.print(
				"KD: You are the descendant of Mehdi Bao Tran, the first emperor of Iran, and the first man to travel the Road to Zion.");
		scanner.nextLine();
		System.out.print(
				"KD: Upon returning from the mystical land of Zion, Bao brought back 5 gems. He hid the gems, each with a different quality, in the Shah mosque.");
		scanner.nextLine();
		System.out.print("KD: It was rumored that only " + name + " " + last + " would be able to found them.");
		scanner.nextLine();
		System.out.print(
				"KD: When you have all 5 gems, you will achieve the unthinkable. You will have wealth, health, peace, happiness, and most importantly...");
		scanner.nextLine();
		System.out.print("KD: You will get a 5 in AP Compputer Science!");
		scanner.nextLine();
		System.out.print("KD: In the Shah mosque, you shall find these gems, but not without significant obstacles.");
		scanner.nextLine();
		System.out.print("KD: May your adventure begin.");
		scanner.nextLine();
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
		if (commandWord.equalsIgnoreCase("help"))
			printHelp();
		else if (commandWord.equalsIgnoreCase("go"))
			goRoom(command);
		else if (commandWord.equalsIgnoreCase("take"))
			takeItem(command);
		else if (commandWord.equalsIgnoreCase("attack"))
			return attackEnemy(command, currentRoom);
		else if (commandWord.equalsIgnoreCase("talk")) {
			talk(command);
		} else if (commandWord.equalsIgnoreCase("quit")) {
			if (command.hasSecondWord())
				System.out.println("Quit what?");
			else
				return true; // signal that we want to quit
		} else if (commandWord.equalsIgnoreCase("use")) {
			if (command.hasSecondWord() == false)
				System.out.println("Use what?");
			else
				use(command.getSecondWord());
		}
		return false;
	}

	// implementations of user commands:
	private void talk(Command command) {
		String person = command.getSecondWord();
		if (currentRoom.getNPC() != null && currentRoom.getNPC().getCharacterName().equalsIgnoreCase(person)
				&& currentRoom.getNPC().getCharacterHealth() > 0) {
			currentRoom.getNPC().talk(characterInventory);
		} else {
			System.out.println(person + " is not in this room.");
		}
	}

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		System.out.println("You are lost. You are alone. You wander.");
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
		if (currentRoom.getRoomEnemies().size() > 0) {
			System.out.println(currentRoom.getRoomEnemies().get(0).getDescription() + " "
					+ currentRoom.getRoomEnemies().get(0).getCharacterName()
					+ " is not letting you take this item. You must defeat him first.");
			return;
		}

		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know what to pick up...
			System.out.println("Take what?");
			return;
		}

		String object = command.getSecondWord().toLowerCase();
		int itemIndex = -1;

		for (int i = 0; i < currentRoom.getRoomInventory().getNumItems(); i++) {
			if (currentRoom.getRoomInventory().getItem(i).getDescription().equalsIgnoreCase(object)) {
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
		} else if (currentRoom.getRoomInventory().getItem(itemIndex).getDescription().equals("armour")) {
			mainArmour = new Armour();
			characterInventory.addItem(currentRoom.getRoomInventory().getItem(itemIndex));
			currentRoom.getRoomInventory().removeItem(currentRoom.getRoomInventory().getItem(itemIndex));
			System.out.println("Done. Now?");
		} else {
			characterInventory.addItem(currentRoom.getRoomInventory().getItem(itemIndex));
			currentRoom.getRoomInventory().removeItem(currentRoom.getRoomInventory().getItem(itemIndex));
			System.out.println("Done. Now?");
		}
	}

	private boolean attackEnemy(Command command, Room currentRoom) {
		boolean enemyCrit = false;
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know who to attack
			System.out.println("Attack who?");
			return false;
		}
		String enemy = command.getSecondWord();
		if (currentRoom.getNPC() != null && enemy.equalsIgnoreCase(currentRoom.getNPC().getCharacterName())) {
			if (mainSword != null) {

				int charDamage = mainSword.getPower();
				int enemyDamage = currentRoom.getNPC().getWeapon().getPower();
				int crit = (int) (Math.random() * 100);
				for (int i = 0; i < mainSword.getCritChance(); i++) {
					if (i == crit) {
						charDamage *= 3;
						System.out.println("CRITICAL STRIKE!");
						if (currentRoom.getNPC().getCharacterName().equals("DesLauriers")) {
							int gemCount = 0;
							for (int j = 0; j < characterInventory.getNumItems(); j++) {
								if (characterInventory.getItem(j).getDescription().equals("gem"))
									gemCount++;
							}
							if (gemCount < 5)
								charDamage = 0;
							System.out.println("Delaurier: HAH YOU DO NOT HAVE THE GEMS. I AM INVINCLIBLE.");
						}
					}
				}
				charDamage = charDamage - currentRoom.getNPC().getCharacterArmor();
				for (int j = 0; j < currentRoom.getNPC().getWeapon().getCritChance(); j++) {
					if (j == crit) {
						enemyDamage *= 3;
						enemyCrit = true;
					}
				}
				enemyDamage = enemyDamage - mainArmour.getProtection();
				currentRoom.getNPC().setCharacterHealth(currentRoom.getNPC().getCharacterHealth() - charDamage);
				if (currentRoom.getNPC().getCharacterHealth() > 0) {
					mainCharacter.setCharacterHealth(mainCharacter.getCharacterHealth() - enemyDamage);
					if (enemyCrit == true) {
						System.out.println("ENEMY CRITICAL STRIKE");
					}
				}
			} else {
				System.out.println("You don't have a sword yet!");
				// if currentRoom.getNPC().getCharacterHealth() <= 0)

			}
			if (currentRoom.getNPC().getCharacterHealth() <= 0) {
				currentRoom.getNPC().deathPhrase();
				System.out.println(currentRoom.getNPC().getCharacterName() + " has been defeated.");
				System.out.print(currentRoom.getNPC().getCharacterName() + " has dropped ");
				for (int k = 0; k < currentRoom.getNPC().getNpcInventory().size(); k++) {
					currentRoom.getRoomInventory().addItem(currentRoom.getNPC().getNpcInventory().get(k));
					System.out.print(currentRoom.getNPC().getNpcInventory().get(k).getDescription());
					if (k < currentRoom.getNPC().getNpcInventory().size() - 1) {
						System.out.print(", and ");
					} else {
						System.out.print(" ");
					}
				}
				System.out.println();
				currentRoom.setNPC(null);
			} else if (mainCharacter.getCharacterHealth() <= 0) {
				System.out.println("Sorry, you have lost. Catch this L.");
				return true;
			} else {
				if (mainSword != null) {
					System.out.println(currentRoom.getNPC().getCharacterName() + " attacked you back!");
					System.out.print("Your Health: " + mainCharacter.getCharacterHealth() + " |");
					for (int i = 0; i < mainCharacter.getCharacterHealth() / 5; i++) {
						System.out.print("-");
					}
					for (int j = mainCharacter.getCharacterHealth() / 5; j < 20; j++) {
						System.out.print(" ");
					}
					System.out.println("|");
					System.out.println(currentRoom.getNPC().getCharacterName() + "'s Health: "
							+ currentRoom.getNPC().getCharacterHealth());
				}
			}
			return false;

		} else {
			int enemyIndex = -1;

			for (int i = 0; i < currentRoom.getRoomEnemies().size(); i++) {
				if (currentRoom.getRoomEnemies().get(i).getCharacterName().equalsIgnoreCase(enemy)) {
					enemyIndex = i;
				}
			}

			if (enemyIndex == -1) {
				System.out.println("There is no such enemy...");
				return false;
			} else {
				if (mainSword != null) {
					int charDamage = mainSword.getPower();
					int enemyDamage = currentRoom.getRoomEnemies().get(enemyIndex).getWeapon().getPower();
					int crit = (int) (Math.random() * 100);
					for (int i = 0; i < mainSword.getCritChance(); i++) {
						if (i == crit) {
							charDamage *= 3;
							System.out.println("CRITICAL STRIKE!");
						}
					}
					charDamage = charDamage - currentRoom.getRoomEnemies().get(enemyIndex).getCharacterArmor();

					for (int j = 0; j < currentRoom.getRoomEnemies().get(enemyIndex).getWeapon().getCritChance(); j++) {
						if (j == crit) {
							enemyDamage *= 3;
							enemyCrit = true;
						}
					}
					enemyDamage = enemyDamage - mainArmour.getProtection();
					currentRoom.getRoomEnemies().get(enemyIndex).setCharacterHealth(
							currentRoom.getRoomEnemies().get(enemyIndex).getCharacterHealth() - charDamage);
					if (currentRoom.getRoomEnemies().size() > 0
							&& currentRoom.getRoomEnemies().get(enemyIndex).getCharacterHealth() > 0) {
						mainCharacter.setCharacterHealth(mainCharacter.getCharacterHealth() - enemyDamage);
						if (enemyCrit != false) {
							System.out.println("ENEMY CRITICAL STRIKE!");
						}
					}
				} else {
					System.out.println("You don't have a sword yet!");
				}

				if (currentRoom.getRoomEnemies().get(enemyIndex).getCharacterHealth() <= 0) {
					currentRoom.getRoomEnemies().get(enemyIndex).deathPhrase();
					System.out.println(
							currentRoom.getRoomEnemies().get(enemyIndex).getCharacterName() + " has been defeated.");
					System.out.print(currentRoom.getRoomEnemies().get(enemyIndex).getCharacterName() + " has dropped ");
					for (int k = 0; k < currentRoom.getRoomEnemies().get(enemyIndex).getEnemyInventory()
							.getNumItems(); k++) {
						currentRoom.getRoomInventory()
								.addItem(currentRoom.getRoomEnemies().get(enemyIndex).getWeapon());
						System.out.print(currentRoom.getRoomEnemies().get(enemyIndex).getEnemyInventory().getItem(k)
								.getDescription());
						if (k < currentRoom.getNPC().getNpcInventory().size() - 1) {
							System.out.print(", and ");
						} else {
							System.out.print(" ");
						}
					}
					System.out.println();
					currentRoom.getRoomEnemies().remove(enemyIndex);

				} else if (mainCharacter.getCharacterHealth() <= 0) {
					System.out.println("Sorry, you have lost. Catch this L.");
					return true;

				} else {
					if (mainSword != null) {
						System.out.println(currentRoom.getRoomEnemies().get(enemyIndex).getCharacterName()
								+ " attacked you back!");
						System.out.print("Your Health: " + mainCharacter.getCharacterHealth() + " |");
						for (int i = 0; i < mainCharacter.getCharacterHealth() / 5; i++) {
							System.out.print("-");
						}
						for (int j = mainCharacter.getCharacterHealth() / 5; j < 20; j++) {
							System.out.print(" ");
						}
						System.out.println("|");
						System.out.println(currentRoom.getRoomEnemies().get(enemyIndex).getCharacterName()
								+ "'s Health: " + currentRoom.getRoomEnemies().get(enemyIndex).getCharacterHealth());
					}
				}
			}
			return false;

		}
	}

	public void use(String secondWord) {
		// checks to see if the item the player wants to use in their inventory
		boolean used = false;
		int inventorySize = characterInventory.getNumItems();
		for (int i = 0; i < inventorySize; i++) {
			Item theItem = characterInventory.getInventory().get(i);
			String itemName = theItem.getDescription().toLowerCase();

			if (itemName.equalsIgnoreCase(secondWord)) {
				used = true;
				// checks the type of the item
				if (theItem instanceof Food) {
					mainCharacter.setCharacterHealth(
							((Food) theItem).getHealthRestored() + mainCharacter.getCharacterHealth());
					if (mainCharacter.getCharacterHealth() > mainCharacter.getCharacterHealthMax())
						mainCharacter.setCharacterHealth(mainCharacter.getCharacterHealthMax());
					System.out.println("Your health is now " + mainCharacter.getCharacterHealth() + "hp");
					characterInventory.removeItem(theItem);
					break;
				} else if (theItem instanceof WeaponAttachment) {
					mainSword.setPower(((WeaponAttachment) theItem).getAttackPowerAdded() + mainSword.getPower());
					mainSword.setCritChance(
							((WeaponAttachment) theItem).getCritChanceAdded() + mainSword.getCritChance());
					mainSword.setDescription(theItem.getDescription() + mainSword.getDescription());
					System.out.println("Your sword has become the " + mainSword.getDescription() + ".");
					characterInventory.removeItem(theItem);
					break;
				} else if (theItem instanceof ArmourAttachment) {
					mainArmour
							.setProtection(((ArmourAttachment) theItem).getArmourAdded() + mainArmour.getProtection());
					mainArmour.setDescription(theItem.getDescription() + mainArmour.getDescription());
					System.out.println("Your armour has become the " + mainArmour.getDescription() + ".");
					characterInventory.removeItem(theItem);
					break;
				} else if (theItem instanceof Pockets) {
					characterInventory
							.setMaxWeight(((Pockets) theItem).getSpaceAdded() + characterInventory.getMaxWeight());
					System.out.println("Your armour has become the " + mainArmour.getDescription() + ".");
					characterInventory.removeItem(theItem);
					break;
				} else
					System.out.println("You cannot use that.");
				// add pockets as usable
			}

		}
		if (used == false)
			System.out.println("You do not have that in your inventory.");
	}
}
