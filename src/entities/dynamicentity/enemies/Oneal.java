package entities.dynamicentity.enemies;

import java.util.Random;
import javafx.scene.image.Image;
import algorithm.BreadthFirstSearch;
import entities.dynamicentity.bomber.Bomber;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;

public class Oneal extends Enemies {

    Bomber bomber;
    private int dx[] = {-1, 0, 1, 0};
    private int dy[] = {0, 1, 0, -1};

    public Oneal() {

    }

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    public Bomber getBomber() {
        return bomber;
    }

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }

    @Override
    public void chooseDirection(GameMap gameMap) {
        if (animations) {
            return;
        }
        int Min = (int) 1e9 + 7;
        int saveDirection = -1;
        Random random = new Random();
        int type = 0;
        type = random.nextInt(2);

        if (type == 0) {
            for (int h = 0; h < 4; ++h) {
                int kx = this.getY() / Sprite.SCALED_SIZE + dy[h];
                int ky = this.getX() / Sprite.SCALED_SIZE + dx[h];
                if (Min > BreadthFirstSearch.minDistance(kx, ky)) {
                    Min = BreadthFirstSearch.minDistance(kx, ky);
                    saveDirection = h;
                }
            }
        } else {
            for (int h = 3; h >= 0; --h) {
                int kx = this.getY() / Sprite.SCALED_SIZE + dy[h];
                int ky = this.getX() / Sprite.SCALED_SIZE + dx[h];
                if (Min > BreadthFirstSearch.minDistance(kx, ky)) {
                    Min = BreadthFirstSearch.minDistance(kx, ky);
                    saveDirection = h;
                }
            }
        }

        if (saveDirection == -1) {
            super.chooseDirection(gameMap);
            return;
        }

        if (random.nextInt(20) > 16) {
            levelSpeed = 4;
        } else if (random.nextInt(20) > 5) {
            levelSpeed = 8;
        } else {
            levelSpeed = 16;
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

    @Override
    public void update() {
        Random random = new Random();
        if (this.animations) {
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
