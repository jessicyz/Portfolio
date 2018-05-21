import processing.core.PApplet;
//public class Firefly implements Patterns {
public class Firefly extends Bomb {


    public Firefly(PApplet applet_ , int totalPieces_, float[] startingPosX, float[] startingPosY, double[] xSpeed_, double[] ySpeed_ , float[] length_){
        super(applet_, totalPieces_, startingPosX, startingPosY, xSpeed_, ySpeed_, length_);

        r = 193;
        g = 255;
        b = 193;

        //         r = (int)(Math.random()*100)+150;
        //         g = (int)(Math.random()*100)+150;
        //         b = (int)(Math.random()*100)+150;
    }

    public Firefly(PApplet applet_,SpaceShip ss, int totalPieces_, double totalVelocity_, float length_){
        super(applet_, ss, totalPieces_, totalVelocity_, length_);
        r = 193;
        g = 255;
        b = 193;

        strokeR = 249;
        strokeG = 255;
        strokeB = 151;
        strokeA = 200;

        stroke = 3;

    }

    @Override
    public void move(){

        for (int i = 0; i < currentPieces; i++){
            if ( (frames[i] <= 20 && seconds[i] == 0) ){

                xPos[i] += xSpeed[i];
                yPos[i] += ySpeed[i];
            }

            if (seconds[i] == 1 && frames[i] >= 20 && frames[i] <=30){
                length[i] += .9;
            }

            if ( ( seconds[i] == 2 && frames[i] >=30) || (seconds[i] >= 3) ){
                //if (seconds >= 3){
                xPos[i] += xSpeed[i] * 1.5;
                yPos[i] += ySpeed[i]  * 1.5;

                length[i] -= .15;
            }

            frames[i]++;
            if (frames[i] >= 60){
                seconds[i]++;
                frames[i] = 0;
            }
        }

        timer();
        if(allFrames%5 == 0 && currentPieces < totalPieces){
            currentPieces++;

        }

    }

    @Override
    public void draw(){
        applet.stroke(249,255,151, 200);
        applet.strokeWeight(3);
        applet.fill(r,g,b);
        for (int i = 0; i < currentPieces; i++){

            applet.ellipse(xPos[i], yPos[i], length[i], length[i]);
        }

    }
}

