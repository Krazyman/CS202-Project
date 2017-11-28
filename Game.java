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
  
  public static final int WIDTH = 800, HEIGHT = 800; 
  
  public Thread thread;
  private boolean running = false;
  public static boolean shot;
  
  private Random r; // just to test
  private Handler handler;
  private HUD hud;
  private Spawn spawner;
  private Menu menu;

  public enum STATE {
    Menu,
    Game,
    Help,
    Win,
    Lose
  }  
  
  public static final int bossTime = 3600;
  public static double time = 0;

  public STATE gameState = STATE.Menu;

  public Game() {
    handler = new Handler();
    menu = new Menu(this);
    this.addKeyListener(new KeyInput(this, handler));
    
    new Window(WIDTH, HEIGHT, "SpaceShooter", this);
    
    hud = new HUD();
    spawner = new Spawn(this, handler, hud);
    hud.update();

    r= new Random(); // just to test
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
    menu.update();
    
    if(gameState == STATE.Game) {
      time += 1;
      hud.update();
      spawner.update();
        
//        try {
//          File soundFile = new File("LoweredAudio.wav");
//          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
//          Clip clip = AudioSystem.getClip();
//          clip.open(audioIn);
//          clip.start();
//          clip.loop(Clip.LOOP_CONTINUOUSLY);
//        } catch (UnsupportedAudioFileException e) {
//          e.printStackTrace();
//        } catch (IOException e) {
//          e.printStackTrace();
//        } catch (LineUnavailableException e) {
//          e.printStackTrace();
//        }  
    } else if (gameState == STATE.Menu ||
               gameState == STATE.Help ||
               gameState == STATE.Lose ||
               gameState == STATE.Win) {time = 3500;}
    
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

    if(gameState == STATE.Game) {
      hud.draw(g);
    } else if (gameState == STATE.Menu ||
               gameState == STATE.Help ||
               gameState == STATE.Lose ||
               gameState == STATE.Win) { menu.draw(g); }
    
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