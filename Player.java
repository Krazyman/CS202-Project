import java.awt.*;
import java.awt.Rectangle;

public class Player extends GameObject {
  
  Handler handler;
  
  public Player(int x, int y, ID id, Handler handler) {
   super(x, y, id); 
   this.handler = handler;
   
  }
  
  public Rectangle getBounds() {
   return new Rectangle(x, y, 64, 64); 
  }
  
  public void update() {
    x += velX;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-32);
    y = Game.clamp((int)y, 0, Game.HEIGHT - 96);
    
    collision();
    shoot();
    death();
    
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
     
     if(tempObject.getId() == ID.Enemy) {
       if(getBounds().intersects(tempObject.getBounds())) {
         HUD.HEALTH -= 25;
       } 
     }
    }
  }
  
  private void shoot(){
    if (Game.shot && HUD.BULLET!=0){
      handler.addObject(new Laser(this.getX()+16,this.getY(),ID.Laser,handler));
      HUD.BULLET -= 1;
    }
  }
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    Image img1 = Toolkit.getDefaultToolkit().getImage("64Ship.png");
    g.drawImage(img1, x, y, null);
  }
  
  public void death() {
    if (HUD.HEALTH <= 0) {
      for (int i=0; i<25; i++) {
       handler.addObject(new Explosion(x-i, y+i, ID.Explosion));
       handler.addObject(new Explosion(x+i, y-i, ID.Explosion));
       handler.addObject(new Explosion(x, y, ID.Explosion));
      }
     handler.removeObject(this); 
    }
  }
  
}