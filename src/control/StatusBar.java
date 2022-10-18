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
    private ImageView statusSound;
    private Text levelText;
    private Text timeText;
    private Text scoreText;
    private Text livesText;
    private Text highScoreText;
    private Image muteButton;
    private Image unmuteButton;

    public StatusBar() {
    }

    public void createStatusBar(Group root, LevelGame levelGame) {
        levelText = new Text("Level: 1");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        levelText.setFill(Color.WHITE);
        levelText.setX(100);
        levelText.setY(20);

        livesText = new Text("Lives: ");
        livesText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        livesText.setFill(Color.WHITE);
        livesText.setX(230);
        livesText.setY(20);

        timeText = new Text("Times: 000");
        timeText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        timeText.setFill(Color.WHITE);
        timeText.setX(360);
        timeText.setY(20);

        scoreText = new Text("Score: 000");
        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        scoreText.setFill(Color.WHITE);
        scoreText.setX(490);
        scoreText.setY(20);

        highScoreText = new Text("High score: 000");
        highScoreText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        highScoreText.setFill(Color.WHITE);
        highScoreText.setX(620);
        highScoreText.setY(20);

        muteButton = new Image("/images/mute.png");
        unmuteButton = new Image("/images/unmute.png");

        statusSound = new ImageView(muteButton);
        statusSound.setX(30);
        statusSound.setY(0);
        statusSound.setScaleX(0.6);
        statusSound.setScaleY(0.6);

        Pane pane = new Pane();
        pane.getChildren().addAll(statusSound, levelText,
                livesText, timeText, scoreText, highScoreText);
        pane.setMinSize(800, 32);
        pane.setMaxSize(800, 480);
        pane.setStyle("-fx-background-color: #353535");

        root.getChildren().add(pane);

        statusSound.setOnMouseClicked(event -> {
            SoundManager.changeStatus();
            updateStatusBar(levelGame);
        });
    }

    public void updateStatusBar(LevelGame levelGame) {
        levelText.setText("Level: " + BombermanGame.bombermanGame.getCurrentLevel());
        scoreText.setText("Score: " + BombermanGame.bombermanGame.getScore());
        timeText.setText("Time: " + levelGame.getTime());
        livesText.setText("Lives: " + levelGame.getBomberman().getLives());
        highScoreText.setText("High score: " + levelGame.getHighScore());

        if (SoundManager.isMuted) {
            statusSound.setImage(unmuteButton);
        } else {
            statusSound.setImage(muteButton);
        }
    }
}

