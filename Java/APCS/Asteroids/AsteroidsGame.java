import processing.core.PApplet;
import java.applet.Applet;

import java.util.List;
import java.util.ArrayList;

import java.applet.AudioClip;
import java.net.URL;
public class AsteroidsGame extends PApplet
{
    //your variable declarations here

    //Timer
    private int seconds = 0;
    private int frames = 0;

    //BGM
    private URL bgmURL = AsteroidsGame.class.getResource("project_latitude.wav");

    //spaceship
    SpaceShip s1;

    //For multiple keyboard inputs at one time
    private boolean acc = false;
    private boolean rotateL = false;
    private boolean rotateR = false;
    private boolean shoot = false;
    private boolean backwards = false;

    //for testing because I suck at using the intended controls. Temporary
    private int mouseMode = -1;

    //Star array
    private Star[] stars;

    //Asteroid array list
    private List<Asteroid> asteroids = new ArrayList<Asteroid>();
    private int numAsteroids = (int)(Math.random() * 30) + 20;

    // bullets
    private List<Bullet> bullets = new ArrayList<Bullet>();
    private List<BackwardBullet> backBullets = new ArrayList<BackwardBullet>();

    //bomb stuff
    private int bombFrag = 8;
    private int bombCD = -1;

    //pentagram stuff
    private int pentagramFrag = 10;
    private int pentagramCD = -1;

    //firefly stuff
    private int firefliesFrag = 12;
    private int fireflyCD = -1;

    //jellyfish stuff
    private int jellyfishFrag = 15;
    private int jellyfishCD = -1;

    //loop stuff
    private int loopFrag = 36;
    private int loopCD = -1;

    //masterSpark stuff
    private int masterSparkFrag = 700; 
    private int masterSparkCD = -1;

    //bombs
    private Bomb bomb,fireflies, loop, jellyfish, pentagram,masterSpark;

    //points
    private int points = 100;
    private int asteroidPoints = 100;
    //infinite points
    private int infinitePoints = -1;

    public static void main(String[] args)
    {
        PApplet.main(new String[] {"AsteroidsGame"});
    }

    public void setup() 
    {
        //your code here
        size(800,600);
        background(0);
        s1 = new SpaceShip(this);
        s1.setX(300);
        s1.setY(300);
        stars = new Star[ (int)(Math.random()*150)+ 100 ];

        //fill Star array
        for (int i = 0; i < stars.length; i++){
            stars[i] = new Star( this, (float)(Math.random()*3) + 1, (float)(Math.random()*width), (float)(Math.random()*height) );
        }

        //Create asteroids
        for (int i = 0; i < numAsteroids; i++){
            
            asteroids.add( new Asteroid(this) );
            
            //create asteroids not too close to ship
            while( (asteroids.get(i).getX() <= s1.getX() + 100 && asteroids.get(i).getX() >= s1.getX() - 100) && 
            (asteroids.get(i).getY() <= s1.getY() + 100 && asteroids.get(i).getY() >= s1.getY() - 100 )){
                asteroids.remove(i);
                asteroids.add(i, new Asteroid(this) );
            }
        }

        //initialize BGM
        AudioClip bgm = Applet.newAudioClip(bgmURL);
        //Play BGM
        bgm.loop();
        
        System.out.println("Instructions: \n" +
                            "Rotate with the left and right arrow keys \n" +
                            "Press Up to accelerate \n" +
                            "Press number keys for bombs. ");


    }

    public void draw() 
    {
        //your code here

        background(0);

        //Controls ship
        if(acc){
            s1.accelerate(.1);
        }
        if(rotateL){
            s1.rotate(-10);
        }
        if(rotateR){
            s1.rotate(10);
        }

        //Display stars
        for(int i = 0; i < stars.length; i++){
            stars[i].display();
        }

        //Only show and move if it is on cooldown
        if(bombCD >= 0){
            bomb.move();
            bomb.draw();
        }

        if(pentagramCD >= 0){
            pentagram.move();
            pentagram.draw();
        }

        if(fireflyCD >= 0){
            fireflies.move();
            fireflies.draw();
        }

        if (jellyfishCD >= 0){
            jellyfish.move();
            jellyfish.draw();
        }

        if(loopCD >= 0){
            loop.move();
            loop.draw();
        }

        if(masterSparkCD >= 23){
            masterSpark.move();
            masterSpark.draw();
        }

        if ( shoot ){
            bullets.add( new Bullet(this, s1) );
            if(infinitePoints == -1){
                points -= 1;
            }
            if(backwards){
                backBullets.add( new BackwardBullet(this,s1) );
                if(infinitePoints == -1){
                    points -= 1;
                }
            }
        }

        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).move();
            bullets.get(i).show();
            if(bullets.get(i).getSeconds() > 1){
                bullets.remove(i);

            }
        }

        for(int i = 0; i < backBullets.size(); i++){
            backBullets.get(i).move();
            backBullets.get(i).show();
            if(backBullets.get(i).getSeconds() > 1){
                backBullets.remove(i);

            }
        }

        //recreate asteroids after cooldown (1 second)
        if(asteroids.size() < numAsteroids && frames == 0){
            asteroids.add( new Asteroid(this) );
            while( (asteroids.get ( asteroids.size()-1 ).getX() <= s1.getX() + 100 && asteroids.get( asteroids.size()-1 ).getX() >= s1.getX() - 100) && 
            (asteroids.get( asteroids.size()-1 ).getY() <= s1.getY() + 100 && asteroids.get( asteroids.size()-1 ).getY() >= s1.getY() - 100 )){
                asteroids.remove( asteroids.size()-1 );
                asteroids.add(new Asteroid(this) );
            }
        }

        //add more asteroids as game progresses
        if(seconds%10 == 0){
            numAsteroids++;
        }

        //check for collisions 
        for(int i = 0; i < asteroids.size(); i++){
            if( bombCD >= 0 && bomb.checkContact(asteroids, i) ){
                asteroids.remove(i);
                points += asteroidPoints;
                //asteroids.add( new Asteroid(this) );
            }

            else if(pentagramCD >= 0 && pentagram.checkContact(asteroids, i) ){
                asteroids.remove(i);
                points += asteroidPoints;
                //asteroids.add( new Asteroid(this) );
            }

            else if(fireflyCD >= 0 && fireflies.checkContact(asteroids, i) ){
                asteroids.remove(i);
                points += asteroidPoints;
                //asteroids.add( new Asteroid(this) );
            }

            else if ( jellyfishCD >= 0 && jellyfish.checkContact(asteroids, i)){
                asteroids.remove(i);
                points += asteroidPoints;
                //asteroids.add( new Asteroid(this) );
            }

            else if(loopCD >= 0 && loop.checkContact(asteroids, i)){
                asteroids.remove(i);
                points += asteroidPoints;
                //asteroids.add( new Asteroid(this) );
            }

            else if(masterSparkCD >= 0 && masterSpark.checkContact(asteroids, i)){
                asteroids.remove(i);
                points += asteroidPoints;
                //asteroids.add( new Asteroid(this) );
            }

            else if (dist( asteroids.get(i).getX(), asteroids.get(i).getY(), s1.getX(), s1.getY() ) <= 20){
                asteroids.remove(i);
                points -= 500;

                //asteroids.add( new Asteroid(this) );
            }

            else if (checkCollision(bullets, asteroids, i) ){
                asteroids.remove(i);
                if(infinitePoints == -1){
                    points += asteroidPoints;
                }
            }

            else if (checkCollisionB(backBullets, asteroids, i) ){
                asteroids.remove(i);
                points += asteroidPoints;
            }

            else{
                asteroids.get(i).move();
                asteroids.get(i).show();

            }

        }

        //for testing because I suck at using the intended controls. Temp
        if(mouseMode > 0){
            s1.setX(pmouseX);
            s1.setY(pmouseY);
        }
        else{

            s1.move();
        }
        s1.show();

        //Timer, decreasing cooldown time
        frames++;
        if(frames >= 60){
            seconds++;
            frames = 0;
            bombCD--;
            fireflyCD--;
            loopCD--;
            jellyfishCD--;
            pentagramCD--;
            masterSparkCD--;
        }

        //show points

        if(infinitePoints == 1){
            textFont(createFont("Courier New Bold",48,true), 48);
            fill(255,0,0);
            text("∞", 20, height-20);

        }
        else{
            textFont(createFont("Courier New Bold",24,true), 24);
            fill(255);
            text(points, 20, height-30);
        }
        
        
        

    }

    public void keyPressed(){
        if (key == CODED) {
            //accerlerate
            if (keyCode == UP) {

                acc = true;

            }

            //rotate left
            if (keyCode == LEFT){
                rotateL = true;
            }

            //rotate right
            if (keyCode == RIGHT){
                rotateR = true;
            }

            //backwards bullet
            if(keyCode == SHIFT && shoot && (points >= 1 || infinitePoints == 1) ){
                backwards = true;
            }

        }
        //shoot
        //if ( key == CODED && keyCode == SPACE ){
        if ( key == ' '  ){
            shoot = true;

        }

        //hyperspace
        if (key == 'h'){
            //hyperspace
            s1.setPointDirection( (int)(Math.random()*360) );
            s1.setDirectionX(0);
            s1.setDirectionY(0);
            s1.setX( (int)(Math.random()*width) );
            s1.setY( (int)(Math.random()*height) );

        }

        //mouse mode
        if (key == 'm'){
            mouseMode *= -1;
        }

        //infinite points
        if (key == 'i'){
            infinitePoints *= -1;
        }

        //set bomb
        // (applet, ship, number, speed, length

        if ( (key == '1' && bombCD <= 4) && (points >= 100 || infinitePoints == 1) ){

            //Creating the bomb
            bomb = new Bomb(this, s1, bombFrag, 8, 5);

            bombCD = 5;

            if(infinitePoints == -1){
                points -= 100;
            }

        }

        //The rest is basically the same as the normal bomb
        if ( (key == '2' && pentagramCD <= 0) && (points >= 500 || infinitePoints == 1) ){

            pentagram = new Pentagram(this, s1, pentagramFrag, 5, 10);

            pentagramCD = 5;
            if(infinitePoints == -1){
                points -= 500;
            }
        }

        if ( (key == '3' && fireflyCD <= 0) && (points >= 1000 || infinitePoints == 1) ){

            fireflies = new Firefly(this, s1, firefliesFrag, 6, 3);

            fireflyCD = 8;
            if(infinitePoints == -1){
                points -= 1000;
            }
        }

        //Does not work exactly how I want it to but whatever ヽ(o｀Д´o)ﾉ
        if ( (key == '4' && jellyfishCD <= 0) && (points >= 2000 || infinitePoints == 1) ){

            jellyfish = new Jellyfish(this, s1, jellyfishFrag, 3,15);

            jellyfishCD = 12;
            if(infinitePoints == -1){
                points -= 2000;
            }
        }

        if ( (key == '5' && loopCD <= 0) && (points >= 5000 || infinitePoints == 1) ){

            loop = new Loop(this, s1, loopFrag, 3, 8);

            loopCD = 24;
            if(infinitePoints == -1){
                points -= 5000;
            }
        }

        //Namesake is a "Freaking huge magical laser." I didn't feel like making that.
        //let's just say that the lag it causes is like a slow time effect...
        if ( (key == '0' && masterSparkCD <= 0) && ( points >= 7777 || infinitePoints == 1) ){

            masterSpark = new MasterSpark(this, s1, masterSparkFrag, 7, 7);
            masterSparkCD = 30;
            if(infinitePoints == -1){
                points -= 7777;
            }
        }

    }

    public void keyReleased(){
        if (key == CODED) {
            if (keyCode == UP) {
                acc = false;
            }

            if (keyCode == LEFT){
                rotateL = false;
            }

            if (keyCode == RIGHT){
                rotateR = false;
            }

            if(keyCode == SHIFT && (points >= 1 || infinitePoints == 1) ){
                backwards = false;
            }

        }
        if ( key == ' '){
            shoot = false;
        }

    }

    public boolean checkCollision(List<Bullet> bullets, List<Asteroid> asteroids, int index){
        for (int i = 0; i < bullets.size(); i++){
            if( dist( asteroids.get(index).getX(), asteroids.get(index).getY(), bullets.get(i).getX(), bullets.get(i).getY() ) <= 20 ){
                //asteroids.remove(index);
                bullets.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionB(List<BackwardBullet> bullets, List<Asteroid> asteroids, int index){
        for (int i = 0; i < bullets.size(); i++){
            if( dist( asteroids.get(index).getX(), asteroids.get(index).getY(), bullets.get(i).getX(), bullets.get(i).getY() ) <= 20 ){
                //asteroids.remove(index);
                bullets.remove(i);
                return true;
            }
        }
        return false;
    }
}

