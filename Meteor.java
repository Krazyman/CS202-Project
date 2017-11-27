import java.awt.*;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Meteor extends GameObject{
 
  Image enemy;
  public Meteor(int x, int y, ID id) {
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
    Image img1 = Toolkit.getDefaultToolkit().getImage("64Meteor.png");
    g.drawImage(img1, x, y, null);
    //g.setColor(Color.gray);
   //g.fillRect(x, y, 16, 16);
  }
  
  public Rectangle getBounds() {
    return new Rectangle(x, y, 48, 48); 
  } 
}