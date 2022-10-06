package uet.oop.bomberman.control;

import uet.oop.bomberman.enumeration.BombermanObject;
import uet.oop.bomberman.enumeration.Direction;
import uet.oop.bomberman.entities.movingentity.Bomber;
import uet.oop.bomberman.entities.movingentity.MovingEntity;
import uet.oop.bomberman.gamemap.GameMap;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static uet.oop.bomberman.BombermanGame.gameMap;

public abstract class Move {
    public static void checkRun(MovingEntity entity) {
        if (!entity.getAnimations()) {
            return;
        }
        if (entity instanceof Bomber) {
            run(entity);
        }
    }

    public static void up(MovingEntity entity) {
        if (!entity.getAnimations() ) {
            entity.setDirection(Direction.UP);
            if (checkBlocked(entity.getX(), entity.getY() - Sprite.SCALED_SIZE)) {
                return;
            }
            if (entity instanceof Bomber) {
                entity.setAnimations(true);
            }
        }
    }

    public static void down(MovingEntity entity) {
        if (!entity.getAnimations()) {
            entity.setDirection(Direction.DOWN);
            if (checkBlocked(entity.getX(), entity.getY() + Sprite.SCALED_SIZE)) {
                return;
            }
            if (entity instanceof Bomber) {
                entity.setAnimations(true);
            }
        }
    }

    public static void left(MovingEntity entity) {
        if (!entity.getAnimations()) {
            entity.setDirection(Direction.LEFT);
            if (checkBlocked(entity.getX() - Sprite.SCALED_SIZE, entity.getY())) {
                return;
            }
            if (entity instanceof Bomber) {
                entity.setAnimations(true);
            }
        }
    }

    public static void right(MovingEntity entity) {
        if (!entity.getAnimations()) {
            entity.setDirection(Direction.RIGHT);
            if (checkBlocked(entity.getX() + Sprite.SCALED_SIZE, entity.getY())) {
                return;
            }
            if (entity instanceof Bomber) {
                entity.setAnimations(true);
            }
        }
    }

    private static void step(MovingEntity entity) {
        if (entity instanceof Bomber) {
            entity.nextFrame();
        }
    }

    public static boolean checkBlocked(int x, int y) {
        x /= Sprite.SCALED_SIZE;
        y /= Sprite.SCALED_SIZE;
        if (Objects.equals(gameMap.getObjectMap(y, x), BombermanObject.WALL)) {
            return true;
        }
        return false;
    }

    private static void run(MovingEntity entity) {
        try {
            Thread.sleep(50);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        ///

        if (entity.getDirection() == null) {
            return;
        }

        step(entity);
        switch (entity.getDirection()) {
            case UP:
                entity.setY(entity.getY() - Sprite.SCALED_SIZE / 4);
                break;
            case DOWN:
                entity.setY(entity.getY() + Sprite.SCALED_SIZE / 4);
                break;
            case LEFT:
                entity.setX(entity.getX() - Sprite.SCALED_SIZE / 4);
                break;
            case RIGHT:
                entity.setX(entity.getX() + Sprite.SCALED_SIZE / 4);
                break;
        }

        if (entity.getX() % Sprite.SCALED_SIZE == 0 && entity.getY() % Sprite.SCALED_SIZE == 0) {
            entity.setAnimations(false);
        }
    }
}
