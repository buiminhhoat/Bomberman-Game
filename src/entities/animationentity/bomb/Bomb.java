package entities.animationentity.bomb;

import algorithm.Util;
import control.SoundManager;
import entities.Entity;
import entities.animationentity.AnimationEntity;
import entities.animationentity.hiddenitem.BombItem;
import entities.animationentity.hiddenitem.FlameItem;
import entities.animationentity.hiddenitem.Portal;
import entities.animationentity.hiddenitem.SpeedItem;
import entities.animationentity.movingentity.MovingEntity;
import entities.animationentity.movingentity.bomber.Bomber;
import entities.animationentity.movingentity.enemies.Creeper;
import entities.animationentity.movingentity.enemies.Enemies;
import entities.block.Brick;
import enumeration.BombermanObject;
import enumeration.Chunk;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.canvas.GraphicsContext;

public class Bomb extends AnimationEntity {
    public static final int ONE_SECOND = 1000;
    private static final int DEFAULT_TIME_BOMB = 3 * ONE_SECOND;

    private boolean exploded = false;

    private Flame flame_center;
    private List<Flame> listFlame = new ArrayList<>();
    private List<Entity> listKill = new ArrayList<>();

    private GameMap gameMap;

    private AnimationEntity dynamicEntity;

    public Bomb() {
        isBlocked = true;
        update();
    }

    public Bomb(AnimationEntity dynamicEntity, GameMap gameMap) {
        super(dynamicEntity.getXPixel() / Sprite.SCALED_SIZE,
                dynamicEntity.getYPixel() / Sprite.SCALED_SIZE,
                Sprite.bomb.getFxImage(), 4, 3, false, 3, Direction.DOWN);

        this.gameMap = gameMap;
        isBlocked = true;
        this.dynamicEntity = dynamicEntity;
        startAnimations();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                explode();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, DEFAULT_TIME_BOMB);
        update();
    }

    private void explode() {
        if (exploded) return;
        exploded = true;
        SoundManager.playChunk(Chunk.EXPLODE_BOMB);
        createFlame();
        checkKill();
        destroyBrick();
    }

    void createFlame() {
        int x = this.getXPixel() / Sprite.SCALED_SIZE;
        int y = this.getYPixel() / Sprite.SCALED_SIZE;
        int lth = dynamicEntity.getLengthExplosionOfBomb();

        flame_center = new Flame(x, y, !(dynamicEntity instanceof Bomber));

        for (int i = y - 1; i >= Math.max(y - lth, 0); --i) {
            if (gameMap.checkBlockedPixelByBlock(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                break;
            }
            listFlame.add(new Flame(x, i, Direction.UP, (boolean) (i == y - lth),
                    !(dynamicEntity instanceof Bomber)));
        }

        for (int i = y + 1; i <= y + lth; ++i) {
            if (gameMap.checkBlockedPixelByBlock(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                break;
            }
            listFlame.add(new Flame(x, i, Direction.DOWN, (boolean) (i == y + lth),
                    !(dynamicEntity instanceof Bomber)));
        }

        for (int i = x - 1; i >= Math.max(x - lth, 0); --i) {
            if (gameMap.checkBlockedPixelByBlock(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
                break;
            }
            listFlame.add(new Flame(i, y, Direction.LEFT, (boolean) (i == x - lth),
                    !(dynamicEntity instanceof Bomber)));
        }

        for (int i = x + 1; i <= x + lth; ++i) {
            if (gameMap.checkBlockedPixelByBlock(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
                break;
            }
            listFlame.add(new Flame(i, y, Direction.RIGHT, (boolean) (i == x + lth),
                    !(dynamicEntity instanceof Bomber)));
        }
    }

    void destroyBrick() {
        int x = this.getXPixel() / Sprite.SCALED_SIZE;
        int y = this.getYPixel() / Sprite.SCALED_SIZE;
        int lth = ((AnimationEntity) dynamicEntity).getLengthExplosionOfBomb();

        for (int i = y - 1; i >= Math.max(y - lth, 0); --i) {
            if (gameMap.checkBlockedPixelByBlock(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                if (gameMap.getEntityMap()[i][x] instanceof Brick) {
                    destroyEntity(i, x, gameMap);
                }
                break;
            }
        }
        for (int i = y + 1; i <= y + lth; ++i) {
            if (gameMap.checkBlockedPixelByBlock(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                if (gameMap.getEntityMap()[i][x] instanceof Brick) {
                    destroyEntity(i, x, gameMap);
                }
                break;
            }
        }
        for (int i = x - 1; i >= Math.max(x - lth, 0); --i) {
            if (gameMap.checkBlockedPixelByBlock(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
                if (gameMap.getEntityMap()[y][i] instanceof Brick) {
                    destroyEntity(y, i, gameMap);
                }
                break;
            }
        }
        for (int i = x + 1; i <= x + lth; ++i) {
            if (gameMap.checkBlockedPixelByBlock(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
                if (gameMap.getEntityMap()[y][i] instanceof Brick) {
                    destroyEntity(y, i, gameMap);
                }
                break;
            }
        }
    }

    private void killEntity(int startXPixel, int endXPixel, int startYPixel, int endYPixel,
                            List<Entity> movingEntities) {
        for (int i = 0; i < movingEntities.size(); ++i) {
            Entity entity = movingEntities.get(i);
            if (listKill.contains(entity)) {
                continue;
            }

            if (dynamicEntity instanceof Creeper) {
                if (entity instanceof Enemies) {
                    continue;
                }
            }

            int x1 = entity.getXPixel();
            int y1 = entity.getYPixel();
            int w1 = Sprite.SCALED_SIZE;
            int h1 = Sprite.SCALED_SIZE;

            int x2 = startXPixel;
            int y2 = startYPixel;
            int w2 = endXPixel - startXPixel + 1;
            int h2 = endYPixel - startYPixel + 1;

            if (Util.checkRectIntersect(x1, y1, w1, h1, x2, y2, w2, h2)) {
                listKill.add(entity);
                ((MovingEntity) entity).dead();
            }
        }
    }

    public void checkKill() {
        int x = this.getXPixel() / Sprite.SCALED_SIZE;
        int y = this.getYPixel() / Sprite.SCALED_SIZE;
        int lth = ((AnimationEntity) dynamicEntity).getLengthExplosionOfBomb();
        int id = 0;
        List<Entity> list = gameMap.getAnimationEntities();

        for (int i = y; i >= Math.max(y - lth, 0); --i) {
            if (gameMap.checkBlockedPixelByBlock(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                break;
            }
            id = i;
        }
        killEntity(x * Sprite.SCALED_SIZE, (x + 1) * Sprite.SCALED_SIZE - 1,
                id * Sprite.SCALED_SIZE, (y + 1) * Sprite.SCALED_SIZE - 1, list);

        for (int i = y; i <= y + lth; ++i) {
            if (gameMap.checkBlockedPixelByBlock(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                break;
            }
            id = i;
        }
        killEntity(x * Sprite.SCALED_SIZE, (x + 1) * Sprite.SCALED_SIZE - 1,
                y * Sprite.SCALED_SIZE, (id + 1) * Sprite.SCALED_SIZE - 1, list);

        for (int i = x; i >= Math.max(x - lth, 0); --i) {
            if (gameMap.checkBlockedPixelByBlock(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
                break;
            }
            id = i;
        }
        killEntity(id * Sprite.SCALED_SIZE, (x + 1) * Sprite.SCALED_SIZE - 1,
                y * Sprite.SCALED_SIZE, (y + 1) * Sprite.SCALED_SIZE - 1, list);

        for (int i = x + 1; i <= x + lth; ++i) {
            if (gameMap.checkBlockedPixelByBlock(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
                break;
            }
            id = i;
        }
        killEntity(x * Sprite.SCALED_SIZE, (id + 1) * Sprite.SCALED_SIZE - 1,
                y * Sprite.SCALED_SIZE, (y + 1) * Sprite.SCALED_SIZE - 1, list);
    }

    @Override
    public void update() {
        if (exploded) {
            if (flame_center != null) {
                flame_center.updateFlame();
            }
            for (int i = 0; i < listFlame.size(); ++i) {
                listFlame.get(i).updateFlame();
            }
            if (flame_center != null && !flame_center.getAnimations()) {
                finishAnimations();
            }
        }

        if (this.animations) {
            if (dynamicEntity instanceof Bomber) {
                switch (this.currentFrame) {
                    case 0:
                        this.img = Sprite.bomb.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.bomb_1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.bomb_2.getFxImage();
                        break;
                }
            } else {
                switch (this.currentFrame) {
                    case 0:
                        this.img = Sprite.bomb_enemy.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.bomb_enemy_1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.bomb_enemy_2.getFxImage();
                        break;
                }
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (exploded) {
            for (int i = 0; i < listFlame.size(); ++i) {
                Flame flame = listFlame.get(i);
                flame.render(gc);
            }
            if (flame_center != null) {
                flame_center.render(gc);
            }
        } else {
            if (this != null) {
                super.render(gc);
            }
        }
    }

    public int getDEFAULT_TIME_BOMB() {
        return DEFAULT_TIME_BOMB;
    }

    public Flame getFlame_center() {
        return flame_center;
    }

    public void setFlame_center(Flame flame_center) {
        this.flame_center = flame_center;
    }

    public List<Flame> getListFlame() {
        return listFlame;
    }

    public void setListFlame(List<Flame> listFlame) {
        this.listFlame = listFlame;
    }

    public void destroyEntity(int x, int y, GameMap gameMap) {
        List<Entity> addItemList = new ArrayList<>();
        gameMap.getIsBlocked()[x][y] = false;
        for (Entity brick : gameMap.getStillObjects()) {
            if (brick instanceof Brick) {
                if (brick.getXPixel() / Sprite.SCALED_SIZE == y
                        && brick.getYPixel() / Sprite.SCALED_SIZE == x) {

                    ((Brick) brick).startAnimations();

                    BombermanObject bombermanObject = gameMap.getMapObject(x, y);

                    Entity item = null;
                    switch (bombermanObject) {
                        case SPEED_ITEM:
                            item = new SpeedItem(y, x, Sprite.powerup_speed.getFxImage());
                            break;
                        case BOMB_ITEM:
                            item = new BombItem(y, x, Sprite.powerup_bombs.getFxImage());
                            break;
                        case FLAME_ITEM:
                            item = new FlameItem(y, x, Sprite.powerup_flames.getFxImage());
                            break;
                        case PORTAL:
                            item = new Portal(y, x, Sprite.portal.getFxImage());
                            break;
                    }
                    if (item == null) continue;
                    addItemList.add(item);
                    break;
                }
            }
        }
        for (Entity entity : addItemList) {
            gameMap.getStillObjects().add(entity);
        }
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public List<Entity> getListKill() {
        return listKill;
    }

    public void setListKill(List<Entity> listKill) {
        this.listKill = listKill;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public AnimationEntity getDynamicEntity() {
        return dynamicEntity;
    }

    public void setDynamicEntity(AnimationEntity dynamicEntity) {
        this.dynamicEntity = dynamicEntity;
    }
}