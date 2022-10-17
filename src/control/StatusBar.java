package control;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StatusBar {
    private ImageView statusGame;
    private Text levelText;
    private Text timeText;
    private Text scoreText;
    private Text livesText;

    private Image pauseButton;
    private Image resumeButton;

    private boolean isPause = false;

    public StatusBar() {
    }

    public void createStatusBar(Group root, LevelGame levelGame) {
        levelText = new Text("Level: 1");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        levelText.setFill(Color.WHITE);
        levelText.setX(50);
        levelText.setY(20);

        livesText = new Text("Lives: ");
        livesText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        livesText.setFill(Color.WHITE);
        livesText.setX(250);
        livesText.setY(20);

        timeText = new Text("Times: 000");
        timeText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        timeText.setFill(Color.WHITE);
        timeText.setX(450);
        timeText.setY(20);

        scoreText = new Text("Score: 000");
        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        scoreText.setFill(Color.WHITE);
        scoreText.setX(650);
        scoreText.setY(20);

        pauseButton = new Image("/textures/pauseButton.png");
        resumeButton = new Image("/textures/resumeButton.png");

        statusGame = new ImageView(pauseButton);
        statusGame.setX(7);
        statusGame.setY(0);
        statusGame.setScaleX(0.8);
        statusGame.setScaleY(0.8);

        Pane pane = new Pane();
        pane.getChildren().addAll(statusGame, levelText, livesText, timeText, scoreText);
        pane.setMinSize(800, 32);
        pane.setMaxSize(800, 480);
        pane.setStyle("-fx-background-color: #353535");

        root.getChildren().add(pane);

        statusGame.setOnMouseClicked(event -> {
            isPause = !isPause;
            updateStatusBar(levelGame);
        });
    }

    public void updateStatusBar(LevelGame levelGame) {
        levelText.setText("Level: " + levelGame.getLevel());
        scoreText.setText("Score: " + levelGame.getScore());
        timeText.setText("Time: " + levelGame.getTime());
        livesText.setText("Lives: " + levelGame.getBomberman().getLives());
        if (isPause) {
            statusGame.setImage(resumeButton);
        } else {
            statusGame.setImage(pauseButton);
        }
    }
}

