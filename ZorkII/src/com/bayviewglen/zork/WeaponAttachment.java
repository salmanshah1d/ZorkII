package com.bayviewglen.zork;

public class WeaponAttachment extends Item{
private int attackPowerAdded;
private int critChanceAdded;
	
	public WeaponAttachment (String name, Double mass, int cost, int powerAdded,int critAdded){
		super(name,mass,cost);
		this.attackPowerAdded = powerAdded;
		this.critChanceAdded = critAdded;
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
