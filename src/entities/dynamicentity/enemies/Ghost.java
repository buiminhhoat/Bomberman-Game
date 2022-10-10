package entities.dynamicentity.enemies;

import javafx.scene.image.Image;
import enumeration.BombermanObject;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;

public class Ghost extends Enemies {

    public Ghost() {
    }

    public Ghost(int x, int y, Image img) {
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
                            this.img = Sprite.ghost_left2.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.ghost_left3.getFxImage();
                            break;
                    }
                    break;
                case RIGHT:
                    if (faceDirection != Direction.RIGHT) {
                        faceDirection = Direction.RIGHT;
                    }
                    switch (this.currentFrame) {
                        case 0:
                            this.img = Sprite.ghost_right2.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.ghost_right3.getFxImage();
                            break;
                    }
                    break;
                default:
                    switch (this.currentFrame) {
                        case 0:
                            switch (faceDirection) {
                                case LEFT:
                                    this.img = Sprite.ghost_left2.getFxImage();
                                    break;
                                case RIGHT:
                                    this.img = Sprite.ghost_right2.getFxImage();
                                    break;
                            }
                            break;
                        case 1:
                            switch (faceDirection) {
                                case LEFT:
                                    this.img = Sprite.ghost_left3.getFxImage();
                                    break;
                                case RIGHT:
                                    this.img = Sprite.ghost_right3.getFxImage();
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            switch (this.faceDirection) {
                case LEFT:
                    this.img = Sprite.ghost_left1.getFxImage();
                    break;
                case RIGHT:
                    this.img = Sprite.ghost_right1.getFxImage();
                    break;
            }
        }
    }

    public void up(GameMap gameMap) {
        if (!getAnimations()) {
            setDirection(Direction.UP);
            if (gameMap.getMapObject((this.getY() - Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE,
                this.getX() / Sprite.SCALED_SIZE) == BombermanObject.WALL) {
                return;
            }
            this.setAnimations(true);
        }
    }

    public void down(GameMap gameMap) {
        if (!getAnimations()) {
            setDirection(Direction.DOWN);
            if (gameMap.getMapObject((this.getY() + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE,
                this.getX() / Sprite.SCALED_SIZE) == BombermanObject.WALL) {
                return;
            }
            setAnimations(true);
        }
    }

    public void left(GameMap gameMap) {
        if (!this.getAnimations()) {
            this.setDirection(Direction.LEFT);
            if (gameMap.getMapObject(this.getY()/ Sprite.SCALED_SIZE,
                (this.getX() - Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE)
                == BombermanObject.WALL) {
                return;
            }
            this.setAnimations(true);
        }
    }

    public void right(GameMap gameMap) {
        if (!this.getAnimations()) {
            this.setDirection(Direction.RIGHT);
            if (gameMap.getMapObject(this.getY()/ Sprite.SCALED_SIZE,
                (this.getX() + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE)
                == BombermanObject.WALL) {
                return;
            }
            this.setAnimations(true);
        }
    }
}
