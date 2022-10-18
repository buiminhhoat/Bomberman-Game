package entities.animationentity.movingentity.enemies.chase;

import static algorithm.ShuffleArray.shuffleArray;

import algorithm.BreadthFirstSearch;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;
import java.util.Random;
import javafx.scene.image.Image;

public class Oneal extends Chase {

    public Oneal() {

    }

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (this.animations) {
            if (this.isDie()) {
                switch (this.currentFrame) {
                    case 0:
                        this.img = Sprite.oneal_dead.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.mob_dead_blue1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.mob_dead_blue2.getFxImage();
                        break;
                }
            } else {
                switch (this.direction) {
                    case LEFT:
                        if (faceDirection != Direction.LEFT) {
                            faceDirection = Direction.LEFT;
                        }
                        switch (this.currentFrame) {
                            case 0:
                                this.img = Sprite.oneal_left2.getFxImage();
                                break;
                            case 1:
                                this.img = Sprite.oneal_left3.getFxImage();
                                break;
                        }
                        break;
                    case RIGHT:
                        if (faceDirection != Direction.RIGHT) {
                            faceDirection = Direction.RIGHT;
                        }
                        switch (this.currentFrame) {
                            case 0:
                                this.img = Sprite.oneal_right2.getFxImage();
                                break;
                            case 1:
                                this.img = Sprite.oneal_right3.getFxImage();
                                break;
                        }
                        break;
                    default:
                        switch (this.currentFrame) {
                            case 0:
                                switch (faceDirection) {
                                    case LEFT:
                                        this.img = Sprite.oneal_left2.getFxImage();
                                        break;
                                    case RIGHT:
                                        this.img = Sprite.oneal_right2.getFxImage();
                                        break;
                                }
                                break;
                            case 1:
                                switch (faceDirection) {
                                    case LEFT:
                                        this.img = Sprite.oneal_left3.getFxImage();
                                        break;
                                    case RIGHT:
                                        this.img = Sprite.oneal_right3.getFxImage();
                                        break;
                                }
                                break;
                        }
                        break;
                }
            }
        } else {
            switch (this.faceDirection) {
                case LEFT:
                    this.img = Sprite.oneal_left1.getFxImage();
                    break;
                case RIGHT:
                    this.img = Sprite.oneal_right1.getFxImage();
                    break;
            }
        }
    }
}
