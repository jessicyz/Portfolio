import processing.core.PApplet;
public class Asteroid extends Floater  
{   
    //your code here

    private int rotSpeed;

    public Asteroid(PApplet applet_){
        super(applet_);

        corners = 6;
        int[] xC = {-11, 7, 13, 6, -11, -5};
        int[] yC = {-8,-8, 0, 10, 8, 0};

        xCorners = xC;
        yCorners = yC;

        
        
        myColor = (int)(Math.random()*50) + 100;
        myCenterX = Math.random() * applet.width;
        myCenterY = Math.random() * applet.height;

        int multBy = 1;
        if ( (int)(Math.random() * 2) %2 == 0){
            multBy *= -1;
        }
        myDirectionX = multBy * (Math.random()* 3 + 1);

        if ( (int)(Math.random() * 2) %2 == 0){
            multBy *= -1;
        }
        myDirectionY = multBy * (Math.random()* 3 + 1);

        if ( (int)(Math.random() * 2) %2 == 0){
            multBy *= -1;
        }

        rotSpeed = (int) (multBy * (Math.random()*3 + 10));
    }

    
    public Asteroid(PApplet applet_, int xPos, int yPos, double dirX, double dirY){
        super(applet_);

        corners = 6;
        int[] xC = {-11, 7, 13, 6, -11, -5};
        int[] yC = {-8,-8, 0, 10, 8, 0};

        xCorners = xC;
        yCorners = yC;

        
        myColor = 100;
        myCenterX = xPos;
        myCenterY = yPos;

        myDirectionX = dirX;
        myDirectionY = dirY;
        
        int multBy = 1;
        if ( (int)(Math.random() * 2) %2 == 0){
            multBy *= -1;
        }

        rotSpeed = (int) (multBy * (Math.random()*30 + 10));
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

    //overried
    public void move(){
        super.move();
        rotate(rotSpeed);
    }


}
