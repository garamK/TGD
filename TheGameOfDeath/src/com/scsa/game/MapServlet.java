package com.scsa.game;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scsa.user.Status;


public class MapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GameDao dao = new GameDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("nextPage", "Map.jsp");
		request.getRequestDispatcher("Main.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		Status user = null; 
		
		try {
			user = dao.getUser((int)session.getAttribute("userNum"));
			
			if(user.getStamina() <= 1){
				
				request.setAttribute("msg", "행동력이 부족하다... 더이상 돌아다닐 수 없어...");
				
				request.setAttribute("nextPage", "Map.jsp");
				request.getRequestDispatcher("Main.jsp").forward(request, response);
				
				return;
			}
			user.setStamina(user.getStamina()-2);
			
			dao.updateStatus("stamina", user.getStamina(), user.getUserNum());
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		
		String temp = request.getParameter("nextLocation");
		
		int nextLocation = 0;
		int userNum = (int)session.getAttribute("userNum");
		
		
		if(temp != null){
			nextLocation = Integer.parseInt(temp);
			
			if(nextLocation != 0){
				
				try {
					dao.updateStatus("location", nextLocation, userNum);
					session.setAttribute("location", nextLocation);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
    	session.setAttribute("userInfo", user);
		request.setAttribute("nextPage", "Map.jsp");
		request.getRequestDispatcher("Main.jsp").forward(request, response);
		
	}

}
