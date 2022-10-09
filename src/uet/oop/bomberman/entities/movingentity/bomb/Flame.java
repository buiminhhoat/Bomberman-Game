package uet.oop.bomberman.entities.movingentity.bomb;

import uet.oop.bomberman.entities.movingentity.MovingEntity;
import uet.oop.bomberman.enumeration.Axis;
import uet.oop.bomberman.enumeration.Direction;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends MovingEntity {

    private final int DEFAULT_TIME_FLAME = 40; // 25 = 1s
    private int lengthFlame;
    private int timeFlame = DEFAULT_TIME_FLAME;

    private boolean lastFlame = false;
    private Axis axisFlame;

    public Flame() {
    }

    public Flame(int x, int y, int lengthFlame) {
        super(x, y, Sprite.bomb_exploded.getFxImage(), 16, 2,
                true, 3, Direction.DOWN);
        this.lengthFlame = lengthFlame;
        axisFlame = Axis.CENTER;
    }

    public Flame(int x, int y, int lengthFlame, Direction direction, boolean lastFlame) {
        super(x, y, Sprite.explosion_horizontal.getFxImage(), 16, 2,
                true, 3, direction);
        this.lengthFlame = lengthFlame;
        this.lastFlame = lastFlame;

        if (direction == Direction.UP || direction == Direction.DOWN) {
            axisFlame = Axis.VERTICAL;
        }
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            axisFlame = Axis.HORIZONTAL;
        }
    }

    public int getLengthFlame() {
        return lengthFlame;
    }

    public void setLengthFlame(int lengthFlame) {
        this.lengthFlame = lengthFlame;
    }

    public int getTimeFlame() {
        return timeFlame;
    }

    public void setTimeFlame(int timeFlame) {
        this.timeFlame = timeFlame;
    }

    private void countdown() {
        this.timeFlame = Math.max(0, this.timeFlame - 1);
    }

    public void flaming() {
        this.nextTimeline();
        this.countdown();
        this.update();
    }

    @Override
    public void update() {
        switch (axisFlame) {
            case CENTER:
                switch (this.currentFrame) {
                    case 0:
                        this.img = Sprite.bomb_exploded1.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.bomb_exploded2.getFxImage();
                        break;
                }
                break;
            case VERTICAL:
                switch (this.currentFrame) {
                    case 0:
                        this.img = Sprite.explosion_vertical1.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.explosion_vertical2.getFxImage();
                        break;
                }
                break;
            case HORIZONTAL:
                switch (this.currentFrame) {
                    case 0:
                        this.img = Sprite.explosion_horizontal1.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.explosion_horizontal2.getFxImage();
                        break;
                }
                break;
        }
    }
}
