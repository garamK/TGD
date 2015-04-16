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
    	
    	try {
			dao.gameStart();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }

}
