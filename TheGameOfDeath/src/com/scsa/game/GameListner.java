package com.scsa.game;

import java.sql.SQLException;
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
    	
		ScheduledJob job = new ScheduledJob();
		
		Timer jobScheduler = new Timer();
		
		jobScheduler.scheduleAtFixedRate(job, 1000, 1000*60*10);
		
    }
	
    class ScheduledJob extends TimerTask {

    	public void run() {
    		
    		sc.setAttribute("time", "");
    		
    		GameDao dao = new GameDao();
    		
    		try {
    			dao.timerJob();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    }
}

