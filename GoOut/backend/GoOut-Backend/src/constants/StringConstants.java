package constants;

public class StringConstants {
	public static final String INVALID_USERNAME = "Invalid Username";
	public static final String TAKEN_USERNAME = "Username has already been taken";
	public static final String INVALID_PASSWORD = "Invalid password";
	
	//parameters for the httprequest.
	//update these as need be to match the ionic backend.
	public static final String PARAM_USERNAME = "username";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_FIRST_NAME = "firstname";
	public static final String PARAM_LAST_NAME = "lastname";
	public static final String PARAM_CONFIRM_PASSWORD ="confirmPassword";

	//database
	public static final String DB_URL = "cs201.cll9sbto0nla.us-west-1.rds.amazonaws.com"; 
	public static final String MAIN_DB_NAME = "testDB";
	public static final String DB_USER = "master";
	public static final String DB_PASSWORD = "masterpassword";
	
	//backend on AWS
	public static final String BACKEND_URL = "http://goout.us-west-1.elasticbeanstalk.com/";
	public static final String WS_URL = "wss://a10f19d73tzk4o.iot.us-east-2.amazonaws.com.iot.us-east-2.amazonaws.com/mqtt/";
//	a10f19d73tzk4o.iot.us-east-2.amazonaws.com
	//"https://iot.us-west-1.amazonaws.com/endpoint"
}
