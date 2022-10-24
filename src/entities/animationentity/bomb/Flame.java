package entities.animationentity.bomb;

import entities.animationentity.AnimationEntity;
import enumeration.Axis;
import enumeration.Direction;
import graphics.Sprite;

import java.util.Timer;
import java.util.TimerTask;

public class Flame extends AnimationEntity {
    private static final int TIME_TRANSITION_FRAME = 70; // ms
    private static final int FLAME_LEVEL_SPEED_ID = 1;
    private static final int MAX_FRAME = 3;
    private static final int[] FRAME = {0, 1, 2, 2, 1, 0};

    private int idFrame;
    private boolean isEnemyOwner;
    private boolean lastFlame;
    private Axis axisFlame;

    public Flame() {
        this.idFrame = 0;
        this.isEnemyOwner = false;
        this.lastFlame = false;
    }

    public Flame(int x, int y) {
        super(x, y, Sprite.bomb_exploded.getFxImage(), LEVEL_SPEED[FLAME_LEVEL_SPEED_ID],
                MAX_FRAME, false, DEFAULT_LIVES, Direction.DOWN);
        this.idFrame = 0;
        this.isEnemyOwner = false;
        this.lastFlame = false;
        this.axisFlame = Axis.CENTER;
        startAnimations();
        update();
    }

    public Flame(int x, int y, boolean isEnemyOwner) {
        super(x, y, Sprite.bomb_exploded.getFxImage(), LEVEL_SPEED[FLAME_LEVEL_SPEED_ID],
                MAX_FRAME, false, DEFAULT_LIVES, Direction.DOWN);
        this.idFrame = 0;
        this.isEnemyOwner = isEnemyOwner;
        this.lastFlame = false;
        this.axisFlame = Axis.CENTER;
        startAnimations();
        update();
    }

    public Flame(int x, int y, Direction direction, boolean lastFlame, boolean isEnemyOwner) {
        super(x, y, Sprite.explosion_horizontal.getFxImage(), LEVEL_SPEED[FLAME_LEVEL_SPEED_ID],
                MAX_FRAME, false, DEFAULT_LIVES, direction);
        this.idFrame = 0;
        this.lastFlame = lastFlame;
        this.isEnemyOwner = isEnemyOwner;
        startAnimations();
        if (direction == Direction.UP || direction == Direction.DOWN) {
            this.axisFlame = Axis.VERTICAL;
        }
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            this.axisFlame = Axis.HORIZONTAL;
        }
        update();
    }

    public void updateFlame() {
        this.update();
    }

    @Override
    public void startAnimations() {
        this.animations = true;
        int timeToTransitionFrame = TIME_TRANSITION_FRAME;

        Timer time = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ++idFrame;
                if (idFrame == FRAME.length) {
                    finishAnimations();
                    time.cancel();
                    return;
                }
                currentFrame = FRAME[idFrame];
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
