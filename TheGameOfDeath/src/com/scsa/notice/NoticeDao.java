package com.scsa.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scsa.db.DBUtil;

public class NoticeDao {
	
	/*
	private int noticeNum;  // 공지번호
	private String title; //제목
	private String content; //내용
	private String ndate; //날짜
	*/

	
	// 저장하기(공지등록) //관리자용
	public void save(Notice n) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			String q = "Insert into Notice values(?,?,?,sysdate)";
			ps = con.prepareStatement(q);
			ps.setInt(1, getMaxNoticeNum() + 1);
			ps.setString(2, n.getTitle());
			ps.setString(3, n.getContent());
			ps.executeUpdate();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	} //

	
	// 공지 번호 매겨주기
	private int getMaxNoticeNum() throws SQLException {

		int max = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String q = "Select max(noticeNum) from Notice"; // 지금 입력된 값들 중에 가장 큰
															// 값 가져와.
			ps = con.prepareStatement(q);
			rs = ps.executeQuery();
			if (rs.next()) { // 만약 뒤에 수가 있으면
				max = rs.getInt(1);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return max;

	}

	
	// 공지목록보여주기 //관리자, 회원용
	public List<Notice> search() throws SQLException {
		List<Notice> list = new ArrayList<Notice>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			String q = "Select noticeNum, title, content, to_char(ndate, 'yy.mm.dd') nd from Notice";
			ps = con.prepareStatement(q);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Notice(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getString(4)));
			}
			
			System.out.println(list);
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return list;
	}

	
	// 해당하는 애 번호로 찾기 -- 상세보기할 때 필요할 수 있음. //관리자, 회원 용
	public Notice search(int noticeNum) throws SQLException {

		Notice n = null; 

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			String q = "Select noticeNum, title, content, to_char(ndate, 'yy.mm.dd') nd from Notice where NoticeNum=?";
			ps = con.prepareStatement(q);
			ps.setInt(1, noticeNum);
			rs = ps.executeQuery();

			if (rs.next()) {
				n = new Notice(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return n;
	}

	
	// 공지삭제부분 //관리자용
	public void delete(int noticeNum) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtil.getConnection();
			String q = "Delete from Notice where NoticeNum=?";
			ps = con.prepareStatement(q);
			ps.setInt(1, noticeNum);
			ps.executeUpdate();
		} finally {
			DBUtil.close(con);
			DBUtil.close(ps);
		}

	}

	
	// 공지업데이트부분 = 해당번호의 내용 수정할 거.//관리자용
	public void update(int noticeNum, String content) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			String q = "Update Notice set content=? where noticeNum=?";
			ps = con.prepareStatement(q);
			ps.setString(1, content);
			ps.setInt(2, noticeNum);
			rs = ps.executeQuery();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	} //

} // class
