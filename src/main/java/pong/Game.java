package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {
    public static int WIDTH = 200;
    public static int HEIGHT = 120;
    public static int MIDDLE_WIDTH = 80;
    public static int MIDDLE_HEIGHT = 60;
    public static int SCALE = 3;

    public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public static Player player;
    public static Enemy enemy;
    public static Ball ball;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.addKeyListener(this);

        player = new Player(MIDDLE_WIDTH, HEIGHT - 10);
        enemy = new Enemy(MIDDLE_WIDTH,0);
        ball = new Ball(MIDDLE_WIDTH+18, MIDDLE_HEIGHT);
    }


    public static void main(String... args) {
        Game game = new Game();

        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);

        new Thread(game).start();
    }

    public void tick() {
        player.tick();
        enemy.tick();
        ball.tick();
    }

    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = layer.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,WIDTH, HEIGHT);

        player.render(graphics);
        enemy.render(graphics);
        ball.render(graphics);

        graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

        bufferStrategy.show();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 / 60);
                render();
                tick();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
}
