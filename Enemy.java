import java.awt.*;
import java.awt.Rectangle;

public class Enemy extends GameObject {
  
  public Enemy(int x, int y, ID id) {
   super(x, y, id); 
   
   if(Game.time%4 == 0) {
   velY = 1;     
   }
  }
  
   public Rectangle getBounds() {
     return new Rectangle(x, y, 128, 128); 
  }
  
  public void update() {
    x += Math.cos(-Game.time*.025)*8 + 0.5;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-128);

  }
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    
    Image img1 = Toolkit.getDefaultToolkit().getImage("128Enemy.gif");
    g.drawImage(img1, x, y, null);
  }
  
}