import entities.dynamicentity.enemies.Creeper;
import entities.dynamicentity.enemies.Enemies;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import algorithm.BreadthFirstSearch;
import entities.Entity;
import entities.block.Brick;
import entities.dynamicentity.DynamicEntity;
import entities.dynamicentity.bomb.Bomb;
import entities.dynamicentity.bomber.Bomber;
import gamemap.GameMap;
import graphics.Sprite;

public class BombermanGame extends Application {

    public static final int WIDTH = 25;
    public static final int HEIGHT = 20;
    public static final String TITLE = "Bomberman Game";
    public Scene scene = null;
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> movingEntities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private List<Entity> listBombingEntity = new ArrayList<>();
    private DynamicEntity bomberman;
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
                    bomberman.setUp(true);
                    System.out.println("UP");
                    break;
                case DOWN:
                    bomberman.setDown(true);
                    System.out.println("DOWN");
                    break;
                case RIGHT:
                    bomberman.setRight(true);
                    System.out.println("RIGHT");
                    break;
                case LEFT:
                    bomberman.setLeft(true);
                    System.out.println("LEFT");
                    break;
                case SPACE:
                    System.out.println("SPACE");
                    ((Bomber) bomberman).createBomb(gameMap);
                    break;
                case P:
                    System.out.println("P");
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    bomberman.setUp(false);
                    break;
                case DOWN:
                    bomberman.setDown(false);
                    break;
                case RIGHT:
                    bomberman.setRight(false);
                    break;
                case LEFT:
                    bomberman.setLeft(false);
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
        int currentLevel = 1;
        gameMap.initMap(currentLevel);
        stillObjects = gameMap.getListStillObjects();
        movingEntities = gameMap.getListMovingEntity();
        for (Entity entity : movingEntities) {
            if (entity instanceof Bomber) {
                bomberman = (DynamicEntity) entity;
                break;
            }
        }
        for (Entity entity : movingEntities) {
            if (entity instanceof Bomber
                || entity instanceof Creeper) {
                listBombingEntity.add(entity);
            }
        }
        BreadthFirstSearch.initBreadthFirstSearch(gameMap);
    }

    private void move() {
        for (Entity entity : movingEntities) {
            if (entity instanceof Bomber) {
                continue;
            }
            if (entity instanceof Enemies) {
                ((Enemies) entity).chooseDirection(gameMap);
            }
            ((DynamicEntity) entity).checkRun();
        }
        bomberman.checkRun();
    }

    private void checkBomb() {
        for (Entity entity: listBombingEntity) {
            if (entity instanceof DynamicEntity) {
                List<Bomb> bombList = ((DynamicEntity) entity).getBombList();
                for (Bomb bomb: bombList) {
                    bomb.checkExplosion(gameMap, movingEntities);
                }
                for (Bomb bomb: bombList) {
                    ((DynamicEntity) entity).explodedBomb(bomb, gameMap);
                }
                bombList.removeIf(bomb -> !bomb.getAnimations());
                for (Bomb bomb: bombList) {
                    bomb.update();
                }
            }
        }
    }

    private void checkDestroyBrick() {
        for (int i = 0; i < stillObjects.size(); ++i) {
            Entity brick = stillObjects.get(i);
            if (brick instanceof Brick) {
                if (((Brick) brick).checkDestroy()) {
                    stillObjects.remove(i);
                    --i;
                }
            }
        }
    }

    private void checkKillEntity() {
        for (int i = 0; i < movingEntities.size(); ++i) {
            Entity entity = movingEntities.get(i);
            if (entity instanceof DynamicEntity) {
                if (((DynamicEntity) entity).getlives() == 0) {

                    movingEntities.remove(i);
                    --i;
                }
            }
        }
    }

    private void fps() {
        try {
            Thread.sleep(40);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void update() {
        bomberman.move(gameMap);
        move();
        checkBomb();
        checkDestroyBrick();
        checkKillEntity();

        BreadthFirstSearch.CalculatorBreadthFirstSearch(bomberman.getYPixel() / Sprite.SCALED_SIZE,
            bomberman.getXPixel() / Sprite.SCALED_SIZE, gameMap);

        stillObjects.forEach(Entity::update);
        movingEntities.forEach(Entity::update);

        fps();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        for (Entity entity: listBombingEntity) {
            if (entity instanceof DynamicEntity) {
                List<Bomb> bombList = ((DynamicEntity) entity).getBombList();
                bombList.forEach(g -> g.render(gc));
            }
        }
        movingEntities.forEach(g -> g.render(gc));
    }
}
