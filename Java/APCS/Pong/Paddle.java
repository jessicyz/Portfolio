import processing.core.PApplet;
public class Paddle extends PApplet{
    float x, y, ySpeed;
    private PApplet applet;
    //length of paddle
    int paddleHeight;
    private final int paddleWidth = 8;
    int r, g, b;
    boolean upPressed = false;
    boolean downPressed = false;
    boolean upPressed2 = false;
    boolean downPressed2 = false;

    public Paddle(PApplet applet_, int length, float x_, float y_,  float ySpeed_, int red, int green, int blue){
        applet = applet_;
        paddleHeight = length;
        x = x_;
        y = y_;
        //xSpeed = xSpeed_;
        ySpeed = ySpeed_;
        r = red;
        g = green;
        b = blue;
    }

    public void pMove(){

        
        if (upPressed){
            y -= ySpeed;

            //make sure it doesn't go past the top
            if (y < 0){
                y = 0;
            }

        }

        if (downPressed){
            y += ySpeed;

            //make sure it doesn't go past the bottom
            if (y > applet.height){
                y = applet.height;
            }

        }

    }



    public void display(){
        applet.fill(r, g, b);
        applet.rectMode(applet.CENTER);
        applet.rect(x, y,paddleWidth,paddleHeight);
    }

    public int getPosition(){
        return (int)y;
    }

    public int getXPosition(){
        return (int)x;
    }

    public int getLength(){
        return paddleHeight;
    }

    public int getWidth(){
        return paddleWidth;
    }

    public void setUpPressed(){
        upPressed = true;
    }

    public void setDownPressed(){
        downPressed = true;
    }

    public void setUpReleased(){
        upPressed = false;
    }

    public void setDownReleased(){
        downPressed = false;
    }

    
    public void setSize(int size){
        paddleHeight = size;
    }

    public void setSpeed(int speed){
        ySpeed = speed;
    }

    public void setXPos(int pos){
        x = pos;
    }
    
    public void setYPos(int pos){
        y = pos;
    }
    
    public void setColor(int red, int green, int blue){
        r = red;
        g = green;
        b = blue;
    }
    
    
    
    
    
    public void pMoveInverse(){

        
        if (upPressed){
            y += ySpeed;

            //make sure it doesn't go past the bottom
            if (y > applet.height){
                y = applet.height;
            }

            
        }

        if (downPressed){

            y -= ySpeed;

            //make sure it doesn't go past the top
            if (y < 0){
                y = 0;
            }

        }

    }



}
