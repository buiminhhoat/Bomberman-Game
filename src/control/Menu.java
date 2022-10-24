package control;

import algorithm.Util;
import entities.animationentity.movingentity.bomber.Bomber;
import enumeration.Music;
import graphics.Sprite;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Menu {
    private Group root;
    private Scene scene;

    private Image background;
    private Image playButton1;
    private Image playButton2;
    private Image continueButton1;
    private Image continueButton2;
    private Image exitButton1;
    private Image exitButton2;

    private ImageView backgroundView;
    private ImageView playButtonView;
    private ImageView continueButtonView;
    private ImageView exitButtonView;

    private AnimationTimer timer;

    public Menu() {
        root = new Group();
        root.getChildren().add(BombermanGame.canvas);
        scene = new Scene(root);
        scene.setCursor(new ImageCursor(Sprite.mouseImg));
    }

    private void initImg() {
        background = new Image("/images/background.png", true);
        playButton1 = new Image("/images/play_button1.png");
        playButton2 = new Image("/images/play_button2.png");
        continueButton1 = new Image("/images/continue_button1.png");
        continueButton2 = new Image("/images/continue_button2.png");
        exitButton1 = new Image("/images/exit_button1.png");
        exitButton2 = new Image("/images/exit_button2.png");
    }

    public void display() {
        SoundManager.playMusic(Music.MENU);

        initImg();

        backgroundView = new ImageView(background);
        backgroundView.setFitWidth(BombermanGame.WIDTH * Sprite.SCALED_SIZE);
        backgroundView.setFitHeight(BombermanGame.HEIGHT * Sprite.SCALED_SIZE
                + BombermanGame.CANVAS_TRANSLATE_Y);
        root.getChildren().add(backgroundView);

        Text bombermanText = new Text("BOMBERMAN");
        bombermanText.setFont(Font.loadFont(Menu.class.getResourceAsStream(
                "/fonts/Bomberman.ttf"), 86));
        bombermanText.setFill(Color.WHITE);
        bombermanText.setStroke(Color.ORANGE);
        bombermanText.setStrokeWidth(5);
        bombermanText.setX(90);
        bombermanText.setY(200);
        root.getChildren().add(bombermanText);

        playButtonView = new ImageView(playButton1);
        playButtonView.setX(290);
        playButtonView.setY(300);
        playButtonView.setFitWidth(220);
        playButtonView.setFitHeight(85);
        root.getChildren().add(playButtonView);

        continueButtonView = new ImageView(continueButton1);
        continueButtonView.setX(270);
        continueButtonView.setY(400);
        continueButtonView.setFitWidth(260);
        continueButtonView.setFitHeight(85);
        root.getChildren().add(continueButtonView);

        exitButtonView = new ImageView(exitButton1);
        exitButtonView.setX(310);
        exitButtonView.setY(500);
        exitButtonView.setFitWidth(185);
        exitButtonView.setFitHeight(70);
        root.getChildren().add(exitButtonView);

        scene.setOnMouseClicked(event -> {
            if (Util.checkMouseInImageView(playButtonView)) {
                timer.stop();
                BombermanGame.bombermanGame.displayNextLevel();
            }
            if (Util.checkMouseInImageView(continueButtonView)) {
                timer.stop();
                BombermanGame.bombermanGame.displayContinue();
            }
            if (Util.checkMouseInImageView(exitButtonView)) {
                System.exit(0);
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
        if (Util.checkMouseInImageView(playButtonView)) {
            playButtonView.setImage(playButton2);
        } else {
            playButtonView.setImage(playButton1);
        }

        if (Util.checkMouseInImageView(continueButtonView)) {
            continueButtonView.setImage(continueButton2);
        } else {
            continueButtonView.setImage(continueButton1);
        }


        if (Util.checkMouseInImageView(exitButtonView)) {
            exitButtonView.setImage(exitButton2);
        } else {
            exitButtonView.setImage(exitButton1);
        }
    }

    public Scene getScene() {
        return scene;
    }
}