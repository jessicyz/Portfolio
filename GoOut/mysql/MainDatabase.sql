-- DROP is a dangerous statement
DROP DATABASE IF EXISTS GoOutDB;
CREATE DATABASE GoOutDB;

USE GoOutDB;

CREATE TABLE Users(

	userID INT(11) PRIMARY KEY AUTO_INCREMENT,

    fullName VARCHAR(999) NOT NULL,
    username VARCHAR(999) NOT NULL,
    password VARCHAR(999) NOT NULL,
    privateUser BOOLEAN NOT NULL
);

INSERT INTO Users(fullName, username, password, privateUser)
	VALUES	("Jess Zhu", "jessicyz", "password", false),
					("Sina Kwhatever", "username", "Passw0rd", false),
                    ("Jeffrey Hernan", "jefnandez", "herfrey", true),
                    ("Steven Xu", "Si", "Yuan", true),
                    ("Jeffrey Miller", "JMillz", "PhD", false);




    
CREATE TABLE Events(
	eventID INT(11) PRIMARY KEY AUTO_INCREMENT,
    eventName VARCHAR(999) NOT NULL,
    userID INT(11) NOT NULL,
    location VARCHAR(999) NOT NULL,
    month INT(2) NOT NULL,
    day INT(2) NOT NULL,
    startTime TIME NOT NULL,
    endTime TIME,
    description VARCHAR(500),
    privateEvent BOOLEAN NOT NULL,
    
    
    FOREIGN KEY fk1(userID) REFERENCES Users(userID)
    
);

INSERT INTO Events(eventName, userID, location, month, day, startTime, endTime, privateEvent)
	VALUES ("Hanging Out with the Boiz", 2, "SAL", 4, 1, 22000, 210000, true),
					("Free Food Hunt", 
					(SELECT userID
						FROM	Users
						WHERE	fullName = 'Jess Zhu'
                        ),
					"PKS", 2, 21, 143000, 153000, false),
                    ("Movie Marathon", 4, "VKC",11,11, 093000, 105000, false)
					
                     
                    ; 
                    
INSERT INTO Events(eventName, userID, location, month, day, startTime, endTime, description, privateEvent)
	VALUES ("DUNGEON TRUCK", 
                    (SELECT userID
						FROM	Users
						WHERE	username = 'Si'), "SGM", 12,31, 040000, 100000, "DUNGEON TRUCK! DUNGEON TRUCK!", true), 
                    ("An Event", 3, "ANN", 9,8, 123000, 130000, "come", true),
                    ("Alternative Applications of Juncture Administration: The Science of Cartography Redefined as Art via Content Creation in the Ongoing and Evolving Paradigm of the Post-Network Media Environment. What Does It Mean To You? (featuring GoOut)", 1, "GFS", 11,21,093000, 100000, "It's all GoOut", true),
                     ("Dragon hunting", 3, "THH", 10,31,123000, 130000, "Dragoons don't interact", false),
                     ("Fight to the Death", 2, "MUS", 3,20,113000, 180000, "BYO instruments", false),
                     ("CSCI201 Study Group", 4, "SAL", 11,11,111111,111112, "Studying for the CSCI201 final", true),
                     ("Turkey Fest", 5, "RTH", 11, 22, 210000, 230000, "Thanksgiving meal", true),
                     ("Accordian+Bagpipe Concert", 2, "UV", 12, 24, 210000, 230000, "One person. Two instrument", false),
                     ("Depricate SAL", 2, "UV", 3, 15, 010000, 230000, "March for a new, less depressing computer science building. SAL -> RTH -> SAL -> Occupy the 24 hour business buildings", true)
                     
                    ; 






CREATE TABLE FollowingUsers(
	followUserID INT(11) PRIMARY KEY AUTO_INCREMENT,
    followedUserID INT(11) NOT NULL,
    followedByUserID INT(11) NOT NULL,


    FOREIGN KEY fk1(followedUserID) REFERENCES Users(userID),
    FOREIGN KEY fk2(followedByUserID) REFERENCES Users(userID)
);

INSERT INTO FollowingUsers(followedUserID, followedByUserID)
	VALUES	(1,4),
					(
						(SELECT userID
						FROM Users
						WHERE username = 'jefnandez'),
							
						(SELECT userID
						FROM Users
                        WHERE fullName = 'Sina Kwhatever')
                    );




CREATE TABLE FollowingEvents(
	followEventID INT(11) PRIMARY KEY AUTO_INCREMENT,
    -- event following
    eventID INT(11) NOT NULL,	
    -- person following the event
    userID INT(11) NOT NULL,
    
--     notificationTime INT(11),

    FOREIGN KEY fk1(userID) REFERENCES Users(userID),
    FOREIGN KEY fk2(eventID) REFERENCES Events(eventID)
);



INSERT INTO FollowingEvents(eventID, userID)
	VALUES	(4,2),
					(
						(SELECT eventID
							FROM Events
                            WHERE eventName = 'An Event'),
						(SELECT userID
							FROM Users
                            WHERE username = 'jessicyz')
						
                    ),
                    (3,4),
				(
					
					(SELECT eventID
						FROM Events 
                        WHERE userID = 1
                        AND eventName LIKE '%juncture%'),
                        (SELECT userID 
						FROM Users 
                        WHERE fullName = 'Sina Kwhatever')
                
                );