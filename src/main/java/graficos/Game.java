package graficos;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Game extends Canvas implements Runnable{
    public static JFrame jframe;
    private final int WIDTH = 240;
    private final int HEIGHT = 160;
    private final int SCALE = 3;
    private BufferedImage image;

    private Spritesheet spritesheet;
    private BufferedImage[] player;
    private int frames = 0;
    private int maxFrames = 20;
    private int curAnimation = 0;
    private int maxAnimation = 2;

    private boolean isRunning;
    private Thread thread;

    public Game(){
        spritesheet= new Spritesheet("spritesheet.png");
        player = new BufferedImage[3];

        player[0]= spritesheet.getSprite(0,0,16,16);
        player[1]= spritesheet.getSprite(16,0,16,16);
        player[2]= spritesheet.getSprite(32,0,16,16);

        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        jframe = new JFrame("Meu jogo");
        initFrame();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
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
        frames++;
        if (frames > maxFrames) {
            frames = 0;
            curAnimation++;
            if (curAnimation > maxAnimation){
                curAnimation = 0;
            }
        }
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(Objects.isNull(bs)){
            this.createBufferStrategy(3);

        }else{
            Graphics g = image.getGraphics();

            g.setColor(new Color(255,255,255));
            g.fillRect(0 , 0 , WIDTH, HEIGHT);

            /*g.setColor(Color.RED);
            g.fillRect(0 , 0 , 50, 50);

            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.WHITE);
            g.drawString("Batata", 20,20);

            Graphics2D g2 = (Graphics2D) g;
            g2.rotate(Math.toRadians(90), 20, 20);

            */

            g.drawImage(player[curAnimation], 20,20,null);

            g.dispose();
            g = bs.getDrawGraphics();
            g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);

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
}
