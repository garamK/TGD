package com.scsa.user;


//VO
public class GameUser {

	private int UserNum;
	private String UserId;
	private String Nick;
	private String Pass;
	private int Play;
	
	public GameUser(int userNum, String userId, String nick, String pass,
			int play) {
		super();
		UserNum = userNum;
		UserId = userId;
		Nick = nick;
		Pass = pass;
		Play = play;
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
		builder.append("]");
		return builder.toString();
	}
	

} //class
