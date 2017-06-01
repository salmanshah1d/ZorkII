package com.bayviewglen.zork;

public class ArmourAttachment extends Item{
	
	private int armourAdded;
	private String armourTittleAdded;
	
	public String getArmourTittleAdded() {
		return armourTittleAdded;
	}

	public void setArmourTittleAdded(String armourTittleAdded) {
		this.armourTittleAdded = armourTittleAdded;
	}

	public ArmourAttachment(String name, Double mass, int cost,int armourIncreased, String word){
		super(name,mass,cost);
		this.armourAdded = armourIncreased;
		this.armourTittleAdded = word;
	}

	public int getArmourAdded() {
		return armourAdded;
	}

	public void setArmourAdded(int armourAdded) {
		this.armourAdded = armourAdded;
	}


}
