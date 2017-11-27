import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/** This class allows player movement via keyboard buttons. */

public class KeyInput extends KeyAdapter{
  private Handler handler;
  
  public KeyInput(Handler handler){
    this.handler = handler;
    
  }
  
  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();
    for(int i=0; i<handler.object.size(); i++){
      GameObject tempObject = handler.object.get(i);
      if(tempObject.id == ID.Player) {
        if(key == KeyEvent.VK_UP){tempObject.setVelY(-5);}
        if(key == KeyEvent.VK_DOWN){tempObject.setVelY(5);}
        if(key == KeyEvent.VK_LEFT){tempObject.setVelX(-5);}
        if(key == KeyEvent.VK_RIGHT){tempObject.setVelX(5);}
        if(key == KeyEvent.VK_SPACE){Game.shot=true;}
      }
    }
  }
  
  /** Releasing the key stops the action. */
  public void keyReleased(KeyEvent e){
    int key = e.getKeyCode();
    for(int i=0; i<handler.object.size(); i++){
      GameObject tempObject = handler.object.get(i);
      if(tempObject.id == ID.Player) {
        if(key == KeyEvent.VK_UP){tempObject.setVelY(0);}
        if(key == KeyEvent.VK_DOWN){tempObject.setVelY(0);}
        if(key == KeyEvent.VK_LEFT){tempObject.setVelX(0);}
        if(key == KeyEvent.VK_RIGHT){tempObject.setVelX(0);}
        if(key == KeyEvent.VK_SPACE){Game.shot=false;}
      }
    }
  }
}