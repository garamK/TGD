package com.scsa.user;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao dao = new UserDao(); // 멤버변수 불러서
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println(action);
		String nextPage = "";
		
		switch(action){
		case "REGISTER" : nextPage = register(request, response); break;
		case "LOGIN": nextPage = login(request, response); break;
		case "LOGOUT": logout(request); break;
		}//스위치문
		request.getRequestDispatcher(nextPage).forward(request, response); ///????
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
			
			String userId = mRequest.getParameter("UserId").trim();
			String nick = mRequest.getParameter("Nick").trim();
			String pass = mRequest.getParameter("Pass").trim();
			String image = mRequest.getFilesystemName("Image");
			
			GameUser gu = new GameUser(0, userId, nick, pass, 0, image);
			System.out.println(gu); //검증용~~

			dao.save(gu);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "등록 중 문제가 발생하였습니다.");
			return "Error.jsp";
		}
		
		return "Index.jsp";
	}
	
	
	//////로그인메서드관련해서 추가해주기
	protected String login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId"); // 아이디와
		String pass = request.getParameter("pass"); // 패스워드 꺼내서

		try {

			if (dao.loginCheck(userId, pass)) { 

				GameUser gu = dao.search(userId);
				
				HttpSession s = request.getSession();
				s.setAttribute("userId", userId);
				s.setAttribute("userNum", gu.getUserNum());
				s.setAttribute("nick", gu.getNick());
				
				return "어디로보낼까?????"; // 정상처리했을 때,

			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 중 에러가 발생하였습니다.");
			return "Error.jsp";
		}
		request.setAttribute("msg", "아이디 또는 패스워드가 잘못되었습니다");
		return "Error.jsp";

	} // 

	
	protected void logout(HttpServletRequest request) throws ServletException,
			IOException {
		HttpSession s = request.getSession(false); // 있으면 가져오고, 없으면 null줘.
		if (s != null)
			s.invalidate();
	}//
	
	
}
