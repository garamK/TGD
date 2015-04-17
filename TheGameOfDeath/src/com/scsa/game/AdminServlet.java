package com.scsa.game;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	AdminDao dao = new AdminDao();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	String action = request.getParameter("action");
    	
    	if(action != null){
    		
    		try {
    			if(action.equals("START")){
    				dao.gameStart();
    			}
    			else{
    				dao.gameEnd();
    			}
    			request.getRequestDispatcher("Admin.jsp").forward(request, response);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	else{
    		request.getRequestDispatcher("Admin.jsp").forward(request, response);
    	}
    	
    }

}
