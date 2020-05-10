package ZeldaClone.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    private int x;
    private int y;
    private int width;
    private int heigth;
    private BufferedImage sprite;

    public Entity(final int x, final int y, final int width, final int heith, final BufferedImage sprite){
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heith;
        this.sprite = sprite;
    }

    public void render(final Graphics graphics){
        graphics.drawImage(sprite, this.getX(), this.getY(),null);
    }

    public void tick(){

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }
}
