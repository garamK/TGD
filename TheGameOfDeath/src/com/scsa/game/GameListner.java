package com.scsa.game;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class GameListner
 *
 */
public class GameListner implements ServletContextListener {

	ServletContext sc;
   
    
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	
    }

	
    public void contextInitialized(ServletContextEvent arg0)  { 
    	
    	sc = arg0.getServletContext();
    	
    	System.out.println("서버 시작");
    	
		ScheduledJob1 job = new ScheduledJob1();
		
		Timer jobScheduler1 = new Timer();
		
		jobScheduler1.scheduleAtFixedRate(job, 1000, 1000*60*10);
		
    }
	
    class ScheduledJob1 extends TimerTask {

    	public void run() {
    		
    		Calendar cal = Calendar.getInstance();
    		cal.add (Calendar.MINUTE, 10);
    		    		
    		sc.setAttribute("time", cal.getTimeInMillis());
    		
    		GameDao dao = new GameDao();
    		
    		try {
    			dao.timerJob();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    class ScheduledJob2 extends TimerTask {

    	public void run() {
    		
    		
    	}
    }
}

