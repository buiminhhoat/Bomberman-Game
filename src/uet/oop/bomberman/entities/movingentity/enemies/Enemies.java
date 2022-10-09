package uet.oop.bomberman.entities.movingentity.enemies;

import java.util.Random;
import javafx.scene.image.Image;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.movingentity.MovingEntity;
import uet.oop.bomberman.enumeration.Direction;
import uet.oop.bomberman.gamemap.GameMap;

public abstract class Enemies extends MovingEntity {
    protected Direction faceDirection = Direction.LEFT;
    protected int maxStep;

    GameMap gameMap;

    Enemies() {

    };

    public Enemies(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, 8,2,
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

    public void randomDirection(GameMap gameMap) {
        if (animations) {
            return;
        }
        if (maxStep == 0) {
            Random generator = new Random();
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
        }
        else {
            --maxStep;
            switch (direction) {
                case UP:
                    Move.up(this, gameMap);
                    break;
                case DOWN:
                    Move.down(this, gameMap);
                    break;
                case LEFT:
                    faceDirection = Direction.LEFT;
                    Move.left(this, gameMap);
                    break;
                case RIGHT:
                    faceDirection = Direction.RIGHT;
                    Move.right(this, gameMap);
                    break;
            }
        }
    }
}
