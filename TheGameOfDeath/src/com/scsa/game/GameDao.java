package com.scsa.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.scsa.db.DBUtil;
import com.scsa.user.Status;

public class GameDao {

	public Status getUser(int userNum) throws SQLException{
		
		Status result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			con = DBUtil.getConnection();
			String sql = "select s.userNum, image, maxHealth, "
					+ "health, power, stamina, kill, death, "
					+ "location, decision, itemNum "
					+ "from status s, gameuser gu where s.userNum = gu.userNum and s.userNum = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userNum);
			rs = ps.executeQuery();
			if(rs.next()){
				result = new Status(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)
						, rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
			}
			
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
		return result;
		
	}
	
	public ArrayList<Status> getUserList() throws SQLException{
		
		ArrayList<Status> list = new ArrayList<Status>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			con = DBUtil.getConnection();
			String sql = "select s.userNum, image, maxHealth, "
					+ "health, power, stamina, kill, death, "
					+ "location, decision, itemNum "
					+ "from status s, gameuser gu where s.userNum = gu.userNum";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new Status(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)
					, rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11)));
			}
			
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
		return list;
	}
	
	public ArrayList<Status> getUserList(int location) throws SQLException{
		
		ArrayList<Status> list = new ArrayList<Status>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			con = DBUtil.getConnection();
			String sql = "select s.userNum, image, maxHealth, "
					+ "health, power, stamina, kill, death, "
					+ "location, decision, itemNum "
					+ "from status s, gameuser gu where s.userNum = gu.userNum and location=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, location);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new Status(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)
					, rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11)));
			}
			
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
		return list;
	}
	
	public void updateStatus(String attr, int point, int usetNum) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = DBUtil.getConnection();
			String sql = "update status set "+attr+" = ? where userNum = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, point);
			ps.setInt(2, usetNum);
			
			ps.executeUpdate();
			
		}finally{
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
	}
}
