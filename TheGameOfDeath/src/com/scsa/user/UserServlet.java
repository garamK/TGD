package com.scsa.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String nextPage = "";
		
		switch(action){
		case "register" : nextPage = register(request, response); break;
				
		
		}
	}
	
	
	protected String register(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String saveDir = "images";
		String saveFullDir = getServletContext().getRealPath(saveDir);
		int maxFileSize = 5*1024*1024;
		String encoding = "utf-8";
		
		MultipartRequest mRequest = null;

		try {
			mRequest = new MultipartRequest(request,saveFullDir,maxFileSize,encoding,		
					new DefaultFileRenamePolicy());
			
			int userNum = Integer.parseInt(mRequest.getParameter("UserNum").trim());
			String userId = mRequest.getParameter("UserId").trim();
			String nick = mRequest.getParameter("Nick").trim();
			String pass = mRequest.getParameter("Pass").trim();
			int play = Integer.parseInt(mRequest.getParameter("Play").trim());
			String image = mRequest.getParameter("Image").trim();
			
			GameUser gu = new GameUser(userNum, userId, nick, pass, play, image);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "상품 등록 중 문제가 발생하였습니다.");
			return "Error.jsp";
		}
		
		return "Index.jsp";
	}
	
	
	
	
}
