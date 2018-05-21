import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import processing.core.PApplet;
public class ABadTime extends PApplet {

    private Ball b;
    private Paddle p1, p2;
    int timer = 0;
    int seconds = -16;
    int length = 80;
    int pSpeed = 10;
    boolean inverse, shrink, slowPaddle, swap, fastBall, seizure, gravity, cut;
    boolean checkInverse, checkShrink, checkSlowPaddle, checkSwap, checkSeizure, checkGravity;
    int r1 = 255;
    int g1 = 0;
    int b1 = 255;
    int r2 = 255;
    int g2 = 255;
    int b2 = 0;

    float ballXSpeed,  ballYSpeed;

    int gravAcc = 4;

    int nextInverse = 16;
    int nextShrink = 4;
    int nextSlowPaddle = 8;
    int nextSwap = 28;
    int nextSeizure = 40;
    int nextGravity = 36;
    int nextCut;

    URL megalovaniaURL = ABadTime.class.getResource("mus_zz_megalovania.wav");
    URL determinationURL = ABadTime.class.getResource("mus_gameover.wav");
    boolean gameOver = false;
    public static void main(String[] args){
        PApplet.main(new String[] {"ABadTime"});

    }

    public void setup(){
        size(800,500);
        noStroke();
        //applet, x, y, xSpeed, ySpeed, r, g, b
        b = new Ball(this, 50, 100, 2, 2, 255, 255, 255);
        //applet, x, y,  ySpeed, r, g, b
        p1 = new Paddle(this, length, width-20, height/2, pSpeed, r1, g1, b1);
        p2 = new Paddle(this, length, 20, height/2, pSpeed, r2, g2, b2);
        AudioClip megalovania = Applet.newAudioClip(megalovaniaURL);

        megalovania.loop();

    }
    public void draw(){
        if (!gameOver){

            //time 
            timer++;
            if (timer == 59){
                seconds += 1;
                timer = 0;
            }

            //interruptions times
            if ((timer == 0 && seconds == -8)|| (timer == 0 && seconds == 0) || (seconds == nextCut && timer ==0)){
                cut = true;
                if (seconds >= 0){
                    nextCut = (int)(Math.random()*40) + seconds + 5;
                }
            }
            else{
                cut = false;

            }

            if (seconds >=  nextInverse && seconds <=  nextInverse + 8){
                inverse = true;
                checkInverse = true;
            }
            else{
                inverse = false;
                if (checkInverse){
                    nextInverse = (int)(Math.random()*40) + seconds + 5;
                }
                checkInverse = false;
            }

            if (seconds >=  nextShrink && seconds <=  nextShrink + 4){
                shrink = true;
                checkShrink = true;
            }
            else{
                shrink = false;
                if (checkShrink){
                    nextShrink = (int)(Math.random()*40) + seconds + 5;
                }
                checkShrink = false;
            }

            if (seconds >=  nextSlowPaddle && seconds <=  nextSlowPaddle + 8){
                slowPaddle = true;
                checkSlowPaddle = true;
            }
            else{
                slowPaddle = false;
                if (checkSlowPaddle){
                    nextSlowPaddle = (int)(Math.random()*40) + seconds + 5;
                }
                checkSlowPaddle = false;
            }

            if (seconds >=  nextSwap && seconds <=  nextSwap + 8){
                swap = true;
                checkSwap = true;
            }
            else{
                swap = false;
                if (checkSwap){
                    nextSwap = (int)(Math.random()*45) + seconds + 5;
                }
                checkSwap = false;
            }

            if (seconds >=  nextSeizure && seconds <=  nextSeizure + 1){
                seizure = true;
                checkSeizure = true;
            }
            else{
                seizure = false;
                if (checkSeizure){
                    nextSeizure = (int)(Math.random()*50) + seconds + 5;
                }
                checkSeizure = false;
            }

            if (seconds >=  nextGravity && seconds <=  nextGravity + 12){
                gravity = true;
                checkGravity = true;
            }
            else{
                gravity = false;
                if (checkGravity){
                    nextGravity = (int)(Math.random()*50) + seconds + 5;
                }
                checkGravity = false;
            }

            //interruptions 
            if (seizure){
                background((int)(Math.random()*254)+1, (int)(Math.random()*254)+1, (int)(Math.random()*254)+1);
            }
            else{
                background(0);
            }

            if (inverse){
                p1.pMoveInverse();
                p1.setColor(255 - r1,255 - g1,255 - b1);
                p1.display();
                p2.pMoveInverse();
                p2.setColor(255 - r2,255 - g2,255 - b2);

                p2.display();
            }
            else{

                //Player 1 arrow key paddle
                p1.pMove();
                p1.setColor(r1,g1,b1);
                p1.display();

                //player 2 controls WS
                p2.pMove();
                p2.setColor(r2,g2,b2);
                p2.display();
            }

            if (swap){
                p1.setXPos(20);
                //p1.setColor(r2,g2,b2);

                p2.setXPos(width-20);
                //p2.setColor(r1,g1,b1);
            }
            else{
                p2.setXPos(20);
                //p2.setColor(r2,g2,b2);

                p1.setXPos(width-20);
                //p1.setColor(r1,g1,b1);

            }

            if (slowPaddle){
                p1.setSpeed(pSpeed/2);
                p2.setSpeed(pSpeed/2);
            }
            else{
                p1.setSpeed(pSpeed);
                p2.setSpeed(pSpeed);
            }

            if (shrink){
                p1.setSize((int)(length*2/3));

                p2.setSize((int)(length*2/3));
            }
            else{
                p1.setSize(length);

                p2.setSize(length);
            }

            if (gravity){
                int p1Pos = p1.getPosition();
                int p2Pos = p2.getPosition();
                if (slowPaddle){
                    if (p1Pos+gravAcc/2 <= height){
                        p1.setYPos(p1Pos+gravAcc/2);
                    }
                    else{
                        p1.setYPos(height);
                    }

                    if (p2Pos+gravAcc/2 <= height){
                        p2.setYPos(p2Pos+gravAcc/2);
                    }
                    else{
                        p2.setYPos(height);
                    }
                }
                else{
                    if (p1Pos+gravAcc <= height){
                        p1.setYPos(p1Pos+gravAcc);
                    }
                    else{
                        p1.setYPos(height);
                    }

                    if (p2Pos+gravAcc <= height){
                        p2.setYPos(p2Pos+gravAcc);
                    }
                    else{
                        p2.setYPos(height);
                    }
                }

            }

            //ball
            b.checkCollision(p1);
            b.checkCollision(p2);
            b.move();
            b.display();

            if (cut){
                p1.setYPos((int)(Math.random()*height+1));
                p2.setYPos((int)(Math.random()*height+1));
                b.setBallPos((int)(Math.random()*(width/2))+width/4, (int)(Math.random()*(height-20))+10);

                if(timer%2 ==0){

                    b.setBallSpeed((int)(Math.random()*5)+5, (int)(Math.random()*-5)-3);
                }
                else{
                    b.setBallSpeed((int)(Math.random()*-5)-5, (int)(Math.random()*5)+3);
                }
                background(0);

            }
            if (b.getGreen() <= 0){
                gameOver = true;

            }

        }
        else{

            b = null;
            p1 = null;
            p2 = null;

            System.out.println("\n\n\nGame Over! \n Time: " + (seconds+16) + " seconds");
            System.out.println("\n\nMEGALOVANIA by toby fox \nDETERMINATION by toby fox");
            AudioClip determination = Applet.newAudioClip(determinationURL);
            AudioClip megalovania = Applet.newAudioClip(megalovaniaURL);
            megalovania.stop();
            determination.play();
            noLoop();

        }

    }

    public void keyPressed(){

        if (key == CODED) {
            if (keyCode == UP) {
                p1.setUpPressed();
            }
            else if (keyCode == DOWN) {
                p1.setDownPressed();
            }
        }

        if (key == 'w'){
            p2.setUpPressed();
        }

        if (key == 's'){
            p2.setDownPressed();
        }

    }

    public void keyReleased(){

        if (key == CODED) {
            if (keyCode == UP) {
                p1.setUpReleased();
            }
            else if (keyCode == DOWN) {
                p1.setDownReleased();
            }
        }

        if (key == 'w'){
            p2.setUpReleased();

        }

        if (key == 's'){
            p2.setDownReleased();

        }

    }
}
