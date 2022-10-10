package uet.oop.bomberman.entities.movingentity.bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingentity.MovingEntity;
import uet.oop.bomberman.entities.movingentity.bomber.Bomber;
import uet.oop.bomberman.enumeration.Direction;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends MovingEntity {

    private final int DEFAULT_TIME_BOMB = 50; // 25 = 1s

    private int timeBomb = DEFAULT_TIME_BOMB;

    private Flame flame_center;
    private List<Flame> listFlame = new ArrayList<>();

    private Entity bomber;

    public Bomb() {
    }

    public Bomb(Entity bomber) {
        super(bomber.getX() / Sprite.SCALED_SIZE, bomber.getY() / Sprite.SCALED_SIZE,
                Sprite.bomb.getFxImage(), 8, 3, true, 3, Direction.DOWN);
        this.bomber = bomber;

        int x = bomber.getX() / Sprite.SCALED_SIZE;
        int y = bomber.getY() / Sprite.SCALED_SIZE;
        int lth = ((Bomber) bomber).getLengthExplosionOfBomb();

        flame_center = new Flame(x, y);

        for (int i = y - 1; i > y - lth; --i) {
            listFlame.add(new Flame(x, i, Direction.UP, false));
        }
        listFlame.add(new Flame(x, y - lth, Direction.UP, true));

        for (int i = y + 1; i < y + lth; ++i) {
            listFlame.add(new Flame(x, i, Direction.DOWN, false));
        }
        listFlame.add(new Flame(x, y + lth, Direction.DOWN, true));

        for (int i = x - 1; i > x - lth; --i) {
            listFlame.add(new Flame(i, y, Direction.LEFT, false));
        }
        listFlame.add(new Flame(x - lth, y, Direction.LEFT, true));

        for (int i = x + 1; i < x + lth; ++i) {
            listFlame.add(new Flame(i, y, Direction.RIGHT, false));
        }
        listFlame.add(new Flame(x + lth, y, Direction.RIGHT, true));
    }

    public int getTimeBomb() {
        return timeBomb;
    }

    public void setTimeBomb(int timeBomb) {
        this.timeBomb = timeBomb;
    }

    private void countdown() {
        this.timeBomb = Math.max(0, this.timeBomb - 1);
    }

    public void checkExplosion() {
        this.nextTimeline();
        this.countdown();
        if (this.timeBomb == 0) {
            this.explode();
        }
    }

    private void explode() {
        flame_center.flaming();
        for (Flame flame : listFlame) {
            flame.flaming();
        }

        if (!flame_center.getAnimations()) {
            this.setAnimations(false);
            this.setTimeBomb(DEFAULT_TIME_BOMB);
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
                case 2:
                    this.img = Sprite.bomb_2.getFxImage();
                    break;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (this.timeBomb == 0) {
            for (Flame flame : listFlame) {
                flame.render(gc);
            }
            flame_center.render(gc);
        } else {
            gc.drawImage(img, x, y);
        }
    }
}
