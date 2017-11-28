import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

public class Menu {
  
  private Game game;
  
  public Menu(Game game) {
   this.game = game; 
  }
  
  public void update() {
    
  }
  
  public void draw(Graphics g) {
    if(game.gameState == Game.STATE.Menu) {
     Font font1 = new Font("arial", 1, 70);
     
     g.setColor(Color.white);
     g.drawString("SPACE SHOOTER", 250, 100); 
    } else if (game.gameState == Game.STATE.Help) {
     g.drawString("HELP", 400, 100); 
    }
  }
  
  
}