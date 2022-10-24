package entities.animationentity.movingentity.enemies.chase;

import static algorithm.ShuffleArray.shuffleArray;

import algorithm.BreadthFirstSearch;
import enumeration.Direction;
import gamemap.GameMap;
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
            if (this.isDie()) {
                switch (this.currentFrame) {
                    case 0:
                        this.img = Sprite.bee_dead.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.mob_dead_bee1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.mob_dead_bee2.getFxImage();
                        break;
                    case 3:
                        this.img = Sprite.nothing.getFxImage();
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

    @Override
    public void chooseDirection(GameMap gameMap) {
        if (animations || isDie()) {
            return;
        }

        if (targetEntity == null) {
            super.chooseDirection(gameMap);
            return;
        }

        BreadthFirstSearch.CalculatorBreadthFirstSearch(
            targetEntity.getYPixel() / Sprite.SCALED_SIZE,
            targetEntity.getXPixel() / Sprite.SCALED_SIZE,
            gameMap);

        int Min = (int) distanceChase;
        int saveDirection = DEFAULT_SAVE_DIRECTION;

        for (int i = 0; i < MAX_DIRECTION; ++i) {
            randomShuffle[i] = i;
        }
        shuffleArray(randomShuffle);

        for (int h = 0; h < MAX_DIRECTION; ++h) {
            int kx = this.getYPixel() / Sprite.SCALED_SIZE + dy[randomShuffle[h]];
            int ky = this.getXPixel() / Sprite.SCALED_SIZE + dx[randomShuffle[h]];
            if (Min > BreadthFirstSearch.minDistance(kx, ky)) {
                Min = BreadthFirstSearch.minDistance(kx, ky);
                saveDirection = randomShuffle[h];
            }
        }

        if (saveDirection == DEFAULT_SAVE_DIRECTION) {
            super.chooseDirection(gameMap);
            return;
        }

        switch (saveDirection) {
            case 0:
                left(gameMap);
                break;
            case 1:
                down(gameMap);
                break;
            case 2:
                right(gameMap);
                break;
            case 3:
                up(gameMap);
                break;
        }
    }
}
