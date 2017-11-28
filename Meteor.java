import java.awt.*;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Subclass of GameObject for laser properties
 */
public class Meteor extends GameObject{
 
  Image enemy;
  public Meteor(int x, int y, ID id) {
   super(x, y, id);
   
   velY = 2;//velocity is positive so it moves down
  }
  
  public void update() {
    x += velX;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-24);//y is zero to spawn at top of canvas
  }
  
    /**
   * Draws 64x64 Meteor.png on top of the hitbox
   * @param Graphics g
   */
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;     
    Image img1 = Toolkit.getDefaultToolkit().getImage("64Meteor.png");
    g.drawImage(img1, x, y, null);
  }
  
  /**
   * Draws 48x48 for hitbox of Meteor
   */
  public Rectangle getBounds() {
    return new Rectangle(x, y, 48, 48); 
  } 
}

