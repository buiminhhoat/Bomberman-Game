package entities.dynamicentity.enemies;

import entities.dynamicentity.bomb.Bomb;
import entities.dynamicentity.bomber.Bomber;
import gamemap.GameMap;
import java.util.List;
import javafx.scene.image.Image;

public class Creeper extends Enemies {
    public static final int DEFAULT_NUMBER_BOMBS = 1;
    public static final int DEFAULT_LENGTH_EXPLOSION_OF_BOMB = 1;

    private int numberBombs = DEFAULT_NUMBER_BOMBS;

    private int lengthExplosionOfBomb = DEFAULT_LENGTH_EXPLOSION_OF_BOMB;
    public Creeper() {
    }

    public Creeper(int x, int y, Image img) {
        super(x, y, img);
    }

//    public void createBomb(List<Bomb> listBombs, GameMap gameMap) {
//        for (Bomb bomb : listBombs) {
//            if (!bomb.getAnimations() && bomb.getBomber() instanceof Creeper) {
//                int numBomb = ((Creeper) this).getNumberBombs();
//                ((Creeper) bomberman).setNumberBombs(numBomb + 1);
//            }
//        }
//            Bomb bomb = new Bomb(this, gameMap);
//            listBombs.add(bomb);
//
//    }
    public void chooseDirection(GameMap gameMap) {
        super.chooseDirection(gameMap);
    }
}
