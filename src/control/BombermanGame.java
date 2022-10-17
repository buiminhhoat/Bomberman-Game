package control;

import graphics.Sprite;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BombermanGame extends Application {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 20;

    public static final int INF = (int) 1e9 + 7;
    public static final String TITLE = "Bomberman Game";

    public static Stage stage;
    public static GraphicsContext gc;
    public static Canvas canvas;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    public void start(Stage stage) {
        this.stage = stage;

        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        stage.setTitle(TITLE);

        SoundManager.initSound();
        displayMenu();

        stage.show();
    }

    public static void displayMenu() {
        canvas.setTranslateY(0);
        Menu menu = new Menu();
        stage.setScene(menu.getScene());
        menu.display();
    }

    public static void displayGame() {
        canvas.setTranslateY(32);
        LevelGame levelGame = new LevelGame();
        stage.setScene(levelGame.getScene());
        levelGame.start();
    }
}
