package com.bayviewglen.zork;

import java.util.ArrayList;

//Used to create the main character. All other characters extend this class
public class Character {
	private String characterName;
	private int characterHealth;
	private int characterPower;
	private int characterArmor;
	
	public int getCharacterPower() {
		return characterPower;
	}


	public void setCharacterPower(int characterPower) {
		this.characterPower = characterPower;
	}


	public int getCharacterArmor() {
		return characterArmor;
	}


	public void setCharacterArmor(int characterArmor) {
		this.characterArmor = characterArmor;
	}


	public Character(){
	}
	
	
public Character(String name){
		this.characterName = name;
	}

public Character(String name, int health){
	this.characterName = name;
	this.characterHealth= health;
}

public Character(String name, int health, int power){
	this.characterName = name;
	this.characterHealth= health;
	this.characterPower = power;
}


public Character(String name, int health, int power, int armor){
	this.characterName = name;
	this.characterHealth= health;
	this.characterPower = power;
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

	
	
	
	
}
