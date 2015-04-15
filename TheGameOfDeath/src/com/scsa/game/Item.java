package com.scsa.game;

public class Item {

	private int itemNum;
	private int iType;
	private int stat;
	private String itemName;
	private int chance;
	private int quantity;
	
	public Item(){}
	
	public Item(int itemNum, int iType, int stat, String itemName, int chance) {
		super();
		this.itemNum = itemNum;
		this.iType = iType;
		this.stat = stat;
		this.itemName = itemName;
		this.chance = chance;
	}
	
	public Item(int itemNum, int iType, int stat, String itemName, int chance, int quantity) {
		super();
		this.itemNum = itemNum;
		this.iType = iType;
		this.stat = stat;
		this.itemName = itemName;
		this.chance = chance;
		this.quantity = quantity;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getiType() {
		return iType;
	}

	public void setiType(int iType) {
		this.iType = iType;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [itemNum=").append(itemNum).append(", iType=")
				.append(iType).append(", stat=").append(stat)
				.append(", itemName=").append(itemName).append(", chance=")
				.append(chance).append(", quantity=").append(quantity)
				.append("]");
		return builder.toString();
	}
	
}
