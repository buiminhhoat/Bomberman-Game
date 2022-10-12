package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import graphics.Sprite;

public abstract class Entity {

    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int xPixel;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int yPixel;

    protected Image img;

    public Entity() {

    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int x, int y, Image img) {
        this.xPixel = x * Sprite.SCALED_SIZE;
        this.yPixel = y * Sprite.SCALED_SIZE;
        this.img = img;
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

    public void render(GraphicsContext gc) {
        gc.drawImage(img, xPixel, yPixel);
    }

    public abstract void update();
}
