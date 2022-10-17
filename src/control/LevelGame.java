package control;

import algorithm.BreadthFirstSearch;
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
import gamemap.GameMap;
import graphics.Camera;
import graphics.Sprite;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;

public class LevelGame {
    private int level;
    private int score;
    private int time;

    private boolean win = false;

    public LevelGame() {
        time = 0;
    }

    public LevelGame(int level) {
        time = 0;
        this.level = level;
    }

    private List<Entity> movingEntities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private MovingEntity bomberman;
    private GameMap gameMap;

    public void start() {
        BombermanGame.scene.setOnKeyPressed(event -> {
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
                        ((Bomber) bomberman).createBomb(gameMap);
                        break;
                    case P:
                        System.out.println("P");
                        break;
                }
            }
        });

        BombermanGame.scene.setOnKeyReleased(event -> {
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
                    level++;
                    ((Bomber) bomberman).setWin(false);
                    initGame();
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
        SoundManager soundManager = new SoundManager();
        soundManager.playMusic();
        Camera.setX(0);
        Camera.setY(0);
        gameMap = new GameMap();
        gameMap.initMap(level);
        stillObjects = gameMap.getListStillObjects();
        movingEntities = gameMap.getListMovingEntity();
        for (Entity entity : movingEntities) {
            if (entity instanceof Bomber) {
                bomberman = (MovingEntity) entity;
                break;
            }
        }

        for (Entity entity : movingEntities) {
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
        for (Entity entity : movingEntities) {
            if (entity instanceof MovingEntity) {
                List<Bomb> bombList = ((MovingEntity) entity).getBombList();

                for (Bomb bomb : bombList) {
                    ((MovingEntity) entity).explodedBomb(bomb, gameMap);
                }
                for (int i = 0; i < bombList.size(); ++i) {
                    Bomb bomb = bombList.get(i);
                    if (!bomb.getAnimations()) {
                        for (Entity deedee: movingEntities) {
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
        for (int i = 0; i < movingEntities.size(); ++i) {
            Entity entity = movingEntities.get(i);
            if (entity instanceof AnimationEntity) {
                if (((AnimationEntity) entity).isDisappeared()) {
                    if (entity instanceof Bomber) {
                        bomberman = null;
                    }
                    ((AnimationEntity) entity).die(gameMap, (Bomber) bomberman);
                    score += ((AnimationEntity) entity).getScore();
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
        if (bomberman != null) {
            move();
        }
        BombermanGame.statusBar.updateStatusBar(this);
        stillObjects.forEach(Entity::update);
        movingEntities.forEach(Entity::update);
        checkBomb();
        checkDestroyBrick();
        checkKillEntity();
        fps();
    }

    public void render() {
        BombermanGame.gc.clearRect(0, 0, BombermanGame.canvas.getWidth(), BombermanGame.canvas.getHeight());
        updateCamera();
        stillObjects.forEach(g -> g.render(BombermanGame.gc));
        for (Entity entity : movingEntities) {
            if (entity instanceof MovingEntity) {
                List<Bomb> bombList = ((MovingEntity) entity).getBombList();
                bombList.forEach(g -> g.render(BombermanGame.gc));
            }
        }
        movingEntities.forEach(g -> g.render(BombermanGame.gc));
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

    public List<Entity> getMovingEntities() {
        return movingEntities;
    }

    public void setMovingEntities(List<Entity> movingEntities) {
        this.movingEntities = movingEntities;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
}
