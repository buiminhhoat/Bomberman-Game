package uet.oop.bomberman.entities.movingentity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.enumeration.Direction;

public abstract class MovingEntity extends Entity {
    protected int levelSpeed;
    protected int currentFrame;
    protected int maxFrame;
    protected Boolean animations;
    protected int lives;
    protected Direction direction;

    public MovingEntity() {
    }

    public MovingEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public MovingEntity(int levelSpeed, int currentFrame, int maxFrame, Boolean animations, int lives, Direction direction) {
        this.levelSpeed = levelSpeed;
        this.currentFrame = currentFrame;
        this.maxFrame = maxFrame;
        this.animations = animations;
        this.lives = lives;
        this.direction = direction;
    }

    public MovingEntity(int xUnit, int yUnit, Image img,
                        int levelSpeed, int currentFrame, int maxFrame, Boolean animations, int lives, Direction direction) {
        super(xUnit, yUnit, img);
        this.levelSpeed = levelSpeed;
        this.currentFrame = currentFrame;
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

    public void finishAnimations() {
        animations = false;
        currentFrame = 0;
    }

    public void nextFrame() {
        this.currentFrame = (this.currentFrame + 1) % this.maxFrame;
    }

    @Override
    public void update() {

    }
}
