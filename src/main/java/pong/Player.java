package pong;

import java.awt.*;

public class Player {
    public int WIDTH;
    public int HEIGHT;

    public boolean right;
    public boolean left;

    public int x;
    public int y;

    public Player(int x, int y) {
        this.WIDTH = 40;
        this.HEIGHT = 10;
        setPosition(x, y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void tick() {
        if (x + WIDTH < Game.WIDTH && right) {
            x++;
        }else if (x > 0 && left) {
            x--;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, WIDTH, HEIGHT);

        g.setFont(new Font("Arial", Font.BOLD, 13));
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(Game.ball.playerPoint), 190,118);
    }
}
