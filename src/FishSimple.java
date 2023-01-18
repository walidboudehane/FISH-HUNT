import javafx.scene.image.Image;

public class FishSimple extends Fish{


    public FishSimple(double x, double y,int level) {
        super(x, y,level);
        
        
        intitialise();
    }
    
    public void intitialise(){

        int nbrImage = (int) Math.floor(Math.random() * 8);

        // generate a random image
        img = new Image("/Image/fish/0"+ nbrImage +".png",size,size,false,false);
        img = setFishColor(img);

        this.vy = - (Math.random() * 100 + 100);
        this.ay = 100;
        this.ax = 0;

        if (this.x != 0){
            this.vx *= -1;
            setDirection(img);
        }

    }

    @Override
    public void setLevel() {
        if (score % 5 == 0){
            level = (int) score / 5;
        }
    }

    
    @Override
    public void update(double dt) {
       
       this.vy += ay * dt;
       this.y += vy * dt;
       this.x += vx * dt;
    }

}
