import processing.core.PApplet;

public class Jellyfish extends Bomb {


    private int swap = 1;
    private double ssXSpd, ssYSpd;

    public Jellyfish(PApplet applet_ , SpaceShip ss, int totalPieces_, float[] startingPosX, float[] startingPosY, double[] xSpeed_, double[] ySpeed_ , float[] length_){
        super(applet_, totalPieces_, startingPosX, startingPosY, xSpeed_, ySpeed_, length_);

        ssXSpd = ship.getDirectionX()/5;
        ssYSpd = ship.getDirectionY()/5;
        r = 185;
        g = 149;
        b = 255;

    }

    public Jellyfish(PApplet applet_,SpaceShip ss, int totalPieces_, double totalVelocity_, float length_){
        super(applet_, ss, totalPieces_, totalVelocity_, length_);
        r = 185;
        g = 149;
        b = 255;

        strokeR = 0;
        strokeG = 159;
        strokeB = 255;
        strokeA = 200;

        stroke = 3;

        ssXSpd = ship.getDirectionX()/5;
        ssYSpd = ship.getDirectionY()/5;

    }

    @Override
    public void move(){

        for (int i = 0; i < totalPieces; i++){
            if ( allSeconds < 1 ){
                xPos[i] += xSpeed[i]/4;
                yPos[i] += ySpeed[i]/4;
            }

            //             if (allSeconds % 2 == 0 && allSeconds >= 1){
            //                 swap *= -1;
            //             }

            if (allSeconds >= 1 && allSeconds % 2 == 0){
                xPos[i] += -1 * xSpeed[i]/4 - ssXSpd;
                yPos[i] += -1 * ySpeed[i]/4 - ssYSpd;
            }

            if (allSeconds >= 1 && allSeconds % 2 == 1){
                xPos[i] += xSpeed[i]/4 - ssXSpd;
                yPos[i] +=  ySpeed[i]/4 - ssYSpd;
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
}


