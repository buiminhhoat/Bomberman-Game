package entities.animationentity.hiddenitem;

import control.SoundManager;
import entities.Entity;
import entities.animationentity.movingentity.MovingEntity;
import entities.animationentity.movingentity.bomber.Bomber;
import enumeration.Chunk;
import javafx.scene.image.Image;

public class FlameItem extends HiddenItem {
    public FlameItem() {
        isBlocked = false;
    }

    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
        isBlocked = false;
    }

    @Override
    public void featureItem(Entity entity) {
        if (entity instanceof Bomber) {
            SoundManager.playChunk(Chunk.PICKUP_ITEM);
            ((MovingEntity) entity).setLengthExplosionOfBomb(
                    ((MovingEntity) entity).getLengthExplosionOfBomb() + 1);
        }
    }
}
