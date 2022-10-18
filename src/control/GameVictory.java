package control;

import algorithm.Util;
import enumeration.Chunk;
import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameVictory {
    private Group root;
    private Scene scene;

    private Image menuButton1;
    private Image menuButton2;
    private Image background;
    private ImageView menuButtonView;
    private ImageView backgroundView;

    private AnimationTimer timer;

    public GameVictory() {
        root = new Group();
        root.getChildren().add(BombermanGame.canvas);
        scene = new Scene(root);
        scene.setCursor(new ImageCursor(Sprite.mouseImg));
    }

    private void initImg() {
        background = new Image("/images/game_victory.png", true);
        menuButton1 = new Image("/images/menu_button1.png");
        menuButton2 = new Image("/images/menu_button2.png");
    }

    public void display() {
        SoundManager.playChunk(Chunk.GAME_VICTORY);

        initImg();

        backgroundView = new ImageView(background);
        backgroundView.setFitWidth(BombermanGame.WIDTH * Sprite.SCALED_SIZE);
        backgroundView.setFitHeight(BombermanGame.HEIGHT * Sprite.SCALED_SIZE + 32);
        root.getChildren().add(backgroundView);

        Text TextTotalScore = new Text("Total Score");
        TextTotalScore.setFont(Font.loadFont(Menu.class.getResourceAsStream(
                "/fonts/PressStart2P-Regular.ttf"), 40));
        TextTotalScore.setFill(Color.YELLOW);
        TextTotalScore.setStroke(Color.ORANGE);
        TextTotalScore.setStrokeWidth(3);
        TextTotalScore.setX(180);
        TextTotalScore.setY(280);
        root.getChildren().add(TextTotalScore);

        String tx = "" + BombermanGame.score;
        while (tx.length() < 5) {
            tx = "0" + tx;
        }
        Text totalScore = new Text(tx);
        totalScore.setFont(Font.loadFont(Menu.class.getResourceAsStream(
                "/fonts/PressStart2P-Regular.ttf"), 60));
        totalScore.setFill(Color.ORANGE);
        totalScore.setStroke(Color.YELLOW);
        totalScore.setStrokeWidth(3);
        totalScore.setX(255);
        totalScore.setY(400);
        root.getChildren().add(totalScore);

        menuButtonView = new ImageView(menuButton1);
        menuButtonView.setX(310);
        menuButtonView.setY(510);
        menuButtonView.setFitWidth(180);
        menuButtonView.setFitHeight(75);
        root.getChildren().add(menuButtonView);

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        timer.start();
    }

    public void update() {
        if (Util.checkMouseInImageView(menuButtonView)) {
            scene.setOnMouseClicked(event -> {
                timer.stop();
                BombermanGame.displayMenu();
            });
            menuButtonView.setImage(menuButton2);
        }
        else {
            menuButtonView.setImage(menuButton1);
        }
    }

    public Scene getScene() {
        return scene;
    }
}