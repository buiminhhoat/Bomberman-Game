package uet.oop.bomberman.entities.dynamicentity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.enumeration.Direction;

public abstract class DynamicEntity extends Entity {
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

    public DynamicEntity(int levelSpeed, int maxFrame, Boolean animations, int lives, Direction direction) {
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
}
