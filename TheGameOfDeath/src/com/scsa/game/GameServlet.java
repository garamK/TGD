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
    	
    	String nextPage = "GameMain.jsp";
    	
    	HttpSession session = request.getSession();
    	
    	if(session.getAttribute("userNum") == null){
    		request.setAttribute("nextPage", nextPage);
    		request.getRequestDispatcher("Main.jsp").forward(request, response);
    	}
    	
		try {
			Status user = dao.getUser((int)session.getAttribute("userNum"));
			
			if(user == null){
				request.setAttribute("msg", "다음 시즌 시작을 기다려주세요");
				request.getRequestDispatcher("Error.jsp").forward(request, response);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
    	
    	String action = request.getParameter("action");
    	
    	if(action != null && action.equals("explore")){
    		nextPage = explore(request, response);
    	}else if(action != null && action.equals("itemUse")){
				try {
					nextPage = itemUse(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    	}else if(action != null && action.equals("decision")){
    		try {
				nextPage = decision(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	
    	try {
			userInfo(request, response);
			getOldLog(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	request.setAttribute("nextPage", nextPage);
		request.getRequestDispatcher("Main.jsp").forward(request, response);
    }
    
    private void getOldLog(HttpServletRequest request, HttpServletResponse response) throws SQLException{
    	
    	ArrayList<Event> eventList = (ArrayList<Event>) request.getAttribute("eventList");
    	
    	if(eventList == null){
    		eventList = new ArrayList<Event>();
    	}
    	
    	HttpSession session = request.getSession();
    	int userNum = (int)session.getAttribute("userNum");
    	ArrayList<Event> oldList = dao.getOldLog(userNum);
    	
    	if(oldList.size() > 0){
    		eventList.add(new Event("======================"));
        	eventList.add(new Event("새로운 Log가 있습니다."));
        	eventList.add(new Event("======================"));
        	
    		for(int i=0; i<oldList.size(); i++){
    			
    			eventList.add(oldList.get(i));
    			
    			if(i < oldList.size()-1 && oldList.get(i).getGroup() != oldList.get(i+1).getGroup()){
    				eventList.add(new Event("======================"));
    	        	eventList.add(new Event("새로운 Log가 있습니다."));
    	        	eventList.add(new Event("======================"));
    			}
    		}
    	}
    	
    	request.setAttribute("eventList", eventList);
    }

	private void userInfo(HttpServletRequest request, HttpServletResponse response) throws SQLException{
    	
    	HttpSession session = request.getSession();
    	int userNum = (int)session.getAttribute("userNum");
    	Status user = dao.getUser(userNum); 
    	String weapon = dao.getItemName(user.getItemNum());
    	session.setAttribute("userInfo", user);
    	request.setAttribute("weapon", weapon);
    	
    	ArrayList <Item> itemList  = dao.getItemList(userNum);
    	session.setAttribute("itemList", itemList);
    }
    
    public String explore(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	try {
			HttpSession session = request.getSession();
			
			// 현재 유저의 정보 로드
    		Status user = dao.getUser((int)session.getAttribute("userNum")); 
    		
    		if(user.getStamina() <= 0){
    			
    			request.setAttribute("msg", "행동력이 부족하다... 더이상 돌아다닐 수 없어...");
    			return "GameMain.jsp";
    		}
    		
    		dao.updateStatus("stamina", user.getStamina()-1, user.getUserNum());
    		
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
							boolean finish = false;
							
							for(int i=0; i<3; i++){
								dice = ran.nextInt(10);
								damage = powerUser + dice;
								
								dice = ran.nextInt(10);
								if(dice<3){
									damage = 0;
									msg = user.getNick() +"의 공격은 빗나갔다...";
									
								}
								else{
									msg = user.getNick() +"은(는) " + countUser.getNick()+"에게 " + damage +
											"의 데미지를 입혔다!";
								}
								countUser.setHealth(countUser.getHealth()-damage);
								
								// 이벤트에 저장
								
								Event ev = new Event(user.getUserNum(), countUser.getUserNum(), msg, eventGroup);
								dao.setEvent(ev);
								eventList.add(ev);
								
								if(countUser.getHealth() <= 0){
									dao.dead(countUser.getUserNum());
									dao.updateStatus("kill", user.getKill()+1, user.getUserNum());
									msg = user.getNick() +"은(는) " + countUser.getNick()+"에게 승리했다!!";
									ev = new Event(user.getUserNum(), countUser.getUserNum(), msg, eventGroup);
									dao.setEvent(ev);
									eventList.add(ev);
									finish = true;
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
									msg = countUser.getNick() +"의 공격은 빗나갔다...";
								}
								else{
									msg = countUser.getNick() +"은(는) " + user.getNick()+"에게 " + damage +
											"의 데미지를 입혔다!";
								}
								user.setHealth(user.getHealth()-damage);
								
								// 이벤트에 저장
								
								ev = new Event(user.getUserNum(), countUser.getUserNum(), msg, eventGroup);
								dao.setEvent(ev);
								eventList.add(ev);
								
								if(user.getHealth() <= 0){
									dao.dead(user.getUserNum());
									dao.updateStatus("kill", countUser.getKill()+1, countUser.getUserNum());
									msg = countUser.getNick() +"은(는) " + user.getNick()+"에게 승리했다!!";
									ev = new Event(user.getUserNum(), countUser.getUserNum(), msg, eventGroup);
									dao.setEvent(ev);
									eventList.add(ev);
									finish = true;
									break;
								}
								else{
									dao.updateStatus("health", user.getHealth(), user.getUserNum());
								}
								
							}
							
							if(!finish){
								Event ev = new Event(user.getUserNum(), countUser.getUserNum(), "오늘은 승부를 내기 어려울 것 같다.", eventGroup);
								eventList.add(ev);
								dao.setEvent(ev);
								
								ev = new Event(user.getUserNum(), countUser.getUserNum(), user.getNick() +"와 " + countUser.getNick()+"은(는) 다음을 기약하며 자리를 떠났다.", eventGroup);
								eventList.add(ev);
								dao.setEvent(ev);
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
									msg = countUser.getNick()+"(은)는 도망갔다.";
									ev = new Event(user.getUserNum(), countUser.getUserNum(), msg, eventGroup);
									dao.setEvent(ev);
									eventList.add(ev);
									dao.updateStatus("health", countUser.getHealth(), countUser.getUserNum());
								}
								
								request.setAttribute("eventList", eventList);
							}
						}
					}
					else{ // 유저 성향이 회피적인 경우
						// 도망, 별다른 일이 일어나지 않음
						eventList.add(new Event(user.getUserNum(), countUser.getUserNum(), "당신은 들키지 않게 조용히 도망갑니다.", eventGroup));
						request.setAttribute("eventList", eventList);
					}
				}
				else{ // 자기 자신이 상대로 뽑혔을 경우
					request.setAttribute("msg", "아무것도 찾지 못했습니다.");
				}
				
			}
			else{ // 사람을 마주하지 않은 경우
				
				Item item = null;
				
				int dec = user.getDecision();
				
				int bonus = dec == 1 ? 15 : -5;
				
				//지역 아이템 정보 로드
				ArrayList<Item> itemList = dao.getItemFromMap(user.getLocation());
				
				if(itemList.size() != 0){
					
					dice = ran.nextInt(itemList.size());
					
					item = itemList.get(dice);
					
					int itemChance = item.getChance();
					dice = ran.nextInt(100)+1;
					
					if(dice < itemChance + bonus){ // 아이템이 나왔음
						
						if(item.getiType() == 0){ // 무기가 나온 경우
							if(user.getItemNum()<item.getItemNum()){
								int gap = dao.getItemPower(item.getItemNum()) - dao.getItemPower(user.getItemNum());
								
								user.setPower(user.getPower()+gap);
								user.setItemNum(item.getItemNum());
								dao.updateStatus("itemnum", user.getItemNum(), user.getUserNum());
								dao.updateStatus("power", user.getPower(), user.getUserNum());
								
								request.setAttribute("msg", "쓸만한 무기를 발견했다! 공격력이 "+gap+"만큼 증가했다.");
							}
							else{
								request.setAttribute("msg", "무기를 발견했으나 별로 필요가 없어보입니다.");
							}
						
						}
						else{ // 음식이 나온 경우
							
							request.setAttribute("msg", "음식을 발견했습니다.");
							
							itemList = (ArrayList<Item>) session.getAttribute("itemList");
							boolean itemExist = false;
							for(Item it : itemList){
								if(it.getItemNum() == item.getItemNum()){
									itemExist = true;
									it.setQuantity(it.getQuantity()+1);
									dao.itemGet(item.getItemNum(), user.getUserNum());
									break;
								}
							}
							
							if(!itemExist){
								dao.insertUserItem(item.getItemNum(), user.getUserNum());
							}
							
							itemList  = dao.getItemList(user.getUserNum());
					    	session.setAttribute("itemList", itemList);
							
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
    	return "GameMain.jsp";
    }
    
    
    //아이템사용 ++++++ 식량!!!!
    private String itemUse(HttpServletRequest request, HttpServletResponse response) throws SQLException{
    	
    	HttpSession session = request.getSession();
    	int userNum = (int)session.getAttribute("userNum");
    	
    	String in = request.getParameter("itemNum");
    	
    	if(in == null){
    		request.setAttribute("msg", "음식을 선택해주세요.");
        	return "GameMain.jsp";
    	}
    	
    	int itemNum  = Integer.parseInt(in);
    	ArrayList<Item> itemList = (ArrayList<Item>) session.getAttribute("itemList");
    	
    	int quantity = 0;
    	
    	for(Item it : itemList){
    		if(it.getItemNum() == itemNum){
    			quantity = it.getQuantity();
    			break;
    		}
    	}
    	
    	System.out.println("itemNum : " + itemNum + " 수량 : " + quantity);
    	Status user = dao.getUser(userNum);
    	int health = user.getHealth();  //체력
    	int maxHealth = user.getMaxHealth();  //최대체력
    	int stat = dao.getItemPower(itemNum);  //해당아이템 사용을 통해서 나오는 수치.
    	
    	//수량 케이스
    	if(quantity == 1){
    		dao.deleteItem(itemNum, userNum);
    	}
    	else if(quantity <= 0){
    		return "GameMain.jsp";
    	}
    	else{
    		dao.itemUse(itemNum, userNum);
    	}
    	
    	//체력 케이스
    	health = health + stat;
    	if(health>maxHealth){
    		health = maxHealth;
    	}
    	
    	itemList  = dao.getItemList(userNum);
    	session.setAttribute("itemList", itemList);
    	
    	request.setAttribute("msg", "가지고 있던 음식을 먹었다. 체력이 회복되었다.");
    	dao.updateStatus("health", health, userNum);
    	return "GameMain.jsp";
    }//
    
    //방어시 행동지침//+++++
    private String decision(HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
    	
    	HttpSession session = request.getSession();
    	
    	String dc = request.getParameter("decision");
    	
    	if(dc == null){
    		request.setAttribute("msg", "행동 지침을 설정해주세요");
    		return "GameMain.jsp";
    	}
    	
    	int userNum = (int)session.getAttribute("userNum");
    	int decision = Integer.parseInt(dc);
    	dao.updateStatus("decision", decision, userNum);
    	
    	if(decision == 1){
    		request.setAttribute("msg", "행동 지침이 전투로 변경되었습니다.");
    	}
    	else{
    		request.setAttribute("msg", "행동 지침이 회피로 변경되었습니다.");
    	}
    	
    	return "GameMain.jsp";
	}
    
}//마지막
