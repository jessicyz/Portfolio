import processing.core.PApplet;

public class Loop extends Bomb {




    public Loop(PApplet applet_ , int totalPieces_, float[] startingPosX, float[] startingPosY, double[] xSpeed_, double[] ySpeed_ , float[] length_){
        super(applet_, totalPieces_, startingPosX, startingPosY, xSpeed_, ySpeed_, length_);

        r = 255;
        g = 27;
        b = 234;
    }
    
    public Loop(PApplet applet_,SpaceShip ss, int totalPieces_, double totalVelocity_, float length_){
        super(applet_, ss, totalPieces_, totalVelocity_, length_);
        r = 255;
        g = 27;
        b = 234;

        strokeR = 255;
        strokeG = 255;
        strokeB = 255;
        strokeA = 255;

        stroke = 3;
        
        

    }
    
    
    @Override
    public void move(){

        for (int i = 0; i < currentPieces; i++){

            if (frames[i] <= 20 && seconds[i] == 0){
                xPos[i] += xSpeed[i];
                yPos[i] += ySpeed[i];
            }

            if (seconds[i] == 4 && frames[i] >= 10){
                length[i] += .2;
            }

            if(seconds[i] >= 6 && seconds[i] < 10){
                xPos[i] -= xSpeed[i];
                yPos[i] -= ySpeed[i];
            }

            if (seconds[i] >= 10){
                //if (seconds >= 3){
                xPos[i] += xSpeed[i] * 1.5;
                yPos[i] += ySpeed[i]  * 1.5;

                length[i] -= .15;
            }

            frames[i] += 1;
            if (frames[i] >= 60){

                seconds[i] += 1;
                frames[i] = 0;

            }

        }

        timer();
        
        if(allFrames%10 == 0 && currentPieces < totalPieces){
            currentPieces++;

        }
    }

    @Override
    public void draw(){
        applet.stroke(255,255,255, 255);
        applet.strokeWeight(3);

        applet.fill(r,g,b);
        for (int i = 0; i < currentPieces; i++){

            applet.ellipse(xPos[i], yPos[i], length[i], length[i]);
        }
    }

}







