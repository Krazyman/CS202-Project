import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;

public class Game extends Canvas implements Runnable{
  
  public static final int WIDTH = 800, HEIGHT = 800;  
  
  public Thread thread;
  private boolean running = false;
  
  private Random r; // just to test
  private Handler handler;
  private HUD hud;
  
  public static double time = 0;

  public Game() {
    handler = new Handler();
    this.addKeyListener(new KeyInput(handler));
    
    new Window(WIDTH, HEIGHT, "SpaceShooter", this);
    
    hud = new HUD();
    hud.update();

    r= new Random(); // just to test
    handler.addObject(new Player(WIDTH/2, HEIGHT/2, ID.Player, handler));
    //handler.addObject(new Enemy(WIDTH/2, 0, ID.Enemy));

    
  }
  
  public synchronized void start() {
    thread = new Thread(this);
    thread.start();
    running = true;
  }
  
  public synchronized void stop() {
    try {thread.join();
    running = false;} 
    catch(Exception e) {
    e.printStackTrace();
    }
  }
  
  public void run(){
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfFrames = 60.0;
    double ns = 1000000000 / amountOfFrames;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while(running){
     if(time%50 == 0) {
     spawnMeteor(2);
      }
     despawnMeteor(); 
     //System.out.println(handler.object.size());
     time += 1;
     long now = System.nanoTime();
     delta += (now - lastTime) /ns;
     lastTime = now;
     while(delta >= 1) {
       update();
       delta--;
     }
     if(running){
       draw();
     }
     frames ++;
     
     if(System.currentTimeMillis() - timer > 1000) {
       timer += 1000;
       //System.out.println("FPS: " + frames);
       frames = 0;
     }
    }
    stop();
  }
  
  private void update() {
    handler.update();
    hud.update();
  }
  
  private void draw() {
    BufferStrategy bs = this.getBufferStrategy();
    if(bs == null) {
     this.createBufferStrategy(3); // creates 3 buffers
     return;
    }
    
    Graphics g = bs.getDrawGraphics();
    
    g.setColor(Color.black);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    
    handler.draw(g);
    hud.draw(g);
    
    g.dispose();
    bs.show();
  }
  
  public static int clamp(int var, int min, int max){
    if(var >= max){
      return var = max;
    }else if(var < min){
      return var = min;
    }else{
      return var;
    }
  }
  
  public static void main(String[] args) {
    new Game();
  }
  
  public void spawnMeteor(int num) {
    for(int i=0; i<num; i++) {
     handler.addObject(new Meteor(r.nextInt(WIDTH), 0, ID.Meteor));
     handler.addObject(new Star(r.nextInt(WIDTH), HEIGHT, ID.Star));
    }
  }
  
  public void despawnMeteor() {
    for(int i=0; i<handler.object.size(); i++) {
     GameObject tempObject = handler.object.get(i);
     
     if(tempObject.getId() == ID.Meteor) {
       if (tempObject.getY() > HEIGHT)  {
        handler.removeObject(tempObject); 
       }
     }
    }
  }
  
}