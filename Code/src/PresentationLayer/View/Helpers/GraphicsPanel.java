package PresentationLayer.View.Helpers;

import BusinessLayer.Entities.User;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Class GraphicsPanel.
 *
 * This class extending JPanel will repaint 4 bars with paintComponents that refers at
 * lives from each user (player and computer) and 4 labels refers to the same quantity of
 * the bars but specifying the number of each.
 */
public class GraphicsPanel extends JPanel {
    /**
     * The constant MAX_WIDTH.
     */
    public static final int MAX_WIDTH = 300;
    /**
     * The constant MAX_LIVES.
     */
    public static final int MAX_LIVES = 250;
    /**
     * The constant HEIGHT_BARS.
     */
    public static final int HEIGHT_BARS = 30;
    /**
     * The constant FONT_SIZE.
     */
    public static final int FONT_SIZE = 18;
    /**
     * The constant X_POSITION_BARS.
     */
    public static final int X_POSITION_BARS = 50;
    /**
     * The constant PIXELS_TROOP_WIDTH.
     */
    public static final int PIXELS_TROOP_WIDTH = 20;
    /**
     * The constant CUSTOM_PURPLE.
     */
    public static final Color CUSTOM_PURPLE = new Color(101, 66, 138);
    /**
     * The constant CUSTOM_GRAY.
     */
    public static final Color CUSTOM_GRAY = new Color(97,117,135);
    /**
     * The constant DEFAULT_FONT.
     */
    public static final String DEFAULT_FONT = "Arial";
    /**
     * The constant FONT_PATH.
     */
    public static final String FONT_PATH = "font/titleFont.ttf";

    private int widthTroopsPlayer;
    private int widthTroopsIA;
    private int widthLivesPlayer;
    private int widthLivesIA;
    private int troopsIA;
    private int troopsPlayer;
    private JLabel jLabelTroopsIA;
    private JLabel jLabelTroopsPlayer;
    private JLabel jLabelLivesIA;
    private JLabel jLabelLivesPlayer;

    /**
     * Instantiates a new Graphics panel.
     */
    public GraphicsPanel() {
        setSettings();
        initiateAttributes();
        initiateLabels();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        makeTroopsGraphics(g);
        makeLivesGraphics(g);
    }

    /**
     * Method that changes the attribute of lives depending on the user.
     *
     * @param life the total life
     * @param user the user
     */
    public void changeWidthLives(int life, int user) {
        if (life > 0) {
            if (user == User.PLAYER_USER) {
                jLabelLivesPlayer.setText(String.valueOf(life));
                widthLivesPlayer =  (life*MAX_WIDTH) / MAX_LIVES;
            } else {
                jLabelLivesIA.setText(String.valueOf(life));
                widthLivesIA =  (life*MAX_WIDTH) / MAX_LIVES;
            }
        } else {
            if (user == User.PLAYER_USER) {
                jLabelLivesPlayer.setText("0");
                widthLivesPlayer =  1;
            } else {
                jLabelLivesIA.setText("0");
                widthLivesIA =  1;
            }
        }

        this.repaint();
        this.revalidate();
    }

    /**
     * method that changes the quantity of troops that is having the user.
     *
     * @param user       the user
     * @param moreOrLess more or less troops
     */
    public synchronized void changeWidthTroops(int user, int moreOrLess) {
        if (user == User.PLAYER_USER) {
            troopsIA = troopsIA + moreOrLess;
            if (widthTroopsIA < MAX_WIDTH) {
                widthTroopsIA =  widthTroopsIA + PIXELS_TROOP_WIDTH*moreOrLess;
            }
            jLabelTroopsIA.setText(String.valueOf(troopsIA));
        } else {
            troopsPlayer = troopsPlayer  + moreOrLess;
            if (widthTroopsPlayer < MAX_WIDTH) {
                widthTroopsPlayer =  widthTroopsPlayer + PIXELS_TROOP_WIDTH*moreOrLess;
            }
            jLabelTroopsPlayer.setText(String.valueOf(troopsPlayer));
        }

        this.repaint();
        this.revalidate();
    }

    private void setSettings() {
        this.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
        this.setLayout(new GridLayout(8,2));
    }

    private void initiateAttributes() {
        this.widthTroopsPlayer = 0;
        this.widthTroopsIA = 0;
        this.widthLivesPlayer = MAX_WIDTH;
        this.widthLivesIA = MAX_WIDTH;
        this.troopsIA = 0;
        this.troopsPlayer = 0;
    }

    private void initiateLabels() {
        initiateJLabelLives();
        initiateEmptyGrid();
        initiateJLabelTroops();
    }

    private void initiateJLabelLives() {
        jLabelLivesIA = new JLabel();
        jLabelLivesIA.setText(String.valueOf(MAX_LIVES));
        jLabelLivesIA.setFont(initiateFont());
        jLabelLivesIA.setForeground(Color.BLACK);
        this.add(jLabelLivesIA);

        jLabelLivesPlayer = new JLabel();
        jLabelLivesPlayer.setText(String.valueOf(MAX_LIVES));
        jLabelLivesPlayer.setFont(initiateFont());
        jLabelLivesPlayer.setForeground(Color.BLACK);
        this.add(jLabelLivesPlayer);
    }

    private void initiateEmptyGrid() {
        JLabel jLabelEmpty = new JLabel();
        jLabelEmpty.setText("");
        this.add(jLabelEmpty);
    }

    private void initiateJLabelTroops() {
        jLabelTroopsIA = new JLabel();
        jLabelTroopsIA.setText(String.valueOf(troopsIA));
        jLabelTroopsIA.setFont(initiateFont());
        jLabelTroopsIA.setForeground(Color.BLACK);
        this.add(jLabelTroopsIA);

        jLabelTroopsPlayer = new JLabel();
        jLabelTroopsPlayer.setText(String.valueOf(troopsPlayer));
        jLabelTroopsPlayer.setFont(initiateFont());
        jLabelTroopsPlayer.setForeground(Color.BLACK);
        this.add(jLabelTroopsPlayer);
    }

    private void makeTroopsGraphics(Graphics g) {
        g.setColor(CUSTOM_GRAY);
        g.fillRect(X_POSITION_BARS, 192, widthTroopsPlayer, HEIGHT_BARS);

        g.setColor(CUSTOM_PURPLE);
        g.fillRect(X_POSITION_BARS, 145, widthTroopsIA, HEIGHT_BARS);
    }

    private void makeLivesGraphics(Graphics g) {
        g.setColor(CUSTOM_GRAY);
        g.fillRect(X_POSITION_BARS, 54, widthLivesPlayer, HEIGHT_BARS);

        g.setColor(CUSTOM_PURPLE);
        g.fillRect(X_POSITION_BARS, 7, widthLivesIA, HEIGHT_BARS);
    }

    private Font initiateFont(){
        Font font;

        try {
            InputStream is =  new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(Font.BOLD, GraphicsPanel.FONT_SIZE);
        } catch (Exception ex) {
            font = new Font(DEFAULT_FONT, Font.PLAIN, FONT_SIZE);
        }

        return font;
    }
}