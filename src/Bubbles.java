import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bubbles extends Entity {

    private double bubbleSize;
    public Bubbles(double x, double y) {
        super(x, y);
        initialise();
    }

    public void initialise(){
        
        this.bubbleSize = Math.floor(Math.random()*30) + 10;     // random bubble size
        this.x += 20*((Math.random() > 0.5 ? 1 : -1));           // random bubble location 
        this.vy = -(Math.floor(Math.random() * 100) + 350);      // random bubble speed
        this.vx = 0;

    }

    public void update(double dt) throws InterruptedException {
            
        this.y += dt * vy;   // bubbles move up
        
    }

    public void draw(GraphicsContext context) {
        
        context.setFill(Color.rgb(0, 0, 255, 0.4)); 
        context.fillOval(x - bubbleSize/2, y - bubbleSize/2, bubbleSize, bubbleSize);
    }
    
}
