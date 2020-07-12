package ZeldaClone.entity;

import ZeldaClone.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;

    private final BufferedImage[] leftPlayer = new BufferedImage[4];
    private final BufferedImage[] rightPlayer = new BufferedImage[4];
    private final BufferedImage[] frontPlayer = new BufferedImage[4];
    private final BufferedImage[] backPlayer = new BufferedImage[4];

    private int frames = 0;
    private int maxFrames = 5;
    private int index = 0;
    private int maxIndex = 3;
    private boolean moved = false;

    private final int speed = 1;

    public Player(int x, int y, int width, int heith, BufferedImage sprite) {
        super(x, y, width, heith, sprite);

        for(int i = 0; i < 4; i++){
            leftPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 0, 16, 16);
            rightPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 16, 16, 16);
            backPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 32, 16, 16);
            frontPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 48, 16, 16);
        }
    }

    public void tick(){
        moved = false;

        if(right){
            moved = true;
            setX(getX()+speed);
        }else if(left){
            moved = true;
            setX(getX()-speed);
        }

        if(up){
            moved = true;
            setY(getY()-speed);
        }else if(down){
            moved = true;
            setY(getY()+speed);
        }

        if(moved){
            frames ++;
            if(frames == maxFrames){
                frames = 0;
                index ++ ;
                if(index > maxIndex){
                    index = 0;
                }
            }
        }
    }

    public void render(Graphics g){
        if(right){
            g.drawImage(rightPlayer[index], this.getX(), this.getY(), null);
        }else if(left){
            g.drawImage(leftPlayer[index], this.getX(), this.getY(), null);
        }else if(down){
            g.drawImage(frontPlayer[index], this.getX(), this.getY(), null);
        }else if(up){
            g.drawImage(backPlayer[index], this.getX(), this.getY(), null);
        }else{
            g.drawImage(frontPlayer[0], this.getX(), this.getY(), null);
        }
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
