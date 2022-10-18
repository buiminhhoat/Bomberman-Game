package control;

import graphics.Sprite;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BombermanGame extends Application {
    public static int score = 0;
    public static int currentLevel = 0;

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
        displayMenu();

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
        SoundManager.stopMusic();
        canvas.setTranslateY(32);
        Menu menu = new Menu();
        stage.setScene(menu.getScene());
        menu.display();
    }

    public static void displayGameOver() {
        SoundManager.stopMusic();
        canvas.setTranslateY(32);
        GameOver gameOver = new GameOver();
        gameOver.display();
        stage.setScene(gameOver.getScene());
        currentLevel = 0;
        score = 0;
    }

    public static void displayGameVictory() {
        SoundManager.stopMusic();
        canvas.setTranslateY(32);
        GameVictory gameVictory = new GameVictory();
        gameVictory.display();
        stage.setScene(gameVictory.getScene());
        currentLevel = 0;
        score = 0;
    }

    public static void displayNextLevel() {
        ++currentLevel;
        SoundManager.stopMusic();
        canvas.setTranslateY(32);
        NextLevel nextLevel = new NextLevel();
        nextLevel.display();
        stage.setScene(nextLevel.getScene());
    }

    public static void displayGame() {
        SoundManager.stopMusic();
        canvas.setTranslateY(32);
        LevelGame levelGame = new LevelGame(currentLevel);

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
        stage.setScene(levelGame.getScene());
    }
}
