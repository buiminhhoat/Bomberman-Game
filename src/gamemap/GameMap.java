package gamemap;

import entities.dynamicentity.enemies.Creeper;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.Entity;
import entities.block.Brick;
import entities.block.Grass;
import entities.block.Wall;
import entities.dynamicentity.bomb.Bomb;
import entities.dynamicentity.bomber.Bomber;
import entities.dynamicentity.enemies.Balloon;
import entities.dynamicentity.enemies.Ghost;
import entities.dynamicentity.enemies.Oneal;
import enumeration.BombermanObject;
import graphics.Sprite;

public class GameMap {

    private int level;
    private int row;
    private int col;
    private List<Entity> stillObjects = new ArrayList<>();
    private List<Entity> movingEntities = new ArrayList<>();

    private BombermanObject[][] map;
    private boolean[][] isBlocked;

    public GameMap() {

    }

    public BombermanObject getMapObject(int x, int y) {
        if (x < 0 || y < 0) {
            return null;
        }
        return map[x][y];
    }

    public boolean checkBlocked(int x, int y) {
        if (isBlocked[x][y]) {
            return true;
        }

//        for (Bomb bomb : listBombs) {
//            int xB = bomb.getX() / Sprite.SCALED_SIZE;
//            int yB = bomb.getY() / Sprite.SCALED_SIZE;
//            if (y == xB && x == yB) {
//                return true;
//            }
//        }
        return false;
    }

    public boolean checkBlockedPixel(int x, int y) {
        x /= Sprite.SCALED_SIZE;
        y /= Sprite.SCALED_SIZE;
        if (isBlocked[y][x]) {
            return true;
        }

//        for (Bomb bomb : listBombs) {
//            int xB = bomb.getX() / Sprite.SCALED_SIZE;
//            int yB = bomb.getY() / Sprite.SCALED_SIZE;
//            if (x == xB && y == yB) {
//                return true;
//            }
//        }
        return false;
    }

    public void destroyBrick(int x, int y) {
        isBlocked[x][y] = false;
        for (Entity brick : stillObjects) {
            if (brick instanceof Brick) {
                if (brick.getX() / Sprite.SCALED_SIZE == y
                    && brick.getY() / Sprite.SCALED_SIZE == x) {
                    ((Brick) brick).setAnimations(true);
                    return;
                }
            }
        }
    }

    private BombermanObject convertEntity(char c) {
        switch (c) {
            case '#':
                return BombermanObject.WALL;
            case '*':
                return BombermanObject.BRICK;
            case 'x':
                return BombermanObject.PORTAL;
            case 'p':
                return BombermanObject.BOMBERMAN;
            case '1':
                return BombermanObject.BALLOON;
            case '2':
                return BombermanObject.ONEAL;
            case '3':
                return BombermanObject.GHOST;
            case '4':
                return BombermanObject.CREEPER;
            case 'b':
                return BombermanObject.BOMB_ITEM;
            case 'f':
                return BombermanObject.FLAME_ITEM;
            case 's':
                return BombermanObject.SPEED_ITEM;
            default:
                return BombermanObject.GRASS;
        }
    }

    public void initMap(int level) {
        this.level = level;
        try {
            String levelPath = "./res/levels/Level" + level + ".txt";
            File file = new File(levelPath);

            Scanner scanner = new Scanner(file);
            level = scanner.nextInt();
            this.row = scanner.nextInt();
            this.col = scanner.nextInt();

            map = new BombermanObject[row][col];
            isBlocked = new boolean[row][col];
            scanner.nextLine();

            for (int i = 0; i < row; ++i) {
                String c = scanner.nextLine();
                for (int j = 0; j < col; ++j) {
                    map[i][j] = convertEntity(c.charAt(j));
                    if (map[i][j] == BombermanObject.BRICK
                        || map[i][j] == BombermanObject.WALL) {
                        isBlocked[i][j] = true;
                    } else {
                        isBlocked[i][j] = false;
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception!");
            e.printStackTrace();
        }
    }

    public void setPosIsBlocked(int x, int y) {
        isBlocked[x][y] = true;
    }

    public void setPosIsOpened(int x, int y) {
        isBlocked[x][y] = false;
    }

    public int getIdPos(int x, int y) {
        return x * col + y;
    }

    public int getIdX(int id) {
        return (int) id / col;
    }

    public int getIdY(int id) {
        return id - getIdX(id) * col;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public BombermanObject[][] getMap() {
        return map;
    }

    public void setMap(BombermanObject[][] map) {
        this.map = map;
    }

    public boolean[][] getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean[][] isBlocked) {
        this.isBlocked = isBlocked;
    }

    public List<Entity> getListStillObjects() {
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                Entity object;
                object = new Grass(j, i, Sprite.grass.getFxImage());
                stillObjects.add(object);

                BombermanObject bombermanObject = map[i][j];
                switch (bombermanObject) {
                    case WALL:
                        object = new Wall(j, i, Sprite.wall.getFxImage());
                        break;
                    case BRICK:
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                }
                stillObjects.add(object);
            }
        }
        return stillObjects;
    }

    public List<Entity> getListMovingEntity() {
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                Entity movingEntity = null;
                BombermanObject bombermanObject = map[i][j];
                switch (bombermanObject) {
                    case BOMBERMAN:
                        movingEntity = new Bomber(j, i, Sprite.player_up.getFxImage());
                        break;
                    case BALLOON:
                        movingEntity = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                        break;
                    case ONEAL:
                        movingEntity = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                        break;
                    case GHOST:
                        movingEntity = new Ghost(j, i, Sprite.ghost_left1.getFxImage());
                    case CREEPER:
                        movingEntity = new Creeper(j, i, Sprite.ghost_left1.getFxImage());
                }
                if (movingEntity == null) {
                    continue;
                }
                movingEntities.add(movingEntity);
            }
        }
        return movingEntities;
    }
}
