package com.scsa.game;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GameDao dao = new GameDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("Map.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
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
		
		request.getRequestDispatcher("Map.jsp").forward(request, response);
		
	}

}
