package com.scsa.game;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SurvivorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GameDao dao = new GameDao(); // 멤버변수 불러서
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println(action);
		String nextPage = "";
		
		switch(action){
		case "LIST" : nextPage = list(request, response); break;
		}//스위치문
		request.getRequestDispatcher(nextPage).forward(request, response); ///????
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
} //class

/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
 */