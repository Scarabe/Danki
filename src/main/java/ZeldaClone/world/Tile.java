package ZeldaClone.world;

import ZeldaClone.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private int x;
    private int y;
    private BufferedImage sprite;

    public Tile(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void render(Graphics g){
        g.drawImage(sprite, x, y, null);
    }
}
