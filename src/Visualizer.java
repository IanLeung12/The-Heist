import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Visualizer{
    private JFrame frame;

    private JLayeredPane lframe;
    private JPanel gPanel = new JPanel();
    private JPanel bPanel = new JPanel();

    BufferedImage Diamond;

    BufferedImage money;
    BufferedImage bomb;

    ArrayList<Integer> deck;

    ArrayList<Integer> hand;

    int pick = -10;

    int pickx = 30;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    int totalWinnings = 0;
    int totalSpent = 0;
    int currentPayout = 0;
    double houseProfit = 0;

    boolean playing = false;


    Card card = new Card(3, "Diamonds");

    Visualizer(ArrayList<Integer> deck) {
        this.deck = deck;
        hand = new ArrayList<>(13);
        this.frame = new JFrame("Game");
        lframe = new JLayeredPane();
        lframe.setBounds(0, 0, size.width, size.height);

        try {
            Diamond = image("Pictures/diamond.png");
            money = image("Pictures/money.png");
            bomb = image("Pictures/bomb.png");
        } catch (Exception ignored) {

        }

        gPanel = new Visualizer.GridAreaPanel();
        gPanel.setBackground(new Color(25, 100, 23));
        gPanel.setBounds(0, 0, size.width, size.height);
        gPanel.setOpaque(true);
        bPanel = addButtons();
        bPanel.setBounds(0, 0, size.width, size.height);
        bPanel.setOpaque(false);

        lframe.add(gPanel, 0, 0);
        lframe.add(bPanel, 1, 0);

        frame.add(lframe);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size);
        frame.setVisible(true);
    }


    private JPanel addButtons() {
        JPanel buttonPanel = new JPanel();
        JButton playButton = new RoundedButton("Play");

        playButton.setBounds(50, 350, 400, 250);
        playButton.setForeground(new Color(242, 245, 130));
        playButton.setFont(new Font("elephant", Font.BOLD, 108));
        playButton.setBackground(new Color(5, 66, 10));
        playButton.setFocusPainted(false);
        playButton.addActionListener(e -> {
            if (!playing) {
                playing = true;
                totalSpent += 30;
                reset();
            }
        });

        buttonPanel.add(playButton);
        buttonPanel.setLayout(null);
        return buttonPanel;
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
        if (playing) {
            if (hand.size() >= 5) {
                end();
            } else {
                if (pick == -10 && !bombRolled()) {
                    if (!roll()) {
                        currentPayout = 0;
                        end();
                    }
                }
            }
        }


    }

    public void end() {
        playing = false;
        totalWinnings += currentPayout;
        if (totalWinnings > 0) {
            houseProfit = Math.round((double) totalSpent/totalWinnings * 100.0)/100.0;
        }

    }

    public boolean bombRolled() {
        if (!hand.isEmpty()) {
            if (hand.get(hand.size() - 1) == -1) {
                end();
                return true;
            }
        }
        return false;
    }

    public void reset() {
        deck.clear();
        for (int i = 1; i < 13; i ++) {
            deck.add(i);
        }
        deck.add(-1);
        pick = -10;
        currentPayout = 0;
        hand.clear();
    }


    public boolean roll() {
        if (!deck.isEmpty()) {
            int pos = (int) (Math.random() * deck.size());
            pick = deck.get(pos);
            deck.remove((Integer) (hand.size() + 1));
        }
        return pick >= 0;
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
                drawCard(hand.get(i), 300 + i * 120, 45, g2d);
            }

            if (pick != -10) {
                drawCard(pick, pickx, 45, g2d);
                pickx += 10;
                if (pickx >= 300 + hand.size() * 120) {
                    if (pick != -1) {
                        currentPayout += pick;
                    } else {
                        currentPayout = 0;
                    }

                    hand.add(pick);
                    pickx = 45;
                    pick = -10;
                }

            }

            g.setColor(Color.white);
            g.setFont(new Font("elephant", Font.PLAIN, 60));
            drawBorderedString("Current Payout: $" + currentPayout, 500, 400, 50,2, g2d);
            drawBorderedString("Total Winnings: $" + totalWinnings, 500, 500, 50,2, g2d);
            drawBorderedString("Total Spent: $" + totalSpent, 500, 600, 50,2, g2d);
            drawBorderedString("House Profit :" + houseProfit + "x", 500, 700, 50,2, g2d);
            String inst = "INSTRUCTIONS: \n" +
                    "Pay $30 to draw up to 5 cards \n" +
                    "in a deck of 13 cards. The cards \n" +
                    "range from 1 - 12, plus a bomb card. \n" +
                    "Cards are replaced, but the lowest \n" +
                    "value card is removed from play each\n" +
                    "turn. The bomb card ends the game \n" +
                    "with no payout. Otherwise, the payout\n" +
                    "is the sum of your cards";
            int y = 100;
            for (String line : inst.split("\n")) {
                drawBorderedString(line, 1100, y, 42, 2, g2d);
                y += 60;
            }

            drawCard(-1, 30, 750, g2d);
            for (int i = 1; i < 13; i ++) {
                drawCard(i, 30 + i * 140, 750, g2d);
            }
        }

        private void drawPie(int x, int y, int rad, int start, int ext, Color clr, Graphics2D g2d) {
            Arc2D pie = new Arc2D.Double();
            pie.setArcByCenter(x, y, rad, start, ext, Arc2D.PIE);
            g2d.setColor(clr);
            g2d.fill(pie);
            g2d.setColor(Color.black);
            g2d.draw(pie);
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
            if (val == -1) {
                g2d.drawImage(bomb, x + 15, y + 45, 120, 120, null);
            } else {
                g2d.drawImage(money, x + 15, y + 45, 120, 120, null);
                g2d.setFont(new Font("Elephant", Font.PLAIN, 60));
                g2d.setColor(Color.WHITE);
                drawBorderedString(String.valueOf(val), x + 50, y + 120, 48f,5f, g2d);
            }
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

} //end of DisplayGrid