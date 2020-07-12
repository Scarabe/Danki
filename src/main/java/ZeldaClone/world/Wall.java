package ZeldaClone.world;

import ZeldaClone.Game;

import java.awt.image.BufferedImage;

public class Wall extends Tile{
    public static final BufferedImage SPRITE = Game.spritesheet.getSprite(16, 0, 16, 16);

    public Wall(int x, int y) {
        super(x, y, SPRITE);
    }

}
