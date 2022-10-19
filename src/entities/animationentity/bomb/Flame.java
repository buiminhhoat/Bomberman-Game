package entities.animationentity.bomb;

import entities.animationentity.AnimationEntity;
import enumeration.Axis;
import enumeration.Direction;
import graphics.Sprite;

import java.util.Timer;
import java.util.TimerTask;

public class Flame extends AnimationEntity {

    private boolean isEnemyOwner = false;
    private boolean lastFlame = false;
    private int idFrame = 0;
    private Axis axisFlame;
    private final int[] frame = {0, 1, 2, 2, 1, 0};

    public Flame() {
    }

    public Flame(int x, int y) {
        super(x, y, Sprite.bomb_exploded.getFxImage(), 8, 3,
            false, 3, Direction.DOWN);
        axisFlame = Axis.CENTER;
        startAnimations();
        update();
    }

    public Flame(int x, int y, boolean isEnemyOwner) {
        super(x, y, Sprite.bomb_exploded.getFxImage(), 8, 3,
                false, 3, Direction.DOWN);
        this.isEnemyOwner = isEnemyOwner;
        axisFlame = Axis.CENTER;
        startAnimations();
        update();
    }

    public Flame(int x, int y, Direction direction, boolean lastFlame, boolean isEnemyOwner) {
        super(x, y, Sprite.explosion_horizontal.getFxImage(), 8, 3,
            false, 3, direction);
        this.lastFlame = lastFlame;
        this.isEnemyOwner = isEnemyOwner;
        startAnimations();
        if (direction == Direction.UP || direction == Direction.DOWN) {
            axisFlame = Axis.VERTICAL;
        }
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            axisFlame = Axis.HORIZONTAL;
        }
        update();
    }

    public void updateFlame() {
        this.update();
    }

    @Override
    public void startAnimations() {
        this.animations = true;
        int timeToTransitionFrame = 70;

        Timer time = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ++idFrame;
                if (idFrame == frame.length) {
                    finishAnimations();
                    time.cancel();
                    return;
                }
                currentFrame = frame[idFrame];
            }
        };
        time.schedule(timerTask, 0, timeToTransitionFrame);
    }

    @Override
    public void update() {
        if (!isEnemyOwner) {
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
        } else {
            switch (axisFlame) {
                case CENTER:
                    switch (this.currentFrame) {
                        case 0:
                            this.img = Sprite.bomb_exploded_enemy.getFxImage();
                            break;
                        case 1:
                            this.img = Sprite.bomb_exploded_enemy1.getFxImage();
                            break;
                        case 2:
                            this.img = Sprite.bomb_exploded_enemy2.getFxImage();
                            break;
                    }
                    break;
                case VERTICAL:
                    if (lastFlame) {
                        switch (this.currentFrame) {
                            case 0:
                                switch (direction) {
                                    case UP:
                                        this.img = Sprite.explosion_vertical_top_last_enemy.getFxImage();
                                        break;
                                    case DOWN:
                                        this.img = Sprite.explosion_vertical_down_last_enemy.getFxImage();
                                        break;
                                }
                                break;
                            case 1:
                                switch (direction) {
                                    case UP:
                                        this.img = Sprite.explosion_vertical_top_last_enemy1.getFxImage();
                                        break;
                                    case DOWN:
                                        this.img = Sprite.explosion_vertical_down_last_enemy1.getFxImage();
                                        break;
                                }
                                break;
                            case 2:
                                switch (direction) {
                                    case UP:
                                        this.img = Sprite.explosion_vertical_top_last_enemy2.getFxImage();
                                        break;
                                    case DOWN:
                                        this.img = Sprite.explosion_vertical_down_last_enemy2.getFxImage();
                                        break;
                                }
                                break;
                        }
                    } else {
                        switch (this.currentFrame) {
                            case 0:
                                this.img = Sprite.explosion_vertical_enemy.getFxImage();
                                break;
                            case 1:
                                this.img = Sprite.explosion_vertical_enemy1.getFxImage();
                                break;
                            case 2:
                                this.img = Sprite.explosion_vertical_enemy2.getFxImage();
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
                                        this.img = Sprite.explosion_horizontal_left_last_enemy.getFxImage();
                                        break;
                                    case RIGHT:
                                        this.img = Sprite.explosion_horizontal_right_last_enemy.getFxImage();
                                        break;
                                }
                                break;
                            case 1:
                                switch (direction) {
                                    case LEFT:
                                        this.img = Sprite.explosion_horizontal_left_last_enemy1.getFxImage();
                                        break;
                                    case RIGHT:
                                        this.img = Sprite.explosion_horizontal_right_last_enemy1.getFxImage();
                                        break;
                                }
                                break;
                            case 2:
                                switch (direction) {
                                    case LEFT:
                                        this.img = Sprite.explosion_horizontal_left_last_enemy2.getFxImage();
                                        break;
                                    case RIGHT:
                                        this.img = Sprite.explosion_horizontal_right_last_enemy2.getFxImage();
                                        break;
                                }
                                break;
                        }
                    } else {
                        switch (this.currentFrame) {
                            case 0:
                                this.img = Sprite.explosion_horizontal_enemy.getFxImage();
                                break;
                            case 1:
                                this.img = Sprite.explosion_horizontal_enemy1.getFxImage();
                                break;
                            case 2:
                                this.img = Sprite.explosion_horizontal_enemy2.getFxImage();
                                break;
                        }
                    }
                    break;
            }
        }
    }
}
