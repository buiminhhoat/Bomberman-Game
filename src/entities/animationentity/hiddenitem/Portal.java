package entities.animationentity.hiddenitem;

import entities.Entity;
import entities.animationentity.movingentity.bomber.Bomber;
import entities.animationentity.movingentity.enemies.Enemies;
import gamemap.GameMap;
import java.util.List;
import javafx.scene.image.Image;

public class Portal extends HiddenItem {
    public Portal() {
        isBlocked = false;
    }

    public Portal(int x, int y, Image img) {
        super(x, y, img);
        isBlocked = false;
    }

    public void featureItem(Entity entity, GameMap gameMap) {
        List<Entity> entityList = gameMap.getMovingEntities();
        int count = 0;
        for (Entity entity1: entityList) {
            if (entity1 instanceof Enemies) {
                ++count;
            }
        }
        if (count == 0) {
            ((Bomber) entity).setWin(true);
        }
    }
}
