package com.scsa.user;

//VO
public class GameUser {

	private int UserNum;
	private String UserId;
	private String Nick;
	private String Pass;
	private int Play;
	private String image;

	
	public GameUser(int userNum, String userId, String nick, String pass,
			int play, String image) {
		super();
		UserNum = userNum;
		UserId = userId;
		Nick = nick;
		Pass = pass;
		Play = play;
		this.image = image;
	}


	public GameUser() {
		super();
	}

	

	public int getUserNum() {
		return UserNum;
	}


	public void setUserNum(int userNum) {
		UserNum = userNum;
	}


	public String getUserId() {
		return UserId;
	}


	public void setUserId(String userId) {
		UserId = userId;
	}


	public String getNick() {
		return Nick;
	}


	public void setNick(String nick) {
		Nick = nick;
	}


	public String getPass() {
		return Pass;
	}


	public void setPass(String pass) {
		Pass = pass;
	}


	public int getPlay() {
		return Play;
	}


	public void setPlay(int play) {
		Play = play;
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
		builder.append(UserNum);
		builder.append(", UserId=");
		builder.append(UserId);
		builder.append(", Nick=");
		builder.append(Nick);
		builder.append(", Pass=");
		builder.append(Pass);
		builder.append(", Play=");
		builder.append(Play);
		builder.append(", image=");
		builder.append(image);
		builder.append("]");
		return builder.toString();
	}
	
	
} //class
