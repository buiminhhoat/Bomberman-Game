package entities.animationentity.movingentity.bomber;

import entities.Entity;
import entities.animationentity.hiddenitem.HiddenItem;
import entities.animationentity.hiddenitem.Portal;
import entities.animationentity.movingentity.MovingEntity;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.scene.image.Image;

public class Bomber extends MovingEntity {
    private boolean win = false;

    public Bomber() {

    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img, 8, 2,
            false, 100, Direction.DOWN);
    }

    public void pickUpItem(GameMap gameMap) {
        for (int i = 0; i < gameMap.getStillObjects().size(); ++i) {
            Entity hiddenItem = gameMap.getStillObjects().get(i);
            if (hiddenItem instanceof HiddenItem) {
                if (((HiddenItem) hiddenItem).pickUp(this)) {
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
        if (this.animations) {
            if (this.getlives() == 0) {
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

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
