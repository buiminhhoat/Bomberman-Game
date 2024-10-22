package entities.animationentity.movingentity.enemies;

import entities.animationentity.bomb.Bomb;
import entities.animationentity.movingentity.bomber.Bomber;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;
import java.util.List;
import javafx.scene.image.Image;

public class Creeper extends Enemies {

    private static final int TIME_CREATE_BOMB = 5 * ONE_SECOND;
    private static final int CREEPER_NUMBER_BOMB = 1;
    GameMap gameMap;
    private long lastTimeCreateBomb;

    public Creeper() {
        lastTimeCreateBomb = System.currentTimeMillis();
    }

    public Creeper(int x, int y, Image img, GameMap gameMap) {
        super(x, y, img);
        numberBombs = CREEPER_NUMBER_BOMB;
        lastTimeCreateBomb = System.currentTimeMillis();
        this.gameMap = gameMap;
    }

    @Override
    public void update() {
        if (this.animations) {
            if (this.isDie()) {
                switch (this.currentFrame) {
                    case 0:
                        this.img = Sprite.creeper_dead.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.mob_dead_creeper1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.mob_dead_creeper2.getFxImage();
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
            }
        } else {
            long curTime = System.currentTimeMillis();
            if (curTime - lastTimeCreateBomb >= TIME_CREATE_BOMB) {
                createBomb(gameMap);
                lastTimeCreateBomb = curTime;
            }
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

    @Override
    public void die(GameMap gameMap, Bomber bomberman) {
        List<Bomb> bombList = ((Creeper) this).getBombList();
        for (int j = 0; j < bombList.size(); ++j) {
            gameMap.setPosIsBombOpened(
                bombList.get(j).getYPixel() / Sprite.SCALED_SIZE,
                bombList.get(j).getXPixel() / Sprite.SCALED_SIZE);
        }
    }
}
