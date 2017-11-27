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
  public static boolean shot;
  
  private Random r; // just to test
  private Handler handler;
  private HUD hud;
  private Spawn spawner;  
  
  public static final int bossTime = 1000;
  public static double time = 0;

  public Game() {
    handler = new Handler();
    this.addKeyListener(new KeyInput(handler));
    
    new Window(WIDTH, HEIGHT, "SpaceShooter", this);
    
    hud = new HUD();
    spawner = new Spawn(handler, hud);
    hud.update();

    r= new Random(); // just to test
    handler.addObject(new Player(WIDTH/2, HEIGHT/2, ID.Player, handler));
    
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
    spawner.update();
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
}