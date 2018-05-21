package constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
	public static void main(String[] args){
		
		
		String pattern =  "dd/MM/yyyy hh:mm:ss";
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		// set the time 
		Calendar setTime = new GregorianCalendar(2017,11-1,18,4,8,00);
		// get current time 
		
		Calendar currentTime = Calendar.getInstance();

		System.out.println("Current month: "+currentTime.get(Calendar.MONTH));

		System.out.println(dateFormat.format(setTime.getTime())); 
		System.out.println(dateFormat.format(currentTime.getTime())); 
		
		System.out.println(differBy(setTime, currentTime, 2));
		
//		Calendar a1Date = null; 
//		try{
//			a1Date = simpleDateFormat.parse("18/11/2017 8:20:00");
//			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			System.out.println(dateFormat.format(a1Date));
//			System.out.println();
//		}catch(ParseException e){
//			e.printStackTrace();
//		}
	}
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
		System.out.println(setDate);
		System.out.println(currentDate);
		System.out.println(setDate!=currentDate);
		if(!(setYear==currentYear && setMonth==currentMonth && setDate==currentDate && setHour==currentHour)){
			return false; 
		}
		System.out.println(setMinute);
		System.out.println(currentMinute);
		if(setMinute == currentMinute - minute){
			return true; 
		}
		return false; 
	}
	
}
