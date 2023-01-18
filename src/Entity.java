public abstract class Entity {

    protected double x,y;
    protected double vx,vy;  
       

    public Entity (double x, double y){
        this.x = x; 
        this.y = y;  
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setX (double x){
        this.x = x;
    }

    public void setY (double y){
        this.y = y;
    }

    public void update() throws InterruptedException{} 

}
