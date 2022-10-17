package algorithm;

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
}
