import java.awt.*;

public class Laser extends GameObject{
 private Handler handler;
  
  public Laser(int x, int y, ID id, Handler handler) {
   super(x, y, id);
   this.handler = handler;
   velY = -3;
  }
  
  public void update() {
    x += velX;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-32);
    collision();
  }
  
  private void collision(){
    for(int i=0; i<handler.object.size(); i++) {
     GameObject tempObject = handler.object.get(i);
     
     if(tempObject.getId() == ID.Meteor) {
       if(getBounds().intersects(tempObject.getBounds())) {
         handler.removeObject(tempObject);
         handler.removeObject(this);
         handler.addObject(new Explosion(x, y, ID.Explosion));
       }
     }
     
     if(tempObject.getId() == ID.Enemy) {
       if(getBounds().intersects(tempObject.getBounds())) {
         if(HUD.BOSS1 != 0) {
          HUD.BOSS1 -= 1; 
         } else if (HUD.BOSS2 != 0) {
          HUD.BOSS2 -= 1; 
         } else if (HUD.BOSS3 != 0) {
          HUD.BOSS3 -= 1; 
         } else if (HUD.BOSS4 != 0) {
          HUD.BOSS4 -= 1; 
         } else if (HUD.BOSS5 != 0) {
          HUD.BOSS5 -= 1; 
         } else if (HUD.BOSS5 ==0) {
          handler.removeObject(tempObject);
          handler.removeObject(this);
          for (int j=0; j<10; j++) {
            handler.addObject(new Explosion(x, y, ID.Explosion));
          }                 
         }
       }
     }
    }
  }
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    Image img1 = Toolkit.getDefaultToolkit().getImage("32Laser.gif");
    g.drawImage(img1, x, y, null);
  }
  
  public Rectangle getBounds() {
    return new Rectangle(x, y, 32, 32); 
  }
}