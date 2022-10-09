package uet.oop.bomberman;

import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.movingentity.bomber.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingentity.MovingEntity;
import uet.oop.bomberman.entities.movingentity.enemies.Balloon;
import uet.oop.bomberman.entities.movingentity.enemies.Oneal;
import uet.oop.bomberman.gamemap.GameMap;
import uet.oop.bomberman.graphics.Sprite;

public class BombermanGame extends Application {

    public static final int WIDTH = 25;
    public static final int HEIGHT = 20;

    public static final int MAXSIZE = 100;
    public static final String TITLE = "Bomberman Game";

    private int currentLevel = 1;

    private GraphicsContext gc;
    private Canvas canvas;
    private List <Entity> movingEntities = new ArrayList<>();
    private List <Entity> stillObjects = new ArrayList<>();

    private MovingEntity bomberman;

    public Scene scene = null;

    private GameMap gameMap;

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
                    Move.up(bomberman, gameMap);
                    System.out.println("UP");
                    break;
                case DOWN:
                    Move.down(bomberman, gameMap);
                    System.out.println("DOWN");
                    break;
                case RIGHT:
                    Move.right(bomberman, gameMap);
                    System.out.println("RIGHT");
                    break;
                case LEFT:
                    Move.left(bomberman, gameMap);
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
        gameMap.initMap(1);
        stillObjects = gameMap.getListStillObjects();
        movingEntities = gameMap.getListMovingEntity();
        for (Entity entity: movingEntities) {
            if (entity instanceof Bomber) {
                bomberman = (MovingEntity) entity;
                break;
            }
        }
    }

    public void update() {
        Move.checkRun(bomberman);
        for (int i = 0; i < movingEntities.size(); ++i) {
            Entity entity = movingEntities.get(i);
            if (entity instanceof Bomber) {
                continue;
            }
            if (entity instanceof Balloon) {
                entity = (Balloon) entity;
                ((Balloon) entity).setGameMap(gameMap);
                ((Balloon) entity).randomDirection();
            }
            if (entity instanceof Oneal) {
                entity = (Oneal) entity;
                ((Oneal) entity).setGameMap(gameMap);
                ((Oneal) entity).randomDirection();
            }
            Move.checkRun((MovingEntity) entity);
        }
        movingEntities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        movingEntities.forEach(g -> g.render(gc));
    }
}
