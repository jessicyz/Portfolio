# GoOut 
A social media mobile app with easy events sharing. 
**Created November, 2017**

## Table of Contents 
- [About](#about)
- [Installation Instructions](#installation-instructions)
- [Built With](#built-with)
- [Features](#features)

## Team
- [Siyuan Xu](https://github.com/1009700427)
- [Jeffrey Hernandez](https://github.com/JeffreyDH)
- [Sina Krachiani](https://github.com/karachia)
- [Jessica Zhu](https://github.com/jessicyz)

## About 
GoOut is a social media app that allows for USC students sharing and adding events on a USC campus map.

## Installation Instructions: 
### Prerequisites: 
Requires Node.js, MySql database and Eclipse 
1. Download the repository 
2. ```cd GoOut/ionic-app```
3. Run ```ionic serve --lab``` to start the frontend 
4. Open Eclipse and start Tomcat 
	- You can launch multiple windows to test the multi-threading event notification functionality. 

## Built With 
- Frontend 
	- Ionic 
	- HTML/CSS
	- Google Map API
- Backend 
	- Java 
	- WebSocket 
	- MySQL

## Features 

### Login and Register Page 
When you first open the app, you will be prompted with a login screen. You can either login, register, or login as guest (with limited functionalities). 

![1](https://user-images.githubusercontent.com/22974252/35259148-e7c2214c-ffb8-11e7-935f-c59a0cbd56de.png)

### Main Interface 
When you haev successfully logged into the account, you will see a map serving as the main interface for this app. This map will be used to include event locations. 

![2](https://user-images.githubusercontent.com/22974252/35259242-899e337a-ffb9-11e7-819f-4dc7f107864f.png)

At the bottom of the main interface, there are two options of ```Find Events``` and ```Find People```. You can find either events or people by just typing into the text box. <br />
![3](https://user-images.githubusercontent.com/22974252/35259358-3ee6b856-ffba-11e7-8a26-b63a8545a166.png)
![5](https://user-images.githubusercontent.com/22974252/35259599-9c7632d4-ffbb-11e7-8db4-71bab1468882.png)
