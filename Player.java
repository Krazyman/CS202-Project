import java.awt.*;
import java.awt.Rectangle;

public class Player extends GameObject {
  
  private Game game;
  private Handler handler;
  public static int ammo = 5;
  public static int deathCounter = 150;
  
  public Player(int x, int y, ID id, Handler handler, Game game) {
   super(x, y, id); 
   this.handler = handler;
   this.game = game;
   
  }
  
  public Rectangle getBounds() {
   return new Rectangle(x, y, 64, 64); 
  }
  
  public void update() {
    x += velX;
    y += velY;
    
    x = Game.clamp(x, 0, Game.WIDTH-64);
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
         HUD.HEALTH -= 50;
         if(HUD.BOSS1 > 0) {
          HUD.BOSS1 -= 50;
          this.setX(tempObject.getX());
          this.setY(tempObject.getY() + 256);
         } else if (HUD.BOSS2 > 0) {
          HUD.BOSS2 -= 50;
          this.setX(tempObject.getX());
          this.setY(tempObject.getY() + 256);
         } else if (HUD.BOSS3 > 0) {
          HUD.BOSS3 -= 50;
          this.setX(tempObject.getX());
          this.setY(tempObject.getY() + 256);
         } else if (HUD.BOSS4 > 0) {
          HUD.BOSS4 -= 50;
          this.setX(tempObject.getX());
          this.setY(tempObject.getY() + 256);
         } else if (HUD.BOSS5 > 0) {
          HUD.BOSS5 -= 50; 
          this.setX(tempObject.getX());
          this.setY(tempObject.getY() + 256);
         } else if (HUD.BOSS5 <= 0) {
          handler.removeObject(tempObject);
          for (int j=0; j<25; j++) {
            int tempX = tempObject.getX();
            int tempY = tempObject.getY();
            handler.addObject(new Explosion(tempX-i, tempY+i, ID.Explosion));
            handler.addObject(new Explosion(tempX+i, tempY-i, ID.Explosion));
            handler.addObject(new Explosion(tempX, tempY, ID.Explosion));
          }
          if(deathCounter <= 0) {
            game.gameState = Game.STATE.Win; 
          } else {
            deathCounter --; 
          }       
         }
       } 
     }
    }
  }
  
  private void shoot(){
    if (Game.shot && HUD.BULLET!=0){
      handler.addObject(new Laser(this.getX()+16,this.getY(),ID.Laser,handler, game));
      HUD.BULLET -= 1;
    }
    if (HUD.BULLET == 0 && ammo != 0) {
      HUD.BULLET = 100;
      ammo --;
    }
  }
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    Image img1 = Toolkit.getDefaultToolkit().getImage("64Ship.png");
    g.drawImage(img1, x, y, null);
  }
  
  public void death() {
    if(HUD.BOSSPASS >= Game.HEIGHT) {
     game.gameState = Game.STATE.Lose; 
     ammo = 5;
    }
    if (HUD.HEALTH <= 0) {
      for (int i=0; i<25; i++) {
       handler.addObject(new Explosion(x-i, y+i, ID.Explosion));
       handler.addObject(new Explosion(x+i, y-i, ID.Explosion));
       handler.addObject(new Explosion(x, y, ID.Explosion));
      }
      if(deathCounter <= 0) {
        game.gameState = Game.STATE.Lose; 
      } else {
       deathCounter --; 
      }
     ammo = 5;
    }
  }
  
}