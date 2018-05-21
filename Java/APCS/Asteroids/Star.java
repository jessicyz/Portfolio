import processing.core.PApplet;
public class Star{
    private float length;
    //private int color;
    private int r,g,b;
    private float posX, posY;
    private PApplet applet;
    //private int timer = 0;
    public Star(PApplet applet_,float length_, float xPos, float yPos){
        applet = applet_;
        length = length_;
        //color = 255;
        
        r = (int)(Math.random()*100) + 150;
        g = (int)(Math.random()*100) + 150;
        b = (int)(Math.random()*100) + 150;
        
        posX = xPos;
        posY = yPos;
    }
    

    
    public void display(){
        applet.fill(r,g,b);
        applet.noStroke();
        applet.ellipseMode(applet.CENTER);
        applet.ellipse(posX, posY, length, length);
    }
    
    
    
}
