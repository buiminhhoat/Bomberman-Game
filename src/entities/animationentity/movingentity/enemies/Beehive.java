package entities.animationentity.movingentity.enemies;

import entities.animationentity.movingentity.enemies.chase.Chase;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Beehive extends Chase {

    public Beehive() {

    }

    public Beehive(int x, int y, Image img) {
        super(x, y, img);
    }

    public void chooseDirection(GameMap gameMap) {

    }

    @Override
    public void update() {
        if (this.animations) {
            if (this.getlives() == 0) {
                switch (this.currentFrame) {
                    case 0:
                        this.img = Sprite.beehive.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.mob_dead_beehive1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.mob_dead_beehive2.getFxImage();
                        break;
                }
            } else {
                this.img = Sprite.beehive.getFxImage();
            }
        } else {
            this.img = Sprite.beehive.getFxImage();
        }
    }
}
