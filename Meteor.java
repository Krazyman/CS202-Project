import java.awt.*;

public class Meteor extends GameObject{
 
  
  public Meteor(int x, int y, ID id) {
   super(x, y, id);
   
   velY = 1;
  }
  
  public void update() {
    x += velX;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-32);
  }
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    
    g.setColor(Color.gray);
    g.fillRect(x, y, 16, 16);
  }
  
  public Rectangle getBounds() {
    return new Rectangle(x, y, 16, 16); 
  } 
}