package algorithm;

import control.BombermanGame;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Util {
    public static boolean checkRectIntersect(int x1, int y1, int w1, int h1,
                                             int x2, int y2, int w2, int h2) {
        if (w1 <= 0 || h1 <= 0 || w2 <= 0 || h2 <= 0) {
            return false;
        }
        boolean onOx = (x1 <= x2 + w2 - 1 && x2 <= x1 + w1 - 1);
        boolean onOy = (y1 <= y2 + h2 - 1 && y2 <= y1 + h1 - 1);
        return (onOx && onOy);
    }

    public static boolean checkMouseInRect(double x, double y, double w, double h) {
        PointerInfo inf = MouseInfo.getPointerInfo();
        Point p = inf.getLocation();
        double mouseX = p.getX();
        double mouseY = p.getY();
        x += BombermanGame.stage.getX() + 8;
        y += BombermanGame.stage.getY() + 30;

        if (mouseX < x || mouseX > x + w || mouseY < y || mouseY > y + h) {
            return false;
        }
        return true;
    }

    public static boolean checkMouseInImageView(ImageView imageView) {
        return checkMouseInRect(imageView.getX(), imageView.getY(),
                imageView.getFitWidth(), imageView.getFitHeight());
    }
}
