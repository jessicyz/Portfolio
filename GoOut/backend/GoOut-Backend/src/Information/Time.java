package Information;

// models the hour, minute and seconds 
public class Time {
	private int hour; 
	private int minute; 
	private int second; 
	public Time(int hour, int minute, int second){
		this.hour = minute; 
		this.minute = minute; 
		this.second = second; 
	}
	// getters and setters 
	public int getHour(){
		return this.hour; 
	}
	public void setHour(int newHour){
		this.hour = newHour; 
	}
	public int getMinute(){
		return this.minute; 
	}
	public void setMinute(int newMinute){
		this.minute = newMinute; 
	}
	public int getSecond(){
		return this.second;
	}
	public void setSecond(int newSecond){
		this.second = newSecond; 
	}
}
