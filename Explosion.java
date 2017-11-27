import java.awt.*;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Explosion extends GameObject{
 
  
  public Explosion(int x, int y, ID id) {
   super(x, y, id);
   
   velY = 5;
  }
  
  public void update() {
    x += velX;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-32);
  }
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    Image img1 = Toolkit.getDefaultToolkit().getImage("32Explosion.gif");
    g.drawImage(img1, x, y, null);
  }
  
  public Rectangle getBounds() {
    return new Rectangle(x, y, 32, 32); 
  } 
}