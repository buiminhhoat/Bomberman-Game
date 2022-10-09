package uet.oop.bomberman.control;

import uet.oop.bomberman.enumeration.Direction;
import uet.oop.bomberman.entities.movingentity.MovingEntity;
import uet.oop.bomberman.gamemap.GameMap;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Move {
    public static void checkRun(MovingEntity entity) {
        if (!entity.getAnimations()) {
            return;
        }
        run(entity);
    }

    public static void up(MovingEntity entity, GameMap gameMap) {
        if (!entity.getAnimations()) {
            entity.setDirection(Direction.UP);
            if (gameMap.checkBlocked(entity.getX(), entity.getY() - Sprite.SCALED_SIZE)) {
                return;
            }
            entity.setAnimations(true);
        }
    }

    public static void down(MovingEntity entity, GameMap gameMap) {
        if (!entity.getAnimations()) {
            entity.setDirection(Direction.DOWN);
            if (gameMap.checkBlocked(entity.getX(), entity.getY() + Sprite.SCALED_SIZE)) {
                return;
            }
            entity.setAnimations(true);
        }
    }

    public static void left(MovingEntity entity, GameMap gameMap) {
        if (!entity.getAnimations()) {
            entity.setDirection(Direction.LEFT);
            if (gameMap.checkBlocked(entity.getX() - Sprite.SCALED_SIZE, entity.getY())) {
                return;
            }
            entity.setAnimations(true);
        }
    }

    public static void right(MovingEntity entity, GameMap gameMap) {
        if (!entity.getAnimations()) {
            entity.setDirection(Direction.RIGHT);
            if (gameMap.checkBlocked(entity.getX() + Sprite.SCALED_SIZE, entity.getY())) {
                return;
            }
            entity.setAnimations(true);
        }
    }

    private static void step(MovingEntity entity) {
        entity.nextTimeline();
    }

    private static void run(MovingEntity entity) {
        if (entity.getDirection() == null) {
            return;
        }

        step(entity);
        switch (entity.getDirection()) {
            case UP:
                entity.setY(entity.getY() - Sprite.SCALED_SIZE / entity.getLevelSpeed());
                break;
            case DOWN:
                entity.setY(entity.getY() + Sprite.SCALED_SIZE / entity.getLevelSpeed());
                break;
            case LEFT:
                entity.setX(entity.getX() - Sprite.SCALED_SIZE / entity.getLevelSpeed());
                break;
            case RIGHT:
                entity.setX(entity.getX() + Sprite.SCALED_SIZE / entity.getLevelSpeed());
                break;
        }

        if (entity.getX() % Sprite.SCALED_SIZE == 0 && entity.getY() % Sprite.SCALED_SIZE == 0) {
            entity.setAnimations(false);
        }
    }
}
