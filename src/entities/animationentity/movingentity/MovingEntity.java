package entities.animationentity.movingentity;

import entities.animationentity.AnimationEntity;
import entities.animationentity.bomb.Bomb;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

public class MovingEntity extends AnimationEntity {

    protected List<Bomb> bombList = new ArrayList<>();

    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;

    public MovingEntity() {

    }

    public MovingEntity(int x, int y, Image img) {
        super(x, y, img);
    }

    public MovingEntity(int levelSpeed, int maxFrame, Boolean animations, int lives,
        Direction direction) {
        this.levelSpeed = levelSpeed;
        this.maxFrame = maxFrame;
        this.animations = animations;
        this.lives = lives;
        this.direction = direction;
    }

    public MovingEntity(int x, int y, Image img, int levelSpeed, int maxFrame,
        Boolean animations, int lives, Direction direction) {
        super(x, y, img);
        this.levelSpeed = levelSpeed;
        this.maxFrame = maxFrame;
        this.animations = animations;
        this.lives = lives;
        this.direction = direction;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public int getLevelSpeed() {
        return levelSpeed;
    }

    public void setLevelSpeed(int levelSpeed) {
        this.levelSpeed = levelSpeed;
    }

    public List<Bomb> getBombList() {
        return bombList;
    }

    public void setBombList(List<Bomb> bombList) {
        this.bombList = bombList;
    }

    public void move(GameMap gameMap) {
        if (up) {
            up(gameMap);
            return;
        }
        if (down) {
            down(gameMap);
            return;
        }
        if (left) {
            left(gameMap);
            return;
        }
        if (right) {
            right(gameMap);
            return;
        }
    }

    public void up(GameMap gameMap) {
        if (!getAnimations()) {
            setDirection(Direction.UP);
            if (gameMap.checkBlockedPixel(this.getXPixel(),
                this.getYPixel() - Sprite.SCALED_SIZE)) {
                return;
            }
            this.setAnimations(true);
        }
    }

    public void down(GameMap gameMap) {
        if (!getAnimations()) {
            setDirection(Direction.DOWN);
            if (gameMap.checkBlockedPixel(this.getXPixel(),
                this.getYPixel() + Sprite.SCALED_SIZE)) {
                return;
            }
            setAnimations(true);
        }
    }

    public void left(GameMap gameMap) {
        if (!this.getAnimations()) {
            this.setDirection(Direction.LEFT);
            if (gameMap.checkBlockedPixel(this.getXPixel() - Sprite.SCALED_SIZE,
                this.getYPixel())) {
                return;
            }
            this.setAnimations(true);
        }
    }

    public void right(GameMap gameMap) {
        if (!this.getAnimations()) {
            this.setDirection(Direction.RIGHT);
            if (gameMap.checkBlockedPixel(this.getXPixel() + Sprite.SCALED_SIZE,
                this.getYPixel())) {
                return;
            }
            this.setAnimations(true);
        }
    }

    public void checkRun() {
        if (!this.getAnimations()) {
            return;
        }
        run(this);
    }

    private void run(MovingEntity entity) {
        if (entity.getDirection() == null) {
            return;
        }

        entity.nextTimeline();

        if (this.getlives() == 0) {
            return;
        }

        switch (entity.getDirection()) {
            case UP:
                entity.setYPixel(entity.getYPixel() - Sprite.SCALED_SIZE / entity.getLevelSpeed());
                break;
            case DOWN:
                entity.setYPixel(entity.getYPixel() + Sprite.SCALED_SIZE / entity.getLevelSpeed());
                break;
            case LEFT:
                entity.setXPixel(entity.getXPixel() - Sprite.SCALED_SIZE / entity.getLevelSpeed());
                break;
            case RIGHT:
                entity.setXPixel(entity.getXPixel() + Sprite.SCALED_SIZE / entity.getLevelSpeed());
                break;
        }

        if (entity.getXPixel() % Sprite.SCALED_SIZE == 0
            && entity.getYPixel() % Sprite.SCALED_SIZE == 0) {
            entity.setAnimations(false);
        }
    }

    public void createBomb(GameMap gameMap) {
        int numBomb = this.getNumberBombs();
        if (numBomb == 0 || gameMap.checkBlockedPixel(this.getXPixel(), this.getYPixel())) {
            return;
        }
        setNumberBombs(numBomb - 1);
        Bomb bomb = new Bomb(this, gameMap);
        bombList.add(bomb);
        gameMap.setPosIsBombBlocked(this.getYPixel() / Sprite.SCALED_SIZE,
            this.getXPixel() / Sprite.SCALED_SIZE);
    }

    public void explodedBomb(Bomb bomb, GameMap gameMap) {
        if (!bomb.getAnimations()) {
            int numBomb = getNumberBombs();
            setNumberBombs(numBomb + 1);
            gameMap.setPosIsBombOpened(bomb.getYPixel() / Sprite.SCALED_SIZE,
                bomb.getXPixel() / Sprite.SCALED_SIZE);
        }
    }

    public void die() {
        if (this.lives == 0) {
            return;
        }
        --this.lives;
        if (this.lives == 0) {
            this.setAnimations(true);
        }
    }

}
