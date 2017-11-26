import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

/* Creates a scrolling background */

public class Background {
 private float y = 0;

 public void update() {
  y -= 0.5;
  if (y == -800) {
   y = 0;
  }
 }

 public void draw(Graphics g) {

  BufferedImage image = null;
  
  try {
   image = ImageIO.read(new File("Background.png"));
  } catch(IOException e){System.out.println(e);}

  g.drawImage(image, 0, (int)y, null);
  g.drawImage(image, 0, (int)y + 800, null);
 }
} 