import java.util.Random;

public class Spawn {
  
  private Handler handler;
  private Game game;
  private HUD hud;
  private Random r; // just to test
  private int count = 1;
  private int play = 0;
  
  public Spawn(Game game, Handler handler, HUD hud) {
   this.game = game;
   this.handler = handler; 
   this.hud = hud;
   r = new Random();
  }
  
  public void update() {
    if (game.gameState == Game.STATE.Game && play == 0) {
     handler.addObject(new Player(Game.WIDTH/2, Game.HEIGHT/2, ID.Player, handler));
     play = 1;
    }
    spawnStar(1);
    despawnStar();
    despawnMeteor();
    despawnExplosion();
    despawnLaser();
    if (Game.time <= Game.bossTime) {
      if (Game.time%50 == 0) {spawnMeteor(10);}
      despawnMeteor();
      despawnExplosion();
    }
    if (Game.time > Game.bossTime && count == 1) {
     handler.addObject(new Enemy(400, 0, ID.Enemy)); 
     count = 0;
    }
    
    if(game.gameState != Game.STATE.Game && play == 1) {
      play = 0;
    }
  }
  
  public void spawnMeteor(int num) {
    for(int i=0; i<num; i++) {
      GameObject tempObject = new Meteor(r.nextInt(Game.WIDTH), 0, ID.Meteor);
      handler.addObject(tempObject);
      tempObject.setVelY(r.nextInt(5)+1);
    }
  }
  
  public void despawnMeteor() {
    for(int i=0; i<handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);
      
      if(tempObject.getId() == ID.Meteor) {
        if (tempObject.getY() > Game.HEIGHT)  {
          handler.removeObject(tempObject); 
        } 
      }
    }
  }
    
  public void spawnStar(int num) {
    for(int i=0; i<num; i++) {
      handler.addObject(new Star(r.nextInt(Game.WIDTH), 0, ID.Star)); 
    }
  }
  
  public void despawnStar(){
    for(int i=0; i<handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);
      if(tempObject.getId() == ID.Star) {
        if (tempObject.getY() > Game.HEIGHT)  {
          handler.removeObject(tempObject); 
        }
      }
    }
  }
  
  public void despawnExplosion() {
    for(int i=0; i<handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);
      if(tempObject.getId() == ID.Explosion) {
        if (Game.time%50==0)  {
          handler.removeObject(tempObject); 
        }
      }
    }
  }
  
  public void despawnLaser() {
     for(int i=0; i<handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);
      if(tempObject.getId() == ID.Laser) {
       if (tempObject.getY() < 0)  {
          handler.removeObject(tempObject); 
        }
      }
    }
  }
}