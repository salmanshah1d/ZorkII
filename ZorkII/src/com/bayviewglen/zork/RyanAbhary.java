package com.bayviewglen.zork;

import java.util.Scanner;

public class RyanAbhary extends NonPlayableCharacter{
	private static String characterName = "RyanAbhary";
	private int characterHealth = 50;
	private Weapon Weapon = new GreasyShank();
	private Scanner keyboard = new Scanner(System.in);
	
	
	public int getCharacterHealth() {
		return characterHealth;
	}

	public void setCharacterHealth(int characterHealth) {
		this.characterHealth = characterHealth;
	}

	public Weapon getWeapon() {
		return Weapon;
	}

	public void setWeapon(Weapon weapon) {
		Weapon = weapon;
	}

	public void talk(Inventory inv){
		boolean stillTalking = true;
		System.out.println("RyanAbhary: Hey what's up. The ceiling LOL. (Select the number of the option you wish to say.)");
		while(stillTalking){
		System.out.println();
		System.out.println("1: What are you doing inside a temple?");
		System.out.println("2: Tell me a joke.");
		System.out.println("3: What do you do in your spare time?");
		System.out.println("4: Goodbye.");
		String response  = scanner.nextLine();
		if(response.equals("1")==false&&response.equals("2")==false&&response.equals("3")==false&&response.equals("4")==false&&response.equals("5")){
			System.out.println("That is not a valid option.");
		}else if(response.equals("1")){
			System.out.println("RyanAbhary: Looking for friends.");
		}else if(response.equals("2")){
			System.out.print("Press enter after reading each line.");
			keyboard.nextLine();
			System.out.print("Three friends were exploring an unknown beach. They came across a lamp and they rubbed it.");
			keyboard.nextLine();
			System.out.print("A genie came out, and said, \"You have freed me from my eternal prison. I shall grant you one wish.\"");
			keyboard.nextLine();
			System.out.print("The first friend said his first wish, \"I wish to have $10 million.\" He immediately received a bank statement with $10 million.");
			keyboard.nextLine();
			System.out.print("The second friend, a little smarter than the first, said his first wish, \"I wish to be the richest man in the world.\" He immediately received a bank statement with $100 billion.");
			keyboard.nextLine();
			System.out.print("The third friend thought for a long time. \"I wish for my right arm to spin clockwise.\" The genie, a little confused, granted his wish.");
			keyboard.nextLine();
			System.out.print("The first friend said his second wish, \"I wish to have an IQ of 300.\" All of a sudden, he felt much smarter.");
			keyboard.nextLine();
			System.out.print("The second friend, again a little smarter than the first, said his second wish, \"I wish to be the smartest man in the world.\" He became so.");
			keyboard.nextLine();
			System.out.print("The third friend thought for a very long time. \"I wish for my left arm to spin counter-clockwise.\" The genie, again a little confused, granted his wish.");
			keyboard.nextLine();
			System.out.print("The first friend said his third and final wish, \"I wish to have perfect health\" All of a sudden, his back pain went away.");
			keyboard.nextLine();
			System.out.print("The second friend, still a little smarter than the first, said his second wish, \"I wish to be immortal with perfect health\" This happened as well.");
			keyboard.nextLine();
			System.out.print("The third friend thought for a very, very long time. \"I wish for my head to bob up and down constantly.\" The genie granted his wish.");
			keyboard.nextLine();
			System.out.print("The genie then went away, and the 3 friends went back to their lives. They met 20 years later. The first and second friend arrived first.");
			keyboard.nextLine();
			System.out.print("The two were discussing. The first said, \"Bro my life is awesome!\" The second said, \"Real talk bro.\"");
			keyboard.nextLine();
			System.out.print("The third friend arrived. He said, \"Guys, I think I messed up.\"\n");
		}else if(response.equals("3")){
			System.out.println("RyanAbhary: I look at overpriced unfashionable clothes that my parents won't pay for.");
		}else{
			System.out.println("Ryan: CYA.");
			stillTalking = false;
		}
		}
	}
	
	public RyanAbhary(){
		super(characterName);
		//creates the inventory for the character
		//ryanMakInventory.add(new Food("Spicy Noodles", 0.5, 5, 5 ));
		//ryanMakInventory.add(new Pockets("Pockets", 0.0,30, 35));
		//ryanMakInventory.add(new DirtyShank());
		//String description, double mass, int cost ,int power, int critChance
		
	}
	
	
/*	
	public void displayInventory(){
	//	shayanSnInventoryNames =new ArrayList<String>();
		if(ryanMakInventory.size() > 0){
		System.out.println("Ryan Mak: I don't have much, but take a look.");
		System.out.println("(Type the name of the item you wish to buy. Or type exit.)");
		System.out.println();
		for (int i = 0; i<ryanMakInventory.size(); i++){
			System.out.print(ryanMakInventory.get(i).getDescription()+ ": " + ryanMakInventory.get(i).getCost() + ", ");
		}
	}else {
		System.out.println("I have nothing more to sell.");
	}
	
	}*/

	public void deathPhrase(){
		System.out.println(characterName + ": *While dying* I may die. But my jokes live on.");
	}
}
