package com.scsa.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.scsa.db.DBUtil;

public class AdminDao {

	public void gameStart() throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		try{
			con = DBUtil.getConnection();
			//1. 게임 유저의 참가 상황을 모두 1로 만듬
			String sql = "update gameUser set play = 1";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			DBUtil.close(ps);
			
			//2. 기존 유저의 status 삭제
			
			sql = "delete status";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			DBUtil.close(ps);
			
			sql = "delete userItem";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			DBUtil.close(ps);
			
			sql = "delete event";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			DBUtil.close(ps);
			
			// 유저 목록 불러오기
			sql = "select userNum from gameUser";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				list.add(rs.getInt(1));
			}
			DBUtil.close(ps);
			
			Random rnd = new Random();
			int dice = 0;
			int weapon = 0;
			
			sql = "insert into status values(?, ?, ?, ?, 30, 0, 0, ?, 1, ?)";
			ps = con.prepareStatement(sql);
			for(int i : list){
				ps.setInt(1, i); // 회원번호
				
				dice = 280 + rnd.nextInt(51); // 체력 랜덤 생성
				
				ps.setInt(2, dice); // 최대체력
				ps.setInt(3, dice); // 체력
				
				dice = rnd.nextInt(16) + 1; // 위치 랜덤 생성
				
				ps.setInt(5, dice); // 위치
				
				weapon = rnd.nextInt(2) + 1; // 무기 랜덤 생성
				ps.setInt(6, weapon); // 무기
				
				dice = 20 + rnd.nextInt(6); // 공격력 랜덤 생성
				
				ps.setInt(4, weapon == 1 ? dice + 5 : dice + 12); // 공격력
				
				ps.executeUpdate();
			}
			
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	}
	
	public void gameEnd() throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = DBUtil.getConnection();
			String sql = "update gameUser set play = 0";
			ps = con.prepareStatement(sql);
			DBUtil.close(ps);
			
			//2. 기존 유저의 status 삭제
			
			sql = "delete status";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			DBUtil.close(ps);
			
			sql = "delete userItem";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			DBUtil.close(ps);
			
			sql = "delete event";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			DBUtil.close(ps);
			
		}finally{
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	}
}


/*
게임 시작

1. 게임 유저의 참가 상황을 모두 1로 만듬

2. 기존 유저의 status 삭제

3. 새로운 status 정보 생성(랜덤 요소 필요)

게임 끝

1.게임 유저의 참가 상황을 모두 0으로 만듬



*/