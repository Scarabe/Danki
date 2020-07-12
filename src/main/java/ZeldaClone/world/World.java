package ZeldaClone.world;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World {
    private Tile[]  tiles;

    public static int width;
    public static int height;

    public World(String path){
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));
            width = map.getWidth();
            height = map.getHeight();

            int[] pixels = new int[width * height];
            tiles = new Tile[pixels.length];

            map.getRGB(0,0, width,height, pixels, 0, map.getWidth());

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    switch(pixels[x + (y * width)]){
                        case 0xFF000000:
                            tiles[x+ (y * width)] = new Floor(x*16, y*16);
                            break;
                        case 0xFFFFFFFF:
                            tiles[x+ (y * width)] = new Wall(x*16, y*16);
                            break;
                        case 0xFF0026FF:
                            tiles[x+ (y * width)] = new Floor(x*16, y*16);
                            break;
                        default:
                            tiles[x+ (y * width)] = new Floor(x*16, y*16);
                            break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = tiles[x + (y*width)];
                tile.render(g);
            }
        }
    }
}
