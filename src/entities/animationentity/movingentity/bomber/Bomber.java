package entities.animationentity.movingentity.bomber;

import control.BombermanGame;
import control.SoundManager;
import entities.Entity;
import entities.animationentity.AnimationEntity;
import entities.animationentity.hiddenitem.HiddenItem;
import entities.animationentity.hiddenitem.Portal;
import entities.animationentity.movingentity.MovingEntity;
import enumeration.Chunk;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javafx.scene.image.Image;

public class Bomber extends MovingEntity {
    public static final int ONE_SECOND = 1000;
    private static final int BOMBER_LIVES = 3;
    private static final int BOMBER_LEVEL_SPEED_ID = 2;
    private static final int MAX_FRAME = 2;
    private static final long IMMORTAL_TIME = 7 * ONE_SECOND;
    private boolean win;

    private boolean immortal;
    private long lastTimeDeath;

    public Bomber() {
        this.win = false;
        this.immortal = false;
        this.lastTimeDeath = 0;
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img, LEVEL_SPEED[BOMBER_LEVEL_SPEED_ID], MAX_FRAME,
                false, BOMBER_LIVES, Direction.DOWN);
        this.win = false;
        this.immortal = false;
        this.lastTimeDeath = 0;
    }

    public void pickUpItem(GameMap gameMap) {
        for (int i = 0; i < gameMap.getStillObjects().size(); ++i) {
            Entity hiddenItem = gameMap.getStillObjects().get(i);
            if (hiddenItem instanceof HiddenItem) {
                if (((HiddenItem) hiddenItem).pickUp(this)) {
                    BombermanGame.bombermanGame.setScore(BombermanGame.bombermanGame.getScore()
                        + ((HiddenItem) hiddenItem).getScore());
                    if (hiddenItem instanceof Portal) {
                        ((Portal) hiddenItem).featureItem(this, gameMap);
                        continue;
                    }
                    ((HiddenItem) hiddenItem).featureItem(this);
                    gameMap.getStillObjects().remove(hiddenItem);
                    --i;
                }
            }
        }
    }

    @Override
    public void update() {
        if (new Date().getTime() - this.lastTimeDeath < IMMORTAL_TIME) {
            this.setImmortal(true);
        } else {
            this.setImmortal(false);
        }
        if (this.animations) {
            if (this.isDie()) {
                switch (this.currentFrame) {
                    case 0:
                        this.img = Sprite.player_dead1.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.player_dead2.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.player_dead3.getFxImage();
                        break;
                    case 3:
                        this.img = Sprite.nothing.getFxImage();
                        break;
                }
            } else {
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

    @Override
    public void dead() {
        if (this.lives == 0 || this.isDie) {
            return;
        }
        if (this.isImmortal()) {
            return;
        }

        SoundManager.stopMusic();
        SoundManager.playChunk(Chunk.BOMBER_DIE);

        --this.lives;
        this.setDie(true);

        cancelLiveTimer();
        if (getAnimations()) {
            finishAnimations();
        }
        startAnimations();
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isImmortal() {
        return immortal;
    }

    public void setImmortal(boolean immortal) {
        this.immortal = immortal;
    }

    public long getLastTimeDeath() {
        return lastTimeDeath;
    }

    public void setLastTimeDeath(long lastTimeDeath) {
        this.lastTimeDeath = lastTimeDeath;
    }
}
