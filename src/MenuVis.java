import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.nio.Buffer;
import java.util.ArrayList;


public class MenuVis{
    private JFrame frame;
    private JLayeredPane lframe;
    private JPanel gPanel = new JPanel();
    private JPanel bPanel = new JPanel();
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    BufferedImage bg;
    boolean start = false;

    MenuVis() {

        this.frame = new JFrame("Game");
        lframe = new JLayeredPane();
        lframe.setBounds(0, 0, size.width, size.height);


        bg = image("Pictures/Menu.png");

        gPanel = new GridAreaPanel();
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

    /**
     * addButtons
     * makes a JPanel with buttons to edit the city
     * @return the JPanel with the buttons
     */
    private JPanel addButtons() {
        JPanel buttonPanel = new JPanel();
        JButton startButton = new RoundedButton("START");
        JButton addButton = new JButton("How To Play");

        startButton.setBounds(size.width/2 - 250, size.height/2 - 125, 500, 250);
        startButton.setForeground(new Color(243, 246, 255));
        startButton.setFont(new Font("elephant", Font.BOLD, 108));
        startButton.setBackground(new Color(173, 19, 19));
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> start = true);

        buttonPanel.add(startButton);
        buttonPanel.setLayout(null);
        return buttonPanel;
    }


    public void refresh() {
        frame.repaint();
        if (start) {
            frame.dispose();
        }
    }

    class GridAreaPanel extends JPanel {

        public void paintComponent(Graphics g) {


            setDoubleBuffered(true);

            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(bg, 0,0, size.width, size.height, null);
            g.setColor(Color.black);


        }

    }
} //end of DisplayGrid