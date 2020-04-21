package pong;

import java.awt.*;
import java.util.Random;

public class Ball {
    public int WIDTH;
    public int HEIGHT;

    public int enemyPoint;
    public int playerPoint;

    public double x;
    public double y;

    public double dx;
    public double dy;
    public double speed = 1.2;

    public Ball(int x, int y) {
        this.WIDTH = 3;
        this.HEIGHT = 3;

        this.x = x;
        this.y = y;

        randomAngle();
    }

    private void randomAngle() {
        int angle = new Random().nextInt(120 - 45) + 45;
        this.dx = Math.cos(Math.toRadians(angle));
        this.dy = Math.sin(Math.toRadians(angle));
    }

    public void tick() {

        if (x + (dx * speed) + WIDTH >= Game.WIDTH) {
            dx *= -1;
        } else if (x + (dx * speed) + WIDTH < 0) {
            dx *= -1;
        }

        if (y >= Game.HEIGHT) {
            enemyPoint++;
            x = Game.MIDDLE_WIDTH + 18;
            y = Game.MIDDLE_HEIGHT;
            randomAngle();
            Game.enemy.setPosition(Game.MIDDLE_WIDTH, 0);
            Game.player.setPosition(Game.MIDDLE_WIDTH, Game.HEIGHT - 10);
        } else if (y < 0) {
            playerPoint++;
            x = Game.MIDDLE_WIDTH + 18;
            y = Game.MIDDLE_HEIGHT;
            randomAngle();
            Game.enemy.setPosition(Game.MIDDLE_WIDTH, 0);
            Game.player.setPosition(Game.MIDDLE_WIDTH, Game.HEIGHT - 10);
        }

        Rectangle bounds = new Rectangle((int) (x + (dx * speed)), (int) (y + (dy * speed)), WIDTH, HEIGHT);
        Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.WIDTH, Game.player.HEIGHT);
        Rectangle boundsEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y, Game.enemy.WIDTH, Game.enemy.HEIGHT);

        if (bounds.intersects(boundsPlayer)) {
            randomAngle();
            if (dy > 0) {
                dy *= -1;
            }
        } else if (bounds.intersects(boundsEnemy)) {
            randomAngle();
            if (dy < 0) {
                dy *= -1;
            }
        }

        x += dx * speed;
        y += dy * speed;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int) x, (int) y, WIDTH, HEIGHT);
    }
}
