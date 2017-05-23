<<<<<<< HEAD
package com.bayviewglen.zork;

public class Item {
	private String description;
	private int weight;
	private int cost;
	
	Item(String objectInp, int weightInp) {
		description = objectInp;
		weight = weightInp;
	}
	
	Item(String objectInp, int weightInp, int costInp) {
		description = objectInp;
		weight = weightInp;
		cost = costInp;
	}
	
	public String toString(){
		return("(" + description + ", " + weight + ")");
	}

	public String display() {
		return(weight + "-pound " + description);
	}
}
=======
package com.bayviewglen.zork;

public class Item {
	private String description;
	private int mass;

	//create getters and setters
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public void setMass(int mass) {
		this.mass = mass;
	}

	public int getMass() {
		return mass;
	}

	public Item(String description, int mass) {
		super();
		this.description = description;
		this.mass = mass;
	}
	public Item(String description) {
		super();
		this.description = description;
		this.mass = 0;
	}

}
>>>>>>> refs/remotes/origin/master
