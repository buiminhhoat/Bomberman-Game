package entities.block;

import entities.Entity;
import javafx.scene.image.Image;

public class Wall extends Entity {

    public Wall() {
        isBlocked = true;
    }

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        isBlocked = true;
    }

    @Override
    public void update() {

    }
}
