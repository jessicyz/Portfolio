package Information;
import java.io.*;
import java.util.*;

public class UserEventInformationMessage implements Serializable{
	public static final long serialVersionUID = 1;
	private String eventName; 
	private String username; 
	// in minutes 
	private int notificationTime;
	private Calendar eventTime; 
	public UserEventInformationMessage(String eventName, String username, int notificationTime, Calendar eventTime){
		this.eventName = eventName; 
		this.username = username; 
		this.notificationTime = notificationTime; 
		this.eventTime = eventTime; 
	}
	public String eventName(){
		return this.eventName; 
	}
	public String getUsername(){
		return this.username; 
	}
	public int getNotificationTime(){
		return this.notificationTime;
	}
	public Calendar getEventTime(){
		return this.eventTime;
	}
}
