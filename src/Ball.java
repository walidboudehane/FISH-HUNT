import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends Entity {

    public double ballSize = 0;
    private double vballSize = 0;
    public boolean fishHit; 
    

    public Ball (double x, double y) {
        super(x, y);
        fishHit = false;
        initialise();
    }

    public void initialise(){

        this.ballSize = 50;
        this.vballSize = -300;
        
    }

    public void update(double dt){

        this.ballSize += dt * this.vballSize;
    
    }


    public void draw(GraphicsContext context) {
        
        context.setFill(Color.BLACK); 
        context.fillOval(x - ballSize/2, y - ballSize/2, ballSize, ballSize);
    }

    public void hitFish (Fish fish){

        // left of the fish rectagle
        double fishMinX = fish.getX();
        double fishMinY = fish.getY();

        // right of the figh rectangle
        double fishMaxX = fish.getX() + fish.size;
        double fishMaxY = fish.getY() + fish.size;

        if (this.ballSize < 0){
            // the ball hit the fish
            if (getX() < fishMaxX && getX()> fishMinX && getY() < fishMaxY && getY() > fishMinY){
                fishHit = true;
            }
        }
    }

}
