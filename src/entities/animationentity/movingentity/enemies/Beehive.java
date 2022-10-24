package entities.animationentity.movingentity.enemies;

import entities.Entity;
import entities.animationentity.movingentity.bomber.Bomber;
import entities.animationentity.movingentity.enemies.chase.Bee;
import entities.animationentity.movingentity.enemies.chase.Chase;
import gamemap.GameMap;
import graphics.Sprite;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.Image;

public class Beehive extends Chase {

    private static final int NUMBER_BEE = 3;
    private static final int ANGRY_BEE_LEVEL_SPEED_ID = 2;
    private static final int WAIT_CREATE_BEE = ONE_SECOND;

    public Beehive() {

    }

    public Beehive(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (this.animations) {
            if (this.isDie()) {
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
                    case 3:
                        this.img = Sprite.nothing.getFxImage();
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
                List<Entity> entityList = gameMap.getAnimationEntities();
                for (int i = 1; i <= NUMBER_BEE; ++i) {
                    Bee bee = new Bee(getXPixel() / Sprite.SCALED_SIZE,
                        getYPixel() / Sprite.SCALED_SIZE, Sprite.bee_left1.getFxImage());
                    bee.setTargetEntity(bomberman);
                    bee.setDistanceChase(INF);
                    bee.setLevelSpeed(LEVEL_SPEED[ANGRY_BEE_LEVEL_SPEED_ID]);
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
