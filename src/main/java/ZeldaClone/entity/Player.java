package ZeldaClone.entity;

import java.awt.image.BufferedImage;

public class Player extends Entity {
    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;

    private final int speed = 1;

    public Player(int x, int y, int width, int heith, BufferedImage sprite) {
        super(x, y, width, heith, sprite);
    }

    public void tick(){
        if(right){
            setX(getX()+speed);
        }else if(left){
            setX(getX()-speed);
        }

        if(up){
            setY(getY()-speed);
        }else if(down){
            setY(getY()+speed);
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
