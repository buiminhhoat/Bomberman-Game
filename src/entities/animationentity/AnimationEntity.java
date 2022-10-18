package entities.animationentity;

import entities.Entity;
import entities.animationentity.bomb.Bomb;
import entities.animationentity.movingentity.bomber.Bomber;
import entities.animationentity.movingentity.enemies.chase.DeeDee;
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

    protected boolean isDie = false;
    protected int lives = 1;
    protected int levelSpeed = 8; // {16, 8, 4, 2}

    protected Direction direction;

    protected int score = 100;

    private final int[] frame = {0, 0, 0, 1, 2};
    private int idFrame = 0;

    private boolean liveTimerIsRunning = false;
    private boolean deathTimerIsRunning = false;
    private Timer liveTimer = new Timer();
    private Timer deathTimer = new Timer();

    public AnimationEntity() {
        initTimer();
    }

    public AnimationEntity(int x, int y, Image img) {
        super(x, y, img);
        initTimer();
    }

    public AnimationEntity(int x, int y, Image img, int levelSpeed, int maxFrame,
        Boolean animations, int lives, Direction direction) {
        super(x, y, img);
        this.levelSpeed = levelSpeed;
        this.maxFrame = maxFrame;
        this.animations = animations;
        this.lives = lives;
        this.direction = direction;
        initTimer();
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

    public void initTimer() {
        Entity entity = this;

        int timeToTransitionFrame = 500;
        switch (this.levelSpeed) {
            case 16:
                timeToTransitionFrame = 30;
                break;
            case 8:
                timeToTransitionFrame = 20;
                break;
            case 4:
                timeToTransitionFrame = 10;
                break;
        }

        if (entity instanceof Bomb) {
            timeToTransitionFrame = 150;
        }

        TimerTask timerLiveTask = new TimerTask() {
            @Override
            public void run() {
                if (liveTimerIsRunning) {
                    if (!animations) {
                        cancelLiveTimer();
                    }
                    else {
                        currentFrame = (currentFrame + 1) % maxFrame;
                    }
                }
            }
        };
        liveTimer.schedule(timerLiveTask, 0, timeToTransitionFrame);


        timeToTransitionFrame = 150;

        TimerTask timerDeathTask = new TimerTask() {
            @Override
            public void run() {
                if (deathTimerIsRunning) {
                    ++idFrame;
                    if (idFrame == frame.length) {
                        if (((AnimationEntity) entity).getLives() == 0) {
                            disappeared = true;
                        }
                        if (entity instanceof Bomber) {
                            ((Bomber) entity).setDie(false);
                            entity.setXPixel(Entity.START_X_PIXEL);
                            entity.setYPixel(Entity.START_Y_PIXEL);
                        }
                        cancelDeathTimer();
                    } else {
                        currentFrame = frame[idFrame];
                    }
                }
            }
        };

        deathTimer.schedule(timerDeathTask, 0, timeToTransitionFrame);
    }

    public void startAnimations() {
        this.animations = true;
        if (!this.isDie()) {
            liveTimerIsRunning = true;
        } else {
            deathTimerIsRunning = true;
        }
    }

    public void cancelLiveTimer() {
        finishAnimations();
        liveTimerIsRunning = false;
    }

    public void cancelDeathTimer() {
        finishAnimations();
        deathTimerIsRunning = false;
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

    public void setAnimations(Boolean animations) {
        this.animations = animations;
    }

    public void setDisappeared(boolean disappeared) {
        this.disappeared = disappeared;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLevelSpeed() {
        return levelSpeed;
    }

    public void setLevelSpeed(int levelSpeed) {
        this.levelSpeed = levelSpeed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int[] getFrame() {
        return frame;
    }

    public int getIdFrame() {
        return idFrame;
    }

    public void setIdFrame(int idFrame) {
        this.idFrame = idFrame;
    }

    public Timer getLiveTimer() {
        return liveTimer;
    }

    public void setLiveTimer(Timer liveTimer) {
        this.liveTimer = liveTimer;
    }

    public Timer getDeathTimer() {
        return deathTimer;
    }

    public void setDeathTimer(Timer deathTimer) {
        this.deathTimer = deathTimer;
    }

    public boolean isDie() {
        return isDie;
    }

    public void setDie(boolean die) {
        this.isDie = die;
    }
}
