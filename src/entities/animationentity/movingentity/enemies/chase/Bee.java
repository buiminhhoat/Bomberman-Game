package entities.animationentity.movingentity.enemies.chase;

import enumeration.Direction;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Bee extends Chase {

    public Bee() {

    }

    public Bee(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (this.animations) {
            switch (this.direction) {
                case LEFT:
                    if (faceDirection != Direction.LEFT) {
                        faceDirection = Direction.LEFT;
                    }
                    switch (this.currentFrame) {
                        case 0:
                            this.img = Sprite.bee_left2.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.bee_left3.getFxImage();
                            break;
                    }
                    break;
                case RIGHT:
                    if (faceDirection != Direction.RIGHT) {
                        faceDirection = Direction.RIGHT;
                    }
                    switch (this.currentFrame) {
                        case 0:
                            this.img = Sprite.bee_right2.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.bee_right3.getFxImage();
                            break;
                    }
                    break;
                default:
                    switch (this.currentFrame) {
                        case 0:
                            switch (faceDirection) {
                                case LEFT:
                                    this.img = Sprite.bee_left2.getFxImage();
                                    break;
                                case RIGHT:
                                    this.img = Sprite.bee_right2.getFxImage();
                                    break;
                            }
                            break;
                        case 1:
                            switch (faceDirection) {
                                case LEFT:
                                    this.img = Sprite.bee_left3.getFxImage();
                                    break;
                                case RIGHT:
                                    this.img = Sprite.bee_right3.getFxImage();
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            switch (this.faceDirection) {
                case LEFT:
                    this.img = Sprite.bee_left1.getFxImage();
                    break;
                case RIGHT:
                    this.img = Sprite.bee_right1.getFxImage();
                    break;
            }
        }
    }
}
