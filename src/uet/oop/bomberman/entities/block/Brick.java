package uet.oop.bomberman.entities.block;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamicentity.DynamicEntity;
import uet.oop.bomberman.enumeration.Direction;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends DynamicEntity {

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
