package uet.oop.bomberman.control;

import uet.oop.bomberman.enumeration.Direction;
import uet.oop.bomberman.entities.movingentity.Bomber;
import uet.oop.bomberman.entities.movingentity.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.concurrent.TimeUnit;

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
        if (!entity.getAnimations()) {
            if (entity instanceof Bomber) {
                entity.setDirection(Direction.UP);
                entity.setAnimations(true);
            }
        }
    }

    public static void down(MovingEntity entity) {
        if (!entity.getAnimations()) {
            if (entity instanceof Bomber) {
                entity.setDirection(Direction.DOWN);
                entity.setAnimations(true);
            }
        }
    }

    public static void left(MovingEntity entity) {
        if (!entity.getAnimations()) {
            if (entity instanceof Bomber) {
                entity.setDirection(Direction.LEFT);
                entity.setAnimations(true);
            }
        }
    }

    public static void right(MovingEntity entity) {
        if (!entity.getAnimations()) {
            if (entity instanceof Bomber) {
                entity.setDirection(Direction.RIGHT);
                entity.setAnimations(true);
            }
        }
    }

    private static void step(MovingEntity entity) {
        if (entity instanceof Bomber) {
            entity.nextFrame();
        }
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
