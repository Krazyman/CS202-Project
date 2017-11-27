import java.awt.Graphics;
import java.awt.Color;

public class HUD {
 
  public static int HEALTH = 100;
  public static int BULLET = 100;
  public static int BOSS1 = 100;
  public static int BOSS2 = 100;
  public static int BOSS3 = 100;
  public static int BOSS4 = 100;
  public static int BOSS5 = 100;

  private int greenValue = 255;
  private int blueValue = 255;
  private int redValue = 255;
  private int value1, value2, value3, value4;

  
  public void update() {   
    HEALTH = Game.clamp(HEALTH, 0, 100);
    BULLET = Game.clamp(BULLET, 0, 100);
    greenValue = HEALTH * 2;
    blueValue = BULLET * 2;
    value1 = BOSS1 * 2;
    value2 = BOSS2 * 2;
    value3 = BOSS3 * 2;
    value4 = BOSS4 * 2;    
    redValue = BOSS5 * 2;

  }
  
  public void draw(Graphics g) {
   // health bar
   g.setColor(Color.gray);
   g.fillRect(15, 15, 200, 32);
   g.setColor(new Color(50, greenValue, 0));
   g.fillRect(15, 15, HEALTH*2, 32);
   g.setColor(Color.white);
   g.drawRect(15, 15, 200, 32);
   // bullet bar
   g.setColor(Color.gray);
   g.fillRect(15, 50, 200, 32);
   g.setColor(new Color(0, 75, blueValue));
   g.fillRect(15, 50, BULLET*2, 32);
   g.setColor(Color.white);
   g.drawRect(15, 50, 200, 32);

   g.setColor(Color.gray);
   g.fillRect(550, 15, 200, 32);
   g.setColor(Color.white);
   g.drawRect(15, 50, 200, 32);
   // boss health 5
   g.setColor(new Color(redValue, 0, 0));
   g.fillRect(550, 15, BOSS5*2, 32);
   // boss health 4
   g.setColor(new Color(0, 0, value4));
   g.fillRect(550, 15, BOSS4*2, 32);
   // boss health 3
   g.setColor(new Color(redValue, 0, value3));
   g.fillRect(550, 15, BOSS3*2, 32);
   // boss health 2
   g.setColor(new Color(redValue, value2, 0));
   g.fillRect(550, 15, BOSS2*2, 32);
   // boss health 1   
   g.setColor(new Color(value1, 0, 0));
   g.fillRect(550, 15, BOSS1*2, 32);
   
   
   String timer = Double.toString(Game.time);;
   g.drawString(timer,400, 400);
  }
}