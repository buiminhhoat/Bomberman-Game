package entities.animationentity.hiddenitem;

import control.SoundManager;
import entities.Entity;
import entities.animationentity.movingentity.MovingEntity;
import entities.animationentity.movingentity.bomber.Bomber;
import enumeration.Chunk;
import javafx.scene.image.Image;

public class SpeedItem extends HiddenItem {
    private static final int LEVEL_SPEED_UP_ID = 2;

    public SpeedItem() {
        isBlocked = false;
    }

    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
        isBlocked = false;
    }

    @Override
    public void featureItem(Entity entity) {
        if (entity instanceof Bomber) {
            SoundManager.playChunk(Chunk.PICKUP_ITEM);
            ((MovingEntity) entity).setLevelSpeed(LEVEL_SPEED[LEVEL_SPEED_UP_ID]);
        }
    }
}
