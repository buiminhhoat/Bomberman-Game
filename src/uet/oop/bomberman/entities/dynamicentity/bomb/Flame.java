package uet.oop.bomberman.entities.dynamicentity.bomb;

import uet.oop.bomberman.entities.dynamicentity.DynamicEntity;
import uet.oop.bomberman.enumeration.Axis;
import uet.oop.bomberman.enumeration.Direction;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends DynamicEntity {

    private boolean lastFlame = false;
    private int idFrame = 0;
    private Axis axisFlame;
    private int[] frame = {0, 1, 2, 2, 1, 0};

    public Flame() {
    }

    public Flame(int x, int y) {
        super(x, y, Sprite.bomb_exploded.getFxImage(), 4, 3,
            true, 3, Direction.DOWN);
        axisFlame = Axis.CENTER;
    }

    public Flame(int x, int y, Direction direction, boolean lastFlame) {
        super(x, y, Sprite.explosion_horizontal.getFxImage(), 4, 3,
            true, 3, direction);
        this.lastFlame = lastFlame;

        if (direction == Direction.UP || direction == Direction.DOWN) {
            axisFlame = Axis.VERTICAL;
        }
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            axisFlame = Axis.HORIZONTAL;
        }
    }

    public void flaming() {
        this.nextTimeline();
        this.update();
    }

    @Override
    public void nextTimeline() {
        ++this.timeline;
        int timeToTransitionFrame = 2;
        if (this.timeline % timeToTransitionFrame == 0) {
            ++this.idFrame;
            if (this.idFrame == this.frame.length) {
                this.animations = false;
                return;
            }
            this.currentFrame = this.frame[this.idFrame];
        }
    }

    @Override
    public void update() {
        switch (axisFlame) {
            case CENTER:
                switch (this.currentFrame) {
                    case 0:
                        this.img = Sprite.bomb_exploded.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.bomb_exploded1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.bomb_exploded2.getFxImage();
                        break;
                }
                break;
            case VERTICAL:
                if (lastFlame) {
                    switch (this.currentFrame) {
                        case 0:
                            switch (direction) {
                                case UP:
                                    this.img = Sprite.explosion_vertical_top_last.getFxImage();
                                    break;
                                case DOWN:
                                    this.img = Sprite.explosion_vertical_down_last.getFxImage();
                                    break;
                            }
                            break;
                        case 1:
                            switch (direction) {
                                case UP:
                                    this.img = Sprite.explosion_vertical_top_last1.getFxImage();
                                    break;
                                case DOWN:
                                    this.img = Sprite.explosion_vertical_down_last1.getFxImage();
                                    break;
                            }
                            break;
                        case 2:
                            switch (direction) {
                                case UP:
                                    this.img = Sprite.explosion_vertical_top_last2.getFxImage();
                                    break;
                                case DOWN:
                                    this.img = Sprite.explosion_vertical_down_last2.getFxImage();
                                    break;
                            }
                            break;
                    }
                } else {
                    switch (this.currentFrame) {
                        case 0:
                            this.img = Sprite.explosion_vertical.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.explosion_vertical1.getFxImage();
                            break;
                        case 2:
                            this.img = Sprite.explosion_vertical2.getFxImage();
                            break;
                    }
                }
                break;
            case HORIZONTAL:
                if (lastFlame) {
                    switch (this.currentFrame) {
                        case 0:
                            switch (direction) {
                                case LEFT:
                                    this.img = Sprite.explosion_horizontal_left_last.getFxImage();
                                    break;
                                case RIGHT:
                                    this.img = Sprite.explosion_horizontal_right_last.getFxImage();
                                    break;
                            }
                            break;
                        case 1:
                            switch (direction) {
                                case LEFT:
                                    this.img = Sprite.explosion_horizontal_left_last1.getFxImage();
                                    break;
                                case RIGHT:
                                    this.img = Sprite.explosion_horizontal_right_last1.getFxImage();
                                    break;
                            }
                            break;
                        case 2:
                            switch (direction) {
                                case LEFT:
                                    this.img = Sprite.explosion_horizontal_left_last2.getFxImage();
                                    break;
                                case RIGHT:
                                    this.img = Sprite.explosion_horizontal_right_last2.getFxImage();
                                    break;
                            }
                            break;
                    }
                } else {
                    switch (this.currentFrame) {
                        case 0:
                            this.img = Sprite.explosion_horizontal.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.explosion_horizontal1.getFxImage();
                            break;
                        case 2:
                            this.img = Sprite.explosion_horizontal2.getFxImage();
                            break;
                    }
                }
                break;
        }
    }
}
