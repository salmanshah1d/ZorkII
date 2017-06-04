package com.bayviewglen.zork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Game {
	// name, health, max health
	public Character mainCharacter = new Character("", 100, 100); // name will
																	// be added
																	// later
	private Parser parser;
	private Room currentRoom;
	private Weapon characterSword; // sets character's sword
	private Inventory characterInventory = new Inventory(100); // 100 is wallet
																// amount
	private Inventory roomInventory;
	private Armour characterArmour;
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

				// Set's room's name
				String roomName = roomScanner.nextLine();
				room.setRoomName(roomName.split(":")[1].trim());

				// Set's room's description
				String roomDescription = roomScanner.nextLine();
				room.setDescription(roomDescription.split(":")[1].replaceAll("<br>", "\n").trim());

				// Set's room's exits in the format RoomDirection-RoomName
				String roomExits = roomScanner.nextLine();
				String[] rooms = roomExits.split(":")[1].split(",");
				HashMap<String, String> temp = new HashMap<String, String>();
				for (String s : rooms) {
					temp.put(s.split("-")[0].trim(), s.split("-")[1]);
				}
				exits.put(roomName.substring(10).trim().toUpperCase().replaceAll(" ", "_"), temp);

				// set's room's items (if any) in the format itemName-itemWeight
				String[] roomItems = roomScanner.nextLine().split(":")[1].split(",");
				roomInventory = new Inventory();
				for (int s = 0; s < roomItems.length; s++) {
					if (roomItems[s].equals(" None-0")) {
						s += 1;
					} else if (roomItems[s].substring(0, roomItems[s].length() - 2).equals("Sword")) {
						roomInventory.addItem(new Sword());
					} else if (roomItems[s].substring(0, roomItems[s].length() - 2).equals("Armour")) {
						roomInventory.addItem(new Armour());
						characterArmour = new Armour();
					} else {
						roomInventory.addItem(new Item(roomItems[s].split("-")[0].trim(),
								Integer.parseInt(roomItems[s].split("-")[1].trim())));
					}
				}
				room.setRoomInventory(roomInventory);
				String[] roomEnemies = roomScanner.nextLine().split(":")[1].split(",");

				// sets room's enemies (if any) in the format
				// enemyType-enemyName
				ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
				for (int t = 0; t < roomEnemies.length; t++) {
					if (roomEnemies[t].equals("None-0")) {
						t += 1;
					} else if (roomEnemies[t].split("-")[0].trim().equals("Yute")) {
						enemyList.add(new Yute(roomEnemies[t].split("-")[1].trim()));
					} else if (roomEnemies[t].split("-")[0].trim().equals("WasteMansYute")) {
						enemyList.add(new WasteMansYute(roomEnemies[t].split("-")[1].trim()));
					} else if (roomEnemies[t].split("-")[0].trim().equals("HypeBeastYute")) {
						enemyList.add(new HypeBeastYute(roomEnemies[t].split("-")[1].trim()));
					}
				}
				room.setRoomEnemies(enemyList);

				// sets the room's NonPlayableCharacters (if any) in the format
				// npcName
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

				// sets the room's conditions (if any) in the format
				// requiredItem-messageToPrint
				String roomConditions = roomScanner.nextLine().split(":")[1].trim();
				room.setConditionMessage(roomConditions.split("-")[1].trim());
				String[] conditions = roomConditions.split("-")[0].trim().split(",");
				Inventory conditionList = new Inventory();

				for (int i = 0; i < conditions.length; i++) {
					if (!conditions[i].equals("None")) {
						conditionList.addItem(new Item(conditions[i]));
					}
				}
				room.setConditions(conditionList);

				// This puts the room we created (without the exits in the
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
		finished = false; // variable needed to automatically quit upon death
		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		credits();
	}

	private void credits() {
		System.out.println("-------------------------------Credits-------------------------------");
		System.out.println("-                                                                   -");
		System.out.println("-                            Justin Tran                            -");
		System.out.println("-                           Salman Shahid                           -");
		System.out.println("-                             Devin Chan                            -");
		System.out.println("-                            Mehdi Hassani                          -");
		System.out.println("-                           Nikko Northrup                          -");
		System.out.println("-                                                                   -");
		System.out.println("-Thanks for an amazing year, Mr. DesLauriers. It's been a pleasure. -");
		System.out.println("-            Love, The Genius Team Behind Road to Zion              -");
		System.out.println("-                                                                   -");
		System.out.println("---------------------------------------------------------------------");
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Welcome to Road to Zion. Please enter your name: ");
		String name = textCheck(keyboard.nextLine());
		mainCharacter.setCharacterName(name);
		System.out.println("Please press enter after each line.");
		keyboard.nextLine();
		intro(name); // method with intro story
		System.out.println(currentRoom.longDescription());
	}

	// checks if text is valid
	private String textCheck(String text) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		boolean valid = true;
		@SuppressWarnings("resource")
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
	}

	private void intro(String name) {
		@SuppressWarnings("resource")
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
		System.out.print("KD: You will get a 5 in AP Computer Science!");
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
		else if (commandWord.equalsIgnoreCase("drop"))
			dropItem(command);
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

	private void dropItem(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know what to pick up...
			System.out.println("Drop what?");
			System.out.println(currentRoom.longDescription());
			return;
		}
		
		String object = command.getSecondWord().toLowerCase();
		int itemIndex = -1;

		for (int i = 0; i < characterInventory.getNumItems(); i++) {
			if (characterInventory.getItem(i).getDescription().equalsIgnoreCase(object)) {
				itemIndex = i;
			}
		}
		
		if (itemIndex == -1) {
			System.out.println("There is no such item...");
			return;
		}
		
		currentRoom.getRoomInventory().addItem(characterInventory.getItem(itemIndex));
		characterInventory.removeItem(characterInventory.getItem(itemIndex));
		System.out.println("Done.");
		System.out.println(currentRoom.longDescription());
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

		if (nextRoom == null) {
			System.out.println("There is no door!");
			return;
		} else if (currentRoom.nextRoom(direction).checkConditions() != true) { // checks
																				// if
																				// room
																				// conditions
																				// are
																				// being
																				// met
			System.out.println("\n" + currentRoom.nextRoom(direction).conditionMessage());
			System.out.println(currentRoom.longDescription());
			return;
		} else {
			currentRoom = nextRoom;
			System.out.println(currentRoom.longDescription());
		}
	}

	// takes [item]
	private void takeItem(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know what to pick up...
			System.out.println("Take what?");
			System.out.println(currentRoom.longDescription());
			return;
		}

		if (currentRoom.getRoomEnemies().size() > 0) {
			System.out.println(currentRoom.getRoomEnemies().get(0).getDescription() + " "
					+ currentRoom.getRoomEnemies().get(0).getCharacterName()
					+ " is not letting you take this item. You must defeat him first.");
			System.out.println(currentRoom.longDescription());
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
			characterSword = new Sword();
			characterInventory.addItem(currentRoom.getRoomInventory().getItem(itemIndex));
			currentRoom.getRoomInventory().removeItem(currentRoom.getRoomInventory().getItem(itemIndex));
			System.out.println("Done.");
			System.out.println(currentRoom.longDescription());
		} else if (currentRoom.getRoomInventory().getItem(itemIndex) instanceof Weapon) {
			characterInventory.addItem(currentRoom.getRoomInventory().getItem(itemIndex));
			currentRoom.getRoomInventory().removeItem(currentRoom.getRoomInventory().getItem(itemIndex));
			System.out.println("Done.");
			System.out.println(currentRoom.longDescription());
		} else if (currentRoom.getRoomInventory().getItem(itemIndex).getDescription().equals("armour")) {
			characterArmour = new Armour();
			characterInventory.addItem(currentRoom.getRoomInventory().getItem(itemIndex));
			currentRoom.getRoomInventory().removeItem(currentRoom.getRoomInventory().getItem(itemIndex));
			System.out.println("Done.");
			System.out.println(currentRoom.longDescription());
		} else if (currentRoom.getRoomInventory().getItem(itemIndex).getDescription().equals("backpack")) {
			characterInventory.setMaxWeight(characterInventory.getMaxWeight() + 20);
			characterInventory.addItem(currentRoom.getRoomInventory().getItem(itemIndex));
			currentRoom.getRoomInventory().removeItem(currentRoom.getRoomInventory().getItem(itemIndex));
			System.out.println("Done.");
			System.out.println(currentRoom.longDescription());
		} else {
			characterInventory.addItem(currentRoom.getRoomInventory().getItem(itemIndex));
			currentRoom.getRoomInventory().removeItem(currentRoom.getRoomInventory().getItem(itemIndex));
			System.out.println("Done.");
			System.out.println(currentRoom.longDescription());
		}
	}

	// attacks [enemy]
	private boolean attackEnemy(Command command, Room currentRoom) {
		boolean enemyCrit = false;
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know who to attack
			System.out.println("Attack who?");
			System.out.println(currentRoom.longDescription());
			return false;
		}
		String enemy = command.getSecondWord();
		if (currentRoom.getNPC() != null && enemy.equalsIgnoreCase(currentRoom.getNPC().getCharacterName())) {
			if (characterSword != null && characterArmour != null) {

				int charDamage = characterSword.getPower();
				int enemyDamage = currentRoom.getNPC().getWeapon().getPower();
				int crit = (int) (Math.random() * 100);
				for (int i = 0; i < characterSword.getCritChance(); i++) {
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
							System.out.println("DesLauriers: HAH YOU DO NOT HAVE THE GEMS. I AM INVINCLIBLE.");
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
				enemyDamage = enemyDamage - characterArmour.getProtection();
				currentRoom.getNPC().setCharacterHealth(currentRoom.getNPC().getCharacterHealth() - charDamage);
				if (currentRoom.getNPC().getCharacterHealth() > 0) {
					mainCharacter.setCharacterHealth(mainCharacter.getCharacterHealth() - enemyDamage);
					if (enemyCrit == true) {
						System.out.println("ENEMY CRITICAL STRIKE");
					}
				}

				if (currentRoom.getNPC().getCharacterHealth() <= 0) {
					System.out.println();
					currentRoom.getNPC().deathPhrase();
					System.out.println();
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
					currentRoom.setNPC(null);
					System.out.println(currentRoom.longDescription());
				} else if (mainCharacter.getCharacterHealth() <= 0) {
					System.out.println("Sorry, you have lost. Catch this L.");
					return true;
				} else {
					if (characterSword != null) {
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
			} else {
				System.out.println("You need both a sword and armour to attack enemies.");
			}
			System.out.println(currentRoom.longDescription());
			return false;
		}

		else {
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
				if (characterSword != null && characterArmour != null) {
					int charDamage = characterSword.getPower();
					int enemyDamage = currentRoom.getRoomEnemies().get(enemyIndex).getWeapon().getPower();
					int crit = (int) (Math.random() * 100);
					for (int i = 0; i < characterSword.getCritChance(); i++) {
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
					enemyDamage = enemyDamage - characterArmour.getProtection();
					currentRoom.getRoomEnemies().get(enemyIndex).setCharacterHealth(
							currentRoom.getRoomEnemies().get(enemyIndex).getCharacterHealth() - charDamage);
					if (currentRoom.getRoomEnemies().size() > 0
							&& currentRoom.getRoomEnemies().get(enemyIndex).getCharacterHealth() > 0) {
						mainCharacter.setCharacterHealth(mainCharacter.getCharacterHealth() - enemyDamage);
						if (enemyCrit != false) {
							System.out.println("ENEMY CRITICAL STRIKE!");
						}
					}

					if (currentRoom.getRoomEnemies().get(enemyIndex).getCharacterHealth() <= 0) {
						System.out.println();
						currentRoom.getRoomEnemies().get(enemyIndex).deathPhrase();
						System.out.println();
						System.out.println(currentRoom.getRoomEnemies().get(enemyIndex).getCharacterName()
								+ " has been defeated.");
						System.out.print(
								currentRoom.getRoomEnemies().get(enemyIndex).getCharacterName() + " has dropped ");
						for (int k = 0; k < currentRoom.getRoomEnemies().get(enemyIndex).getEnemyInventory()
								.getNumItems(); k++) {
							currentRoom.getRoomInventory()
									.addItem(currentRoom.getRoomEnemies().get(enemyIndex).getWeapon());
							System.out.print(currentRoom.getRoomEnemies().get(enemyIndex).getEnemyInventory().getItem(k)
									.getDescription());
							if (k < currentRoom.getRoomEnemies().get(enemyIndex).getEnemyInventory().getNumItems()
									- 1) {
								System.out.print(", and ");
							} else {
								System.out.print(" ");
							}
						}
						currentRoom.getRoomEnemies().remove(enemyIndex);
						System.out.println(currentRoom.longDescription());

					} else if (mainCharacter.getCharacterHealth() <= 0) {
						System.out.println("Sorry, you have lost. Catch this L.");
						return true;

					} else {
						if (characterSword != null) {
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
							System.out.println(
									currentRoom.getRoomEnemies().get(enemyIndex).getCharacterName() + "'s Health: "
											+ currentRoom.getRoomEnemies().get(enemyIndex).getCharacterHealth());
						}
					}
				} else {
					System.out.println("You need both a sword and armour to attack enemies.");
					System.out.println(currentRoom.longDescription());
				}
			}
			return false;
		}
	}

	// uses [item]
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
					System.out.println("Your health is now " + mainCharacter.getCharacterHealth() + " HP.");
					characterInventory.removeItem(theItem);
					System.out.println(currentRoom.longDescription());
				} else if (theItem instanceof WeaponAttachment) {
					characterSword
							.setPower(((WeaponAttachment) theItem).getAttackPowerAdded() + characterSword.getPower());
					characterSword.setCritChance(
							((WeaponAttachment) theItem).getCritChanceAdded() + characterSword.getCritChance());
					characterSword.setDescription(
							((WeaponAttachment) theItem).getSwordTitleAdded() + characterSword.getDescription() + " ");
					System.out.println("Your sword now has become the " + " " + characterSword.getDescription() + ".");
					characterInventory.removeItem(theItem);
					System.out.println(currentRoom.longDescription());
				} else if (theItem instanceof ArmourAttachment) {
					characterArmour.setProtection(
							((ArmourAttachment) theItem).getArmourAdded() + characterArmour.getProtection());
					characterArmour.setDescription(((ArmourAttachment) theItem).getArmourTittleAdded() + " "
							+ characterArmour.getDescription());
					System.out.println("Your armour has become the " + characterArmour.getDescription() + ".");
					characterInventory.removeItem(theItem);
					System.out.println(currentRoom.longDescription());
				} else if (theItem instanceof Pockets) {
					characterInventory
							.setMaxWeight(((Pockets) theItem).getSpaceAdded() + characterInventory.getMaxWeight());
					System.out.println("Your carry weight has increased to " + characterInventory.getMaxWeight());
					characterInventory.removeItem(theItem);
					System.out.println(currentRoom.longDescription());
				} else {
					System.out.println("You cannot use that.");
					System.out.println(currentRoom.longDescription());
				}
			}

		}
		if (used == false) {
			System.out.println("You do not have that in your inventory.");
			System.out.println(currentRoom.longDescription());
		}
	}
}
