import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;
import javax.sound.sampled.*;
import java.io.*;

public class Game extends Canvas implements Runnable{
  
  public static final int WIDTH = 800, HEIGHT = 800;//width and height of canvas
  
  public Thread thread;
  private boolean running = false;
  public static boolean shot;//boolean for shooting
  
  private Random r; // just to test
  private Handler handler;
  private HUD hud;
  private Spawn spawner;  
  
  public static final int bossTime = 1;
  public static double time = 0;

  public Game() {
    handler = new Handler();
    this.addKeyListener(new KeyInput(handler));
    
    new Window(WIDTH, HEIGHT, "SpaceShooter", this);//creating window with title "SpaceShooter"
    
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
    
    try {//plays .wav file continuously 
      File soundFile = new File("LoweredAudio.wav");
      AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);//audio in from soundfile
      Clip clip = AudioSystem.getClip();//clip from audio in
      clip.open(audioIn);//open clip
      clip.start();//start clip
      clip.loop(Clip.LOOP_CONTINUOUSLY);//loop clip continuously
    } catch (UnsupportedAudioFileException e) {//catching exceptions
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }
    
    while(running){//while game is running
     time += 1;//increment time by 1
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
    new Game();//calls game method to star the game 
  }
}