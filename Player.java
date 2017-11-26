import java.awt.*;
import java.awt.Rectangle;

public class Player extends GameObject {
  
  Handler handler;
  
  public Player(int x, int y, ID id, Handler handler) {
   super(x, y, id); 
   this.handler = handler;
   
  }
  
  public Rectangle getBounds() {
   return new Rectangle(x, y, 32, 32); 
  }
  
  public void update() {
    x += velX;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-32);
    y = Game.clamp((int)y, 0, Game.HEIGHT - 72);
    
    collision();
  }
  
  private void collision(){
    for(int i=0; i<handler.object.size(); i++) {
     GameObject tempObject = handler.object.get(i);
     
     if(tempObject.getId() == ID.Meteor) {
       if(getBounds().intersects(tempObject.getBounds())) {
         HUD.HEALTH -= 2;
         handler.removeObject(tempObject);
       }
     }
    }
  }
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    
    g.setColor(Color.blue);
    g.fillRect(x, y, 32, 32);
  }
  
}