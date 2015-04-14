package com.scsa.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.scsa.db.DBUtil;

//유저관리DAO -- 관리자가 볼거.
public class UserDao {

	/*
	private int userNum;
	private String userId;
	private String nick;
	private String pass;
	private int play;
	private String image;
	 */

	
	// 시퀀스 해주는 부분
		private int getMaxUserNum() throws SQLException {
			int max = 0;
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				con = DBUtil.getConnection();
				String q = "Select max(userNum) from GameUser"; // 지금 입력된 값들 중에 가장 큰 값
															// 가져와.
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
	
	
	// User 저장하기
	public void save(GameUser gu) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtil.getConnection();
			String sql = "insert into GameUser values(?, ?, ?, ?, false, ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, getMaxUserNum() + 1); // 시퀀스해주는 부분
			ps.setString(2, gu.getUserId());
			ps.setString(3, gu.getNick());
			ps.setString(4, gu.getPass());
			ps.setString(5, gu.getImage());
			ps.executeUpdate();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	}
	
	
	//User 수정하기-- 비밀번호랑 닉네임 수정가능(user 본인만 할 거)
	public void update(String userId, String nick, String pass) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = DBUtil.getConnection();
			String sql = "update GameUser set nick=? pass=? where userId=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, nick);
			ps.setString(2, pass);
			ps.setString(3, userId);
			
			ps.executeUpdate();
			
		}finally{
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	}

	
	//User 목록보기 (관리자만 볼 거)
	public ArrayList<GameUser> search() throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<GameUser> list = new ArrayList<GameUser>();
		
		try{
			con = DBUtil.getConnection();
			String sql = "select userNum, userId, nick from GameUser";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				list.add(new GameUser(rs.getInt(1), rs.getString(2), rs.getString(3)));
				}
			
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
		return list;
	}
	
	
	//
	public GameUser search(String userId) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		GameUser gu = null;
		try{
			con = DBUtil.getConnection();
			String sql = "select userNum, userId, nick from GameUser where userId = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			if(rs.next()){
				gu = new GameUser(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
		return gu;
	}
	
	
	
	//유저 관리자가 강퇴시키기/유저 스스로가 탈퇴하기  -- userId 입력받아서
	public void delete(String userId) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = DBUtil.getConnection();
			String sql = "delete from GameUser where userId = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.executeUpdate();
		}finally{
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	}

	
	//로그인 체크.
	public boolean loginCheck(String userId, String pass) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
			con = DBUtil.getConnection();
			String sql = "select pass from GameUser where userId = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			String idCheck = "";
			
			if(rs.next()){
				idCheck = rs.getString(1);
				
				if(pass.equals(idCheck)){
					result = true;
				}
			}
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return result;
	}
	
	
} // class
