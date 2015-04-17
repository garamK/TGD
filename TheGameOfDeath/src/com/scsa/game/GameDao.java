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
			String sql = "select s.userNum, userId, nick, image, maxHealth, "
					+ "health, power, stamina, kill, death, "
					+ "location, decision, itemNum "
					+ "from status s, gameuser gu where s.userNum = gu.userNum and s.userNum = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userNum);
			rs = ps.executeQuery();
			if(rs.next()){
				result = new Status(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
				                       		rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), 
						rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getInt(13));
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
			String sql = "select s.userNum, userId, nick, image, maxHealth, "
					+ "health, power, stamina, kill, death, "
					+ "location, decision, itemNum "
					+ "from status s, gameuser gu where s.userNum = gu.userNum order by kill - death desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new Status(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), 
						rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getInt(13)));
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
			String sql = "select s.userNum, userId, nick, image, maxHealth, "
					+ "health, power, stamina, kill, death, "
					+ "location, decision, itemNum "
					+ "from status s, gameuser gu where s.userNum = gu.userNum and location=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, location);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new Status(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), 
						rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getInt(13)));
			}
			
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
		return list;
	}
	
	//////~~~~~            //attr : 수정하고싶은항목(예를들어, kill, health..)
	public void updateStatus(String attr, int point, int userNum) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = DBUtil.getConnection();
			String sql = "update status set "+attr+" = ? where userNum = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, point); //수치
			ps.setInt(2, userNum);
			
			ps.executeUpdate();
			
		}finally{
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	}
	
	public Item getItemFromMap(int location) throws SQLException{
		
		Item result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = DBUtil.getConnection();
			String sql = "select i.itemNum, iType, stat, itemName, chance from item i, itemMap im "
					+ "where i.itemNum = im.itemNum and mapNum = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, location);
			rs = ps.executeQuery();
			if(rs.next()){
				result = new Item(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
			}
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
		return result;
	}
	
	public void setEvent(Event ev) throws SQLException{
		System.out.println(ev);
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = DBUtil.getConnection();
			String sql = "insert into event values(ev_seq.nextval, ?, ?, ?, ?, 0)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, ev.getGroup());
			ps.setInt(2, ev.getUser1());
			ps.setInt(3, ev.getUser2());
			ps.setString(4, ev.getMsg());
			
			ps.executeUpdate();
			
		}finally{
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
	}
	
	public int getMaxEventGroup() throws SQLException {

		int max = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String q = "Select max(EVENTGROUP) from event"; 
			
			ps = con.prepareStatement(q);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				max = rs.getInt(1);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
		return max;

	}
	
	public ArrayList<Item> getItemList(int userNum) throws SQLException{
		
		ArrayList<Item> list = new ArrayList<Item>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			con = DBUtil.getConnection();
			String sql = "select i.ItemNum, IType, Stat, ItemName, quantity from item i, userItem ui "
					+ "where i.ItemNum = ui.ItemNum and userNum = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userNum);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new Item(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), 0, rs.getInt(5)));
			}
			
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
		return list;
	}
	
	public Item getItem(int itemNum) throws SQLException{
		
		Item result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			con = DBUtil.getConnection();
			String sql = "select i.ItemNum, IType, Stat, ItemName, quantity from item i, userItem ui "
					+ "where i.ItemNum = ui.ItemNum and ItemNum = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, itemNum);
			rs = ps.executeQuery();
			while(rs.next()){
				result = new Item(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), 0, rs.getInt(5));
			}
			
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
		return result;
	}
	
	public int getItemPower(int itemNum) throws SQLException{
		
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String q = "Select stat from item where itemNum = ?";
			
			ps = con.prepareStatement(q);
			ps.setInt(1, itemNum);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
			
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
		return result;
	}
	
	public String getItemName(int itemNum) throws SQLException{
		
		String result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String q = "Select ItemName from item where itemNum = ?";
			
			ps = con.prepareStatement(q);
			ps.setInt(1, itemNum);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				result = rs.getString(1);
			}
			
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
		return result;
	}
	
	public void dead(int userNum) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = DBUtil.getConnection();
			String sql = "update status set death = death+1, stamina = 0, health = 200 where userNum = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userNum);
			
			ps.executeUpdate();
			
		}finally{
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	}

	//+++++ 아이템사용시 감소시시킬 거.
	public void itemUse(int itemNum, int userNum) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = DBUtil.getConnection();
			String sql = "update userItem set quantity=quantity-1 where itemNum=? and userNum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, itemNum);
			ps.setInt(2, userNum);
			
			ps.executeUpdate();
		}finally{
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	}

	//+++아이템지우기
	public void deleteItem(int itemNum, int userNum) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = DBUtil.getConnection();
			String sql = "delete userItem where userNum=? and itemNum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userNum);
			ps.setInt(2, itemNum);
			ps.executeUpdate();
		}finally{
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	}//delete
	
	
	public int getLocation(int userNum) throws SQLException{
		
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			String q = "Select location from status where userNum = ?";
			
			ps = con.prepareStatement(q);
			ps.setInt(1, userNum);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
			
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		
		return result;
	}
	
}//
