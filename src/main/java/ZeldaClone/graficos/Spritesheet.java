package ZeldaClone.graficos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheet {

    private BufferedImage spirteSheet;

    public Spritesheet(final String path){
        try {
            spirteSheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite(int x, int y, int w, int h){
        return spirteSheet.getSubimage(x, y, w, h);
    }
}
