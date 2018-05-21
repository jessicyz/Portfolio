package Information;

import java.util.*;

// the LoginUser class extends from the ordinary User class 
public class LoginUser extends User{
	private String username; 
	private String lName; 
	private String fName; 
	private String password; 
	private int ID; 
	private Vector<Event> EventList; 
	private Vector<User> followingUsers; 
	private Map<Event, Integer> followingEventsNotificationTime; 
	public LoginUser(String username, String lName, String fName, String password, int ID){
		super(username);
		this.username = username; 
		this.lName = lName; 
		this.fName = fName; 
		this.password = password; 
		this.ID = ID; 
		this.EventList = new Vector<Event>();
		this.followingUsers = new Vector<User>(); 
		this.followingEventsNotificationTime = new HashMap<Event, Integer>();
	}
	// getters and setters for each private data member 
	public String getUsername(){
		return this.username; 
	}
	public void setUsername(String newUsername){
		this.username = newUsername; 
	}
	public String getLName(){
		return this.lName;
	}
	public void setLName(String newLName){
		this.lName = newLName; 
	}
	public String getFName(){
		return this.fName;
	}
	public void setFName(String newFName){
		this.fName = newFName; 
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String newPassword){
		this.password = newPassword; 
	}
	public int getID(){
		return this.ID; 
	}
	public void setID(int newID){
		this.ID = newID; 
	}
	public Vector<Event> getEventList(){
		return this.EventList; 
	}
	public void addEvent(Event e){
		this.EventList.add(e); 
	}
	public void deleteEvent(Event e){
		this.EventList.remove(e);
	}
	public Vector<User> getFollowingUsers(){
		return this.followingUsers; 
	}
	public void addFollowingUsers(User u){
		this.followingUsers.add(u); 
	}
	public void removeFollowingUsers(User u){
		this.followingUsers.remove(u); 
	}
	public Map<Event, Integer> getFollowingEventsNotificationTime(){
		return this.followingEventsNotificationTime; 
	}
	public void addFollowingEventsNotificationTime(Event e, int time){
		this.followingEventsNotificationTime.put(e, time); 
	}
	public void setFollowingEventsNotificationTime(Event e, int time){
		this.followingEventsNotificationTime.put(e, time); 
	}
	public void removeFollowingEventsNotificationTime(Event e, int time){
		this.followingEventsNotificationTime.remove(e); 
	}
}
