import java.awt.*;
import java.awt.Rectangle;

public class Enemy extends GameObject {
  
  public Enemy(int x, int y, ID id) {
   super(x, y, id); 
   
<<<<<<< HEAD
   if(Game.time%4 == 0) {
   velY = 1;     
   }
=======
   //if(Game.time%3 == 0) {
     velY = 1;
  // } 
>>>>>>> ab8f7a0d047e9c47516526d23617c6f008308d15
  }
   /**
   * Update method for enemy movement
   */
  public void update() {
<<<<<<< HEAD
    x += Math.cos(-Game.time*.025)*8 + 0.5;
=======
    x += Math.cos(-Game.time*.0025)*8;//+ 0.5;
>>>>>>> ab8f7a0d047e9c47516526d23617c6f008308d15
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-128);

  }
  
  /**
   * Draws 128x128 Enemy.gif on top of Enemy hitbox
   * @param Graphics g
   */
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    
    Image img1 = Toolkit.getDefaultToolkit().getImage("128Enemy.gif");
    g.drawImage(img1, x, y, null);
  }
    /**
   * Creates hitbox of enemy which the .gif will be drawn on 
   */
   public Rectangle getBounds() {
     return new Rectangle(x, y, 128, 128); 
  }
  
}