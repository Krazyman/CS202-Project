import java.awt.*;

public class Star extends GameObject{
 
  
  public Star(int x, int y, ID id) {
   super(x, y, id);
   
   velY = -5;
  }
  
  public void update() {
    x += velX;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-32);
  }
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    
    g.setColor(Color.white);
    g.fillRect(x, y, 4, 4);
  }
  
  public Rectangle getBounds() {
    return new Rectangle(x, y, 16, 16); 
  } 
}