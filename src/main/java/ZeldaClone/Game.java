package ZeldaClone;

import ZeldaClone.entity.Entity;
import ZeldaClone.entity.Player;
import graficos.Spritesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game extends Canvas implements Runnable, KeyListener {
    public static JFrame jframe;
    private final int WIDTH = 240;
    private final int HEIGHT = 160;
    private final int SCALE = 3;
    private BufferedImage image;

    private Player player;
    private boolean isRunning;
    private Thread thread;

    public List<Entity> entities;
    public Spritesheet spritesheet;

    public Game(){
        addKeyListener(this);

        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        jframe = new JFrame("Meu jogo");
        initFrame();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        entities = new ArrayList<>();
        spritesheet = new Spritesheet("/spritesheetZelda.png");
        player = new Player(0,0,16,16, spritesheet.getSprite(32, 0, 16,16));
        entities.add(player);
    }

    private void initFrame() {
        jframe.add(this);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        jframe.pack();
    }

    public synchronized void Start(){
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void Stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isRunning = false;
    }

    public void tick() {
        entities.forEach(Entity::tick);
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(Objects.isNull(bs)){
            this.createBufferStrategy(3);

        }else{
            Graphics g = image.getGraphics();

            g.setColor(new Color(0, 0, 0));
            g.fillRect(0 , 0 , WIDTH, HEIGHT);

            g.dispose();
            g = bs.getDrawGraphics();
            g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);

            for (Entity e : entities) {
                e.render(g);
            }

            bs.show();
        }
    }

    @Override
    public void run() {
        int frames = 0;

        long lastTime = System.nanoTime();

        double amounthOfTicks = 60.0;
        double ns = 1000000000/amounthOfTicks;
        double delta = 0;
        double timer = System.currentTimeMillis();

        while(isRunning){
            long now = System.nanoTime();
            delta+= (now - lastTime)/ns;
            lastTime= now;

            if(delta>=1){
                tick();
                render();
                frames++;
                delta--;
            }

            if(System.currentTimeMillis() - timer >= 1000){
                System.out.println("FPS: "+frames);
                frames=  0;
                timer+= 1000;
            }
        }
        Stop();
    }

    public static void main(String[] args){
        Game game = new Game();
        game.Start();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT || keyEvent.getKeyCode() == KeyEvent.VK_D){
            player.setRight(true);
        }else if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT || keyEvent.getKeyCode() == KeyEvent.VK_A){
            player.setLeft(true);
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_UP || keyEvent.getKeyCode() == KeyEvent.VK_W){
            player.setUp(true);
        }else if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN || keyEvent.getKeyCode() == KeyEvent.VK_S){
            player.setDown(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT || keyEvent.getKeyCode() == KeyEvent.VK_D){
            player.setRight(false);
        }else if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT || keyEvent.getKeyCode() == KeyEvent.VK_A){
            player.setLeft(false);
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_UP || keyEvent.getKeyCode() == KeyEvent.VK_W){
            player.setUp(false);
        }else if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN || keyEvent.getKeyCode() == KeyEvent.VK_S){
            player.setDown(false);
        }
    }
}
