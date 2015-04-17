package com.scsa.game;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scsa.user.Status;
import com.scsa.user.UserDao;
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GameDao dao = new GameDao(); // 멤버변수 불러서
	UserDao dao2 = new UserDao();//
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println(action);
		String nextPage = "";
		if (action == null) {
			nextPage = list(request, response);
		} else {

			switch (action) {
			case "LIST":
				nextPage = list(request, response);
				break;
				
			case "DELETE":
				nextPage = delete(request, response);
				break;
			}// 스위치문
		}// else
		
		request.setAttribute("nextPage", nextPage);
		request.getRequestDispatcher("Main.jsp").forward(request, response);
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {

		List<Status> list = null; // 리스트를 꺼내준다.
		try {
			list = dao.getUserList();
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원검색중오류발생.");
			return "Error.jsp";
		}
		request.setAttribute("list", list);
		return "UserList.jsp";
	}
	
	//삭제
	protected String delete(HttpServletRequest request,	HttpServletResponse response) 
			throws ServletException, IOException {
			String userId = request.getParameter("userId");
				try {
					dao2.delete(userId);
				} catch (SQLException e) {
					e.printStackTrace();
					request.setAttribute("msg", "삭제중 오류가 발생했습니다.");
					return "Error.jsp";
				}
				return list(request, response);
			} 
	
	
	
}//
