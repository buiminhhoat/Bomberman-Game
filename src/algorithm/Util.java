package algorithm;

import control.BombermanGame;
import java.awt.MouseInfo;
import javafx.scene.image.ImageView;

public abstract class Util {

    public static final int FIX_TITLE_X = 8;
    public static final int FIX_TITLE_Y = 30;

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
        double mouseX = getMouseX();
        double mouseY = getMouseY();
        x += BombermanGame.stage.getX() + FIX_TITLE_X;
        y += BombermanGame.stage.getY() + FIX_TITLE_Y;

        if (mouseX < x || mouseX > x + w || mouseY < y || mouseY > y + h) {
            return false;
        }
        return true;
    }

    public static boolean checkMouseInImageView(ImageView imageView) {
        return checkMouseInRect(imageView.getX(), imageView.getY(),
            imageView.getFitWidth(), imageView.getFitHeight());
    }

    public static double getMouseX() {
        return MouseInfo.getPointerInfo().getLocation().getX();
    }

    public static double getMouseY() {
        return MouseInfo.getPointerInfo().getLocation().getY();
    }
}
