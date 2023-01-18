import javafx.scene.image.Image;

public class FishSpecial extends Fish {

    private boolean isCrabe;
    private double time;
    private double radians;
    private double amplitude;
    private double previousY;

    public FishSpecial(double x, double y, int level) {

        super(x, y, level);
        time = 0;
        intitialise();   
    }
    
    @Override
    public void intitialise() {

        double random = Math.random();

        // select a random fish with 50% chance
        if (random > 0.5){
            img = new Image("/Image/crabe.png",size,size,false,false);
            img = setFishColor(img);
            this.vx = 1.3 * vx;
            isCrabe = true;   
        }
        else {
            img = new Image("/Image/star.png",size,size,false,false);
            img = setFishColor(img);
            this.ay = 0;
            this.amplitude = 50;
            previousY = this.y;
            isCrabe = false;
            
        }

        if (this.x != 0){
            this.vx *= -1;
        }

        
    }

    @Override
    public void update(double dt ) {
        
        // crabe moves with a back and forth motion 
        if (isCrabe){
       
            time += dt;

            if(time <= 0.5){
                this.x += dt*vx;        // move ahead
                
            }
            else if(time <= 0.75){
                this.x -= dt*vx;        // move back
            }
            else{
                this.x += dt*vx;
                time -= 0.75;           // move ahead again
            } 

        }
        // star moves with a sinusoidal motion
        else {

            radians += 2*Math.PI*dt;    // convert deltaTime into radians
            
            this.x += dt * vx;          // move ahead

            this.y = amplitude*Math.sin(radians) + previousY;   // sinusoidal motion
        }

    }
    
}
