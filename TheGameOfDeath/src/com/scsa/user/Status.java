package com.scsa.user;

public class Status extends GameUser {

	
	private int maxHealth;
	private int health;
	private int power;
	private int stamina;
	private int kill;
	private int death;
	private int location;
	private int decision;
	private int itemNum;
	
	public Status(){}

	public Status(int userNum, String image, int maxHealth, int health, int power,
			int stamina, int kill, int death, int location, int decision,
			int itemNum) {
		super(userNum, image);
		this.maxHealth = maxHealth;
		this.health = health;
		this.power = power;
		this.stamina = stamina;
		this.kill = kill;
		this.death = death;
		this.location = location;
		this.decision = decision;
		this.itemNum = itemNum;
	}
	
	public Status(int userNum, String userId, String nick, String pass,
			int play, String image, int maxHealth, int health, int power,
			int stamina, int kill, int death, int location, int decision,
			int itemNum) {
		super(userNum, userId, nick, pass, play, image);
		this.maxHealth = maxHealth;
		this.health = health;
		this.power = power;
		this.stamina = stamina;
		this.kill = kill;
		this.death = death;
		this.location = location;
		this.decision = decision;
		this.itemNum = itemNum;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getKill() {
		return kill;
	}

	public void setKill(int kill) {
		this.kill = kill;
	}

	public int getDeath() {
		return death;
	}

	public void setDeath(int death) {
		this.death = death;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getDecision() {
		return decision;
	}

	public void setDecision(int decision) {
		this.decision = decision;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Status [maxHealth=").append(maxHealth)
				.append(", health=").append(health).append(", power=")
				.append(power).append(", stamina=").append(stamina)
				.append(", kill=").append(kill).append(", death=")
				.append(death).append(", location=").append(location)
				.append(", decision=").append(decision).append(", itemNum=")
				.append(itemNum).append("]");
		return builder.toString();
	}
}
