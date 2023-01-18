import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class Game {
    
    private double WIDTH = FishHunt.WINDOW_WIDTH;
    private double HEIGHT = FishHunt.WINDOW_HEIGHT;
    private Bubbles[] bubbleGroup1 = new Bubbles[5] ;
    private Bubbles[] bubbleGroup2 = new Bubbles[5] ;
    private Bubbles[] bubbleGroup3 = new Bubbles[5] ;
    private FishSimple fishSimple;
    private FishSpecial fishSpecial;
    private double bubbleTime;
    private double fishTime;
    private double fishSpecialTime;
    private Ball ball;
    private int score;

    public Game(){

        score = 0; 
        addBubbleGroup(bubbleGroup1);
        addBubbleGroup(bubbleGroup2);
        addBubbleGroup(bubbleGroup3); 
        createFishSimple();
        createFishSpecial();

    }

    public void update(double deltaTime){

        // generate bubbles every 3 seconds
        bubbleTime += deltaTime;
        if (bubbleTime >= 3){
            addBubbleGroup(bubbleGroup1);
            addBubbleGroup(bubbleGroup2);
            addBubbleGroup(bubbleGroup3);
            bubbleTime -= 3; 
        }
        createBubles(deltaTime,bubbleGroup1);
        createBubles(deltaTime,bubbleGroup2);
        createBubles(deltaTime,bubbleGroup3);

        // shoot a ball
        if (ball != null){
            ball.update(deltaTime);

            // test if the ball hit one of the fish
            ball.hitFish(fishSimple);
            ball.hitFish(fishSpecial);

            if (ball.fishHit){
                fishSimple.setDead();
                score ++;
            }
            else if (ball.fishHit){
                fishSpecial.setDead();
                score ++;
                
            }
            
        }

        // generate a fish every 3 seconds
        fishTime += deltaTime;
        if (fishTime >= 3){
            createFishSimple();
            fishTime -= 3;
        }
        if (fishSimple.isAlive){
            fishSimple.update(deltaTime);
        }
        
        // generate a special fish every 5 seconds
        fishSpecialTime += deltaTime;
        if (fishSpecialTime >= 10){
            createFishSpecial();
            fishSpecialTime -= 10;
        }
        if (fishSpecial.isAlive){
            fishSpecial.update(deltaTime);
        }
        
    }

    public void draw(GraphicsContext context){

        drawBubles(context);
        
        if (fishSimple.isAlive){
            fishSimple.draw(context);
        }
        if (fishSpecial.isAlive){
            fishSpecial.draw(context);
        }

        if (ball != null){
            ball.draw(context);
            ball = null;
        }
    }

    // create an array of 5 bubbles
    private void addBubbleGroup(Bubbles[] b){
        double baseX = Math.random() * WIDTH;
        for (int i = 0; i < 5; i++){
            b[i] = new Bubbles(baseX, HEIGHT);
        }
    }


    public void drawBubles(GraphicsContext context){

        for (Bubbles bubble : bubbleGroup1){
    
            bubble.draw(context);
        }

        for (Bubbles bubble : bubbleGroup2){
    
            bubble.draw(context);
        }

        for (Bubbles bubble : bubbleGroup3){
    
            bubble.draw(context);
        }

    }

    // send the bubbles
    public void createBubles(double deltaTime, Bubbles[] b){
        
        for (Bubbles bubble : b){
            try {

                bubble.update(deltaTime);
            } 
            catch (InterruptedException e) {
        
                e.printStackTrace();
            }
        }
        
    } 
    

    public void sendBall(MouseEvent event){
        double x = event.getX();
        double y = event.getY();
        
        ball = new Ball(x, y);
    }

    
    public void createFishSimple(){

        fishSimple = new FishSimple(0, 0, 1);
        
    }

    public void createFishSpecial(){
       
        fishSpecial = new FishSpecial(0, 0, 1);
        
    }

    public int getScore(){
        return score;
    }

}    

