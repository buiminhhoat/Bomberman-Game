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
import entities.dynamicentity.enemies.Balloon;
import entities.dynamicentity.enemies.Ghost;
import entities.dynamicentity.enemies.Oneal;
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
    private List<Bomb> listBombs = new ArrayList<>();
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
                    bomberman.up(gameMap);
                    System.out.println("UP");
                    break;
                case DOWN:
                    bomberman.down(gameMap);
                    System.out.println("DOWN");
                    break;
                case RIGHT:
                    bomberman.right(gameMap);
                    System.out.println("RIGHT");
                    break;
                case LEFT:
                    bomberman.left(gameMap);
                    System.out.println("LEFT");
                    break;
                case SPACE:
                    System.out.println("SPACE");
                    int numBomb = ((Bomber) bomberman).getNumberBombs();
                    if (numBomb == 0 || gameMap.checkBlockedPixel(bomberman.getX(),
                        bomberman.getY())) {
                        break;
                    }
                    ((Bomber) bomberman).setNumberBombs(numBomb - 1);
                    Bomb bomb = new Bomb(bomberman, gameMap);
                    listBombs.add(bomb);
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

        gameMap = new GameMap(listBombs);
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
        BreadthFirstSearch.initBreadthFirstSearch(gameMap);
    }

    public void update() {
        for (Entity entity : movingEntities) {
            if (entity instanceof Bomber) {
                continue;
            }
            if (entity instanceof Balloon) {
                ((Balloon) entity).chooseDirection(gameMap);
            }
            if (entity instanceof Oneal) {
                ((Oneal) entity).chooseDirection(gameMap);
            }
            if (entity instanceof Ghost) {
                ((Ghost) entity).chooseDirection(gameMap);
            }
            ((DynamicEntity) entity).checkRun();
        }

        for (Bomb bomb : listBombs) {
            bomb.checkExplosion(gameMap);
        }

        for (Bomb bomb : listBombs) {
            if (!bomb.getAnimations()) {
                int numBomb = ((Bomber) bomberman).getNumberBombs();
                ((Bomber) bomberman).setNumberBombs(numBomb + 1);
            }
        }
        listBombs.removeIf(bomb -> !bomb.getAnimations());

        for (int i = 0; i < stillObjects.size(); ++i) {
            Entity brick = stillObjects.get(i);
            if (brick instanceof Brick) {
                if (((Brick) brick).checkDestroy()) {
                    stillObjects.remove(i);
                    --i;
                }
            }
        }

        bomberman.checkRun();
        BreadthFirstSearch.CalculatorBreadthFirstSearch(bomberman.getY() / Sprite.SCALED_SIZE,
            bomberman.getX() / Sprite.SCALED_SIZE, gameMap);

        stillObjects.forEach(Entity::update);
        movingEntities.forEach(Entity::update);
        listBombs.forEach(Entity::update);

        ///
        try {
            Thread.sleep(40);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        listBombs.forEach(g -> g.render(gc));
        movingEntities.forEach(g -> g.render(gc));
    }
}
