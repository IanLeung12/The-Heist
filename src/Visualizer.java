import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


public class Visualizer {
    private JFrame frame;

    public BufferedImage Diamond;

    Card card = new Card(3, "Diamonds");

    Visualizer() {

        this.frame = new JFrame("Game");

        Mouse mouse = new Mouse();
        try {
            Diamond = image("diamond.png");
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
        frame.repaint();
    }

    class GridAreaPanel extends JPanel {

        public void paintComponent(Graphics g) {


            setDoubleBuffered(true);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(2.5f));
            g.setColor(Color.black);

            drawCard(card, g2d);
        }

        private void drawCircle(int x, int y, int d, Graphics g) {
            g.fillOval(x, y, d, d);
            g.setColor(Color.black);
            g.drawOval(x, y, d, d);
        }

        private void drawCard(Card card, Graphics2D g2d) {
            g2d.setColor(new Color(234, 234, 239));
            g2d.fillRect(card.x, card.y, card.width, card.height);
            g2d.setColor(Color.black);
            g2d.drawRect(card.x, card.y, card.width, card.height);
            g2d.setFont(new Font("Elephant", 0, 36));
            g2d.drawString(card.valToString(), card.x + 10, card.y + 40);


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
        public void mouseMoved(MouseEvent e) {
            System.out.println("a");
        }
    }

} //end of DisplayGrid