package entities.animationentity.movingentity.enemies;

import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Creeper extends Enemies {

    public Creeper() {

    }

    public Creeper(int x, int y, Image img) {
        super(x, y, img);
        numberBombs = 1;
    }

    public void chooseDirection(GameMap gameMap) {
        ++timeline;
        super.chooseDirection(gameMap);
        if (numberBombs > 0 && !animations && timeline >= 25 * 5) {
            createBomb(gameMap);
            timeline = 0;
        }
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
                            this.img = Sprite.creeper_left2.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.creeper_left3.getFxImage();
                            break;
                    }
                    break;
                case RIGHT:
                    if (faceDirection != Direction.RIGHT) {
                        faceDirection = Direction.RIGHT;
                    }
                    switch (this.currentFrame) {
                        case 0:
                            this.img = Sprite.creeper_right2.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.creeper_right3.getFxImage();
                            break;
                    }
                    break;
                default:
                    switch (this.currentFrame) {
                        case 0:
                            switch (faceDirection) {
                                case LEFT:
                                    this.img = Sprite.creeper_left2.getFxImage();
                                    break;
                                case RIGHT:
                                    this.img = Sprite.creeper_right2.getFxImage();
                                    break;
                            }
                            break;
                        case 1:
                            switch (faceDirection) {
                                case LEFT:
                                    this.img = Sprite.creeper_left3.getFxImage();
                                    break;
                                case RIGHT:
                                    this.img = Sprite.creeper_right3.getFxImage();
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            switch (this.faceDirection) {
                case LEFT:
                    this.img = Sprite.creeper_left1.getFxImage();
                    break;
                case RIGHT:
                    this.img = Sprite.creeper_right1.getFxImage();
                    break;
            }
        }
    }

}
