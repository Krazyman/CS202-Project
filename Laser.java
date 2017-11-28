import java.awt.*;

/**
 * Subclass of GameObject for laser properties
 */
public class Laser extends GameObject{
  private Handler handler;
  private Game game;
  
  public Laser(int x, int y, ID id, Handler handler, Game game) {
    super(x, y, id);
    this.game = game;
    this.handler = handler;
    velY = -3;//velocity negative so it moves upward
  }
  
  public void update() {
    x += velX;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-32);
    collision();
    if (Player.death) {
      Player.eDeathCounter--; 
      if(Player.eDeathCounter <= 0) {
        game.gameState = Game.STATE.Win;
      }
    } 
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
          if(HUD.BOSS1 > 0) {
            HUD.BOSS1 -= 2;
            handler.removeObject(this);
          } else if (HUD.BOSS2 > 0) {
            HUD.BOSS2 -= 2;
            handler.removeObject(this);
          } else if (HUD.BOSS3 > 0) {
            HUD.BOSS3 -= 2;
            handler.removeObject(this);
          } else if (HUD.BOSS4 > 0) {
            HUD.BOSS4 -= 2;
            handler.removeObject(this);
          } else if (HUD.BOSS5 > 0) {
            HUD.BOSS5 -= 2; 
            handler.removeObject(this);
          } else if (HUD.BOSS5 <= 0) {
            handler.removeObject(tempObject);
            handler.removeObject(this);
            for (int j=0; j<25; j++) {
              int tempX = tempObject.getX();
              int tempY = tempObject.getY();
              handler.addObject(new Explosion(tempX-i, tempY+i, ID.Explosion));
              handler.addObject(new Explosion(tempX+i, tempY-i, ID.Explosion));
              handler.addObject(new Explosion(tempX, tempY, ID.Explosion));
            }
            Player.death = true;
          }
        }
      }
    }
  }
  
  /**
   * Draws 32x32 Laser.gif on top of the hitbox
   * @param Graphics g
   */
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    Image img1 = Toolkit.getDefaultToolkit().getImage("32Laser.gif");
    g.drawImage(img1, x, y, null);
  }
  
  /**
   * Draws 32x32 as the hitbox
   */
  public Rectangle getBounds() {
    return new Rectangle(x, y, 32, 32); 
  }
}