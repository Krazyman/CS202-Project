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
       }
     }
    }
  }
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    Image img1 = Toolkit.getDefaultToolkit().getImage("32Laser.gif");
    g.drawImage(img1, x, y, null);
    
//    g.setColor(Color.red);
//    g.fillRect(x, y, 1, 3);
  }
  
  public Rectangle getBounds() {
    return new Rectangle(x, y, 32, 32); 
  } 
}