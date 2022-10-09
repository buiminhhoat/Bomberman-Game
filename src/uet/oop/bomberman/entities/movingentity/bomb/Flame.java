package uet.oop.bomberman.entities.movingentity.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingentity.MovingEntity;
import uet.oop.bomberman.enumeration.Direction;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.HEIGHT;
import static uet.oop.bomberman.BombermanGame.WIDTH;

public class Flame extends MovingEntity {

    private final int DEFAULT_TIME = 50; // 25 = 1s
    private final int DEFAULT_LENGTH = 1;

    private int length = DEFAULT_LENGTH;
    private int time = DEFAULT_TIME;

    public Flame() {
    }


    public Flame(int x, int y) {
        super(x, y, Sprite.bomb.getFxImage(), 4, 3,
                true, 3, Direction.DOWN);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void countdown() {
        --this.time;
    }

//    public void readyExplode() {
//        this.nextTimeline();
//        this.countdown();
//        if (this.timeBomb == 0) {
//            this.setAnimations(false);
//            this.setTimeBomb(DEFAULT_TIME);
//        }
//    }
//
//    @Override
//    public void update() {
//        if (this.animations) {
//            switch (this.currentFrame) {
//                case 0:
//                    this.img = Sprite.bomb.getFxImage();
//                    break;
//                case 1:
//                    this.img = Sprite.bomb_1.getFxImage();
//                    break;
//                case 3:
//                    this.img = Sprite.bomb_2.getFxImage();
//                    break;
//            }
//        }
//    }
}
