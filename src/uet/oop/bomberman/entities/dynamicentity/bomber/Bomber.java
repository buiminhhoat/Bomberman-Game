package uet.oop.bomberman.entities.dynamicentity.bomber;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.dynamicentity.DynamicEntity;
import uet.oop.bomberman.enumeration.Direction;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends DynamicEntity {
    public static final int DEFAULT_NUMBER_BOMBS = 3;
    public static final int DEFAULT_LENGTH_EXPLOSION_OF_BOMB = 2;

    private int numberBombs = DEFAULT_NUMBER_BOMBS;
    private int lengthExplosionOfBomb = DEFAULT_LENGTH_EXPLOSION_OF_BOMB;

    public Bomber() {
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img, 4, 2,
                false, 3, Direction.DOWN);
    }

    public int getNumberBombs() {
        return numberBombs;
    }

    public void setNumberBombs(int numberBombs) {
        this.numberBombs = numberBombs;
    }

    public int getLengthExplosionOfBomb() {
        return lengthExplosionOfBomb;
    }

    public void setLengthExplosionOfBomb(int lengthExplosionOfBomb) {
        this.lengthExplosionOfBomb = lengthExplosionOfBomb;
    }

    @Override
    public void update() {
        if (this.animations) {
            switch (this.direction) {
                case UP:
                    switch (this.currentFrame) {
                        case 0:
                            this.img = Sprite.player_up_1.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.player_up_2.getFxImage();
                            break;
                    }
                    break;
                case DOWN:
                    switch (this.currentFrame) {
                        case 0:
                            this.img = Sprite.player_down_1.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.player_down_2.getFxImage();
                            break;
                    }
                    break;
                case LEFT:
                    switch (this.currentFrame) {
                        case 0:
                            this.img = Sprite.player_left_1.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.player_left_2.getFxImage();
                            break;
                    }
                    break;
                case RIGHT:
                    switch (this.currentFrame) {
                        case 0:
                            this.img = Sprite.player_right_1.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.player_right_2.getFxImage();
                            break;
                    }
                    break;
            }
        } else {
            switch (this.direction) {
                case UP:
                    this.img = Sprite.player_up.getFxImage();
                    break;
                case DOWN:
                    this.img = Sprite.player_down.getFxImage();
                    break;
                case LEFT:
                    this.img = Sprite.player_left.getFxImage();
                    break;
                case RIGHT:
                    this.img = Sprite.player_right.getFxImage();
                    break;
            }
        }
    }
}
