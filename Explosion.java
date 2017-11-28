import java.awt.*;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Subclass of GameObject for explosion properties
 */
public class Explosion extends GameObject{
 
  
  public Explosion(int x, int y, ID id) {
   super(x, y, id);
  }
  
  public void update() {
    x += velX;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-32);
  }
  
  /**
   * Draws 32x32 Explosion.gif on top of actual "explosion"
   * @param Graphics g
   */
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    Image img1 = Toolkit.getDefaultToolkit().getImage("32Explosion.gif");
    g.drawImage(img1, x, y, null);
  }
  
  /**
   * Creates the actual explosion which the .gif will be drawn on top of
   */
  public Rectangle getBounds() {
    return new Rectangle(x, y, 32, 32);
  } 
}