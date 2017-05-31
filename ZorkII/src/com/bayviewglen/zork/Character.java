package com.bayviewglen.zork;

import java.util.ArrayList;

//Used to create the main character. All other characters extend this class
public class Character {
	private String characterName;
	private String description;
	private int characterHealth;
	private int characterHealthMax; // what is this?
	private Weapon characterWeapon;
	private int characterWallet;
	//private int characterPower;
	private int characterArmor;

	/*public int getCharacterPower() {
		return characterPower;
	}*/

	/*public void setCharacterPower(int characterPower) {
		this.characterPower = characterPower;
	}*/

	public Weapon getWeapon() {
		return characterWeapon;
	}
	
	public int getCharacterArmor() {
		return characterArmor;
	}

	public void setCharacterArmor(int characterArmor) {
		this.characterArmor = characterArmor;
	}

	public Character() {
		this.characterHealth = 100;
		this.characterWallet = 0;
	}

	public Character(String name) {
		this.characterName = name;
	}

	public Character(String kind, String name, int health, Weapon weapon) {
		this.description = kind;
		this.characterName = name;
		this.characterHealth = health;
		this.characterWeapon = weapon;
	}

	/*
	 * public Character(String name, int health, int power){ this.characterName
	 * = name; this.characterHealth= health; this.characterPower = power; }
	 */

	public Character(String name, int health, int power, int armor) {
		this.characterName = name;
		this.characterHealth = health;
		//this.characterPower = power;
		this.characterArmor = armor;
	}

	public Character(String name, int health, int maxHealth, int power, int armor) {
		this.characterName = name;
		this.characterHealth = health;
		this.characterHealthMax = maxHealth;
		//this.characterPower = power;
		this.characterArmor = armor;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public int getCharacterHealth() {
		return characterHealth;
	}

	public void setCharacterHealth(int characterHealth) {
		this.characterHealth = characterHealth;
	}

	public int getCharacterHealthMax() {
		return characterHealthMax;
	}

	public void setCharacterHealthMax(int characterHealthMax) {
		this.characterHealthMax = characterHealthMax;
	}

	public void setWeapon(Weapon item) {
		this.characterWeapon = item;
	}
	
	public String getDescription() {
		return description;
	}
}
