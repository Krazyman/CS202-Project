import java.awt.*;
import java.awt.Rectangle;

public class Enemy extends GameObject {
  
  public Enemy(int x, int y, ID id) {
   super(x, y, id); 
   
   velY = 1;
  }
  
   public Rectangle getBounds() {
   return new Rectangle(x, y, 64, 64); 
  }
  
  public void update() {
    x += Math.cos(-Game.time*.0025)*8 + 0.5;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-32);
    
    if (y>=Game.HEIGHT) {
     System.out.println("gg"); 
    }
  }
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    
    g.setColor(Color.red);
    g.fillRect(x, y, 64, 64);
  }
  
}