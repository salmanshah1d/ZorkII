package com.bayviewglen.zork;

public class WeaponAttachment extends Item{
private int attackPowerAdded;
private int critChanceAdded;
private String swordTitleAdded;
	
	public String getSwordTitleAdded() {
	return swordTitleAdded;
}

public void setSwordTitleAdded(String swordTitleAdded) {
	this.swordTitleAdded = swordTitleAdded;
}

	public WeaponAttachment (String description, Double mass,int cost, int powerAdded,int critAdded, String word){
		super(description, mass, cost);
		this.attackPowerAdded =powerAdded;
		this.critChanceAdded = critAdded;
		this.swordTitleAdded = word;
		
		
	}

	public int getAttackPowerAdded() {
		return attackPowerAdded;
	}

	public void setAttackPowerAdded(int attackPowerAdded) {
		this.attackPowerAdded = attackPowerAdded;
	}

	public int getCritChanceAdded() {
		return critChanceAdded;
	}

	public void setCritChanceAdded(int critChanceAdded) {
		this.critChanceAdded = critChanceAdded;
	}



}
