package entities.animationentity.movingentity.enemies.chase;

import static algorithm.ShuffleArray.shuffleArray;

import algorithm.BreadthFirstSearch;
import entities.Entity;
import entities.animationentity.movingentity.enemies.Enemies;
import gamemap.GameMap;
import graphics.Sprite;
import java.util.Random;
import javafx.scene.image.Image;

public class Chase extends Enemies {

    protected static final int dx[] = {-1, 0, 1, 0};
    protected static final int dy[] = {0, 1, 0, -1};
    protected static final int INF = (int) 1e9 + 7;

    protected static final int DEFAULT_DISTANCE_CHASE = 7;

    protected int distanceChase = DEFAULT_DISTANCE_CHASE;
    Entity targetEntity;
    protected int[] randomShuffle = new int[4];

    public Chase() {

    }

    public Chase(int x, int y, Image img) {
        super(x, y, img);
    }

    public Entity getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(Entity targetEntity) {
        this.targetEntity = targetEntity;
    }

    @Override
    public void chooseDirection(GameMap gameMap) {
        if (animations || getlives() == 0) {
            return;
        }

        if (targetEntity == null) {
            super.chooseDirection(gameMap);
            return;
        }

        BreadthFirstSearch.CalculatorBreadthFirstSearch(
            targetEntity.getYPixel() / Sprite.SCALED_SIZE,
            targetEntity.getXPixel() / Sprite.SCALED_SIZE,
            gameMap);

        int Min = (int) distanceChase;
        int saveDirection = -1;
        Random random = new Random();
        int type = 0;
        type = random.nextInt(2);

        for (int i = 0; i < 4; ++i) {
            randomShuffle[i] = i;
        }
        shuffleArray(randomShuffle);

        for (int h = 0; h < 4; ++h) {
            int kx = this.getYPixel() / Sprite.SCALED_SIZE + dy[randomShuffle[h]];
            int ky = this.getXPixel() / Sprite.SCALED_SIZE + dx[randomShuffle[h]];
            if (Min > BreadthFirstSearch.minDistance(kx, ky)) {
                Min = BreadthFirstSearch.minDistance(kx, ky);
                saveDirection = randomShuffle[h];
            }
        }

        if (saveDirection == -1) {
            super.chooseDirection(gameMap);
            return;
        }

        if (random.nextInt(20) > 16) {
            levelSpeed = 4;
        } else if (random.nextInt(20) > 5) {
            levelSpeed = 8;
        } else {
            levelSpeed = 16;
        }

        switch (saveDirection) {
            case 0:
                left(gameMap);
                break;
            case 1:
                down(gameMap);
                break;
            case 2:
                right(gameMap);
                break;
            case 3:
                up(gameMap);
                break;
        }
    }

    public int getDistanceChase() {
        return distanceChase;
    }

    public void setDistanceChase(int distanceChase) {
        this.distanceChase = distanceChase;
    }
}
