package Information;

// the object for storing date information 
public class Date {
	private int day; 
	private int month; 
	private Time start; 
	private Time end;
	public Date(int day, int month, Time start, Time end){
		this.day = day; 
		this.month = month; 
		this.start = start; 
		this.end = end; 
	}
	// getters and setters 
	public int getDay(){
		return this.day; 
	}
	public void setDay(int newDay){
		this.day = newDay; 
	}
	public int getMonth(){
		return this.month; 
	}
	public void setMonth(int newMonth){
		this.month = newMonth; 
	}
	public Time getStart(){
		return start; 
	}
	public void setStart(Time newStart){
		this.start = newStart; 
	}
	public Time getEnd(){
		return this.end; 
	}
	public void setEnd(Time newEnd){
		this.end = newEnd; 
	}
}
