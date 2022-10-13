package entities.animationentity.hiddenitem;

import entities.Entity;
import entities.animationentity.movingentity.MovingEntity;
import entities.animationentity.movingentity.bomber.Bomber;
import javafx.scene.image.Image;

public class BombItem extends HiddenItem {
    public BombItem() {
        isBlocked = false;
    }

    public BombItem(int x, int y, Image img) {
        super(x, y, img);
        isBlocked = false;
    }

    @Override
    public void featureItem(Entity entity) {
        if (entity instanceof Bomber) {
            ((MovingEntity) entity).setNumberBombs(((MovingEntity) entity).getNumberBombs() + 1);
        }
    }
}
