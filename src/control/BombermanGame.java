package control;

import graphics.Sprite;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.WindowEvent;

import java.awt.*;

public class BombermanGame extends Application {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 20;

    public static final String TITLE = "Bomberman Game";
    public static final int INF = (int) 1e9 + 7;

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
        displayGame();

        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
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
        Scanner sc = null;
        try {
            File file = new File("res/data/highscore.txt");
            sc = new Scanner(file);
            int highscore = sc.nextInt();
            levelGame.setHighScore(highscore);
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        levelGame.start();
    }
}
