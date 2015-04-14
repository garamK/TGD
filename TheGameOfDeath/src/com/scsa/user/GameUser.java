package com.scsa.user;

//VO
public class GameUser {

	private int userNum;
	private String userId;
	private String nick;
	private String pass;
	private int play;
	private String image;

	
	public GameUser(int userNum, String userId, String nick, String pass,
			int play, String image) {
		super();
		this.userNum = userNum;
		this.userId = userId;
		this.nick = nick;
		this.pass = pass;
		this.play = play;
		this.image = image;
	}

	public GameUser() {
		super();
	}

	

	public int getUserNum() {
		return userNum;
	}


	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public int getPlay() {
		return play;
	}


	public void setPlay(int play) {
		this.play = play;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GameUser [UserNum=");
		builder.append(userNum);
		builder.append(", UserId=");
		builder.append(userId);
		builder.append(", Nick=");
		builder.append(nick);
		builder.append(", Pass=");
		builder.append(pass);
		builder.append(", Play=");
		builder.append(play);
		builder.append(", image=");
		builder.append(image);
		builder.append("]");
		return builder.toString();
	}
	
	
} //class
