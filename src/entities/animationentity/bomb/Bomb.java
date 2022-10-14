package entities.animationentity.bomb;

import entities.Entity;
import entities.animationentity.AnimationEntity;
import entities.animationentity.hiddenitem.BombItem;
import entities.animationentity.hiddenitem.FlameItem;
import entities.animationentity.hiddenitem.HiddenItem;
import entities.animationentity.hiddenitem.SpeedItem;
import entities.animationentity.movingentity.MovingEntity;
import entities.animationentity.movingentity.bomber.Bomber;
import entities.animationentity.movingentity.enemies.Creeper;
import entities.animationentity.movingentity.enemies.Enemies;
import entities.block.Brick;
import enumeration.BombermanObject;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

public class Bomb extends AnimationEntity {

    private final int DEFAULT_TIME_BOMB = 50; // 25 = 1s

    private int timeBomb = DEFAULT_TIME_BOMB;

    private Flame flame_center;
    private List<Flame> listFlame = new ArrayList<>();
    private List<Entity> listKill = new ArrayList<>();

    private AnimationEntity dynamicEntity;

    public Bomb() {
        isBlocked = true;
    }

    public Bomb(AnimationEntity dynamicEntity, GameMap gameMap) {
        super(dynamicEntity.getXPixel() / Sprite.SCALED_SIZE,
            dynamicEntity.getYPixel() / Sprite.SCALED_SIZE,
            Sprite.bomb.getFxImage(), 8, 3, true, 3, Direction.DOWN);

        isBlocked = true;

        this.dynamicEntity = dynamicEntity;

        int x = dynamicEntity.getXPixel() / Sprite.SCALED_SIZE;
        int y = dynamicEntity.getYPixel() / Sprite.SCALED_SIZE;
        int lth = dynamicEntity.getLengthExplosionOfBomb();

        flame_center = new Flame(x, y);

        for (int i = y - 1; i >= y - lth; --i) {
            if (gameMap.checkBlockedPixelByBlock(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                break;
            }
            listFlame.add(new Flame(x, i, Direction.UP, (boolean) (i == y - lth)));
        }

        for (int i = y + 1; i <= y + lth; ++i) {
            if (gameMap.checkBlockedPixelByBlock(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                break;
            }
            listFlame.add(new Flame(x, i, Direction.DOWN, (boolean) (i == y + lth)));
        }

        for (int i = x - 1; i >= x - lth; --i) {
            if (gameMap.checkBlockedPixelByBlock(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
                break;
            }
            listFlame.add(new Flame(i, y, Direction.LEFT, (boolean) (i == x - lth)));
        }

        for (int i = x + 1; i <= x + lth; ++i) {
            if (gameMap.checkBlockedPixelByBlock(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
                break;
            }
            listFlame.add(new Flame(i, y, Direction.RIGHT, (boolean) (i == x + lth)));
        }
    }

    public int getTimeBomb() {
        return timeBomb;
    }

    public void setTimeBomb(int timeBomb) {
        this.timeBomb = timeBomb;
    }

    private void countdown() {
        this.timeBomb = Math.max(0, this.timeBomb - 1);
    }

    public void checkExplosion(GameMap gameMap, List<Entity> movingEntities) {
        this.nextTimeline();
        this.countdown();
        if (this.timeBomb == 0) {
            this.explode(gameMap, movingEntities);
        }
    }

    private void killEntity(int xPixel, int yPixel, List<Entity> movingEntities) {
        for (Entity entity : movingEntities) {
            if (listKill.contains(entity)) {
                continue;
            }

            if (dynamicEntity instanceof Creeper) {
                if (entity instanceof Enemies) {
                    continue;
                }
            }

            if (entity.getXPixel() == xPixel && entity.getYPixel() == yPixel) {
                listKill.add(entity);
                ((MovingEntity) entity).die();
            }
        }
    }

    private void explode(GameMap gameMap, List<Entity> movingEntities) {
        int x = this.getXPixel() / Sprite.SCALED_SIZE;
        int y = this.getYPixel() / Sprite.SCALED_SIZE;
        int lth = ((AnimationEntity) dynamicEntity).getLengthExplosionOfBomb();

        killEntity(x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE, movingEntities);

        for (int i = y - 1; i >= y - lth; --i) {
            if (gameMap.checkBlockedPixelByBlock(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                if (gameMap.getEntityMap()[i][x] instanceof Brick
                    && flame_center.getTimeline() == 3) {
                    destroyEntity(i, x, gameMap);
                }
                break;
            }
            killEntity(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, movingEntities);
        }

        for (int i = y + 1; i <= y + lth; ++i) {
            if (gameMap.checkBlockedPixelByBlock(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                if (gameMap.getEntityMap()[i][x] instanceof Brick
                    && flame_center.getTimeline() == 3) {
                    destroyEntity(i, x, gameMap);
                }
                break;
            }
            killEntity(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, movingEntities);
        }

        for (int i = x - 1; i >= x - lth; --i) {
            if (gameMap.checkBlockedPixelByBlock(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
                if (gameMap.getEntityMap()[y][i] instanceof Brick
                    && flame_center.getTimeline() == 3) {
                    destroyEntity(y, i, gameMap);
                }
                break;
            }
            killEntity(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE, movingEntities);
        }

        for (int i = x + 1; i <= x + lth; ++i) {
            if (gameMap.checkBlockedPixelByBlock(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
                if (gameMap.getEntityMap()[y][i] instanceof Brick
                    && flame_center.getTimeline() == 3) {
                    destroyEntity(y, i, gameMap);
                }
                break;
            }
            killEntity(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE, movingEntities);
        }

        flame_center.flaming();
        for (Flame flame : listFlame) {
            flame.flaming();
        }

        if (!flame_center.getAnimations()) {
            this.setAnimations(false);
            this.setTimeBomb(DEFAULT_TIME_BOMB);
        }
    }

    @Override
    public void update() {
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
        if (this.timeBomb == 0) {
            for (Flame flame : listFlame) {
                flame.render(gc);
            }
            flame_center.render(gc);
        } else {
            super.render(gc);
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
        gameMap.getIsBlocked()[x][y] = false;
        for (Entity brick : gameMap.getStillObjects()) {
            if (brick instanceof Brick) {
                if (brick.getXPixel() / Sprite.SCALED_SIZE == y
                    && brick.getYPixel() / Sprite.SCALED_SIZE == x) {

                    ((Brick) brick).setAnimations(true);

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
                    }
                    if (item == null) continue;
                    List<Entity> stillObject = gameMap.getStillObjects();
                    stillObject.add(item);
                    return;
                }
            }
        }
    }
}
