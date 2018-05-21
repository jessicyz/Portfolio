import processing.core.PApplet;
public class Ball{
    private float x, y, xSpeed, ySpeed;
    private PApplet applet;
    private final int DIAMETER = 20;
    int r, g, b;
    public Ball(PApplet applet_, float x_, float y_, float xSpeed_, float ySpeed_, int red, int green, int blue){
        applet = applet_;
        x = x_;
        y = y_;
        xSpeed = xSpeed_;
        ySpeed = ySpeed_;
        r = red;
        g = green;
        b = blue;
    }

    public void move(){
        x+= xSpeed;
        y += ySpeed;
        if (x> applet.width-DIAMETER/2 || x < 0+DIAMETER/2){

            y = applet.height/2;
            x = applet.width/2;
            if (g - 20 >= 0){
                g -= 20;
                b -= 20;
            }
            else{
                g = 0;
                b = 0;
            }
        }

        if (y> applet.height-DIAMETER/2 || y < 0+DIAMETER/2){
            ySpeed *= -1;

        }
    }

    public void display(){
        applet.fill(r,g,b);
        applet.ellipseMode(applet.CENTER);
        applet.ellipse(x, y, DIAMETER, DIAMETER);
    }

    public void checkCollision(Paddle p){
        int yPos = p.getPosition();
        int xPos = p.getXPosition();
        int pLength = p.getLength();
        int pWidth = p.getWidth();
        double velocity = Math.sqrt(xSpeed*xSpeed + ySpeed*ySpeed);

        if (y <= yPos + pLength/2  && y >= yPos - pLength/2 && x >= xPos - pWidth/2 && x <= xPos + pWidth/2){
            if (xSpeed < 0){
                x = xPos + 1+ pWidth/2;
            }
            if (xSpeed > 0){
                x = xPos - 1 - pWidth/2;
            }
            xSpeed *= -1;
            if (g + 10 <= 255){
                g += 10;
                b += 10;
            }
            else{
                g = 255;
                b = 255;
            }

        }


    }
    
    public float getXSpeed(){
        return xSpeed;
    }
    
    public float getYSpeed(){
        return ySpeed;
    }
    
    
    public int getGreen(){
        return g;
    }
    
    public void setBallPos(int xPos, int yPos){
        x = xPos;
        y = yPos;
    }
    
    public void setBallSpeed(int xVel, int yVel){
        xSpeed = xVel;
        ySpeed = yVel;
    }
}
