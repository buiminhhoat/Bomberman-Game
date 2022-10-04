package uet.oop.bomberman.entities.movingentity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.enumeration.Direction;

public abstract class MovingEntity extends Entity {
    protected int speed;
    protected int currentFrame;
    protected int maxFrame;
    protected Boolean animations;
    protected int life;
    protected Direction direction;

    public MovingEntity() {
    }

    public MovingEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public MovingEntity(int speed, int currentFrame, int maxFrame, Boolean animations, int life, Direction direction) {
        this.speed = speed;
        this.currentFrame = currentFrame;
        this.maxFrame = maxFrame;
        this.animations = animations;
        this.life = life;
        this.direction = direction;
    }

    public MovingEntity(int xUnit, int yUnit, Image img,
                        int speed, int currentFrame, int maxFrame, Boolean animations, int life, Direction direction) {
        super(xUnit, yUnit, img);
        this.speed = speed;
        this.currentFrame = currentFrame;
        this.maxFrame = maxFrame;
        this.animations = animations;
        this.life = life;
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
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
