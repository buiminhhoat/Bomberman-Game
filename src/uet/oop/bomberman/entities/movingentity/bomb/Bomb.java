package uet.oop.bomberman.entities.movingentity.bomb;

import uet.oop.bomberman.entities.movingentity.MovingEntity;
import uet.oop.bomberman.enumeration.Direction;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends MovingEntity {

    private final int DEFAULT_TIMEBOMB = 50; // 25 = 1s

    private int timeBomb = DEFAULT_TIMEBOMB;

    public Bomb() {
    }


    public Bomb(int x, int y) {
        super(x, y, Sprite.bomb.getFxImage(), 4, 3,
                true, 3, Direction.DOWN);
    }

    public int getTimeBomb() {
        return timeBomb;
    }

    public void setTimeBomb(int timeBomb) {
        this.timeBomb = timeBomb;
    }

    public void countdown() {
        this.timeBomb--;
    }

    public void readyExplode() {
        this.nextTimeline();
        this.countdown();
        if (this.timeBomb == 0) {
            this.setAnimations(false);
            this.setTimeBomb(DEFAULT_TIMEBOMB);
        }
    }

    @Override
    public void update() {
        if (this.animations) {
            switch (this.currentFrame) {
                case 0:
                    this.img = Sprite.bomb.getFxImage();
                    break;
                case 1:
                    this.img = Sprite.bomb_1.getFxImage();
                    break;
                case 3:
                    this.img = Sprite.bomb_2.getFxImage();
                    break;
            }
        }
    }
}
