import java.util.Random;

public class Spawn {
  
  private Handler handler;
  private HUD hud;
  private Random r; // just to test
  
  public Spawn(Handler handler, HUD hud) {
   this.handler = handler; 
   this.hud = hud;
   
   r = new Random();
  }
  
  public void update() {
    spawnStar(1);
    despawnStar();
    if (Game.time%50 == 0) {spawnMeteor(15);}
    despawnMeteor();
  }
  
  public void spawnMeteor(int num) {
    for(int i=0; i<num; i++) {
      handler.addObject(new Meteor(r.nextInt(Game.WIDTH), 0, ID.Meteor));
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
}