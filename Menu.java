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
      g.setFont(new Font("Arial", 1, 25));
      g.setColor(Color.white);
      g.drawString("CS202 SPACE SHOOTER GAME", 200, 240); 
      g.drawString("CREATED BY MANWAI NGUYEN AND ARGEE NABAS", 90, 300);
      g.drawString("HELP (press H)", 300, 400);
      g.drawString("PLAY (press P)", 300, 500);
      g.drawString("QUIT ANYTIME (press Q)", 300, 600);
    } else if (game.gameState == Game.STATE.Help) {
      g.setColor(Color.white);
      g.drawString("Directional Arrows to Move", 400, 100); 
      g.drawString("Space-bar to shoot", 400, 120);
      g.drawString("Survive and then destroy the enemy!", 400, 140);
      g.drawString("Press B to go back to start menu", 400, 160);
    }
  }
}