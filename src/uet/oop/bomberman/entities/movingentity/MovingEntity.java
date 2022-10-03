package uet.oop.bomberman.entities.movingentity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class MovingEntity extends Entity {
    protected int speed;
    protected int currentFrame;
    protected int maxFrame;
    protected int life;
    protected int direction;

    public MovingEntity() {

    }

    public MovingEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public MovingEntity(int speed, int currentFrame, int maxFrame, int life, int direction) {
        this.speed = speed;
        this.currentFrame = currentFrame;
        this.maxFrame = maxFrame;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void update() {

    }
}
