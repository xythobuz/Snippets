/*
 * Conways Game of Life
 * By: Thomas Buck <xythobuz@me.com>
 * Visit: www.xythobuz.org
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class GoL extends javax.swing.JPanel {
    private Image src = null;
    public static int width = 300;
    public static int height = 300;
    private int data[][];

    public static final int DEAD = 1;
    public static final int ALIVE = 0;

    public GoL() {
        data = new int[width][height];
        fillRandom();
        new Worker().execute();
    }

    public void fillRandom() {
        Random r = new Random(System.nanoTime());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // data[i][j] = r.nextInt(10);
                // if (data[i][j] >= 8) {
                //     data[i][j] = ALIVE;
                // } else {
                //     data[i][j] = DEAD;
                // }
                data[i][j] = r.nextInt(2);
            }
        }
    }

    private void drawFromTo(int d[][], int e[]) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (d[i][j] == ALIVE) {
                    e[i + (width * j)] = Color.black.getRGB();
                } else {
                    e[i + (width * j)] = Color.white.getRGB();
                }
            }
        }
    }

    private int neighbours(int x, int y) {
        int count = 0;
        for (int i = (x - 1); i <= (x + 1); i++) {
            for (int j = (y - 1); j <= (y + 1); j++) {
                if ((i >= 0) && (j >= 0) && (i < width) && (j < height)) {
                    if ((i != x) && (j != y)) {
                        if (data[i][j] != DEAD) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private void nextGeneration() {
        int next[][] = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int n = neighbours(i, j);
                if (data[i][j] == DEAD) {
                    // DEAD
                    if (n == 3) {
                        next[i][j] = ALIVE;
                    } else {
                        next[i][j] = DEAD;
                    }
                } else {
                    // ALIVE
                    if ((n == 2) || (n == 3)) {
                        next[i][j] = ALIVE;
                    } else {
                        next[i][j] = DEAD;
                    }
                }
            }
        }
        data = next;
    }

    private int[] drawView(int[] d) {
        nextGeneration();
        drawFromTo(data, d);
        return d;
    }

    class Worker extends SwingWorker<Void, Image>{
        private long lastFrame = System.currentTimeMillis();
        @Override
        protected void process(java.util.List<Image> chunks){
            for (Image bufferedImage : chunks){
                src = bufferedImage;
                repaint();
            }
        }

        @Override
        public Void doInBackground() throws Exception {
            int[] mem = new int[width * height];
            while(true) {
                if (System.currentTimeMillis() >= (lastFrame + 200)) {
                    mem = drawView(mem);
                    Image img = createImage(new MemoryImageSource(width, height, mem, 0, width));
                    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2 = bi.createGraphics();
                    g2.drawImage(img, 0, 0, null);
                    g2.dispose();
                    publish(bi);
                    lastFrame = System.currentTimeMillis();
                }
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (src != null) g.drawImage(src, 0, 0, this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                JFrame jf = new JFrame();
                jf.getContentPane().add(new GoL(), BorderLayout.CENTER);
                jf.setSize(width, height);
                jf.setVisible(true);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
