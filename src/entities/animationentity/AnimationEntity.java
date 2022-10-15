package entities.animationentity;

import entities.Entity;
import entities.animationentity.movingentity.bomber.Bomber;
import entities.animationentity.movingentity.enemies.chase.Oneal;
import enumeration.Direction;
import gamemap.GameMap;
import javafx.scene.image.Image;

import java.util.Timer;
import java.util.TimerTask;

public abstract class AnimationEntity extends Entity {
    public static final int DEFAULT_NUMBER_BOMBS = 10;

    public static final int DEFAULT_LENGTH_EXPLOSION_OF_BOMB = 2;

    protected int numberBombs = DEFAULT_NUMBER_BOMBS;

    protected int lengthExplosionOfBomb = DEFAULT_LENGTH_EXPLOSION_OF_BOMB;

    protected int currentFrame = 0;
    protected int maxFrame;
    protected Boolean animations;

    protected boolean disappeared = false;
    protected int lives = 1;
    protected int levelSpeed; // {16, 8, 4, 2}

    protected Direction direction;

    private final int[] frame = {0, 0, 0, 1, 2};
    private int idFrame = 0;

    private Timer liveTimer = new Timer();
    private Timer deathTimer = new Timer();

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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void startAnimations() {
        this.animations = true;
        if (this.getlives() != 0) {
            int timeToTransitionFrame = 500;
            switch (levelSpeed) {
                case 16:
                    timeToTransitionFrame = 1000;
                    break;
                case 8:
                    timeToTransitionFrame = 500;
                    break;
                case 4:
                    timeToTransitionFrame = 100;
                    break;
            }
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    currentFrame = (currentFrame + 1) % maxFrame;
                }
            };
            liveTimer.schedule(timerTask, 0, timeToTransitionFrame);
        } else {
            int timeToTransitionFrame = 200;

            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    ++idFrame;
                    if (idFrame == frame.length) {
                        finishAnimations();
                        disappeared = true;
                        deathTimer.cancel();
                        return;
                    }
                    currentFrame = frame[idFrame];
                }
            };
            deathTimer.schedule(timerTask, 0, timeToTransitionFrame);
        }
    }

    public void cancelLiveTimer() {
          liveTimer.cancel();
    }

    public void finishAnimations() {
        this.animations = false;
        idFrame = 0;
        currentFrame = 0;
    }

    @Override
    public void update() {

    }

    public boolean isDisappeared() {
        return disappeared;
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

    public void die(GameMap gameMap, Bomber bomberman) {

    }
}
