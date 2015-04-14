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
import com.scsa.db.MemberDAO;
import com.scsa.web.Member;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
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
			
			int userNum = Integer.parseInt(mRequest.getParameter("UserNum").trim());
			String userId = mRequest.getParameter("UserId").trim();
			String nick = mRequest.getParameter("Nick").trim();
			String pass = mRequest.getParameter("Pass").trim();
			int play = Integer.parseInt(mRequest.getParameter("Play").trim());
			String image = mRequest.getParameter("Image").trim();
			
			GameUser gu = new GameUser(userNum, userId, nick, pass, play, image);
			
			System.out.println(gu);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "상품 등록 중 문제가 발생하였습니다.");
			return "Error.jsp";
		}
		
		return "Index.jsp";
	}
	
	
	//////로그인메서드관련해서 추가해주기
	protected String login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		String uid = request.getParameter("userId"); // 아이디와
		String pw = request.getParameter("pass"); // 패스워드 꺼내서
		UserDao dao = new UserDao(); // 멤버변수 불러서

		try {
			GameUser gu = dao.search(userId);

			if (dao.loginCheck(uid, pw)) { // if(m!=null && pw.equals(m.getPw()))

				// Cookie c = new Cookie("uid", uid);
				// c.setMaxAge(10*60); //로그인 유지 시간 -- 재사용된 시점 기준으로 10분 동안만 이 데이터
				// 유지행
				// response.addCookie(c);

				HttpSession s = request.getSession();
				s.setAttribute("uid", uid);
				s.setAttribute("name", gu.getUname());

				// System.out.println(m.getUname());
				// request.setAttribute("name", m.getUname());
				// request.getRequestDispatcher("LoginResult.jsp").forward(request,
				// response);
				return "LoginResult.jsp"; // 정상처리했을 때,

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("msg", "아이디 또는 패스워드가 잘못되었습니다");
		return "Error.jsp";

	} // login()

	
	protected void logout(HttpServletRequest request) throws ServletException,
			IOException {
		HttpSession s = request.getSession(false); // 있으면 가져오고, 없으면 null줘.
		if (s != null)
			s.invalidate();
	}// logout()
	
	
}
