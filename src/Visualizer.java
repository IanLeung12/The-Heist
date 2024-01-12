import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Visualizer{
    private JFrame frame;

    public BufferedImage Diamond;

    BufferedImage money;

    ArrayList<Integer> deck;

    ArrayList<Integer> hand;
    int counter = 0;

    int pick = -10;

    int pickx = 30;

    double spinSpeed = 20;
    int angle = 0;


    int totalWinnings;
    int totalSpent;


    Card card = new Card(3, "Diamonds");

    Visualizer(ArrayList<Integer> deck) {
        this.deck = deck;
        hand = new ArrayList<>(13);
        this.frame = new JFrame("Game");
        Mouse mouse = new Mouse();

        try {
            Diamond = image("Pictures/diamond.png");
            money = image("Pictures/money.png");
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
        counter ++;
        if (counter > 500 && pick == -10 && deck.size() > 0) {
            int pos = (int) (Math.random() * deck.size());
            pick = deck.remove(pos);
            counter = 0;
        }

        if (spinSpeed > -15) {
            spinSpeed -= 0.05;
        } else {
            spinSpeed = 15 + (Math.random() * 20);
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
                drawCardb(60 - i * 2, 60 - i * 3, g2d);
            }



            for (int i = 0; i < hand.size(); i ++) {
                drawCard(hand.get(i), 300 + i * 60, 45, g2d);
            }

            if (pick != -10) {
                drawCard(pick, pickx, 45, g2d);
                pickx += 10;
                if (pickx >= 300 + hand.size() * 60) {
                    hand.add(pick);
                    pickx = 45;
                    pick = -10;
                    counter = 0;
                }

            }

            g2d.setColor(new Color(221, 239, 245));
            drawCircle(45, 325, 300, g);
            int x2;
            int y2;
            if (spinSpeed > 0) {
                x2 = (int) (195 + 150 * Math.sin(Math.toRadians(angle + spinSpeed)));
                y2 = (int) (475 + 150 * Math.cos(Math.toRadians(angle + spinSpeed)));
                angle = (int) (angle + spinSpeed);
            } else {
                x2 = (int) (195 + 150 * Math.sin(Math.toRadians(angle)));
                y2 = (int) (475 + 150 * Math.cos(Math.toRadians(angle)));
            }
            g2d.drawLine(195, 475, x2, y2);




        }

        private double rad(double angle) {
            return (angle*Math.PI)/180.0;
        }
        private void drawCircle(int x, int y, int d, Graphics g) {
            g.fillOval(x, y, d, d);
            g.setColor(Color.black);
            g.drawOval(x, y, d, d);
        }

        private void drawBorderedString(String str, int x, int y, float size, float stroke, Graphics2D g2d) {
            AffineTransform originalTransform = g2d.getTransform();
            Stroke originalStroke = g2d.getStroke();
            Color originalColor = g2d.getColor();

            try {
                g2d.translate(x, y);

                g2d.setColor(Color.black);
                FontRenderContext frc = g2d.getFontRenderContext();
                TextLayout tl = new TextLayout(str, g2d.getFont().deriveFont(size), frc);
                Shape shape = tl.getOutline(null);

                g2d.setStroke(new BasicStroke(stroke));
                g2d.draw(shape);

                g2d.setColor(Color.white);
                g2d.fill(shape);
            } finally {
                // Restore the original state
                g2d.setTransform(originalTransform);
                g2d.setStroke(originalStroke);
                g2d.setColor(originalColor);
            }
        }

        private void drawCard(int val, int x, int y, Graphics2D g2d) {
            g2d.setStroke(new BasicStroke(10f));
            drawTemplate(x, y, g2d);
            g2d.drawImage(money, x + 15, y + 45, 120, 120, null);
            g2d.setFont(new Font("Elephant", Font.PLAIN, 60));
            g2d.setColor(Color.WHITE);
            drawBorderedString(String.valueOf(val), x + 50, y + 120, 48f,5f, g2d);
        }

        private void drawCardb(int x, int y, Graphics2D g2d) {

            g2d.setStroke(new BasicStroke(10f));
            drawTemplate(x, y, g2d);
            for (int i = 0; i < 3; i ++) {
                for (int j = 0; j < 5; j ++) {
                    g2d.drawImage(Diamond, (int) (15 * (double) 1 + x + 40 * i * (double) 1), (int) (15 * (double) 1 + y + 36 * j * (double) 1),
                            (int) (40 * (double) 1), (int) (36 * (double) 1), null);
                }
            }
        }

        private void drawTemplate(int x, int y, Graphics2D g2d) {
            g2d.setColor(new Color(231, 232, 236));
            int width = 150;
            int height = 210;
            int arc = 5;
            g2d.fillRect(x, y, width, height);
            g2d.setColor(Color.RED);
            g2d.drawRoundRect(x, y, width, height, arc, arc);
            g2d.setColor(new Color(128, 8, 15));
            g2d.setStroke(new BasicStroke(2f));
            int diff = 7;
            g2d.drawRoundRect((int) (x - diff * 0.65), (int) (y - diff * 0.65), (int) (width + 1.5 * diff), (int) (height + 1.5 * diff), (int) (arc * 2.5), (int) (arc * 2.5));
        }
    }//end of GridAreaPanel

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