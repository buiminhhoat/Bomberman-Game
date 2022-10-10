package uet.oop.bomberman.control;

import uet.oop.bomberman.enumeration.Direction;
import uet.oop.bomberman.entities.dynamicentity.DynamicEntity;
import uet.oop.bomberman.gamemap.GameMap;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Move {
    public static void checkRun(DynamicEntity entity) {
        if (!entity.getAnimations()) {
            return;
        }
        run(entity);
    }

    public static void up(DynamicEntity entity, GameMap gameMap) {
        if (!entity.getAnimations()) {
            entity.setDirection(Direction.UP);
            if (gameMap.checkBlockedPixel(entity.getX(), entity.getY() - Sprite.SCALED_SIZE)) {
                return;
            }
            entity.setAnimations(true);
        }
    }

    public static void down(DynamicEntity entity, GameMap gameMap) {
        if (!entity.getAnimations()) {
            entity.setDirection(Direction.DOWN);
            if (gameMap.checkBlockedPixel(entity.getX(), entity.getY() + Sprite.SCALED_SIZE)) {
                return;
            }
            entity.setAnimations(true);
        }
    }

    public static void left(DynamicEntity entity, GameMap gameMap) {
        if (!entity.getAnimations()) {
            entity.setDirection(Direction.LEFT);
            if (gameMap.checkBlockedPixel(entity.getX() - Sprite.SCALED_SIZE, entity.getY())) {
                return;
            }
            entity.setAnimations(true);
        }
    }

    public static void right(DynamicEntity entity, GameMap gameMap) {
        if (!entity.getAnimations()) {
            entity.setDirection(Direction.RIGHT);
            if (gameMap.checkBlockedPixel(entity.getX() + Sprite.SCALED_SIZE, entity.getY())) {
                return;
            }
            entity.setAnimations(true);
        }
    }

    private static void step(DynamicEntity entity) {
        entity.nextTimeline();
    }

    private static void run(DynamicEntity entity) {
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
