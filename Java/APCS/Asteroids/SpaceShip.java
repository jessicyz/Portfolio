import processing.core.PApplet;
public class SpaceShip extends Floater  
{   
    //your code here
  
    
    public SpaceShip(PApplet applet_){
        super(applet_);
        
        corners = 4;
        int[] xC = {-8, 16, -8, -2};
        int[] yC = {-8, 0,8,0};
        
        xCorners = xC;
        yCorners = yC;
        
        
        
        myColor = 200;
        myCenterX = 0;
        myCenterY = 0;
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
    

}