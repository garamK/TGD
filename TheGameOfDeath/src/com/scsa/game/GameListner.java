package com.scsa.game;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.scsa.user.Status;

/**
 * Application Lifecycle Listener implementation class GameListner
 *
 */
public class GameListner implements ServletContextListener {

	ServletContext sc;
	GameDao dao = new GameDao();
    
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	
    	sc = arg0.getServletContext();
    	
    	System.out.println("서버 시작");
    	
		ScheduledJob1 job1 = new ScheduledJob1();
		
		ScheduledJob2 job2 = new ScheduledJob2();
		
		Timer jobScheduler1 = new Timer();
		Timer jobScheduler2 = new Timer();
		
		jobScheduler1.scheduleAtFixedRate(job1, 1000, 1000*60*10);
		
		jobScheduler2.scheduleAtFixedRate(job2, 1000, 1000*60*35);
    }
	
    class ScheduledJob1 extends TimerTask {

    	public void run() {
    		
    		Calendar cal = Calendar.getInstance();
    		cal.add (Calendar.MINUTE, 10);
    		    		
    		sc.setAttribute("time", cal.getTimeInMillis());
    		
    		try {
    			dao.timerJob();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    class ScheduledJob2 extends TimerTask {

    	public void run() {
    		
    		try {
				ArrayList<Status> userList = dao.getUserList();
				sc.setAttribute("number1", userList.get(0));
				
				Thread.sleep(1000*60*3);
				
				sc.removeAttribute("number1");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    	}
    }
}

