package Information;
import java.util.*; 

public class Event {
	private String eventName; 
	private String location; 
	private java.sql.Time startTime; 
	private java.sql.Time endTime; 
	public Event(String eventName, String location, java.sql.Time startTime, java.sql.Time endTime){
		this.eventName = eventName; 
		this.location = location; 
		this.startTime = startTime; 
		this.endTime = endTime; 
	}
	public String getEventName(){
		return this.eventName; 
	}
	public void setEventName(String str){
		this.eventName = str; 
	}
	public String getLocation(){
		return this.location; 
	}
	public void setLocation(String loc){
		this.location = loc; 
	}
	public java.sql.Time getStartTime(){
		return this.startTime; 
	}
	public void setStartTime(java.sql.Time newTime){
		this.startTime = newTime; 
	}
	public java.sql.Time getEndTime(){
		return this.endTime; 
	}
	public void setEndTime(java.sql.Time newTime){
		this.endTime = newTime; 
	}
	
}
