package graphics;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {
    public static final Image mouseImg = new Image("/Images/mouse.png");

    public static final int DEFAULT_SIZE = 32;
    public static final int SCALED_SIZE = DEFAULT_SIZE;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;

    public static Sprite nothing = new Sprite(DEFAULT_SIZE, 5, 1, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    /*
    |--------------------------------------------------------------------------
    | Forest map
    |--------------------------------------------------------------------------
     */
    public static Sprite forest_map_grass = new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.forestMapTiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite forest_map1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.forestMapTiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite forest_map2 = new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.forestMapTiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite forest_map3 = new Sprite(DEFAULT_SIZE, 12, 0, SpriteSheet.forestMapTiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite forest_map4 = new Sprite(DEFAULT_SIZE, 12, 1, SpriteSheet.forestMapTiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite forest_map5 = new Sprite(DEFAULT_SIZE, 1, 2, SpriteSheet.forestMapTiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite forest_map6 = new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.forestMapTiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite forest_map7 = new Sprite(DEFAULT_SIZE, 11, 2, SpriteSheet.forestMapTiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite forest_map8 = new Sprite(DEFAULT_SIZE, 11, 1, SpriteSheet.forestMapTiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite forest_map9 = new Sprite(DEFAULT_SIZE, 2, 0, SpriteSheet.forestMapTiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite forest_map10 = new Sprite(DEFAULT_SIZE, 2, 12, SpriteSheet.forestMapTiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite forest_map11 = new Sprite(DEFAULT_SIZE, 1, 12, SpriteSheet.forestMapTiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite forest_map12 = new Sprite(DEFAULT_SIZE, 1, 0, SpriteSheet.forestMapTiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite forest_map13 = new Sprite(DEFAULT_SIZE, 11, 0, SpriteSheet.forestMapTiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    /*
    |--------------------------------------------------------------------------
    | Board sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite grass = new Sprite(DEFAULT_SIZE, 6, 0, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite grass_flower = new Sprite(DEFAULT_SIZE, 6, 1, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite brick = new Sprite(DEFAULT_SIZE, 7, 0, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite wall = new Sprite(DEFAULT_SIZE, 5, 0, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite portal = new Sprite(DEFAULT_SIZE, 4, 0, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    /*
    |--------------------------------------------------------------------------
    | Bomber Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite player_up = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite player_down = new Sprite(DEFAULT_SIZE, 2, 0, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_left = new Sprite(DEFAULT_SIZE, 3, 0, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_right = new Sprite(DEFAULT_SIZE, 1, 0, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_up_1 = new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_up_2 = new Sprite(DEFAULT_SIZE, 0, 2, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_down_1 = new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_down_2 = new Sprite(DEFAULT_SIZE, 2, 2, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_left_1 = new Sprite(DEFAULT_SIZE, 3, 1, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_left_2 = new Sprite(DEFAULT_SIZE, 3, 2, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_right_1 = new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_right_2 = new Sprite(DEFAULT_SIZE, 1, 2, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_dead1 = new Sprite(DEFAULT_SIZE, 4, 2, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_dead2 = new Sprite(DEFAULT_SIZE, 5, 2, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_dead3 = new Sprite(DEFAULT_SIZE, 6, 2, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite shield = new Sprite(DEFAULT_SIZE, 4, 1, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    /*
    |--------------------------------------------------------------------------
    | Character
    |--------------------------------------------------------------------------
     */
    //BALLOM
    public static Sprite balloom_left1 = new Sprite(DEFAULT_SIZE, 9, 0, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite balloom_left2 = new Sprite(DEFAULT_SIZE, 9, 1, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite balloom_left3 = new Sprite(DEFAULT_SIZE, 9, 2, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite balloom_right1 = new Sprite(DEFAULT_SIZE, 10, 0, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite balloom_right2 = new Sprite(DEFAULT_SIZE, 10, 1, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite balloom_right3 = new Sprite(DEFAULT_SIZE, 10, 2, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite balloom_dead = new Sprite(DEFAULT_SIZE, 9, 3, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    //ONEAL
    public static Sprite oneal_left1 = new Sprite(DEFAULT_SIZE, 11, 0, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite oneal_left2 = new Sprite(DEFAULT_SIZE, 11, 1, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite oneal_left3 = new Sprite(DEFAULT_SIZE, 11, 2, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite oneal_right1 = new Sprite(DEFAULT_SIZE, 12, 0, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite oneal_right2 = new Sprite(DEFAULT_SIZE, 12, 1, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite oneal_right3 = new Sprite(DEFAULT_SIZE, 12, 2, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite oneal_dead = new Sprite(DEFAULT_SIZE, 11, 3, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    //Doll
    public static Sprite doll_left1 = new Sprite(DEFAULT_SIZE, 13, 0, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite doll_left2 = new Sprite(DEFAULT_SIZE, 13, 1, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite doll_left3 = new Sprite(DEFAULT_SIZE, 13, 2, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite doll_right1 = new Sprite(DEFAULT_SIZE, 14, 0, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite doll_right2 = new Sprite(DEFAULT_SIZE, 14, 1, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite doll_right3 = new Sprite(DEFAULT_SIZE, 14, 2, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite doll_dead = new Sprite(DEFAULT_SIZE, 13, 3, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    //Minvo
    public static Sprite minvo_left1 = new Sprite(DEFAULT_SIZE, 8, 5, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite minvo_left2 = new Sprite(DEFAULT_SIZE, 8, 6, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite minvo_left3 = new Sprite(DEFAULT_SIZE, 8, 7, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite minvo_right1 = new Sprite(DEFAULT_SIZE, 9, 5, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite minvo_right2 = new Sprite(DEFAULT_SIZE, 9, 6, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite minvo_right3 = new Sprite(DEFAULT_SIZE, 9, 7, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite minvo_dead = new Sprite(DEFAULT_SIZE, 8, 8, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    //Kondoria
    public static Sprite kondoria_left1 = new Sprite(DEFAULT_SIZE, 10, 5, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite kondoria_left2 = new Sprite(DEFAULT_SIZE, 10, 6, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite kondoria_left3 = new Sprite(DEFAULT_SIZE, 10, 7, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite kondoria_right1 = new Sprite(DEFAULT_SIZE, 11, 5, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite kondoria_right2 = new Sprite(DEFAULT_SIZE, 11, 6, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite kondoria_right3 = new Sprite(DEFAULT_SIZE, 11, 7, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite kondoria_dead = new Sprite(DEFAULT_SIZE, 10, 8, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    //Ghost
    public static Sprite ghost_left1 = new Sprite(DEFAULT_SIZE, 8, 5, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite ghost_left2 = new Sprite(DEFAULT_SIZE, 8, 6, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite ghost_left3 = new Sprite(DEFAULT_SIZE, 8, 7, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite ghost_right1 = new Sprite(DEFAULT_SIZE, 9, 5, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite ghost_right2 = new Sprite(DEFAULT_SIZE, 9, 6, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite ghost_right3 = new Sprite(DEFAULT_SIZE, 9, 7, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite ghost_dead = new Sprite(DEFAULT_SIZE, 8, 8, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    //Creeper
    public static Sprite creeper_left1 = new Sprite(DEFAULT_SIZE, 8, 9, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite creeper_left2 = new Sprite(DEFAULT_SIZE, 8, 10, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite creeper_left3 = new Sprite(DEFAULT_SIZE, 8, 11, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite creeper_right1 = new Sprite(DEFAULT_SIZE, 9, 9, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite creeper_right2 = new Sprite(DEFAULT_SIZE, 9, 10, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite creeper_right3 = new Sprite(DEFAULT_SIZE, 9, 11, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite creeper_dead = new Sprite(DEFAULT_SIZE, 8, 12, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    //Bee
    public static Sprite bee_left1 = new Sprite(DEFAULT_SIZE, 10, 9, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite bee_left2 = new Sprite(DEFAULT_SIZE, 10, 10, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite bee_left3 = new Sprite(DEFAULT_SIZE, 10, 11, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite bee_right1 = new Sprite(DEFAULT_SIZE, 11, 9, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite bee_right2 = new Sprite(DEFAULT_SIZE, 11, 10, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite bee_right3 = new Sprite(DEFAULT_SIZE, 11, 11, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite bee_dead = new Sprite(DEFAULT_SIZE, 10, 12, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    //Beehive
    public static Sprite beehive = new Sprite(DEFAULT_SIZE, 6, 12, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    //DeeDee
    public static Sprite deedee_left = new Sprite(DEFAULT_SIZE, 12, 9, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite deedee_right = new Sprite(DEFAULT_SIZE, 13, 9, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite deedee_left_eat = new Sprite(DEFAULT_SIZE, 12, 10, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite deedee_right_eat = new Sprite(DEFAULT_SIZE, 13, 10, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite deedee_left_chew1 = new Sprite(DEFAULT_SIZE, 12, 11, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite deedee_left_chew2 = new Sprite(DEFAULT_SIZE, 14, 9, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite deedee_right_chew1 = new Sprite(DEFAULT_SIZE, 13, 11, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite deedee_right_chew2 = new Sprite(DEFAULT_SIZE, 15, 9, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite deedee_left_ble1 = new Sprite(DEFAULT_SIZE, 14, 10, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite deedee_left_ble2 = new Sprite(DEFAULT_SIZE, 14, 11, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite deedee_right_ble1 = new Sprite(DEFAULT_SIZE, 15, 10, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite deedee_right_ble2 = new Sprite(DEFAULT_SIZE, 15, 11, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite deedee_dead = new Sprite(DEFAULT_SIZE, 12, 12, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    //DEATH
    public static Sprite mob_dead_red1 = new Sprite(DEFAULT_SIZE, 0, 14, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_red2 = new Sprite(DEFAULT_SIZE, 0, 15, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_blue1 = new Sprite(DEFAULT_SIZE, 1, 14, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_blue2 = new Sprite(DEFAULT_SIZE, 1, 15, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_gray1 = new Sprite(DEFAULT_SIZE, 2, 14, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_gray2 = new Sprite(DEFAULT_SIZE, 2, 15, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_darkred1 = new Sprite(DEFAULT_SIZE, 3, 14, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_darkred2 = new Sprite(DEFAULT_SIZE, 3, 15, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_bee1 = new Sprite(DEFAULT_SIZE, 4, 14, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_bee2 = new Sprite(DEFAULT_SIZE, 4, 15, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_beehive1 = new Sprite(DEFAULT_SIZE, 5, 14, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_beehive2 = new Sprite(DEFAULT_SIZE, 5, 15, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_creeper1 = new Sprite(DEFAULT_SIZE, 6, 14, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_creeper2 = new Sprite(DEFAULT_SIZE, 6, 15, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_deedee1 = new Sprite(DEFAULT_SIZE, 7, 14, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite mob_dead_deedee2 = new Sprite(DEFAULT_SIZE, 7, 15, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    /*
    |--------------------------------------------------------------------------
    | Bomb Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb = new Sprite(DEFAULT_SIZE, 0, 3, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite bomb_1 = new Sprite(DEFAULT_SIZE, 1, 3, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite bomb_2 = new Sprite(DEFAULT_SIZE, 2, 3, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite bomb_enemy = new Sprite(DEFAULT_SIZE, 0, 4, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite bomb_enemy_1 = new Sprite(DEFAULT_SIZE, 1, 4, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite bomb_enemy_2 = new Sprite(DEFAULT_SIZE, 2, 4, SpriteSheet.tiles, DEFAULT_SIZE,
            DEFAULT_SIZE);
    /*
    |--------------------------------------------------------------------------
    | FlameSegment Enemy Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb_exploded_enemy = new Sprite(DEFAULT_SIZE, 4, 5, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite bomb_exploded_enemy1 = new Sprite(DEFAULT_SIZE, 4, 6, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite bomb_exploded_enemy2 = new Sprite(DEFAULT_SIZE, 4, 7, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical_enemy = new Sprite(DEFAULT_SIZE, 5, 6, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite explosion_vertical_enemy1 = new Sprite(DEFAULT_SIZE, 6, 6, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite explosion_vertical_enemy2 = new Sprite(DEFAULT_SIZE, 7, 6, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite explosion_horizontal_enemy = new Sprite(DEFAULT_SIZE, 5, 8, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite explosion_horizontal_enemy1 = new Sprite(DEFAULT_SIZE, 5, 9, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite explosion_horizontal_enemy2 = new Sprite(DEFAULT_SIZE, 5, 10, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite explosion_horizontal_left_last_enemy = new Sprite(DEFAULT_SIZE, 4, 8,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_horizontal_left_last_enemy1 = new Sprite(DEFAULT_SIZE, 4, 9,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_horizontal_left_last_enemy2 = new Sprite(DEFAULT_SIZE, 4, 10,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_horizontal_right_last_enemy = new Sprite(DEFAULT_SIZE, 6, 8,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_horizontal_right_last_enemy1 = new Sprite(DEFAULT_SIZE, 6, 9,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_horizontal_right_last_enemy2 = new Sprite(DEFAULT_SIZE, 6, 10,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical_top_last_enemy = new Sprite(DEFAULT_SIZE, 5, 5,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical_top_last_enemy1 = new Sprite(DEFAULT_SIZE, 6, 5,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical_top_last_enemy2 = new Sprite(DEFAULT_SIZE, 7, 5,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical_down_last_enemy = new Sprite(DEFAULT_SIZE, 5, 7,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical_down_last_enemy1 = new Sprite(DEFAULT_SIZE, 6, 7,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical_down_last_enemy2 = new Sprite(DEFAULT_SIZE, 7, 7,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb_exploded = new Sprite(DEFAULT_SIZE, 0, 5, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite bomb_exploded1 = new Sprite(DEFAULT_SIZE, 0, 6, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite bomb_exploded2 = new Sprite(DEFAULT_SIZE, 0, 7, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical = new Sprite(DEFAULT_SIZE, 1, 6, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite explosion_vertical1 = new Sprite(DEFAULT_SIZE, 2, 6, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite explosion_vertical2 = new Sprite(DEFAULT_SIZE, 3, 6, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite explosion_horizontal = new Sprite(DEFAULT_SIZE, 1, 8, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite explosion_horizontal1 = new Sprite(DEFAULT_SIZE, 1, 9, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite explosion_horizontal2 = new Sprite(DEFAULT_SIZE, 1, 10, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite explosion_horizontal_left_last = new Sprite(DEFAULT_SIZE, 0, 8,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_horizontal_left_last1 = new Sprite(DEFAULT_SIZE, 0, 9,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_horizontal_left_last2 = new Sprite(DEFAULT_SIZE, 0, 10,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_horizontal_right_last = new Sprite(DEFAULT_SIZE, 2, 8,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_horizontal_right_last1 = new Sprite(DEFAULT_SIZE, 2, 9,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_horizontal_right_last2 = new Sprite(DEFAULT_SIZE, 2, 10,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical_top_last = new Sprite(DEFAULT_SIZE, 1, 5,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical_top_last1 = new Sprite(DEFAULT_SIZE, 2, 5,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical_top_last2 = new Sprite(DEFAULT_SIZE, 3, 5,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical_down_last = new Sprite(DEFAULT_SIZE, 1, 7,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical_down_last1 = new Sprite(DEFAULT_SIZE, 2, 7,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite explosion_vertical_down_last2 = new Sprite(DEFAULT_SIZE, 3, 7,
            SpriteSheet.tiles, DEFAULT_SIZE, DEFAULT_SIZE);
    /*
    |--------------------------------------------------------------------------
    | Brick FlameSegment
    |--------------------------------------------------------------------------
     */
    public static Sprite brick_exploded = new Sprite(DEFAULT_SIZE, 7, 1, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite brick_exploded1 = new Sprite(DEFAULT_SIZE, 7, 2, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite brick_exploded2 = new Sprite(DEFAULT_SIZE, 7, 3, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    public static Sprite powerup_bombs = new Sprite(DEFAULT_SIZE, 0, 11, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite powerup_flames = new Sprite(DEFAULT_SIZE, 1, 11, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite powerup_speed = new Sprite(DEFAULT_SIZE, 2, 11, SpriteSheet.tiles,
            DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite powerup_wallpass = new Sprite(DEFAULT_SIZE, 3, 10, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite powerup_detonator = new Sprite(DEFAULT_SIZE, 4, 10, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite powerup_bombpass = new Sprite(DEFAULT_SIZE, 5, 10, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public static Sprite powerup_flamepass = new Sprite(DEFAULT_SIZE, 6, 10, SpriteSheet.tiles,
            DEFAULT_SIZE,
            DEFAULT_SIZE);
    public final int SIZE;
    public int[] _pixels;
    protected int _realWidth;
    protected int _realHeight;
    private int _x, _y;
    private SpriteSheet _sheet;

    public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        _x = x * SIZE;
        _y = y * SIZE;
        _sheet = sheet;
        _realWidth = rw;
        _realHeight = rh;
        load();
    }

    public Sprite(int size, int color) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
        int calc = animate % time;
        int diff = time / 3;

        if (calc < diff) {
            return normal;
        }

        if (calc < diff * 2) {
            return x1;
        }

        return x2;
    }

    public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
        int diff = time / 2;
        return (animate % time > diff) ? x1 : x2;
    }

    private void setColor(int color) {
        for (int i = 0; i < _pixels.length; i++) {
            _pixels[i] = color;
        }
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                _pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
            }
        }
    }

    public int getSize() {
        return SIZE;
    }

    public int getPixel(int i) {
        return _pixels[i];
    }

    public Image getFxImage() {
        WritableImage wr = new WritableImage(SIZE, SIZE);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (_pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                } else {
                    pw.setArgb(x, y, _pixels[x + y * SIZE]);
                }
            }
        }
        Image input = new ImageView(wr).getImage();
        return resample(input, SCALED_SIZE / DEFAULT_SIZE);
    }

    private Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(
                W * S,
                H * S
        );

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                final int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(x * S + dx, y * S + dy, argb);
                    }
                }
            }
        }

        return output;
    }
}
