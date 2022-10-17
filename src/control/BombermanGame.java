package control;

import graphics.Sprite;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class BombermanGame extends Application {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 20;

    public static final int INF = (int) 1e9 + 7;
    public static final String TITLE = "Bomberman Game";

    public static Scene scene = null;
    public static GraphicsContext gc;
    public static Canvas canvas;
    public static StatusBar statusBar;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }
    public void start(Stage stage) {
        LevelGame levelGame = new LevelGame(2);

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(32);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        statusBar = new StatusBar();
        statusBar.createStatusBar(root, levelGame);
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();

        levelGame.start();
    }
}
