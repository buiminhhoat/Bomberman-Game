package entities.animationentity.movingentity.enemies.chase;

import static algorithm.ShuffleArray.shuffleArray;

import algorithm.AstarAlgorithm;
import algorithm.AstarAlgorithm.Node;
import algorithm.BreadthFirstSearch;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;
import java.util.Random;
import javafx.scene.image.Image;

public class Minvo extends Chase{
    public Minvo() {

    }

    public Minvo(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (this.animations) {
            if (this.getlives() == 0) {
                switch (this.currentFrame) {
                    case 0:
                        this.img = Sprite.minvo_dead.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.mob_dead_red1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.mob_dead_red2.getFxImage();
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
                                this.img = Sprite.doll_left1.getFxImage();
                                break;
                            case 1:
                                this.img = Sprite.minvo_left3.getFxImage();
                                break;
                        }
                        break;
                    case RIGHT:
                        if (faceDirection != Direction.RIGHT) {
                            faceDirection = Direction.RIGHT;
                        }
                        switch (this.currentFrame) {
                            case 0:
                                this.img = Sprite.minvo_right2.getFxImage();
                                break;
                            case 1:
                                this.img = Sprite.minvo_right3.getFxImage();
                                break;
                        }
                        break;
                    default:
                        switch (this.currentFrame) {
                            case 0:
                                switch (faceDirection) {
                                    case LEFT:
                                        this.img = Sprite.minvo_left2.getFxImage();
                                        break;
                                    case RIGHT:
                                        this.img = Sprite.minvo_right2.getFxImage();
                                        break;
                                }
                                break;
                            case 1:
                                switch (faceDirection) {
                                    case LEFT:
                                        this.img = Sprite.minvo_left3.getFxImage();
                                        break;
                                    case RIGHT:
                                        this.img = Sprite.minvo_right3.getFxImage();
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
                    this.img = Sprite.minvo_left1.getFxImage();
                    break;
                case RIGHT:
                    this.img = Sprite.minvo_right1.getFxImage();
                    break;
            }
        }
    }

    AstarAlgorithm astarAlgorithm = new AstarAlgorithm();
    @Override
    public void chooseDirection(GameMap gameMap) {
        if (animations || getlives() == 0) {
            return;
        }

        if (targetEntity == null ||
            gameMap.checkBlocked(targetEntity.getYPixel() / Sprite.SCALED_SIZE,
                                targetEntity.getXPixel() / Sprite.SCALED_SIZE)) {
            super.chooseDirection(gameMap);
            return;
        }

        int saveDirection = -1;
        astarAlgorithm.CalculatorAstarAlgorithm(
            targetEntity.getYPixel() / Sprite.SCALED_SIZE,
            targetEntity.getXPixel() / Sprite.SCALED_SIZE,
            this.getYPixel() / Sprite.SCALED_SIZE,
            this.getXPixel() / Sprite.SCALED_SIZE,
            gameMap);


        Node nodeNext = astarAlgorithm.getNodeNext(this.getYPixel() / Sprite.SCALED_SIZE,
            this.getXPixel() / Sprite.SCALED_SIZE);

        if (nodeNext != null) {
            saveDirection = nodeNext.getH();
        }

        if (saveDirection == -1) {
            super.chooseDirection(gameMap);
            return;
        }
        switch (saveDirection) {
            case 0:
                down(gameMap);
                break;
            case 1:
                left(gameMap);
                break;
            case 2:
                up(gameMap);
                break;
            case 3:
                right(gameMap);
                break;
        }
    }
}