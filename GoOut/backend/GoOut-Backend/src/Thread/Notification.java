package Thread;
import java.util.Calendar;

import javax.websocket.*;

import Server.GoOutServer;

public class Notification extends Thread{
	private GoOutServer gos; 
	private Session s; 
	private Calendar currentTime; 
	private Calendar setTime; 
	private int minuteDif; 
	private String eventName; 
	// passes in the notification time 
	public Notification(Session s, GoOutServer gos, Calendar setTime, int minuteDif, String eventName){
		this.s = s; 
		this.gos = gos; 
		this.setTime = setTime; 
		this.minuteDif = minuteDif;
		this.eventName = eventName; 
		this.start(); 
	}
	public void run(){
		// when there is a certain minuntes left 
		while(true){
			this.currentTime = Calendar.getInstance();
			System.out.println("Set Time 1: "+setTime.get(Calendar.MINUTE)+" currentTime "+currentTime.get(Calendar.MINUTE));
			if(differBy(setTime, currentTime, minuteDif)){
				System.out.println(Integer.toString(minuteDif)+" minutes before event starts!!!");
				gos.sendMessage(eventName+" is happening in "+Integer.toString(minuteDif)+" minutes.", s);
				break; 
			}
		}
		// when it is the exact time 
		while(true){
			this.currentTime = Calendar.getInstance();
			//System.out.println("Set Time 2: "+setTime.get(Calendar.MINUTE)+" currentTime "+currentTime.get(Calendar.MINUTE));
			if(sameTime(setTime, currentTime)){
				System.out.println("Event has now started!!!");
				gos.sendMessage(eventName+" is happening right now.", s);
				break;  
			}
		}
	}
	// returns true if the two calendars differ by a certain amount 
	public static boolean differBy(Calendar setTime, Calendar currentTime, int minute){
		int setYear = setTime.get(Calendar.YEAR);
		int currentYear = currentTime.get(Calendar.YEAR);
		int setMonth = setTime.get(Calendar.MONTH); 
		int currentMonth = currentTime.get(Calendar.MONTH);
		int setDate = setTime.get(Calendar.DATE);
		int currentDate = currentTime.get(Calendar.DATE);
		int setHour = setTime.get(Calendar.HOUR);
		int currentHour = currentTime.get(Calendar.HOUR);
		int setMinute = setTime.get(Calendar.MINUTE);
		int currentMinute = currentTime.get(Calendar.MINUTE);
//		System.out.println(setDate);
//		System.out.println(currentDate);
//		System.out.println(setDate!=currentDate);
		if(!(setYear==currentYear && setMonth==currentMonth && setDate==currentDate && setHour==currentHour)){
			return false; 
		}
//		System.out.println(setMinute);
//		System.out.println(currentMinute);
		if(setMinute == currentMinute + minute){
			return true; 
		}
		return false; 
	}
	// returns true if the two calendars have the same time 
	public static boolean sameTime(Calendar setTime, Calendar currentTime){
		int setYear = setTime.get(Calendar.YEAR);
		int currentYear = currentTime.get(Calendar.YEAR);
		int setMonth = setTime.get(Calendar.MONTH); 
		int currentMonth = currentTime.get(Calendar.MONTH);
		int setDate = setTime.get(Calendar.DATE);
		int currentDate = currentTime.get(Calendar.DATE);
		int setHour = setTime.get(Calendar.HOUR);
		int currentHour = currentTime.get(Calendar.HOUR);
		int setMinute = setTime.get(Calendar.MINUTE);
		int currentMinute = currentTime.get(Calendar.MINUTE);
		if(setYear==currentYear && setMonth==currentMonth && setDate==currentDate && setHour==currentHour && setMinute==currentMinute){
			return true; 
		}
		return false; 
	}
}
