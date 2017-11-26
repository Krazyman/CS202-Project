import java.awt.*;
import java.awt.Rectangle;

public class Enemy extends GameObject {
  
  public Enemy(int x, int y, ID id) {
   super(x, y, id); 
   
   velY = 5;
   //velX =(int) Math.cos(Game.time)*8;
  }
  
   public Rectangle getBounds() {
   return new Rectangle(x, y, 16, 16); 
  }
  
  public void update() {
    x += Math.cos(-Game.time*.025)*4 + 0.49;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-32);
    y = Game.clamp((int)y, 0, Game.HEIGHT - 48);
  }
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    
    g.setColor(Color.red);
    g.fillRect(x, y, 16, 16);
  }
  
}