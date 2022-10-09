package uet.oop.bomberman.entities.movingentity.enemies;

import java.util.Random;
import javafx.scene.image.Image;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.movingentity.MovingEntity;
import uet.oop.bomberman.enumeration.Direction;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemies {
    public Balloon() {
    }

    public Balloon(int x, int y, Image img) {
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
                            this.img = Sprite.balloom_left2.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.balloom_left3.getFxImage();
                            break;
                    }
                    break;
                case RIGHT:
                    if (faceDirection != Direction.RIGHT) {
                        faceDirection = Direction.RIGHT;
                    }
                    switch (this.currentFrame) {
                        case 0:
                            this.img = Sprite.balloom_right2.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.balloom_right3.getFxImage();
                            break;
                    }
                    break;
                default:
                    switch (this.currentFrame) {
                        case 0:
                            switch (faceDirection) {
                                case LEFT:
                                    this.img = Sprite.balloom_left2.getFxImage();
                                    break;
                                case RIGHT:
                                    this.img = Sprite.balloom_right2.getFxImage();
                                    break;
                            }
                            break;
                        case 1:
                            switch (faceDirection) {
                                case LEFT:
                                    this.img = Sprite.balloom_left3.getFxImage();
                                    break;
                                case RIGHT:
                                    this.img = Sprite.balloom_right3.getFxImage();
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            switch (this.faceDirection) {
                case LEFT:
                    this.img = Sprite.balloom_left1.getFxImage();
                    break;
                case RIGHT:
                    this.img = Sprite.balloom_right1.getFxImage();
                    break;
            }
        }
    }
}
