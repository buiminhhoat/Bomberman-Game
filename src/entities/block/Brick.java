package entities.block;

import entities.animationentity.AnimationEntity;
import entities.animationentity.hiddenitem.HiddenItem;
import enumeration.Direction;
import graphics.Sprite;
import javafx.scene.image.Image;

import java.util.Timer;
import java.util.TimerTask;

public class Brick extends AnimationEntity {
    private static final int MAX_FRAME = 3;
    private boolean isDestroy;

    private HiddenItem hiddenItem;

    public Brick() {
        this.isBlocked = true;
        this.isDestroy = false;
    }

    public Brick(int x, int y, Image img) {
        super(x, y, img, LEVEL_SPEED[DEFAULT_LEVEL_SPEED_ID], MAX_FRAME,
                false, DEFAULT_LIVES, Direction.DOWN);
        this.isBlocked = true;
        this.isDestroy = false;
    }

    public boolean checkDestroy() {
        if (this.isDestroy) {
            return true;
        }
        return false;
    }

    @Override
    public void startAnimations() {
        this.animations = true;
        int timeToTransitionFrame = 110;

        Timer time = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ++currentFrame;
                if (currentFrame == maxFrame) {
                    isDestroy = true;
                    finishAnimations();
                    time.cancel();
                }
            }
        };
        time.schedule(timerTask, 0, timeToTransitionFrame);
    }

    @Override
    public void update() {
        if (this.animations) {
            switch (this.currentFrame) {
                case 0:
                    this.img = Sprite.brick_exploded.getFxImage();
                    break;
                case 1:
                    this.img = Sprite.brick_exploded1.getFxImage();
                    break;
                case 2:
                    this.img = Sprite.brick_exploded2.getFxImage();
                    break;
            }
        }
    }
}
