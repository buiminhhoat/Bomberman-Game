package control;

import enumeration.Chunk;
import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NextLevel {
    private Group root;
    private Scene scene;

    public NextLevel() {
        root = new Group();
        root.getChildren().add(BombermanGame.canvas);
        scene = new Scene(root);
        scene.setCursor(new ImageCursor(Sprite.mouseImg));
    }

    public void display() {
        SoundManager.playChunk(Chunk.NEXT_LEVEL);

        Rectangle rectangle = new Rectangle(0, 0, BombermanGame.WIDTH * Sprite.SCALED_SIZE,
                BombermanGame.HEIGHT * Sprite.SCALED_SIZE + BombermanGame.CANVAS_TRANSLATE_Y);
        rectangle.setFill(Color.BLACK);
        rectangle.setStroke(Color.BLACK);
        root.getChildren().add(rectangle);

        String tx = "Stage " + BombermanGame.bombermanGame.getCurrentLevel();
        Text totalScore = new Text(tx);
        totalScore.setFont(Font.loadFont(Menu.class.getResourceAsStream(
                "/fonts/PressStart2P-Regular.ttf"), 30));
        totalScore.setFill(Color.WHITE);
        totalScore.setX(300);
        totalScore.setY(300);
        root.getChildren().add(totalScore);

        Text comment = new Text("Press SPACE to continue!");
        comment.setFont(Font.loadFont(Menu.class.getResourceAsStream(
                "/fonts/PressStart2P-Regular.ttf"), 15));
        comment.setFill(Color.ORANGE);
        comment.setX(240);
        comment.setY(600);
        root.getChildren().add(comment);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                BombermanGame.bombermanGame.displayGame();
            }
        });
    }

    public Scene getScene() {
        return scene;
    }
}