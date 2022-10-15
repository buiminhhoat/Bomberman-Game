package entities.animationentity;

import entities.Entity;
import entities.animationentity.movingentity.bomber.Bomber;
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

    protected int lives = 1;
    protected int levelSpeed; // {16, 8, 4, 2}

    protected Direction direction;

    private final int[] frame = {0, 0, 0, 1, 2};
    private int idFrame = 0;


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

            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    currentFrame = (currentFrame + 1) % maxFrame;
                    if (!animations) {
                        timer.cancel();
                    }
                }
            };

            timer.schedule(timerTask, 0, timeToTransitionFrame);

        } else {
            int timeToTransitionFrame = 500;

            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    ++idFrame;
                    if (idFrame == frame.length) {
                        finishAnimations();
                        timer.cancel();
                        return;
                    }
                    currentFrame = frame[idFrame];
                }
            };
            timer.schedule(timerTask, 0, timeToTransitionFrame);
        }
    }

    public void finishAnimations() {
        this.animations = false;
        idFrame = 0;
        currentFrame = 0;
    }



//    public void nextTimeline() {
//        int timeToTransitionFrame;
//
//        ++this.timeline;
//        if (this.getlives() != 0) {
//            timeToTransitionFrame = 1;
//            switch (levelSpeed) {
//                case 16:
//                    timeToTransitionFrame = 5;
//                    break;
//                case 8:
//                    timeToTransitionFrame = 4;
//                    break;
//                case 4:
//                    timeToTransitionFrame = 2;
//                    break;
//            }
//            if (this.timeline % timeToTransitionFrame == 0) {
//                this.currentFrame = (this.currentFrame + 1) % this.maxFrame;
//            }
//        } else {
//            timeToTransitionFrame = 3;
//            if (this.timeline % timeToTransitionFrame == 0) {
//                ++this.idFrame;
//                if (this.idFrame == this.frame.length) {
//                    this.animations = false;
//                    return;
//                }
//                this.currentFrame = this.frame[this.idFrame];
//            }
//        }
//    }

    @Override
    public void update() {

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
