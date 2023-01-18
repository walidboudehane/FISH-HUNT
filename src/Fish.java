import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

abstract class Fish {
    protected double ax,ay;
    protected double vx,vy;
    protected double x,y;
    protected int level;
    protected double WIDTH = FishHunt.WINDOW_WIDTH;
    protected double HEIGHT = FishHunt.WINDOW_HEIGHT;
    protected int score;
    protected Image img;
    protected double size; 
    protected boolean isAlive;

    public Fish(double x, double y,int level){

        this.x = (Math.random() > 0.5 ? 0 : WIDTH);
        this.y = Math.floor(((3*Math.random()/5) * HEIGHT) + (1* HEIGHT/5));
        this.vx = 100 * Math.pow(level, 1/3) + 200;
        this.level=level;
        this.size = 130;
        this.isAlive = true;
    }
    
    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
    public int getLevel(){
        return this.level;
    }
    public double getVx(){
        return this.vx;
    }
    public double getVy(){
        return this.vy;
    }
    public double getAx(){
        return this.ax;
    }
    public double getAy(){
        return this.ay;
    }
    public void setDead(){
        this.isAlive = false;
    }


    public void setLevel() {
        if (score%5==0){
            level=(int)score/5;
        }
    }

    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void setVx(double vx){
        this.vx = vx;
    }
    public void setVy(double vy){
        this.vy = vy;
    }
    public void setAx(double ax){
        this.ax = ax;;
    }
    public void setAy(double ay){
        this.ay = ay;;
    }

    public void setDirection(Image img) {
        
        this.img = ImageHelpers.flop(img);

    } 

    public abstract void intitialise();

    // set a random color to the fish
    public Image setFishColor(Image img) {
        Color color = Color.rgb(
            (int)Math.floor(Math.random()*256),
            (int)Math.floor(Math.random()*256), 
            (int)Math.floor(Math.random()*256)
        );

        return ImageHelpers.colorize(img,color);

        
    }

    public void update(double dt){}

    public void draw(GraphicsContext context) {
        
        context.drawImage(img, getX(),getY());
        
    }

}