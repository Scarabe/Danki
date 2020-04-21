package pong;

import java.awt.*;

public class Enemy {
    public int WIDTH;
    public int HEIGHT;

    public double x;
    public double y;

    public Enemy(int x, int y) {
        this.WIDTH = 40;
        this.HEIGHT = 10;

        setPosition(x, y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void tick() {
        if (Game.ball.y-200 < Game.HEIGHT / 3) {
            double newX = x + (Game.ball.x - x - 6) * 0.04;
            if (newX + WIDTH - 1 < Game.WIDTH && newX > 0) {
                x = newX;
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, WIDTH, HEIGHT);

        g.setFont(new Font("Arial", Font.BOLD, 13));
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(Game.ball.enemyPoint), 2, 13);
    }
}
