package entities.dynamicentity.enemies;

import entities.dynamicentity.bomb.Bomb;
import entities.dynamicentity.bomber.Bomber;
import gamemap.GameMap;
import java.util.List;
import javafx.scene.image.Image;

public class Creeper extends Enemies {
    public Creeper() {
    }

    public Creeper(int x, int y, Image img) {
        super(x, y, img);
        numberBombs = 1;
    }

    public void chooseDirection(GameMap gameMap) {
        super.chooseDirection(gameMap);
    }
}
