package entities.block;

import entities.animationentity.AnimationEntity;
import enumeration.Direction;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Brick extends AnimationEntity {

    private boolean isDestroy = false;

    public Brick() {
    }

    public Brick(int x, int y, Image img) {
        super(x, y, img, 4, 3,
            false, 3, Direction.DOWN);
    }

    public boolean checkDestroy() {
        if (this.animations) {
            this.nextTimeline();
        }
        if (this.isDestroy) {
            return true;
        }
        return false;
    }

    @Override
    public void nextTimeline() {
        ++this.timeline;
        int timeToTransitionFrame = 2;
        if (this.timeline % timeToTransitionFrame == 0) {
            ++this.currentFrame;
            if (this.currentFrame == this.maxFrame) {
                this.isDestroy = true;
                this.animations = false;
                return;
            }
        }
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
