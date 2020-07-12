package ZeldaClone.world;

import ZeldaClone.Game;

import java.awt.image.BufferedImage;

public class Floor extends Tile{
    public static final BufferedImage SPRITE = Game.spritesheet.getSprite(0, 0, 16, 16);

    public Floor(int x, int y) {
        super(x, y, SPRITE);
    }
}
