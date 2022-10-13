package entities;

import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {

    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int xPixel;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int yPixel;

    protected boolean isBlocked;

    protected Image img;

    public Entity() {
        isBlocked = false;
    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int x, int y, Image img) {
        this.xPixel = x * Sprite.SCALED_SIZE;
        this.yPixel = y * Sprite.SCALED_SIZE;
        this.img = img;
        isBlocked = false;
    }

    public int getXPixel() {
        return xPixel;
    }

    public void setXPixel(int xPixel) {
        this.xPixel = xPixel;
    }

    public int getYPixel() {
        return yPixel;
    }

    public void setYPixel(int yPixel) {
        this.yPixel = yPixel;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, xPixel, yPixel);
    }

    public abstract void update();
}
