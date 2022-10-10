package entities.dynamicentity;

import entities.dynamicentity.bomb.Bomb;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import entities.Entity;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;

public abstract class DynamicEntity extends Entity {
    public static final int DEFAULT_NUMBER_BOMBS = 3;
    public static final int DEFAULT_LENGTH_EXPLOSION_OF_BOMB = 2;

    protected int numberBombs = DEFAULT_NUMBER_BOMBS;

    protected int lengthExplosionOfBomb = DEFAULT_LENGTH_EXPLOSION_OF_BOMB;

    protected List<Bomb> bombList = new ArrayList<>();

    protected int levelSpeed; // {16, 8, 4, 2}
    protected int timeline = 0;
    protected int currentFrame = 0;
    protected int maxFrame;
    protected Boolean animations;
    protected int lives;

    protected Direction direction;

    public DynamicEntity() {
    }

    public DynamicEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public DynamicEntity(int levelSpeed, int maxFrame, Boolean animations, int lives,
        Direction direction) {
        this.levelSpeed = levelSpeed;
        this.maxFrame = maxFrame;
        this.animations = animations;
        this.lives = lives;
        this.direction = direction;
    }

    public DynamicEntity(int xUnit, int yUnit, Image img,
        int levelSpeed, int maxFrame, Boolean animations, int lives, Direction direction) {
        super(xUnit, yUnit, img);
        this.levelSpeed = levelSpeed;
        this.maxFrame = maxFrame;
        this.animations = animations;
        this.lives = lives;
        this.direction = direction;
    }

    public int getLevelSpeed() {
        return levelSpeed;
    }

    public void setLevelSpeed(int levelSpeed) {
        this.levelSpeed = levelSpeed;
    }

    public int getTimeline() {
        return timeline;
    }

    public void setTimeline(int timeline) {
        this.timeline = timeline;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getMaxFrame() {
        return maxFrame;
    }

    public void setMaxFrame(int maxFrame) {
        this.maxFrame = maxFrame;
    }

    public int getlives() {
        return lives;
    }

    public void setlives(int lives) {
        this.lives = lives;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Boolean getAnimations() {
        return animations;
    }

    public void setAnimations(Boolean animations) {
        this.animations = animations;
    }

    public int getNumberBombs() {
        return numberBombs;
    }

    public void setNumberBombs(int numberBombs) {
        this.numberBombs = numberBombs;
    }

    public int getLengthExplosionOfBomb() {
        return lengthExplosionOfBomb;
    }

    public void setLengthExplosionOfBomb(int lengthExplosionOfBomb) {
        this.lengthExplosionOfBomb = lengthExplosionOfBomb;
    }

    public List<Bomb> getBombList() {
        return bombList;
    }

    public void setBombList(List<Bomb> bombList) {
        this.bombList = bombList;
    }

    public void finishAnimations() {
        animations = false;
        timeline = 0;
        currentFrame = 0;
    }

    public void nextTimeline() {
        ++this.timeline;

        int timeToTransitionFrame = 1;
        switch (levelSpeed) {
            case 16:
                timeToTransitionFrame = 5;
                break;
            case 8:
                timeToTransitionFrame = 4;
                break;
            case 4:
                timeToTransitionFrame = 1;
                break;
        }
        if (this.timeline % timeToTransitionFrame == 0) {
            this.currentFrame = (this.currentFrame + 1) % this.maxFrame;
        }
    }

    @Override
    public void update() {

    }

    public void checkRun() {
        if (!getAnimations()) {
            return;
        }
        run(this);
    }

    public void up(GameMap gameMap) {
        if (!getAnimations()) {
            setDirection(Direction.UP);
            if (gameMap.checkBlockedPixel(this.getX(), this.getY() - Sprite.SCALED_SIZE)) {
                return;
            }
            this.setAnimations(true);
        }
    }

    public void down(GameMap gameMap) {
        if (!getAnimations()) {
            setDirection(Direction.DOWN);
            if (gameMap.checkBlockedPixel(this.getX(), this.getY() + Sprite.SCALED_SIZE)) {
                return;
            }
            setAnimations(true);
        }
    }

    public void left(GameMap gameMap) {
        if (!this.getAnimations()) {
            this.setDirection(Direction.LEFT);
            if (gameMap.checkBlockedPixel(this.getX() - Sprite.SCALED_SIZE, this.getY())) {
                return;
            }
            this.setAnimations(true);
        }
    }

    public void right(GameMap gameMap) {
        if (!this.getAnimations()) {
            this.setDirection(Direction.RIGHT);
            if (gameMap.checkBlockedPixel(this.getX() + Sprite.SCALED_SIZE, this.getY())) {
                return;
            }
            this.setAnimations(true);
        }
    }

    private void run(DynamicEntity entity) {
        if (entity.getDirection() == null) {
            return;
        }

        entity.nextTimeline();
        switch (entity.getDirection()) {
            case UP:
                entity.setY(entity.getY() - Sprite.SCALED_SIZE / entity.getLevelSpeed());
                break;
            case DOWN:
                entity.setY(entity.getY() + Sprite.SCALED_SIZE / entity.getLevelSpeed());
                break;
            case LEFT:
                entity.setX(entity.getX() - Sprite.SCALED_SIZE / entity.getLevelSpeed());
                break;
            case RIGHT:
                entity.setX(entity.getX() + Sprite.SCALED_SIZE / entity.getLevelSpeed());
                break;
        }

        if (entity.getX() % Sprite.SCALED_SIZE == 0 && entity.getY() % Sprite.SCALED_SIZE == 0) {
            entity.setAnimations(false);
        }
    }

    public void createBomb(GameMap gameMap) {
        int numBomb = this.getNumberBombs();
        if (numBomb == 0 || gameMap.checkBlockedPixel(this.getX(), this.getY())) {
            return;
        }
        setNumberBombs(numBomb - 1);
        Bomb bomb = new Bomb(this, gameMap);
        bombList.add(bomb);
        gameMap.setPosIsBlocked(this.getY() / Sprite.SCALED_SIZE,
            this.getX() / Sprite.SCALED_SIZE);
    }

    public void explodeBomb(Bomb bomb, GameMap gameMap) {
        if (!bomb.getAnimations()) {
            int numBomb = getNumberBombs();
            setNumberBombs(numBomb + 1);
            gameMap.setPosIsOpened(bomb.getY() / Sprite.SCALED_SIZE,
                bomb.getX() / Sprite.SCALED_SIZE);
        }
    }
}
