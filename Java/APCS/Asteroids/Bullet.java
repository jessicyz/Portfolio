import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

public class Bullet extends Floater  
{   
    //your code here

    private SpaceShip ship;
    private int seconds = 0;
    private int frames = 0;

    public Bullet(PApplet applet_, SpaceShip theShip){
        super(applet_);

        ship = theShip;

        myColor = 255;
        myCenterX = ship.getX();
        myCenterY = ship.getY();

        myPointDirection = ship.getPointDirection();
        double dRadians = myPointDirection * Math.PI / 180;
        myDirectionX = 5 * Math.cos(dRadians) + ship.getDirectionX();
        myDirectionY = 5 * Math.sin(dRadians) + ship.getDirectionY();
    }

    public void setX(int x){
        myCenterX = x;
    }

    public int getX(){
        return (int)myCenterX;
    }

    public void setY(int y){
        myCenterY = y;
    }

    public int getY(){
        return (int)myCenterY;
    }

    public void setDirectionX(double x){
        myDirectionX = x;
    }

    public double getDirectionX(){
        return myDirectionX;
    }

    public void setDirectionY(double y){
        myDirectionY = y;
    }

    public double getDirectionY(){
        return myDirectionY;
    }

    public void setPointDirection(int degrees){
        myPointDirection = degrees;
    }

    public double getPointDirection(){
        return myPointDirection;
    }

    @Override
    public void show ()  //Draws the floater at the current position  
    {             
        applet.fill(0);   
        applet.stroke(255);    
        //convert degrees to radians for sin and cos         
        //double dRadians = myPointDirection * (Math.PI / 180);                 
        //int xRotatedTranslated, yRotatedTranslated;    
        //applet.beginShape();         
        applet.ellipse((float)myCenterX, (float)myCenterY, 5,5);

        frames++;
        if (frames >= 60){
            seconds++;
            frames = 0;
        }
    } 

    public int getSeconds(){
        return seconds;
    }
    public int getFrames(){
        return frames;
    }
}
