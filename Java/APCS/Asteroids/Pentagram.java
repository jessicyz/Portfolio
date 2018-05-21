import processing.core.PApplet;

public class Pentagram extends Bomb {



    public Pentagram(PApplet applet_ , int totalPieces_, float[] startingPosX, float[] startingPosY, double[] xSpeed_, double[] ySpeed_ , float[] length_){
        super(applet_, totalPieces_, startingPosX, startingPosY, xSpeed_, ySpeed_, length_);

        r = 75;
        g = 236;
        b = 19;

    }
    
        public Pentagram(PApplet applet_,SpaceShip ss, int totalPieces_, double totalVelocity_, float length_){
        super(applet_, ss, totalPieces_, totalVelocity_, length_);
        r = 75;
        g = 236;
        b = 19;

        strokeR = 180;
        strokeG = 19;
        strokeB = 236;
        strokeA = 200;
        
        stroke = 2;

    }

    @Override
    public void move(){

        for (int i = 0; i < totalPieces; i++){

            if(i % 2 == 0){
                xPos[i] += xSpeed[i];
                yPos[i] += ySpeed[i];
            }
            
            if(i % 2 == 1){
                xPos[i] += xSpeed[i]/2;
                yPos[i] += ySpeed[i]/2;
            }
            

            timer();

        }
    }


}





