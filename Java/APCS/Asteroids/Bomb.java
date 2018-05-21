import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

public class Bomb {

    protected int[] seconds;
    protected int[] frames;
    protected int totalPieces;
    protected int currentPieces;
    protected PApplet applet;
    protected float[] xPos, yPos, length;
    protected double[] xSpeed, ySpeed;
    protected double totalVelocity;
    protected int allSeconds = 0;
    protected int allFrames = 0;
    protected SpaceShip ship;
    protected int strokeR, strokeG, strokeB, stroke;
    protected int strokeA = 255;
    protected int r = 255;
    protected int g = 255;
    protected int b = 255;
    

    public Bomb(PApplet applet_,SpaceShip ss, int totalPieces_, double totalVelocity_, float length_){
        applet = applet_;
        totalPieces = totalPieces_;
        totalVelocity = totalVelocity_;
        ship = ss;

        xPos = new float[totalPieces];
        yPos = new float[totalPieces];
        xSpeed = new double[totalPieces];
        ySpeed = new double[totalPieces];
        length = new float[totalPieces];
        for (int i = 0; i < totalPieces; i++){
            //Set the current coordinates as the starting points of the bomb fragments
            xPos[i] = ship.getX();
            yPos[i] = ship.getY();

            //angles to calculate X and Y velocities
            double angle = i* 360/totalPieces;
            //Calculating X and Y velocities
            xSpeed[i] = (totalVelocity * Math.cos( Math.toRadians(angle) )  );
            ySpeed[i] = (totalVelocity * Math.sin( Math.toRadians(angle) )  );
            //Size of fragments
            length[i] = length_;

        }
        seconds = new int[totalPieces];
        frames = new int[totalPieces];

        allFrames = 0;
        allSeconds = 0;

        currentPieces = 0;

    }

    //use trig to calculate position and vectors orz

    public Bomb(PApplet applet_){

    }

    public Bomb(PApplet applet_ , int totalPieces_, float[] startingPosX, float[] startingPosY, double[] xSpeed_, double[] ySpeed_ , float[] length_){
        applet = applet_;
        totalPieces = totalPieces_;
        xPos = startingPosX;
        yPos = startingPosY;
        xSpeed = xSpeed_;
        ySpeed = ySpeed_;
        length = length_;

        seconds = new int[totalPieces];
        frames = new int[totalPieces];

        allFrames = 0;
        allSeconds = 0;

        currentPieces = 0;

    }

    public void reset(){
        currentPieces = 0;
    }

    public int getTotalFrag(){
        return totalPieces;
    }

    public int getCurrentFrag(){
        return currentPieces;
    }

    public void move(){

        if( allSeconds >= 1){
            for(int i = 0; i < totalPieces; i++){
                xPos[i] += xSpeed[i];
                yPos[i] += ySpeed[i];
            }
        }

        timer();

    }

    public void draw(){
        
        applet.strokeWeight(stroke);
        applet.stroke(strokeR,strokeG,strokeB,strokeA);
        applet.fill(r,g,b);

        for(int i = 0; i < totalPieces; i++){
            applet.ellipse(xPos[i],yPos[i], length[i],length[i]);
        }
    }

    public void timer(){
        allFrames++;
        if (allFrames >= 60){
            allSeconds++;
            allFrames = 0;
        }

    }

    public float[] getXPos(){
        return xPos;
    }

    public float[] getYPos(){

        return yPos;
    }

    public float[] getLength(){

        return length;
    }

    public boolean checkContact(List<Asteroid> asteroids, int asteroidIndex){
        for( int i = 0; i < totalPieces ; i++){

            if (applet.dist( xPos[i], yPos[i],  asteroids.get(asteroidIndex).getX(), asteroids.get(asteroidIndex).getY() ) <= length[i] + 10 ){
                return true;
            }

        }
        return false;
    }

}

