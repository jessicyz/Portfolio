package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import Thread.Notification; 

@ServerEndpoint(value = "/GoOutServer")
public class GoOutServer {

	private static volatile Vector<Session> sessionVector = new Vector<Session>();
	private static volatile ArrayList<Notification> notificationList = new ArrayList<Notification>(); 
	@OnOpen
	public void open(Session session) {
		System.out.println("Connection made!");
		sessionVector.add(session);
		broadcast("Another Trojan has joined GoOut. Make some friends!", session);
	}
	
	@OnMessage
	// the message will be in format dd/MM/yyyy/hh/mm/ss/minuteDif/eventName
	public void onMessage(String message, Session session) {
		int ARRAY_SIZE = 6;
		System.out.println("message: "+message);
		String[] parts = message.split("/");
		int day, month, year, hour, minute, minuteDif; 
		String eventName = ""; 
		day = Integer.parseInt(parts[0]);
		month = Integer.parseInt(parts[1]);
		year = Integer.parseInt(parts[2]);
		hour = Integer.parseInt(parts[3]);
		minute = Integer.parseInt(parts[4]);
		minuteDif = 1;
		eventName = parts[7]; 
		Calendar tempCalendar = new GregorianCalendar(year, month-1, day, hour, minute, 00);
		Notification n = new Notification(session, this, tempCalendar, minuteDif, eventName);
		System.out.println("Notification Added!!!");
		notificationList.add(n); 
	}
	
	@OnClose
	public void close(Session session) {
		System.out.println("Disconnecting!");
		sessionVector.remove(session);
	}
	
	@OnError
	public void error(Throwable error) {
		System.out.println("Error!");
	}
	private void broadcast(String message, Session sTemp){
		try {
			for(int x=0; x<sessionVector.size(); x++) {
				Session s = sessionVector.get(x);
				if(sTemp!=s){
					s.getBasicRemote().sendText(message);
				}
			}
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
//			close(session);
		}
	}
	public void sendMessage(String message, Session s){
		try{
			s.getBasicRemote().sendText(message);
		}catch(IOException ioe){
			System.out.println("ioe: " + ioe.getMessage());
		}
	}
}