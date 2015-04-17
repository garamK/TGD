package com.scsa.game;

public class Event {

	private int user1;
	private int user2;
	private String msg;
	private int group;
	
	public Event(){}
	
	public Event(String msg){
		this.msg = msg;
	}
	
	public Event(String msg, int group) {
		this.msg = msg;
		this.group = group;
	}

	public Event(int user1, int user2, String msg, int group) {
		this.user1 = user1;
		this.user2 = user2;
		this.msg = msg;
		this.group = group;
	}

	public int getUser1() {
		return user1;
	}

	public void setUser1(int user1) {
		this.user1 = user1;
	}

	public int getUser2() {
		return user2;
	}

	public void setUser2(int user2) {
		this.user2 = user2;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Event [user1=").append(user1).append(", user2=")
				.append(user2).append(", msg=").append(msg).append(", group=")
				.append(group).append("]");
		return builder.toString();
	}
}
