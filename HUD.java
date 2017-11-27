import java.awt.Graphics;
import java.awt.Color;

public class HUD {
 
  public static int HEALTH = 100;
  public static int BULLET = 100;

  private int greenValue = 255;
  private int blueValue = 255;

  
  public void update() {   
    HEALTH = Game.clamp(HEALTH, 0, 100);
    BULLET = Game.clamp(BULLET, 0, 100);
    greenValue = HEALTH * 2;
    blueValue = BULLET * 2;
    
  }
  
  public void draw(Graphics g) {
   g.setColor(Color.gray);
   g.fillRect(15, 15, 200, 32);
   g.setColor(new Color(50, greenValue, 0));
   g.fillRect(15, 15, HEALTH*2, 32);
   g.setColor(Color.white);
   g.drawRect(15, 15, 200, 32);
   
   g.setColor(Color.gray);
   g.fillRect(15, 50, 200, 32);
   g.setColor(new Color(0, 75, blueValue));
   g.fillRect(15, 50, BULLET*2, 32);
   g.setColor(Color.white);
   g.drawRect(15, 50, 200, 32);
  }
}