package control;

import graphics.Sprite;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BombermanGame extends Application {
    private int score = 0;
    private int currentLevel = 0;

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

    public static BombermanGame bombermanGame = new BombermanGame();

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

    public void displayMenu() {
        SoundManager.stopMusic();
        canvas.setTranslateY(32);
        Menu menu = new Menu();
        stage.setScene(menu.getScene());
        menu.display();
    }

    public void displayGameOver() {
        SoundManager.stopMusic();
        canvas.setTranslateY(32);
        GameOver gameOver = new GameOver();
        gameOver.display();
        stage.setScene(gameOver.getScene());
        currentLevel = 0;
        score = 0;
    }

    public void displayGameVictory() {
        SoundManager.stopMusic();
        canvas.setTranslateY(32);
        GameVictory gameVictory = new GameVictory();
        gameVictory.display();
        stage.setScene(gameVictory.getScene());
        currentLevel = 0;
        score = 0;
    }

    public void displayNextLevel() {
        File file = new File("res/data/maxunlocklevel.txt");
        Scanner sc = null;
        int maxunlocklevel = 0;
        try {
            sc = new Scanner(file);
            maxunlocklevel = sc.nextInt();
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ++currentLevel;
        if (currentLevel > maxunlocklevel) {
            maxunlocklevel = currentLevel;
            try {
                Formatter f = new Formatter("res/data/maxunlocklevel.txt");
                f.format(String.valueOf(maxunlocklevel));
                f.close();
            } catch (Exception e) {
                System.out.println("Error write file maxunlocklevel.txt");
            }
        }
        SoundManager.stopMusic();
        canvas.setTranslateY(32);
        NextLevel nextLevel = new NextLevel();
        nextLevel.display();
        stage.setScene(nextLevel.getScene());
    }

    public void displayGame() {
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

    public void displayContinue() {
        SoundManager.stopMusic();
        canvas.setTranslateY(32);
        Scanner sc = null;
        try {
            File file = new File("res/data/maxunlocklevel.txt");
            sc = new Scanner(file);
            currentLevel = sc.nextInt();
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        LevelGame levelGame = new LevelGame(currentLevel);

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
