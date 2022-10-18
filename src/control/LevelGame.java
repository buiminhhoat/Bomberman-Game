package control;

import algorithm.BreadthFirstSearch;
import algorithm.Util;
import entities.Entity;
import entities.animationentity.AnimationEntity;
import entities.animationentity.bomb.Bomb;
import entities.animationentity.movingentity.MovingEntity;
import entities.animationentity.movingentity.bomber.Bomber;
import entities.animationentity.movingentity.enemies.Beehive;
import entities.animationentity.movingentity.enemies.Enemies;
import entities.animationentity.movingentity.enemies.chase.Bee;
import entities.animationentity.movingentity.enemies.chase.Chase;
import entities.animationentity.movingentity.enemies.chase.DeeDee;
import entities.animationentity.movingentity.enemies.chase.Doll;
import entities.animationentity.movingentity.enemies.chase.Oneal;
import entities.block.Brick;
import enumeration.Chunk;
import enumeration.Music;
import gamemap.GameMap;
import graphics.Camera;
import graphics.Sprite;

import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;

public class LevelGame {
    public static final int FINAL_LEVEL = 2;
    private Group root;
    private StatusBar statusBar;
    private Scene scene;
    private int level;
    private int time;

    private int highScore;

    private boolean win = false;

    private List<Entity> animationEntities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private MovingEntity bomberman;
    private GameMap gameMap;

    public LevelGame() {
        time = 0;
        this.level = 1;
        root = new Group();
        statusBar = new StatusBar();
        statusBar.createStatusBar(root, this);
        root.getChildren().add(BombermanGame.canvas);
        scene = new Scene(root);
        scene.setCursor(new ImageCursor(Sprite.mouseImg));
    }

    public LevelGame(int level) {
        time = 0;
        this.level = level;
        root = new Group();
        statusBar = new StatusBar();
        statusBar.createStatusBar(root, this);
        root.getChildren().add(BombermanGame.canvas);
        scene = new Scene(root);
        scene.setCursor(new ImageCursor(Sprite.mouseImg));
    }

    public void start() {
        scene.setOnKeyPressed(event -> {
            if (bomberman != null) {
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
                        if (!bomberman.isDie()) {
                            ((Bomber) bomberman).createBomb(gameMap);
                        }
                        break;
                    case P:
                        System.out.println("P");
                        break;
                }
            }
        });

        scene.setOnKeyReleased(event -> {
            if (bomberman != null) {
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
            }
        });

        initGame();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
                if (((Bomber) bomberman).isWin()) {
                    ((Bomber) bomberman).setWin(false);
                    SoundManager.stopMusic();
                    SoundManager.playChunk(Chunk.LEVEL_COMPLETE);

                    if (level == FINAL_LEVEL) {
                        this.stop();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        BombermanGame.displayGameVictory();
                        return;
                    }

                    this.stop();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    BombermanGame.displayNextLevel();
                    return;
                }

                if (bomberman.isDisappeared()) {
                    this.stop();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    BombermanGame.displayGameOver();
                }
            }
        };
        timer.start();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ++time;
            }
        };
        Timer countTime = new Timer();
        countTime.schedule(timerTask, 0, 1000);
    }
    private void initGame() {

        SoundManager.playMusic(Music.GAME);
        Camera.setX(0);
        Camera.setY(0);
        gameMap = new GameMap();
        gameMap.initMap(level);
        stillObjects = gameMap.getListStillObjects();
        animationEntities = gameMap.getListAnimationEntity();

        for (int i = 0; i < animationEntities.size(); ++i) {
            Entity entity = animationEntities.get(i);
            if (entity instanceof Bomber) {
                bomberman = (MovingEntity) entity;
                break;
            }
        }

        for (int i = 0; i < animationEntities.size(); ++i) {
            Entity entity = animationEntities.get(i);
            if (entity instanceof Chase) {
                if (entity instanceof Oneal) {
                    ((Oneal) entity).setTargetEntity(bomberman);
                }

                if (entity instanceof Doll) {
                    ((Doll) entity).setTargetEntity(bomberman);
                }

                if (entity instanceof Bee) {
                    ((Bee) entity).setDistanceChase(gameMap.getCol() * gameMap.getRow());
                    BreadthFirstSearch.CalculatorBreadthFirstSearch(
                        entity.getYPixel() / Sprite.SCALED_SIZE,
                        entity.getXPixel() / Sprite.SCALED_SIZE,
                        gameMap);
                    int minDist = BombermanGame.INF;
                    for (int j = 0; j < animationEntities.size(); ++j) {
                        Entity targetEntity = animationEntities.get(j);
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

        for (int i = 0; i < animationEntities.size(); ++i) {
            Entity entity = animationEntities.get(i);
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
        for (int k = 0; k < animationEntities.size(); ++k) {
            Entity entity = animationEntities.get(k);
            if (entity instanceof MovingEntity) {
                List<Bomb> bombList = ((MovingEntity) entity).getBombList();

                for (Bomb bomb : bombList) {
                    ((MovingEntity) entity).explodedBomb(bomb, gameMap);
                }
                for (int i = 0; i < bombList.size(); ++i) {
                    Bomb bomb = bombList.get(i);
                    if (!bomb.getAnimations()) {

                        for (int j = 0; j < animationEntities.size(); ++j) {
                            Entity deedee = animationEntities.get(j);
                            if (deedee instanceof DeeDee
                                && ((DeeDee) deedee).getTargetEntity() == bomb) {
                                ((DeeDee) deedee).setTargetEntity(null);
                            }
                        }
                        bombList.remove(i);
                        --i;
                    }
                }
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
        int x1 = bomberman.getXPixel();
        int y1 = bomberman.getYPixel();
        int w1 = Sprite.SCALED_SIZE;
        int h1 = Sprite.SCALED_SIZE;

        for (int i = 0; i < animationEntities.size(); ++i) {
            Entity entity = animationEntities.get(i);
            if (entity instanceof Enemies) {
                int x2 = entity.getXPixel();
                int y2 = entity.getYPixel();
                int w2 = Sprite.SCALED_SIZE;
                int h2 = Sprite.SCALED_SIZE;

                if (Util.checkRectIntersect(x1, y1, w1, h1, x2, y2, w2, h2)) {
                    bomberman.dead();
                }
            }
        }


        for (int i = 0; i < animationEntities.size(); ++i) {
            Entity entity = animationEntities.get(i);
            if (entity instanceof AnimationEntity) {
                if (((AnimationEntity) entity).isDisappeared()) {
                    if (entity instanceof Bomber) {
                        return;
                    }
                    ((AnimationEntity) entity).die(gameMap, (Bomber) bomberman);
                    BombermanGame.score += ((AnimationEntity) entity).getScore();
                    if (BombermanGame.score > highScore) {
                        highScore = BombermanGame.score;
                        try {
                            Formatter f = new Formatter("res/data/highscore.txt");
                            f.format(String.valueOf(highScore));
                            f.close();
                        } catch (Exception e) {
                            System.out.println("Error write file highscore");
                        }
                    }
                    animationEntities.remove(i);
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
        if (bomberman != null) {
            move();
        }
        statusBar.updateStatusBar(this);
        for (int i = 0; i < stillObjects.size(); ++i) {
            Entity entity = stillObjects.get(i);
            entity.update();
        }
        for (int i = 0; i < animationEntities.size(); ++i) {
            Entity entity = animationEntities.get(i);
            entity.update();
        }
        checkBomb();
        checkDestroyBrick();
        checkKillEntity();
        fps();
    }

    public void render() {
        BombermanGame.gc.clearRect(0, 0, BombermanGame.canvas.getWidth(), BombermanGame.canvas.getHeight());
        updateCamera();
        for (int i = 0; i < stillObjects.size(); ++i) {
            Entity entity = stillObjects.get(i);
            entity.render(BombermanGame.gc);
        }
        for (int i = 0; i < animationEntities.size(); ++i) {
            Entity entity = animationEntities.get(i);
            if (entity instanceof MovingEntity) {
                List<Bomb> bombList = ((MovingEntity) entity).getBombList();
                for (int j = 0; j < bombList.size(); ++j) {
                    Entity entity1 = bombList.get(j);
                    entity1.render(BombermanGame.gc);
                }
            }
        }
        if (((Bomber) bomberman).isImmortal()) {
            BombermanGame.gc.drawImage(Sprite.shield.getFxImage(),
                    bomberman.getXPixel() - Camera.getX(), bomberman.getYPixel() - Camera.getY());
        }
        for (int i = 0; i < animationEntities.size(); ++i) {
            Entity entity = animationEntities.get(i);
            entity.render(BombermanGame.gc);
        }
    }

    public void updateCamera() {
        if (bomberman == null) {
            return;
        }
        int midX = ((Bomber) bomberman).getXPixel() + Sprite.SCALED_SIZE / 2;
        int midY = ((Bomber) bomberman).getYPixel() + Sprite.SCALED_SIZE / 2;

        boolean setX = false;
        boolean setY = false;

        if(midX - BombermanGame.WIDTH * Sprite.SCALED_SIZE / 2 < 0) {
            Camera.setX(0);
            setX = true;
        }

        if(midX + BombermanGame.WIDTH * Sprite.SCALED_SIZE / 2 > gameMap.getWidth()) {
            Camera.setX(gameMap.getWidth() - BombermanGame.WIDTH * Sprite.SCALED_SIZE);
            setX = true;
        }

        if(!setX) {
            Camera.setX(midX - BombermanGame.WIDTH * Sprite.SCALED_SIZE / 2);
        }

        if(midY - BombermanGame.HEIGHT * Sprite.SCALED_SIZE / 2 < 0) {
            Camera.setY(0);
            setY = true;
        }

        if(midY + BombermanGame.HEIGHT * Sprite.SCALED_SIZE / 2 > gameMap.getHeight()) {
            Camera.setY(gameMap.getHeight() - BombermanGame.HEIGHT * Sprite.SCALED_SIZE);
            setY = true;
        }

        if(!setY) {
            Camera.setY(midY - BombermanGame.HEIGHT * Sprite.SCALED_SIZE / 2);
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Entity> getAnimationEntities() {
        return animationEntities;
    }

    public void setAnimationEntities(List<Entity> animationEntities) {
        this.animationEntities = animationEntities;
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public void setStillObjects(List<Entity> stillObjects) {
        this.stillObjects = stillObjects;
    }

    public MovingEntity getBomberman() {
        return bomberman;
    }

    public void setBomberman(MovingEntity bomberman) {
        this.bomberman = bomberman;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Scene getScene() {
        return scene;
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    public void setStatusBar(StatusBar statusBar) {
        this.statusBar = statusBar;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
