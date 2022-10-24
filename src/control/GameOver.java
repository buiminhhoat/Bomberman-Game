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

public class GameOver {
    private Group root;
    private Scene scene;

    private Image menuButton1;
    private Image menuButton2;
    private Image background;
    private ImageView menuButtonView;
    private ImageView backgroundView;

    private AnimationTimer timer;

    public GameOver() {
        root = new Group();
        root.getChildren().add(BombermanGame.canvas);
        scene = new Scene(root);
        scene.setCursor(new ImageCursor(Sprite.mouseImg));
    }

    private void initImg() {
        background = new Image("/images/game_over.png", true);
        menuButton1 = new Image("/images/menu_button1.png");
        menuButton2 = new Image("/images/menu_button2.png");
    }

    public void display() {
        SoundManager.playChunk(Chunk.GAME_OVER);

        initImg();

        backgroundView = new ImageView(background);
        backgroundView.setFitWidth(BombermanGame.WIDTH * Sprite.SCALED_SIZE);
        backgroundView.setFitHeight(BombermanGame.HEIGHT * Sprite.SCALED_SIZE
                + BombermanGame.CANVAS_TRANSLATE_Y);
        root.getChildren().add(backgroundView);

        menuButtonView = new ImageView(menuButton1);
        menuButtonView.setX(310);
        menuButtonView.setY(510);
        menuButtonView.setFitWidth(180);
        menuButtonView.setFitHeight(75);
        root.getChildren().add(menuButtonView);

        scene.setOnMouseClicked(event -> {
            if (Util.checkMouseInImageView(menuButtonView)) {
                timer.stop();
                BombermanGame.bombermanGame.displayMenu();
            }
        });

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
            menuButtonView.setImage(menuButton2);
        } else {
            menuButtonView.setImage(menuButton1);
        }
    }

    public Scene getScene() {
        return scene;
    }
}