import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Visualizer implements Runnable{
    private JFrame frame;

    public BufferedImage Diamond;

    ArrayList<Integer> deck;

    Card card = new Card(3, "Diamonds");

    Visualizer(ArrayList<Integer> deck) {
        this.deck = deck;

        this.frame = new JFrame("Game");

        Mouse mouse = new Mouse();

        try {
            Diamond = image("Pictures/diamond.png");
        } catch (Exception ignored) {

        }

        //JPanel buttonPanel = addButtons();
        frame.addMouseListener(mouse);
        frame.addMouseMotionListener(mouse);
        //frame.getContentPane().add(BorderLayout.NORTH, buttonPanel);
        frame.getContentPane().add(BorderLayout.CENTER, new GridAreaPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setVisible(true);
    }

    /**
     * Image
     * Loads an image
     * @param path image location
     * @return the image
     */
    public BufferedImage image(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    private JPanel addButtons() {
//
//    }


    public void refresh() {

        SwingUtilities.invokeLater(() ->frame.repaint());
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true) {
            frame.repaint();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }
        }
    }

    class GridAreaPanel extends JPanel {

        public void paintComponent(Graphics g) {


            setDoubleBuffered(true);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(2.5f));
            g2d.setBackground(new Color(49, 138, 49));
            g2d.clearRect(0, 0, 2000, 2000);
            g.setColor(Color.black);

            for (int i = 0; i < deck.size(); i ++) {
                int row = i / 6;
                int col = i % 6;
                drawCard(deck.get(i), 50 + col * 225, 50 + row * 225, g2d);
            }
            for (int i = 30; i > 0; i -= 5) {
                drawCardb(500 + i, 300 + i, 1, g2d);
            }


        }

        private void drawCircle(int x, int y, int d, Graphics g) {
            g.fillOval(x, y, d, d);
            g.setColor(Color.black);
            g.drawOval(x, y, d, d);
        }

        private void drawCard(int val, int x, int y, Graphics2D g2d) {
            g2d.drawImage(Diamond, x, y, 200, 200, null);

            if (val == -1) {
                g2d.setFont(new Font("Elephant", Font.PLAIN, 32));
                g2d.drawString("BOMB", x + 35, y + 110);
            } else {
                g2d.setFont(new Font("Elephant", Font.PLAIN, 36));
                g2d.drawString(Integer.toString(val), x + 85, y + 110);
            }
        }

        private void drawCardb(int x, int y, double scale, Graphics2D g2d) {

            g2d.setStroke(new BasicStroke((float) (10 * scale)));
            g2d.setColor(new Color(231, 232, 236));
            int width = (int) (150 * scale);
            int height = (int) (210 * scale);
            int arc = (int) (5 * scale);
            g2d.fillRect(x, y, width, height);
            g2d.setColor(Color.RED);
            g2d.drawRoundRect(x, y, width, height, arc, arc);
            g2d.setColor(new Color(128, 8, 15));
            g2d.setStroke(new BasicStroke((float) (3 * scale)));
            int diff = (int) ((10 * scale) - (3 * scale));
            g2d.drawRoundRect((int) (x - diff * 0.65), (int) (y - diff * 0.65), (int) (width + 1.5 * diff), (int) (height + 1.5 * diff), (int) (arc * 2.5), (int) (arc * 2.5));
            for (int i = 0; i < 3; i ++) {
                for (int j = 0; j < 5; j ++) {
                    g2d.drawImage(Diamond, (int) (15 * scale + x + 40 * i * scale), (int) (15 * scale + y + 36 * j * scale),
                            (int) (40 * scale), (int) (36 * scale), null);
                }
            }
        }
    }//end of GridAreaPanela

    class Mouse implements MouseListener, MouseMotionListener {

        Mouse() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {        }
    }

} //end of DisplayGrid