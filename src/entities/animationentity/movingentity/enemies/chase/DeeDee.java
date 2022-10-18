package entities.animationentity.movingentity.enemies.chase;

import static algorithm.ShuffleArray.shuffleArray;

import algorithm.BreadthFirstSearch;
import entities.Entity;
import entities.animationentity.bomb.Bomb;
import entities.animationentity.movingentity.MovingEntity;
import entities.animationentity.movingentity.bomber.Bomber;
import enumeration.Direction;
import gamemap.GameMap;
import graphics.Sprite;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.Image;

public class DeeDee extends Chase {
    protected boolean eating = false;

    private int DIGESTION_TIME = 15 * 1000; // 1s = 1000ms
    public DeeDee() {
        distanceChase = INF;
    }

    public DeeDee(int x, int y, Image img) {
        super(x, y, img);
        distanceChase = INF;
    }

    @Override
    public void update() {
        if (this.animations) {
            if (this.isDie()) {
                switch (this.currentFrame) {
                    case 0:
                        this.img = Sprite.deedee_dead.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.mob_dead_gray1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.mob_dead_gray2.getFxImage();
                        break;
                    case 3:
                        this.img = Sprite.nothing.getFxImage();
                        break;
                }
            } else {
                if (eating) {
                    switch (this.direction) {
                        case LEFT:
                            if (faceDirection != Direction.LEFT) {
                                faceDirection = Direction.LEFT;
                            }
                            switch (this.currentFrame) {
                                case 0:
                                    this.img = Sprite.deedee_left_chew1.getFxImage();
                                    break;
                                case 1:
                                    this.img = Sprite.deedee_left_chew2.getFxImage();
                                    break;
                            }
                            break;
                        case RIGHT:
                            if (faceDirection != Direction.RIGHT) {
                                faceDirection = Direction.RIGHT;
                            }
                            switch (this.currentFrame) {
                                case 0:
                                    this.img = Sprite.deedee_right_chew1.getFxImage();
                                    break;
                                case 1:
                                    this.img = Sprite.deedee_right_chew2.getFxImage();
                                    break;
                            }
                            break;
                        default:
                            switch (this.currentFrame) {
                                case 0:
                                    switch (faceDirection) {
                                        case LEFT:
                                            this.img = Sprite.deedee_left_chew1.getFxImage();
                                            break;
                                        case RIGHT:
                                            this.img = Sprite.deedee_right_chew1.getFxImage();
                                            break;
                                    }
                                    break;
                                case 1:
                                    switch (faceDirection) {
                                        case LEFT:
                                            this.img = Sprite.deedee_left_chew2.getFxImage();
                                            break;
                                        case RIGHT:
                                            this.img = Sprite.deedee_right_chew2.getFxImage();
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } else {
                    switch (this.direction) {
                        case LEFT:
                            if (faceDirection != Direction.LEFT) {
                                faceDirection = Direction.LEFT;
                            }
                            switch (this.currentFrame) {
                                case 0:
                                    this.img = Sprite.deedee_left.getFxImage();
                                    break;
                                case 1:
                                    this.img = Sprite.deedee_left.getFxImage();
                                    break;
                            }
                            break;
                        case RIGHT:
                            if (faceDirection != Direction.RIGHT) {
                                faceDirection = Direction.RIGHT;
                            }
                            switch (this.currentFrame) {
                                case 0:
                                    this.img = Sprite.deedee_right.getFxImage();
                                    break;
                                case 1:
                                    this.img = Sprite.deedee_right.getFxImage();
                                    break;
                            }
                            break;
                        default:
                            switch (this.currentFrame) {
                                case 0:
                                    switch (faceDirection) {
                                        case LEFT:
                                            this.img = Sprite.deedee_left.getFxImage();
                                            break;
                                        case RIGHT:
                                            this.img = Sprite.deedee_right.getFxImage();
                                            break;
                                    }
                                    break;
                                case 1:
                                    switch (faceDirection) {
                                        case LEFT:
                                            this.img = Sprite.deedee_left.getFxImage();
                                            break;
                                        case RIGHT:
                                            this.img = Sprite.deedee_right.getFxImage();
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                }
            }
        } else {
            if (eating) {
                switch (this.faceDirection) {
                    case LEFT:
                        this.img = Sprite.deedee_left_chew1.getFxImage();
                        break;
                    case RIGHT:
                        this.img = Sprite.deedee_right_chew1.getFxImage();
                        break;
                }
            } else {
                switch (this.faceDirection) {
                    case LEFT:
                        this.img = Sprite.deedee_left.getFxImage();
                        break;
                    case RIGHT:
                        this.img = Sprite.deedee_right.getFxImage();
                        break;
                }
            }
        }
    }

    public void chooseDirection(GameMap gameMap) {
        if (animations || isDie()) {
            return;
        }
        eatBomb(gameMap);
        if (eating) {
            levelSpeed = 16;
            super.chooseDirection(gameMap);
            return;
        }
        if (targetEntity == null || !(targetEntity instanceof Bomb)) {
            int Min = distanceChase;
            List<Entity> entityList = gameMap.getMovingEntities();
            for (Entity entity: entityList) {
                if (entity instanceof Bomber) {
                    for (Bomb bomb: ((MovingEntity) entity).getBombList()) {
                        if (bomb == null) {
                            continue;
                        }
                        BreadthFirstSearch.CalculatorBreadthFirstSearch(
                            bomb.getYPixel() / Sprite.SCALED_SIZE,
                            bomb.getXPixel() / Sprite.SCALED_SIZE,
                            gameMap);
                        if (Min > BreadthFirstSearch.minDistance(
                            this.getYPixel() / Sprite.SCALED_SIZE,
                            this.getXPixel() / Sprite.SCALED_SIZE)) {
                            Min = BreadthFirstSearch.minDistance(
                                this.getYPixel() / Sprite.SCALED_SIZE,
                                this.getXPixel() / Sprite.SCALED_SIZE);
                            targetEntity = bomb;
                        }
                    }
                }
            }
        }

        if (targetEntity == null || !(targetEntity instanceof Bomb)) {
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

        if (!eating) {
            levelSpeed = 4;
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

    public boolean isEating() {
        return eating;
    }

    public void setEating(boolean eating) {
        this.eating = eating;
    }

    @Override
    public void up(GameMap gameMap) {
        if (!getAnimations()) {
            setDirection(Direction.UP);
            if (gameMap.checkBlockedPixelByBlock(this.getXPixel(),
                this.getYPixel() - Sprite.SCALED_SIZE)) {
                return;
            }
            startAnimations();
        }
    }

    @Override
    public void down(GameMap gameMap) {
        if (!getAnimations()) {
            setDirection(Direction.DOWN);
            if (gameMap.checkBlockedPixelByBlock(this.getXPixel(),
                this.getYPixel() + Sprite.SCALED_SIZE)) {
                return;
            }
            startAnimations();
        }
    }

    @Override
    public void left(GameMap gameMap) {
        if (!this.getAnimations()) {
            this.setDirection(Direction.LEFT);
            if (gameMap.checkBlockedPixelByBlock(this.getXPixel() - Sprite.SCALED_SIZE,
                this.getYPixel())) {
                return;
            }
            startAnimations();
        }
    }

    @Override
    public void right(GameMap gameMap) {
        if (!this.getAnimations()) {
            this.setDirection(Direction.RIGHT);
            if (gameMap.checkBlockedPixelByBlock(this.getXPixel() + Sprite.SCALED_SIZE,
                this.getYPixel())) {
                return;
            }
            startAnimations();
        }
    }

    public void eatBomb(GameMap gameMap) {
        if (eating) {
            return;
        }
        List<Entity> entityList = gameMap.getMovingEntities();
        for (Entity bomber: entityList) {
            if (bomber instanceof MovingEntity) {
                if (!(bomber instanceof Bomber)) {
                    continue;
                }
                List<Bomb> bombList = ((MovingEntity) bomber).getBombList();
                for (int i = 0; i < bombList.size(); ++i) {
                    Bomb bomb = bombList.get(i);
                    if (this.getXPixel() == bomb.getXPixel()
                        && this.getYPixel() == bomb.getYPixel()) {
                        gameMap.setPosIsBombOpened(
                            bomb.getYPixel() / Sprite.SCALED_SIZE,
                            bomb.getXPixel() / Sprite.SCALED_SIZE);
                        ((Bomber) bomber).setNumberBombs(((Bomber) bomber).getNumberBombs() + 1);
                        for (Entity deedee: entityList) {
                            if (deedee instanceof DeeDee) {
                                if (((DeeDee) deedee).getTargetEntity() == bomb) {
                                    ((DeeDee) deedee).setTargetEntity(null);
                                }
                            }
                        }
                        this.eating = true;
                        bomb.setExploded(true);
                        TimerTask timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                eating = false;
                            }
                        };
                        Timer timer = new Timer();
                        timer.schedule(timerTask, DIGESTION_TIME);
                        bombList.remove(i);
                        --i;
                    }
                }
            }
        }
    }
}
