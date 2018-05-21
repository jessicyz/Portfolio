/* Processing.JS sketch */
/*
	This is where all your code will go.
*/

//creating lists for the different layers
Building[] b3List = new Building[100];
Building[] b2List = new Building[10];
Building[] bList = new Building[10];

// create the people objects
Person person;
Person cape;

//create variables to control lighting
var light = 0;
var sun = 0;
var day = 1;

void setup() {
	size(800, 600);
	background(0, 193, 252);
	//fill(104,162,126);
	//rect(0,450,800,190);
	
	//Sun
	fill(255,255,0);
	ellipse(200,100,100,100);
	
	//Ground
	fill(10, 16, 128);
	rect(0, 525, 800,75);
	
	//create a new object Person
	person = new Person();
	cape = new Person();
	
	//create objects in the lists of buildings 
	for (var i = 0; i < bList.length; i++) {
		bList[i] = new Building();
	}
	
	for (var i = 0; i < b2List.length; i++) {
		b2List[i] = new Building(1);
	}
	
	for (var i = 0; i < b3List.length; i++) {
		b3List[i] = new Building(1,3);
	}
	
	//Music
	var win = new Audio('onett.mp3');
    win.play();
    win.loop = true;
}

// variables for moving the cape
var capePos;
var out;
capePos = 200;
//cape's height range
var y = random(-10,150);

void draw() {
	background(0, 193, 252);
	fill(10, 16, 128);
	rect(0, 525, 800, 75);
	
	//Change color of BG
	background(0, 193-light, 252-light);
	if (day === 1){
		light += 0.5;
	} else if (day === 0){
		light -= 0.5;
	}
	
	if (light === 193){
		day = 0;
	} else if (light === 0){
		day = 1;
	}
	
	//Sun
	//Change color of sun
	fill(255,255,0+sun);
	if (day === 1){
		sun += 255/386;	
	} else if (day === 0){
		sun -= 255/386;
	}
	ellipse(200,100,100,100);
	
	fill(255);
	//stars	
	rect(random(0,800),random(0,600),random(1,5),random(1,5));
	rect(random(0,800),random(0,600),random(1,5),random(1,5));
	rect(random(0,800),random(0,600),random(1,5),random(1,5));
	rect(random(0,800),random(0,600),random(1,5),random(1,5));
	rect(random(0,800),random(0,600),random(1,5),random(1,5));
	rect(random(0,800),random(0,600),random(1,5),random(1,5));
	rect(random(0,800),random(0,600),random(1,5),random(1,5));
	rect(random(0,800),random(0,600),random(1,5),random(1,5));
	
	//Layer 3
	for (var i = 0; i< b3List.length; i++){
		// the interval at which buildings appear compared to the one in front of it
		var interval = random(780,800);
		
		// if it is not the first object and the distance of the building before the object
		// is smaller than the interval, then make a new building appear
		if ((i!==0) && (b3List[i-1].xPos < interval)){
			b3List[i].drawLayer();
			b3List[i].moveLayer();
		} 
		else if (i===0) {
			b3List[i].drawLayer();
			b3List[i].moveLayer();
		}
		
		// create a new object in the list everytime a building goes off screen
		if (b3List[i].xPos + b3List[i].w <= 0) {	
			b3List = append(b3List, new Building(2,1));
			//deletes the objects after they go from screen
			b3List.shift();	
			b3List[i].drawLayer();
			b3List[i].moveLayer();
		}
	}

	//Layer 2
	for (var i = 0; i< b2List.length; i++){
		var interval = random(775,800);	
		if ((i!==0) && (b2List[i-1].xPos < interval)){
			
			b2List[i].drawLayer();
			b2List[i].moveLayer();
		} 
		else if (i===0) {
			b2List[i].drawLayer();
			b2List[i].moveLayer();
		}
		// add a building once a building is off screen (same for all layers)
		if (b2List[i].xPos + b2List[i].w <= 0) {
			b2List = append(b2List, new Building(2));
			b2List.shift();	
			b2List[i].drawLayer();
			b2List[i].moveLayer();
		}
	}


	for (var i = 0; i< bList.length; i++){
		var interval = random(750,775);
		if ((i!==0) && (bList[i-1].xPos < interval)){
			bList[i].drawLayer();
			bList[i].moveLayer();	
		} else if (i===0) {
			bList[i].drawLayer();
			bList[i].moveLayer();
		}
		
		// add a building once a building is off screen (same for all layers)
		if (bList[i].xPos + bList[i].w <= 0) {	
			bList = append(bList, new Building());
			bList.shift();	
			bList[i].drawLayer();
			bList[i].moveLayer();
			console.log(bList.length);
		}
		/*Building[] aList = subset(bList, 1);
				bList = aList;*/
		
		// accesses the height of a building and inputs it for the y location of the person
		if (bList[i].xPos > 100 && bList[i].xPos < (100+bList[i].w)){
			person.drawPerson(bList[i].h);
		}
		
		
	}
	
	fill(10, 16, 128);
	rect(0, 525, 800, 75);
	
	//makes it so that the cape appears randomly onto the screen at random heights
	if ((cape.x - 100) > 800) {
		out += 1;
		if (out > capePos) {
			cape.x = 0;
			console.log(cape.x);
			y = random(-10, 150);
		}
	}
	// moves the cape
	cape.drawPerson(0,y);
	cape.moveCape();
}
		
class Building {
	//width and height of one building
	var w;
	var h;
	
	// color variables
	var r;
	var g;
	var b;
	
	//speed of movement
	var speed;
	
	//position of building
	var xPos;
	var yPos;
	
	//Windows
	var windowSize;
	var windowPosX;
	var windowPosY;
		
	// front layer buildings	
	Building() {
		w = random(100, 200);
		h = random(150, 300);
		r = 10;
		g = 16;
		b = 128;
		xPos = 800+w;
		yPos = 600 - h;
		speed = 8;
		windowSize = random(10,20);
		windowPosX = random(10, w-30-windowSize);
		windowPosY = random(10, h - 100 - windowSize);
	}
	
	// second layer buildings
	Building(s) {
		w = random(75, 150);
		h = random(300, 400);
		r = 47;
		g = 128;
		b = 250;
		xPos = 800+w;
		yPos = 600 - h;
		speed = 4;
		windowSize = random(7,15);
		windowPosX = random(10, w-10 -windowSize);
		windowPosY = random(20, h - windowSize);
	}
	
	// third layer buildings
	Building(s,r) {
		w = random(10, 40);
		h = random(450, 630);
		r = 195;
		g = 229;
		b = 255;
		xPos = 800+w;
		yPos = 600 - h;
		speed = 2;
		windowSize = random(3,8);
		windowPosX = random(10, w-5 -windowSize);
		windowPosY = random(20, h - windowSize);
	}
	
	//create rectangle right outside the screen on the bottom to fill in gaps		
	void drawLayer(){
		noStroke();
		fill(r, g, b);
		rect(xPos, yPos, w, h);
		
		fill(255);
		rect(xPos+windowPosX,yPos+windowPosY,windowSize,windowSize);
	}
	
	//move building along the x axis
	void moveLayer() {
		xPos -= speed;
	}
	
}

class Person {
	var xPos;
	var h;
	var x;
	
	Person () {
		xPos = 200;
	}
	
	// draws ground person
	// get a parameter in here because I don't want to create a new object in draw(), but I do need the height of the current building
	void drawPerson (h1) {
		h = h1;
		// add extra space between Person and buildings
		var extra = 0;
		fill(8, 8, 8);
		//body
		rect(xPos,600-(h+70+extra), 15, 30);
		//legs
		rect(xPos, 600-(h+40+extra),30, 5);
		rect(xPos+30,600-(h+40+extra),5,25);
		rect(xPos,600-(h+40+extra),5,40);
		//arms
		rect(xPos, 600-(h+67+extra), 35,4);
		rect(xPos, 600-(h+60+extra), 35,4);
		//head
		ellipse(xPos+7, 600-(h+80+extra), 15, 15);
	}
	
	//draws cape
	void drawPerson(b, y) {
		//body
		rect(-50+x,100+y,30,15);
		//legs
		rect(-100+x, 100+y,40, 5);
		rect(-100+x, 110+y,40, 5);
		//arms
		rect(-20+x, 111+y, 35,4);
		rect(-20+x, 100+y, 35,4);
		//cape
		noStroke();
		fill(255, 0, 0);
		quad(-20+x,111+y,-20+x,100+y,-80+x,90+y,-80+x,120+y);
		//head
		fill(0, 0, 0);
		ellipse(-25+x, 100+y, 15, 15);
	}
	
	// moves the cape across the screen
	void moveCape() {
		x = x + 1;
	}
	
}
		