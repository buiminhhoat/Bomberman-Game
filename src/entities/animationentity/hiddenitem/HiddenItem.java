package entities.animationentity.hiddenitem;

import entities.Entity;
import entities.animationentity.AnimationEntity;
import javafx.scene.image.Image;

public class HiddenItem extends AnimationEntity {

    private static final int ITEM_SCORE = 50;
    protected boolean pickUp;

    public HiddenItem() {
        isBlocked = true;
        score = ITEM_SCORE;
    }

    public HiddenItem(int x, int y, Image img) {
        super(x, y, img);
        isBlocked = false;
        score = ITEM_SCORE;
    }

    public boolean pickUp(Entity entity) {
        if (entity.getXPixel() == this.getXPixel()
            && entity.getYPixel() == this.getYPixel()) {
            pickUp = true;
            return true;
        }
        return false;
    }

    public boolean isPickUp() {
        return pickUp;
    }

    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
    }

    public void featureItem(Entity entity) {

    }
}
