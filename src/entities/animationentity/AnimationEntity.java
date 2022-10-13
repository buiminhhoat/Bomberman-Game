package entities.animationentity;

import entities.Entity;
import enumeration.Direction;
import javafx.scene.image.Image;

public abstract class AnimationEntity extends Entity {
    public static final int DEFAULT_NUMBER_BOMBS = 3;

    public static final int DEFAULT_LENGTH_EXPLOSION_OF_BOMB = 2;

    protected int numberBombs = DEFAULT_NUMBER_BOMBS;

    protected int lengthExplosionOfBomb = DEFAULT_LENGTH_EXPLOSION_OF_BOMB;

    protected int timeline = 0;
    protected int currentFrame = 0;
    protected int maxFrame;
    protected Boolean animations;
    protected int lives;

    protected int levelSpeed; // {16, 8, 4, 2}

    protected Direction direction;


    public AnimationEntity() {

    }

    public AnimationEntity(int x, int y, Image img) {
        super(x, y, img);
    }

    public AnimationEntity(int x, int y, Image img, int levelSpeed, int maxFrame,
        Boolean animations, int lives, Direction direction) {
        super(x, y, img);
        this.levelSpeed = levelSpeed;
        this.maxFrame = maxFrame;
        this.animations = animations;
        this.lives = lives;
        this.direction = direction;
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

    public Boolean getAnimations() {
        return animations;
    }

    public void setAnimations(Boolean animations) {
        this.animations = animations;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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
                timeToTransitionFrame = 2;
                break;
        }
        if (this.timeline % timeToTransitionFrame == 0) {
            this.currentFrame = (this.currentFrame + 1) % this.maxFrame;
        }
    }

    @Override
    public void update() {

    }

    public void die() {
        --this.lives;
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
}
