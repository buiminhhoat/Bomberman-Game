package graphics;

public abstract class Camera {

    public static int x;
    public static int y;

    public Camera() {

    }

    public static int getX() {
        return x;
    }

    public static void setX(int X) {
        x = X;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int Y) {
        y = Y;
    }
}
