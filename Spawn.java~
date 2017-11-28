import java.util.Random;
import javax.sound.sampled.*;
import java.io.*;

public class Spawn {
  
  private Handler handler;
  private Game game;
  private HUD hud;
  private Random r; // just to test
  private int count = 1;
  private int play = 0;
  Clip clip;
  
  public Spawn(Game game, Handler handler, HUD hud) {
   this.game = game;
   this.handler = handler; 
   this.hud = hud;
   r = new Random();
  }
  
  public void update() {
    if (game.gameState == Game.STATE.Game && play == 0) {
     handler.addObject(new Player(Game.WIDTH/2, Game.HEIGHT/2, ID.Player, handler, game));
     play = 1;
     try {
       File soundFile = new File("LoweredAudio.wav");
       AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
       clip = AudioSystem.getClip();
       clip.open(audioIn);
       clip.start();
       clip.loop(Clip.LOOP_CONTINUOUSLY);
     } catch (UnsupportedAudioFileException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     } catch (LineUnavailableException e) {
       e.printStackTrace();
     }
    }
    
    spawnStar(1);
    despawnStar();
    despawnMeteor();
    despawnExplosion();
    despawnLaser();
    
    if (Game.time <= Game.bossTime) {
      if (Game.time%50 == 0) {spawnMeteor(10);}//spawns 10 meteors every x seconds
      despawnMeteor();
      despawnExplosion();
    }
    //Spawns boss enemy once x time has passed
    if (Game.time > Game.bossTime && count == 1) {
     handler.addObject(new Enemy(400, 0, ID.Enemy)); 
     count = 0;
    }
  }
  
  public void update2() {
   if(game.gameState != Game.STATE.Game && play == 1) {
      play = 0;
      clip.stop();
      count = 1;
      HUD.HEALTH = 100;
      HUD.BULLET = 100;
      HUD.BOSS1 = 100;
      HUD.BOSS2 = 100;
      HUD.BOSS3 = 100;
      HUD.BOSS4 = 100;
      HUD.BOSS5 = 100;
      Player.deathCounter = 150;
    } 
  }
  
  public void spawnMeteor(int num) {
    for(int i=0; i<num; i++) {
      GameObject tempObject = new Meteor(r.nextInt(Game.WIDTH), 0, ID.Meteor);
      handler.addObject(tempObject);
      tempObject.setVelY(r.nextInt(5)+1);//velocity is randomized when spawned
    }
  }
  
  /**
   * Despawns lasers
   */
  public void despawnMeteor() {
    for(int i=0; i<handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);
      
      if(tempObject.getId() == ID.Meteor) {
        if (tempObject.getY() > Game.HEIGHT)  {//despawns meteor when moves beyond canvas
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
  
  /**
   * Despawns lasers
   */
  public void despawnStar(){
    for(int i=0; i<handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);
      if(tempObject.getId() == ID.Star) {
        if (tempObject.getY() > Game.HEIGHT){//despawns laser when moves beyond canvas
          handler.removeObject(tempObject); 
        }
      }
    }
  }
  
  /**
   * Despawns lasers
   */
  public void despawnExplosion() {
    for(int i=0; i<handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);
      if(tempObject.getId() == ID.Explosion) {
        if (Game.time%50==0)  {//if gametime is evenly divisible by 50, despawn explosion
          handler.removeObject(tempObject); 
        }
      }
    }
  }
  
  /**
   * Despawns lasers
   */
  public void despawnLaser() {
     for(int i=0; i<handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);
      if(tempObject.getId() == ID.Laser) {
       if (tempObject.getY() < 0)  {//despawns laser once it moves beyond canvas
          handler.removeObject(tempObject); 
        }
      }
    }
  }
}