import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/** This class allows player movement via keyboard buttons. */

public class KeyInput extends KeyAdapter{
  private Handler handler;
  private boolean[] keyDown = new boolean[4]; //SK FIX
  
  public KeyInput(Handler handler){
    this.handler = handler;
    keyDown[0]=false; //SK FIX up
    keyDown[1]=false; //SK FIX down
    keyDown[2]=false; //SK FIX right
    keyDown[3]=false; //SK FIX left
  }
  
  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();
    for(int i=0; i<handler.object.size(); i++){
      GameObject tempObject = handler.object.get(i);
      if(tempObject.id == ID.Player) {
        if(key == KeyEvent.VK_UP){tempObject.setVelY(-5); keyDown[0] = true;}
        if(key == KeyEvent.VK_DOWN){tempObject.setVelY(5); keyDown[1] = true;}
        if(key == KeyEvent.VK_LEFT){tempObject.setVelX(-5); keyDown[2] = true;}
        if(key == KeyEvent.VK_RIGHT){tempObject.setVelX(5); keyDown[3] = true;}
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
        if(key == KeyEvent.VK_UP){keyDown[0] = false;}
        if(key == KeyEvent.VK_DOWN){keyDown[1] = false;}
        if(key == KeyEvent.VK_LEFT){keyDown[2] = false;}
        if(key == KeyEvent.VK_RIGHT){keyDown[3] = false;}
        //stops vertical movement 
        if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
        //stops horizontal movement
        if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
        if(key == KeyEvent.VK_SPACE){Game.shot=false;}
      }
    }
  }
}