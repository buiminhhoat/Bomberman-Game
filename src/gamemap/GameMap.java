package gamemap;

import entities.Entity;
import entities.animationentity.hiddenitem.FlameItem;
import entities.animationentity.hiddenitem.SpeedItem;
import entities.animationentity.movingentity.bomber.Bomber;
import entities.animationentity.movingentity.enemies.Balloon;
import entities.animationentity.movingentity.enemies.Beehive;
import entities.animationentity.movingentity.enemies.Creeper;
import entities.animationentity.movingentity.enemies.Ghost;
import entities.animationentity.movingentity.enemies.chase.Bee;
import entities.animationentity.movingentity.enemies.chase.Oneal;
import entities.block.Brick;
import entities.block.Grass;
import entities.block.Wall;
import enumeration.BombermanObject;
import graphics.Sprite;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameMap {

    private int level;
    private int row;
    private int col;
    private List<Entity> stillObjects = new ArrayList<>();
    private List<Entity> movingEntities = new ArrayList<>();

    private BombermanObject[][] map;

    private Entity[][] entityMap;


    private boolean[][] isBlocked;
    private boolean[][] bombBlocked;

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
        if (bombBlocked[x][y]) {
            return true;
        }
        return false;
    }

    public boolean checkBlockedPixel(int xPixel, int yPixel) {
        xPixel /= Sprite.SCALED_SIZE;
        yPixel /= Sprite.SCALED_SIZE;
        if (isBlocked[yPixel][xPixel]) {
            return true;
        }
        if (bombBlocked[yPixel][xPixel]) {
            return true;
        }

        return false;
    }

    public boolean checkBlockedPixelByBlock(int xPixel, int yPixel) {
        xPixel /= Sprite.SCALED_SIZE;
        yPixel /= Sprite.SCALED_SIZE;
        if (isBlocked[yPixel][xPixel]) {
            return true;
        }

        return false;
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
            case '5':
                return BombermanObject.BEEHIVE;
            case '6':
                return BombermanObject.BEE;
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
            entityMap = new Entity[row][col];
            bombBlocked = new boolean[row][col];
            isBlocked = new boolean[row][col];
            scanner.nextLine();

            for (int i = 0; i < row; ++i) {
                String c = scanner.nextLine();
                for (int j = 0; j < col; ++j) {
                    bombBlocked[i][j] = false;
                    map[i][j] = convertEntity(c.charAt(j));
                    isBlocked[i][j] = false;
                    BombermanObject bombermanObject = map[i][j];
                    Entity object = null;
                    switch (bombermanObject) {
                        case BOMBERMAN:
                            object = new Bomber(j, i, Sprite.player_up.getFxImage());
                            break;
                        case BALLOON:
                            object = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                            break;
                        case ONEAL:
                            object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                            break;
                        case GHOST:
                            object = new Ghost(j, i, Sprite.ghost_left1.getFxImage());
                            break;
                        case CREEPER:
                            object = new Creeper(j, i, Sprite.ghost_left1.getFxImage());
                            break;

                        case WALL:
                            object = new Wall(j, i, Sprite.wall.getFxImage());
                            break;
                        case BRICK:
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            break;
                        case SPEED_ITEM:
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            break;
                        case FLAME_ITEM:
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            break;
                        case BOMB_ITEM:
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            break;
                    }
                    if (object == null) {
                        continue;
                    }
                    entityMap[i][j] = object;
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception!");
            e.printStackTrace();
        }
    }

    public Entity[][] getEntityMap() {
        return entityMap;
    }

    public void setEntityMap(Entity[][] entityMap) {
        this.entityMap = entityMap;
    }

    public void setPosIsBlocked(int x, int y) {
        isBlocked[x][y] = true;
    }

    public void setPosIsOpened(int x, int y) {
        isBlocked[x][y] = false;
    }

    public void setPosIsBombBlocked(int x, int y) {
        bombBlocked[x][y] = true;
    }

    public void setPosIsBombOpened(int x, int y) {
        bombBlocked[x][y] = false;
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

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public void setStillObjects(List<Entity> stillObjects) {
        this.stillObjects = stillObjects;
    }

    public List<Entity> getMovingEntities() {
        return movingEntities;
    }

    public void setMovingEntities(List<Entity> movingEntities) {
        this.movingEntities = movingEntities;
    }

    public boolean[][] getBombBlocked() {
        return bombBlocked;
    }

    public void setBombBlocked(boolean[][] bombBlocked) {
        this.bombBlocked = bombBlocked;
    }

    public List<Entity> getListStillObjects() {
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                Entity object;

                if (new Random().nextInt(15) == 0) {
                    object = new Grass(j, i, Sprite.grass_flower.getFxImage());
                } else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                if (i == 1) {
                    object = new Grass(j, i, Sprite.forest_map_grass.getFxImage());
                }
                stillObjects.add(object);

                BombermanObject bombermanObject = map[i][j];
                switch (bombermanObject) {
                    case WALL:
                        object = new Wall(j, i, Sprite.wall.getFxImage());
                        break;
                    case BRICK:
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    case SPEED_ITEM:
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    case FLAME_ITEM:
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    case BOMB_ITEM:
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                }

                if (j == 0) {
                    if (i % 2 == 0) {
                        object = new Wall(j, i, Sprite.forest_map1.getFxImage());
                    } else {
                        object = new Wall(j, i, Sprite.forest_map2.getFxImage());
                    }
                }
                if (j == col - 1) {
                    if (i % 2 == 0) {
                        object = new Wall(j, i, Sprite.forest_map3.getFxImage());
                    }  else {
                        object = new Wall(j, i, Sprite.forest_map4.getFxImage());
                    }
                }
                if (j == 1) {
                    if (i % 2 == 0) {
                        object = new Wall(j, i, Sprite.forest_map5.getFxImage());
                    }  else {
                        object = new Wall(j, i, Sprite.forest_map6.getFxImage());
                    }
                }
                if (j == col - 2) {
                    if (i % 2 == 0) {
                        object = new Wall(j, i, Sprite.forest_map7.getFxImage());
                    }  else {
                        object = new Wall(j, i, Sprite.forest_map8.getFxImage());
                    }
                }
                if (i == 0 && j > 0 && j < col - 1) {
                    object = new Wall(j, i, Sprite.forest_map9.getFxImage());
                }

                if (i == row - 1 && j > 0 && j < col - 1) {
                    if (j % 2 == 0) {
                        object = new Wall(j, i, Sprite.forest_map10.getFxImage());
                    }  else {
                        object = new Wall(j, i, Sprite.forest_map11.getFxImage());
                    }
                }
                if (i == 0 && j == 1) {
                    object = new Wall(j, i, Sprite.forest_map12.getFxImage());
                }
                if (i == 0 && j == col - 2) {
                    object = new Wall(j, i, Sprite.forest_map13.getFxImage());
                }

                isBlocked[i][j] = object.isBlocked();
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
                        break;
                    case CREEPER:
                        movingEntity = new Creeper(j, i, Sprite.ghost_left1.getFxImage());
                        break;
                }
                if (movingEntity == null) {
                    continue;
                }
                isBlocked[i][j] = movingEntity.isBlocked();
                movingEntities.add(movingEntity);
            }
        }

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                Entity movingEntity = null;
                BombermanObject bombermanObject = map[i][j];
                switch (bombermanObject) {
                    case BEEHIVE:
                        movingEntity = new Beehive(j, i, Sprite.beehive.getFxImage());
                        break;
                }
                if (movingEntity == null) {
                    continue;
                }
                movingEntities.add(movingEntity);
            }
        }

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                Entity movingEntity = null;
                BombermanObject bombermanObject = map[i][j];
                switch (bombermanObject) {
                    case BEE:
                        movingEntity = new Bee(j, i, Sprite.bee_left1.getFxImage());
                        break;
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
