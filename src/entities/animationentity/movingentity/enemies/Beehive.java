package entities.animationentity.movingentity.enemies;

import entities.Entity;
import entities.animationentity.movingentity.MovingEntity;
import entities.animationentity.movingentity.bomber.Bomber;
import entities.animationentity.movingentity.enemies.chase.Bee;
import entities.animationentity.movingentity.enemies.chase.Chase;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.Image;

public class Beehive extends Chase {

    private static final int WAIT_CREATE_BEE = 1 * 1000;

    public Beehive() {

    }

    public Beehive(int x, int y, Image img) {
        super(x, y, img);
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

    @Override
    public void chooseDirection(GameMap gameMap) {

    }

    public void createBee(GameMap gameMap, Bomber bomberman) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                int numBee = 3;
                List<Entity> entityList = gameMap.getMovingEntities();
                for (int i = 1; i <= numBee; ++i) {
                    Bee bee = new Bee(getXPixel() / Sprite.SCALED_SIZE,
                        getYPixel() / Sprite.SCALED_SIZE, Sprite.bee_left1.getFxImage());
                    bee.setTargetEntity(bomberman);
                    bee.setDistanceChase(INF);
                    bee.setLevelSpeed(4);
                    entityList.add(bee);
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, WAIT_CREATE_BEE);
    }

    @Override
    public void die(GameMap gameMap, Bomber bomberman) {
        createBee(gameMap, (Bomber) bomberman);
    }
}
