import algorithm.BreadthFirstSearch;
import entities.Entity;
import entities.animationentity.AnimationEntity;
import entities.animationentity.bomb.Bomb;
import entities.animationentity.movingentity.MovingEntity;
import entities.animationentity.movingentity.bomber.Bomber;
import entities.animationentity.movingentity.enemies.Balloon.Beehive;
import entities.animationentity.movingentity.enemies.Creeper;
import entities.animationentity.movingentity.enemies.Enemies;
import entities.animationentity.movingentity.enemies.chase.Bee;
import entities.animationentity.movingentity.enemies.chase.Chase;
import entities.animationentity.movingentity.enemies.chase.Oneal;
import entities.block.Brick;
import gamemap.GameMap;
import graphics.Sprite;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class BombermanGame extends Application {

    public static final int WIDTH = 25;
    public static final int HEIGHT = 20;
    public static final int INF = (int) 1e9 + 7;
    public static final String TITLE = "Bomberman Game";

    public Scene scene = null;
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> movingEntities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private List<Entity> listBombingEntity = new ArrayList<>();
    private MovingEntity bomberman;
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

        initGame();
    }

    private void initGame() {
        gameMap = new GameMap();
        int currentLevel = 1;
        gameMap.initMap(currentLevel);
        stillObjects = gameMap.getListStillObjects();
        movingEntities = gameMap.getListMovingEntity();
        for (Entity entity : movingEntities) {
            if (entity instanceof Bomber) {
                bomberman = (MovingEntity) entity;
                break;
            }
        }
        for (Entity entity : movingEntities) {
            if (entity instanceof Bomber
                || entity instanceof Creeper) {
                listBombingEntity.add(entity);
            }
        }

        for (Entity entity : movingEntities) {
            if (entity instanceof Chase) {
                if (entity instanceof Oneal) {
                    ((Oneal) entity).setTargetEntity(bomberman);
                }

                if (entity instanceof Bee) {
                    ((Bee) entity).setDistanceChase(gameMap.getCol() * gameMap.getRow());
                    BreadthFirstSearch.CalculatorBreadthFirstSearch(
                        entity.getYPixel() / Sprite.SCALED_SIZE,
                        entity.getXPixel() / Sprite.SCALED_SIZE,
                        gameMap);
                    int minDist = INF;
                    for (Entity targetEntity : movingEntities) {
                        if (targetEntity instanceof Beehive) {
                            int dist = BreadthFirstSearch.minDistance(
                                targetEntity.getYPixel() / Sprite.SCALED_SIZE,
                                targetEntity.getXPixel() / Sprite.SCALED_SIZE);
                            if (minDist > dist) {
                                minDist = dist;
                                ((Bee) entity).setTargetEntity(targetEntity);
                            }
                        }
                    }
                }
            }
        }
    }

    private void move() {
        bomberman.move(gameMap);
        ((Bomber) bomberman).pickUpItem(gameMap);
        for (Entity entity : movingEntities) {
            if (entity instanceof Bomber) {
                continue;
            }
            if (entity instanceof Enemies) {
                ((Enemies) entity).chooseDirection(gameMap);
            }
            ((MovingEntity) entity).checkRun();
        }
        bomberman.checkRun();
    }

    private void checkBomb() {
        for (Entity entity : listBombingEntity) {
            if (entity instanceof MovingEntity) {
                List<Bomb> bombList = ((MovingEntity) entity).getBombList();
                for (Bomb bomb : bombList) {
                    bomb.checkExplosion(gameMap, movingEntities);
                }
                for (Bomb bomb : bombList) {
                    ((MovingEntity) entity).explodedBomb(bomb, gameMap);
                }
                bombList.removeIf(bomb -> !bomb.getAnimations());
                for (Bomb bomb : bombList) {
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
            if (entity instanceof AnimationEntity) {
                if (((AnimationEntity) entity).getlives() == 0) {

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
        move();
        checkBomb();
        checkDestroyBrick();
        checkKillEntity();
        stillObjects.forEach(Entity::update);
        movingEntities.forEach(Entity::update);
        fps();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        for (Entity entity : listBombingEntity) {
            if (entity instanceof MovingEntity) {
                List<Bomb> bombList = ((MovingEntity) entity).getBombList();
                bombList.forEach(g -> g.render(gc));
            }
        }
        movingEntities.forEach(g -> g.render(gc));
    }
}
