import processing.core.PApplet;

public class MasterSpark extends Bomb {


    public MasterSpark(PApplet applet_ , int totalPieces_, float[] startingPosX, float[] startingPosY, double[] xSpeed_, double[] ySpeed_ , float[] length_){
        super(applet_, totalPieces_, startingPosX, startingPosY, xSpeed_, ySpeed_, length_);

        r = (int)(Math.random()*100)+150;
        g = (int)(Math.random()*100)+150;
        b = (int)(Math.random()*100)+150;

    }

    public MasterSpark(PApplet applet_,SpaceShip ss, int totalPieces_, double totalVelocity_, float length_){
        super(applet_, ss, totalPieces_, totalVelocity_, length_);


    }

    @Override
    public void move(){

        for (int i = 0; i < totalPieces; i++){

            if(i % 7 == 0){
                xPos[i] += xSpeed[i]*5.5;
                yPos[i] += ySpeed[i]*5.5;
            }

            if(i % 7 == 1){
                xPos[i] += xSpeed[i]*4;
                yPos[i] += ySpeed[i]*4;
            }

            if(i % 7 == 2){
                xPos[i] += xSpeed[i]*2.75;
                yPos[i] += ySpeed[i]*2.75;
            }

            if(i % 7 == 3){
                xPos[i] += xSpeed[i]*1.75;
                yPos[i] += ySpeed[i]*1.75;
            }

            if(i % 7 == 4){
                xPos[i] += xSpeed[i];
                yPos[i] += ySpeed[i];
            }

            if(i % 7 == 5){
                xPos[i] += xSpeed[i]/2;
                yPos[i] += ySpeed[i]/2;
            }

            if(i % 7 == 6){
                xPos[i] += xSpeed[i]/4;
                yPos[i] += ySpeed[i]/4;
            }

            timer();
        }
    }

    @Override
    public void draw(){
        applet.stroke(255);
        applet.strokeWeight(2);
        //applet.noStroke();
        for (int i = 0; i < totalPieces; i++){

            if( i%7 == 0){
                applet.fill(255,0,157);
            }

            if(i % 7 == 1){
                applet.fill(157,0,255);
            }

            if(i % 7 == 2){
                applet.fill(0,0,255);
            }

            if(i % 7 == 3){
                applet.fill(0,255,0);
            }

            if(i % 7 == 4){
                applet.fill(255,255,0);
            }

            if(i % 7 == 5){
                applet.fill(255,157,0);
            }

            if(i % 7 == 6){
                applet.fill(255,0,0);
            }

            applet.ellipse(xPos[i], yPos[i], length[i], length[i]);
        }

    }
}


