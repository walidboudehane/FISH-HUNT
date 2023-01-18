import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class FishHunt extends Application {

    public static double WINDOW_WIDTH = 640;
    public static double WINDOW_HEIGHT = 480;

    Scene currentScene;
    
    private Controller controller = new Controller();

    private Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
    
    private GraphicsContext context = canvas.getGraphicsContext2D();

    private Pane root = new Pane();
    
    @Override
    public void start(Stage primaryStage) throws Exception{

        

        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = 0;
            
            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }

                double deltaTime = (now - lastTime) * 1e-9;
                context.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                
                controller.update(deltaTime);
                controller.draw(context);
                updateGame(deltaTime);

                lastTime = now;
            }   

        };
        
        timer.start();
        primaryStage.setTitle("Fish Hunt");
        primaryStage.setScene(homeScreen(primaryStage));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Scene homeScreen(Stage stage){

        VBox homeScreenRoot = new VBox();
        VBox buttonGroup = new VBox();
        Scene homeScreenScene = new Scene(homeScreenRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

        // game image
        Image logoImg = new Image("/image/logo.png");
        ImageView logoImgView = new ImageView(logoImg);
        logoImgView.setPreserveRatio(true);
        homeScreenRoot.getChildren().add(logoImgView);

        homeScreenRoot.setBackground(new Background(new BackgroundFill(Color.DARKBLUE,
        CornerRadii.EMPTY, Insets.EMPTY)));

        Button nwGameButton = new Button("Nouvelle Partie!");
        Button scoreButton = new Button( "Meilleurs scores");

        buttonGroup.getChildren().add(nwGameButton);
        buttonGroup.getChildren().add(scoreButton);
        buttonGroup.setSpacing(10);
        buttonGroup.setAlignment(Pos.CENTER);

        homeScreenRoot.setAlignment(Pos.CENTER);
        homeScreenRoot.getChildren().add(buttonGroup);
        homeScreenRoot.setSpacing(10);

        nwGameButton.setOnMouseClicked((value) -> {
            controller.changeScene(stage, gameScene());
            controller.startGame();
        });

        scoreButton.setOnMouseClicked((value) -> {
            controller.changeScene(stage, highScoreScene(stage, homeScreen(stage)));
        });
        
        return homeScreenScene;
    }

    private Scene gameScene(){

        Scene gameScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        root.setBackground(new Background(new BackgroundFill(Color.DARKBLUE,
        CornerRadii.EMPTY, Insets.EMPTY)));

        root.getChildren().add(canvas);

        // target image
        Image targetImg = new Image("/Image/cible.png");
        ImageView targetImgView = new ImageView(targetImg);
        targetImgView.setFitHeight(50);
        targetImgView.setFitWidth(50);
        root.getChildren().add(targetImgView);

        // target image follows the mouse
        root.setOnMouseMoved((event) -> {
            double w = targetImgView.getBoundsInLocal().getWidth();
            double h = targetImgView.getBoundsInLocal().getHeight();
            double x = event.getX() - w / 2;
            double y = event.getY() - h / 2;

            targetImgView.setX(x);
            targetImgView.setY(y);
        });
        
        // shoot a ball when the mouse is clicked
        root.setOnMouseClicked((event) ->{
            
            controller.sendBall(event);
        });

        return gameScene;
    } 


    private void updateGame (double dt){

        VBox lifeAndScore = new VBox();
        HBox lives = new HBox();

        // score
        Text score = new Text();
        score.setText("" + controller.getScore());
        score.setFont(Font.font("Arial", 28));
        score.setFill(Color.WHITE);
        lifeAndScore.getChildren().add(score);

        // lives available
        Image lifeImg = new Image("/image/fish/00.png",30,30,false,false);
        Image lifeImg1 = lifeImg;
        ImageView lifeImgVw1 = new ImageView(lifeImg1);
        Image lifeImg2 = lifeImg;
        ImageView lifeImgVw2 = new ImageView(lifeImg2);
        Image lifeImg3 = lifeImg;
        ImageView lifeImgVw3 = new ImageView(lifeImg3);

        lives.getChildren().addAll(lifeImgVw1,lifeImgVw2,lifeImgVw3);
        lives.setAlignment(Pos.CENTER);
        lives.setSpacing(15);
        
        lifeAndScore.getChildren().add(lives);
        lifeAndScore.setAlignment(Pos.CENTER);
        lifeAndScore.setSpacing(50);
        lifeAndScore.relocate(WINDOW_WIDTH/2 - 60, 50);

        root.getChildren().add(lifeAndScore);
    }

    private Scene highScoreScene(Stage stage,Scene scene){

        BorderPane highScoreRoot = new BorderPane();
        Pane pane=new Pane();
        VBox buttonGroup = new VBox();

        Scene highScoreScene = new Scene(highScoreRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

        highScoreRoot.setCenter(pane);
        highScoreRoot.setBackground(new Background(new BackgroundFill(Color.web("BEBDB8"),
        CornerRadii.EMPTY, Insets.EMPTY)));


        Text title =new Text("Meilleurs Scores");
        title.setFill(Color.BLACK);
        title.setFont(Font.font("ARRAY",24));
        title.setLayoutX(200);
        title.setTextAlignment(TextAlignment.CENTER);
        highScoreRoot.setTop(title);

        ListView<String> listView = new ListView<>();
        listView.setMaxWidth(WINDOW_WIDTH - 50);

        highScoreRoot.setCenter(listView);
        

        Button menuButton = new Button("Menu");

        buttonGroup.getChildren().add(menuButton);
        buttonGroup.setAlignment(Pos.BOTTOM_CENTER);
        buttonGroup.setPadding(new Insets(10)); 
        highScoreRoot.setBottom(buttonGroup);

        menuButton.setOnMouseClicked((value) -> {
            controller.changeScene(stage, scene);     
        }); 

        return highScoreScene;
    }

}
