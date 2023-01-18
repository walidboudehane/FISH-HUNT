import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller {

    private Game game = new Game();

    boolean gameScene;
    boolean homeScreen;
    boolean scoreScene;

    public Controller(){}

    public void startGame(){
        gameScene = true;
    }

    // go from a scene to another
    public void changeScene(Stage stage, Scene scene){

        stage.setScene(scene);
        
    }

    
    public void update(double deltaTime){
        
        if (gameScene){
            game.update(deltaTime);  
        }
    }

    public void draw(GraphicsContext context){

        if (gameScene){
            game.draw(context);  
        }
        
    }

    public void sendBall(MouseEvent event){
        game.sendBall(event);
    }

    public int getScore(){
        return game.getScore();
    }

}
