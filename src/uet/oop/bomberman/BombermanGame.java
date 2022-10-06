package uet.oop.bomberman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.movingentity.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.block.Grass;
import uet.oop.bomberman.entities.block.Wall;
import uet.oop.bomberman.entities.movingentity.MovingEntity;
import uet.oop.bomberman.gamemap.GameMap;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.gamemap.GameMap;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 15;

    public static final int MAXSIZE = 100;
    public static final String TITLE = "Bomberman Game";

    private int currentLevel = 1;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    private MovingEntity bomberman;

    public static Scene scene = null;

    public static GameMap gameMap;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    Move.up(bomberman);
                    System.out.println("UP");
                    break;
                case DOWN:
                    Move.down(bomberman);
                    System.out.println("DOWN");
                    break;
                case RIGHT:
                    Move.right(bomberman);
                    System.out.println("RIGHT");
                    break;
                case LEFT:
                    Move.left(bomberman);
                    System.out.println("LEFT");
                    break;
                case SPACE:
                    System.out.println("SPACE");
                    break;
                case P:
                    System.out.println("P");
                    break;
            }
        });

        // Them scene vao stage
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        gameMap = new GameMap();
        stillObjects = gameMap.getListStillObjects(1);

        bomberman = new Bomber(1, 1, Sprite.player_down.getFxImage());
        entities.add(bomberman);
    }

    public void update() {
        Move.checkRun(bomberman);
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
