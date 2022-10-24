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

    private static final int CHASE_SCORE_REWARD = 200;
    private static final int RANDOM_RANGE = 20;
    private static final int RANDOM_THRESHOLD_1 = 16;
    private static final int RANDOM_THRESHOLD_2 = 5;
    protected static final int dx[] = {-1, 0, 1, 0};
    protected static final int dy[] = {0, 1, 0, -1};
    protected static final int INF = (int) 1e9 + 7;
    protected static final int DEFAULT_SAVE_DIRECTION = -1;
    protected static final int DEFAULT_DISTANCE_CHASE = 7;

    protected int distanceChase = DEFAULT_DISTANCE_CHASE;
    Entity targetEntity;
    protected int[] randomShuffle = new int[MAX_DIRECTION];

    public Chase() {
        score = CHASE_SCORE_REWARD;
    }

    public Chase(int x, int y, Image img) {
        super(x, y, img);
        score = CHASE_SCORE_REWARD;
    }

    public Entity getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(Entity targetEntity) {
        this.targetEntity = targetEntity;
    }

    @Override
    public void chooseDirection(GameMap gameMap) {
        if (animations || isDie()) {
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
        int saveDirection = DEFAULT_SAVE_DIRECTION;
        Random random = new Random();
//        int type = 0;
//        type = random.nextInt(2);

        for (int i = 0; i < MAX_DIRECTION; ++i) {
            randomShuffle[i] = i;
        }
        shuffleArray(randomShuffle);

        for (int h = 0; h < MAX_DIRECTION; ++h) {
            int kx = this.getYPixel() / Sprite.SCALED_SIZE + dy[randomShuffle[h]];
            int ky = this.getXPixel() / Sprite.SCALED_SIZE + dx[randomShuffle[h]];
            if (Min > BreadthFirstSearch.minDistance(kx, ky)) {
                Min = BreadthFirstSearch.minDistance(kx, ky);
                saveDirection = randomShuffle[h];
            }
        }

        if (saveDirection == DEFAULT_SAVE_DIRECTION) {
            super.chooseDirection(gameMap);
            return;
        }

        if (random.nextInt(RANDOM_RANGE) > RANDOM_THRESHOLD_1) {
            levelSpeed = LEVEL_SPEED[2];
        } else {
            if (random.nextInt(RANDOM_RANGE) > RANDOM_THRESHOLD_2) {
                levelSpeed = LEVEL_SPEED[1];
            } else {
                levelSpeed = LEVEL_SPEED[0];
            }
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
