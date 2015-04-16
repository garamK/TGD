package com.scsa.notice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NoticeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	NoticeDao dao = new NoticeDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action"); 
		System.out.println("요청 action: " + action); // 디버깅

		String nextPage = ""; 
		if (action == null) { 
			nextPage = index(request, response);
		} else {

			switch (action) {
			case "INPUT": 
				nextPage = "NoticeInput.jsp"; 
				break;
			case "SAVE":
				nextPage = save(request, response); 
				break;
			case "LIST":
				nextPage = list(request, response);
				break;
			case "DELETE":
				nextPage = delete(request, response);
				break;
			case "VIEW":
				nextPage = view(request, response);
				break;
			case "UPDATE":
				nextPage=update(request, response);
				break;
			case "UPDATESAVE":
				nextPage=updateSave(request, response);
				break;
				
			default : nextPage = index(request, response);
			} // /end switch
		}// end if

		System.out.println("다음페이지: " + nextPage); // 디버깅해줄때 필요 ㅋㅋ
		
		if(nextPage.equals("Index.jsp")){
			request.getRequestDispatcher("Index.jsp").forward(request, response);
			return;
		}
		
    	request.setAttribute("nextPage", nextPage);
		request.getRequestDispatcher("Main.jsp").forward(request, response);
	}

	//저장하기
		protected String save(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
		
			//String noticeNum = request.getParameter("noticeNum");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			//String ndate = request.getParameter("ndate");
			
			try {
				Notice n = new Notice(title, content); 
				//Notice n = new Notice(Integer.parseInt(noticeNum), title, content, ndate); 
				System.out.println(n); //검증용
				dao.save(n);
			
				
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("msg", "저장중 오류가 발생했습니다.");
				return "Error.jsp";
			}
			request.setAttribute("msg", "정상적으로 저장되었습니다.");
			return list(request, response);
		}//save()

		
		//리스트보여주기
		protected String list(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			List<Notice> list = null; // 리스트를 꺼내준다.
			try {
				list = dao.search();
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("msg", "검색중 오류가 발생했습니다.");
				return "Error.jsp";
			}
			request.setAttribute("list", list);
			return "NoticeList.jsp";
		} //list()
		
		
		//인덱스에 리스트보여주기
		protected String index(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			List<Notice> list = null; // 리스트를 꺼내준다.
			try {
				list = dao.search();
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("msg", "검색중 오류가 발생했습니다.");
				return "Error.jsp";
			}
			request.setAttribute("list", list);
			return "Index.jsp";
		} //list()

		//삭제
		protected String delete(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {

			String n = request.getParameter("noticeNum");
			try {
				int noticeNum = Integer.parseInt(n);
				dao.delete(noticeNum);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("msg", "삭제중 오류가 발생했습니다.");
				return "Error.jsp";
			}
			return list(request, response);
		} 
		

		//뷰-상세보기 //일반인이 인덱스페이지에서 눌렀을 때 보는 경우랑, 관리자님이보는경우
		protected String view(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			String n = request.getParameter("noticeNum");
			Notice  notice = null;
			
			try {
				int noticeNum = Integer.parseInt(n);
				notice = dao.search(noticeNum);  //번호를 통해->공지사항
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("msg", "검색중 오류가 발생했습니다.");
				return "Error.jsp";
			}
			
			request.setAttribute("notice", notice);
		
				return "NoticeView.jsp"; //관리자인 경우
		} 
		
		
		//수정의1번째 단계 -- 번호를 줄테니까 그 데이터를 꺼내서 담아들고 모디파이로
		// 필요하구나...???
		protected String update(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
			Notice  notice = null;
			
			try {
				notice = dao.search(noticeNum);  //번호줄테니까 공지사항을 달라.
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("msg", "수정할 데이터 검색 중 오류가 발생했습니다.");
				return "Error.jsp";
			}
			request.setAttribute("notice", notice);
			return "NoticeModify.jsp";
		}

		
		//updateSave
			protected String updateSave(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				String noticeNum = request.getParameter("noticeNum");
				String content = request.getParameter("content");
				try {
					dao.update(Integer.parseInt(noticeNum), content); //
				} catch (SQLException e) {
					e.printStackTrace();
					request.setAttribute("msg", "수정 중 오류가 발생했습니다.");
					return "Error.jsp";
				}
				return list(request, response);
			}
	
} //class
