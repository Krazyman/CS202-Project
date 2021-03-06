import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
 
  ArrayList<GameObject> object = new ArrayList<GameObject>();
  
  public void update() {
    for (int i=0; i<object.size(); i++) {
     GameObject tempObject = object.get(i);

     tempObject.update();
    }
  }

  public void draw(Graphics g) {
   for (int i=0; i<object.size(); i++) {
     GameObject tempObject = object.get(i);

     tempObject.draw(g);
   }
  }

  public void addObject(GameObject object) {
   this.object.add(object);
  }
  
  public void removeObject(GameObject object) {
   this.object.remove(object);
  }
}