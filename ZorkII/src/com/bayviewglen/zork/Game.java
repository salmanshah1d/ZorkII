package com.bayviewglen.zork;

import java.util.ArrayList;

HEAD
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
						roomInventory.addItem(new Weapon(roomItems[s].split("-")[0].trim(),
								Integer.parseInt(roomItems[s].split("-")[1].trim()), 20));
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
				
				if (roomNPC.equals("ShayanSn")) {
					room.setNPC(new ShayanShakeriNezhad());
				} else if (roomNPC.equals("Salesi")) {
					room.setNPC(new ShayanSalesi());
				} else if (roomNPC.equals("Rodin")) {
					room.setNPC(new Rodin());
				} else if (roomNPC.equals("Andrei")) {
					room.setNPC(new Andrei());
				} else if (roomNPC.equals("Daniel")) {
					room.setNPC(new Rodin());
				} else if (roomNPC.equals("RyanMak")) {
					room.setNPC(new RyanMak());
				} else if (roomNPC.equals("Mr. DesLauriers")) {
					room.setNPC(new Deslaurier());
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
