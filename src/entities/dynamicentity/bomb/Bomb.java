package entities.dynamicentity.bomb;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import entities.Entity;
import entities.dynamicentity.DynamicEntity;
import entities.dynamicentity.bomber.Bomber;
import enumeration.BombermanObject;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;

public class Bomb extends DynamicEntity {

    private final int DEFAULT_TIME_BOMB = 50; // 25 = 1s

    private int timeBomb = DEFAULT_TIME_BOMB;

    private Flame flame_center;
    private List<Flame> listFlame = new ArrayList<>();

    private DynamicEntity dynamicEntity;

    public Bomb() {
    }

    public Bomb(DynamicEntity dynamicEntity, GameMap gameMap) {
        super(dynamicEntity.getX() / Sprite.SCALED_SIZE,
            dynamicEntity.getY() / Sprite.SCALED_SIZE,
            Sprite.bomb.getFxImage(), 8, 3, true, 3, Direction.DOWN);
        this.dynamicEntity = dynamicEntity;

        int x = dynamicEntity.getX() / Sprite.SCALED_SIZE;
        int y = dynamicEntity.getY() / Sprite.SCALED_SIZE;
        int lth = dynamicEntity.getLengthExplosionOfBomb();

        flame_center = new Flame(x, y);

        for (int i = y - 1; i >= y - lth; --i) {
            if (gameMap.checkBlockedPixel(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                break;
            }
            listFlame.add(new Flame(x, i, Direction.UP, (boolean) (i == y - lth)));
        }

        for (int i = y + 1; i <= y + lth; ++i) {
            if (gameMap.checkBlockedPixel(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                break;
            }
            listFlame.add(new Flame(x, i, Direction.DOWN, (boolean) (i == y + lth)));
        }

        for (int i = x - 1; i >= x - lth; --i) {
            if (gameMap.checkBlockedPixel(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
                break;
            }
            listFlame.add(new Flame(i, y, Direction.LEFT, (boolean) (i == x - lth)));
        }

        for (int i = x + 1; i <= x + lth; ++i) {
            if (gameMap.checkBlockedPixel(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
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

    public void checkExplosion(GameMap gameMap) {
        this.nextTimeline();
        this.countdown();
        if (this.timeBomb == 0) {
            this.explode(gameMap);
        }
    }

    private void explode(GameMap gameMap) {
        int x = this.getX() / Sprite.SCALED_SIZE;
        int y = this.getY() / Sprite.SCALED_SIZE;
        int lth = ((DynamicEntity) dynamicEntity).getLengthExplosionOfBomb();

        for (int i = y - 1; i >= y - lth; --i) {
            if (gameMap.checkBlockedPixel(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                if (gameMap.getMapObject(i, x) == BombermanObject.BRICK
                    && flame_center.getTimeline() == 3) {
                    gameMap.destroyBrick(i, x);
                }
                break;
            }
        }

        for (int i = y + 1; i <= y + lth; ++i) {
            if (gameMap.checkBlockedPixel(x * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE)) {
                if (gameMap.getMapObject(i, x) == BombermanObject.BRICK
                    && flame_center.getTimeline() == 3) {
                    gameMap.destroyBrick(i, x);
                }
                break;
            }
        }

        for (int i = x - 1; i >= x - lth; --i) {
            if (gameMap.checkBlockedPixel(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
                if (gameMap.getMapObject(y, i) == BombermanObject.BRICK
                    && flame_center.getTimeline() == 3) {
                    gameMap.destroyBrick(y, i);
                }
                break;
            }
        }

        for (int i = x + 1; i <= x + lth; ++i) {
            if (gameMap.checkBlockedPixel(i * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE)) {
                if (gameMap.getMapObject(y, i) == BombermanObject.BRICK
                    && flame_center.getTimeline() == 3) {
                    gameMap.destroyBrick(y, i);
                }
                break;
            }
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
            gc.drawImage(img, x, y);
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
}
