package com.scsa.notice;
// 관리자가 등록하는 공지사항에 관련한 곳.
public class Notice {

	private int noticeNum;  // 공지번호
	private String title; //제목
	private String content; //내용
	private String ndate; //날짜
	
	
	public Notice(int noticeNum, String title, String content, String ndate) {
		super();
		this.noticeNum = noticeNum;
		this.title = title;
		this.content = content;
		this.ndate = ndate;
	}
	
	
	
	public Notice(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}


	public Notice() {
		super();
	}

	public int getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNdate() {
		return ndate;
	}

	public void setNdate(String ndate) {
		this.ndate = ndate;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notice [noticeNum=");
		builder.append(noticeNum);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", ndate=");
		builder.append(ndate);
		builder.append("]");
		return builder.toString();
	}
	
	
}//class
