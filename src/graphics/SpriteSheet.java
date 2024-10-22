package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Tất cả sprite (hình ảnh game) được lưu trữ vào một ảnh duy nhất Class này giúp lấy ra các sprite
 * riêng từ 1 ảnh chung duy nhất đó
 */
public class SpriteSheet {

    public static SpriteSheet tiles = new SpriteSheet("/textures/classic1.png", 512);
    public static SpriteSheet forestMapTiles = new SpriteSheet("/textures/forest_map.png", 416);
    public final int SIZE;
    public int[] _pixels;
    public BufferedImage image;
    private String _path;

    public SpriteSheet(String path, int size) {
        _path = path;
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        try {
            URL a = SpriteSheet.class.getResource(_path);
            image = ImageIO.read(a);
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, _pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
