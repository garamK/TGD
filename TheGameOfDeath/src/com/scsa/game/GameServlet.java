package com.scsa.game;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scsa.user.Status;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GameDao dao = new GameDao();
       
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	String nextPage = "Main.jsp";
    	
    	String action = request.getParameter("action");
    	
    	if(action != null && action.equals("explore")){
    		nextPage = explore(request, response);
    	}
    	
    	try {
			userInfo(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		request.getRequestDispatcher(nextPage).forward(request, response);
    	
    }
    
    private void userInfo(HttpServletRequest request, HttpServletResponse response) throws SQLException{
    	
    	HttpSession session = request.getSession();
    	Status user = dao.getUser((int)session.getAttribute("userNum")); 
    	String weapon = dao.getItemName(user.getItemNum());
    	request.setAttribute("userInfo", user);
    	request.setAttribute("weapon", weapon);
    }
    
    public String explore(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	try {
			HttpSession session = request.getSession();
			
			// 현재 유저의 정보 로드
    		Status user = dao.getUser((int)session.getAttribute("userNum")); 
    		
    		// 같은 지역에 있는 유저 정보 로드
			ArrayList<Status> userList = dao.getUserList(user.getLocation()); 
			int peopleNum = userList.size();
			
			// 다른 유저를 마주칠 확률 계산(기본은 사람 숫자 *15, 5명 이상일경우 70으로 고정)
			int counterChance = peopleNum > 5 ? 70 : peopleNum*15;
			
			Random ran = new Random();
			int dice = ran.nextInt(100)+1;
			ArrayList<Event> eventList = new ArrayList<Event>();
			
			if(dice < counterChance){ // 사람을 마주한 경우
				// -를 만났다는 메시지 출력
				dice = ran.nextInt(peopleNum);
				
				Status countUser = userList.get(dice);
				int eventGroup = dao.getMaxEventGroup()+1;
				
				if(countUser.getUserNum() != user.getUserNum()){
					String msg = user.getNick() +"은(는) " + countUser.getNick()+"을(를) 발견했다.";
					request.setAttribute("msg", msg);
					
					if(user.getDecision() == 1){ // 유저 성향이 전투적인 경우
						
						int powerUser = user.getPower();
						int powerCounter = countUser.getPower();
						int damage = 0;
						
						if(countUser.getDecision() == 1){ // 상대 성향도 전투적인 경우
							// 전투 시작
							// 발견자 측 공격
							for(int i=0; i<3; i++){
								dice = ran.nextInt(10);
								damage = powerUser + dice;
								
								dice = ran.nextInt(10);
								if(dice<3){
									damage = 0;
									msg = user.getNick() +"의 공격은 빗나갔다.";
									
								}
								else{
									msg = user.getNick() +"은(는) " + countUser.getNick()+"에게 " + damage +
											"의 데미지를 입혔다.";
								}
								countUser.setHealth(countUser.getHealth()-damage);
								
								// 이벤트에 저장
								
								Event ev = new Event(user.getUserNum(), countUser.getUserNum(), msg, eventGroup);
								dao.setEvent(ev);
								eventList.add(ev);
								
								if(countUser.getHealth() <= 0){
									dao.dead(countUser.getUserNum());
									dao.updateStatus("kill", user.getKill()+1, user.getUserNum());
									msg = user.getNick() +"은(는) " + countUser.getNick()+"(을)를 살해했다.";
									ev = new Event(user.getUserNum(), countUser.getUserNum(), msg, eventGroup);
									dao.setEvent(ev);
									eventList.add(ev);
									break;
								}
								else{
									dao.updateStatus("health", countUser.getHealth(), countUser.getUserNum());
								}
								
								// 반대측 공격
								
								dice = ran.nextInt(10);
								damage = powerCounter + dice;
								
								dice = ran.nextInt(10);
								if(dice<3){
									damage = 0;
									msg = countUser.getNick() +"의 공격은 빗나갔다.";
								}
								else{
									msg = countUser.getNick() +"은(는) " + user.getNick()+"에게 " + damage +
											"의 데미지를 입혔다.";
								}
								user.setHealth(user.getHealth()-damage);
								
								// 이벤트에 저장
								
								ev = new Event(user.getUserNum(), countUser.getUserNum(), msg, eventGroup);
								dao.setEvent(ev);
								eventList.add(ev);
								
								if(user.getHealth() <= 0){
									dao.dead(user.getUserNum());
									dao.updateStatus("kill", countUser.getKill()+1, countUser.getUserNum());
									msg = countUser.getNick() +"은(는) " + user.getNick()+"(을)를 살해했다.";
									ev = new Event(user.getUserNum(), countUser.getUserNum(), msg, eventGroup);
									dao.setEvent(ev);
									eventList.add(ev);
									break;
								}
								else{
									dao.updateStatus("health", user.getHealth(), user.getUserNum());
								}
								
							}
							request.setAttribute("eventList", eventList);
							
						}
						else{ // 상대 성향이 회피적인 경우
							dice = ran.nextInt(50)+1;
							
							if(dice > 50){
								// 그냥 도망침
								request.setAttribute("msg", "상대방이 위협을 눈치채고 도망갔습니다.");
							}
							else{
								// 한대 때림
								
								dice = ran.nextInt(10);
								damage = powerUser + dice;
								
								dice = ran.nextInt(10);
								if(dice<3){
									damage = 0;
									msg = countUser.getNick() +"은(는) " + user.getNick()+"의 공격을 피했다.";
								}
								else{
									msg = user.getNick() +"은(는) " + countUser.getNick()+"에게 " + damage +
											"의 데미지를 입혔다.";
								}
								countUser.setHealth(countUser.getHealth()-damage);
								
								// 이벤트에 저장
								
								Event ev = new Event(user.getUserNum(), countUser.getUserNum(), msg, eventGroup);
								dao.setEvent(ev);
								eventList.add(ev);
								
								if(countUser.getHealth() <= 0){
									dao.dead(countUser.getUserNum());
									msg = user.getNick() +"은(는) " + countUser.getNick()+"(을)를 살해했다.";
									ev = new Event(user.getUserNum(), countUser.getUserNum(), msg, eventGroup);
									dao.setEvent(ev);
									eventList.add(ev);
								}
								else{
									dao.updateStatus("health", countUser.getHealth(), countUser.getUserNum());
								}
								
								request.setAttribute("eventList", eventList);
							}
						}
					}
					else{ // 유저 성향이 회피적인 경우
						// 도망, 별다른 일이 일어나지 않음
						request.setAttribute("msg", "당신은 들키지 않게 조용히 도망갑니다.");
					}
				}
				else{ // 자기 자신이 상대로 뽑혔을 경우
					request.setAttribute("msg", "아무것도 찾지 못했습니다.");
				}
				
			}
			else{ // 사람을 마주하지 않은 경우
				
				Item item = null;
				
				//지역 아이템 정보 로드
				item = dao.getItemFromMap(user.getLocation());
				
				if(item != null){
					int itemChance = item.getChance();
					dice = ran.nextInt(100)+1;
					
					if(dice < itemChance + 10){ // 아이템이 나왔음
						
						if(item.getiType() == 0){ // 무기가 나온 경우
							if(user.getItemNum()<item.getItemNum()){
								int gap = dao.getItemPower(item.getItemNum()) - dao.getItemPower(user.getItemNum());
								
								user.setPower(user.getPower()+gap);
								user.setItemNum(item.getItemNum());
								dao.updateStatus("itemnum", user.getItemNum(), user.getUserNum());
								dao.updateStatus("power", user.getPower(), user.getUserNum());
							}
							else{
								request.setAttribute("msg", "무기를 발견했으나 별로 필요가 없어보입니다.");
							}
						
						}
						else{ // 음식이 나온 경우
							
							request.setAttribute("msg", "음식을 발견했습니다.");
						}
						
						
					}
					else{ // 아이템이 나오지 않음
						
						request.setAttribute("msg", "아무것도 찾지 못했습니다.");
					}
					
				}
				else{ // 해당 지역에서 나오는 아이템 없음
					
					request.setAttribute("msg", "아무것도 찾지 못했습니다.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return "Main.jsp";
    }
}
