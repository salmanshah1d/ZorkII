package com.bayviewglen.zork;

import java.util.ArrayList;
import java.util.Scanner;

public class CodeTestingFile {

	public static void main(String[] args) {
		Scanner roomScanner = new Scanner(System.in);
		String roomItems = roomScanner.nextLine();
		// An array of strings in the format E-RoomName
		String[] items = roomItems.split(":")[1].split(",");
		ArrayList<Item> itemList = new ArrayList<Item>(); 
		for (String s : items){
			itemList.add(new Item(s.split("-")[0].trim(), Integer.parseInt(s.split("-")[1].trim())));
		}
		
		System.out.println(itemList);
	}
}
