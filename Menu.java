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
    g.setFont(new Font("Arial", 1, 25));
    if(game.gameState == Game.STATE.Menu) {
      g.setColor(Color.white);
      g.drawString("CS202 SPACE SHOOTER GAME", 200, 240); 
      g.drawString("CREATED BY MANWAI NGUYEN AND ARGEE NABAS", 90, 300);
      g.drawString("HELP (press H)", 300, 400);
      g.drawString("PLAY (press P)", 300, 500);
      g.drawString("QUIT ANYTIME (press ESCAPE)", 200, 600);
    } else if (game.gameState == Game.STATE.Help) {
      g.setColor(Color.white);
      g.drawString("Directional Arrows to Move", 250, 200); 
      g.drawString("Space-bar to shoot", 300, 300);
      g.drawString("Survive and then destroy the enemy!", 200, 400);
      g.drawString("Press B to go back to start menu", 225, 500);
    }
   else if (game.gameState == Game.STATE.Lose){
        g.setColor(Color.white);
      g.setFont(new Font("Arial", 1, 50));
      g.drawString("YOU HAVE BEEN DESTROYED!", 25, 350);
      g.drawString("TRY AGAIN (PRESS P)", 125, 450);
      g.drawString("GO TO MENU (PRESS B)", 125, 550);
      g.drawString("EXIT (PRESS ESCAPE)", 125, 650);
    }
      else if (game.gameState == Game.STATE.Win){
      g.setFont(new Font("Arial", 1, 50));
          g.setColor(Color.white);
      g.drawString("YOU WON! CONGRATULATIONS", 10, 350);
      g.drawString("PLAY AGAIN (PRESS P)", 125, 450);
      g.drawString("GO TO MENU (PRESS B)", 125, 550);
      g.drawString("EXIT (PRESS ESCAPE)", 125, 650);
    }

  }
}