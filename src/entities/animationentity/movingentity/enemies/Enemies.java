package entities.animationentity.movingentity.enemies;

import entities.animationentity.movingentity.MovingEntity;
import enumeration.Direction;
import gamemap.GameMap;
import java.util.Random;
import javafx.scene.image.Image;

public abstract class Enemies extends MovingEntity {

    protected Direction faceDirection = Direction.LEFT;
    protected int maxStep;

    GameMap gameMap;

    public Enemies() {

    }

    public Enemies(int x, int y, Image img) {
        super(x, y, img, 8, 2,
            false, 1, Direction.LEFT);
    }

    public Direction getFaceDirection() {
        return faceDirection;
    }

    public void setFaceDirection(Direction faceDirection) {
        this.faceDirection = faceDirection;
    }

    public int getMaxStep() {
        return maxStep;
    }

    public void setMaxStep(int maxStep) {
        this.maxStep = maxStep;
    }

    void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void chooseDirection(GameMap gameMap) {
        if (animations || getlives() == 0) {
            return;
        }
        Random generator = new Random();
        if (maxStep == 0) {
            maxStep = generator.nextInt(4) + 1;
            int type = generator.nextInt(4);
            switch (type) {
                case 0:
                    this.direction = Direction.UP;
                    break;
                case 1:
                    this.direction = Direction.DOWN;
                    break;
                case 2:
                    this.direction = Direction.LEFT;
                    break;
                case 3:
                    this.direction = Direction.RIGHT;
                    break;
            }
        } else {
            --maxStep;
            switch (direction) {
                case UP:
                    up(gameMap);
                    break;
                case DOWN:
                    down(gameMap);
                    break;
                case LEFT:
                    faceDirection = Direction.LEFT;
                    left(gameMap);
                    break;
                case RIGHT:
                    faceDirection = Direction.RIGHT;
                    right(gameMap);
                    break;
            }
        }
    }
}
