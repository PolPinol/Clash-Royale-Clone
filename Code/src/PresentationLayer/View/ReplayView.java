package PresentationLayer.View;

import BusinessLayer.Entities.Troop;
import PresentationLayer.View.Helpers.GraphicsPanel;
import PresentationLayer.View.Helpers.PaintPanel;
import PresentationLayer.View.Helpers.PaintedPanelGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Class ReplayView.
 *
 * This jFrame consists of a basic jPanel that sorts according to BorderLayout the four jPanels
 * we have added and also contains the background image of the game.
 *
 * The north panel contains the Settings button which consists of a basic jPanel containing
 * the corresponding button.
 *
 * In westPanel we have a jPanel that is organized with GridLayout in order to create an array for
 * each jPanel that forms each square of the game.
 *
 * On the eastPanel side we have the panel that contains the graphs with the lives of the user and
 * the AI.
 *
 * Finally we have the south panel that contains the buttons to pause and play the game.
 */
public class ReplayView extends GameView {
    /**
     * The constant PAUSE.
     */
    public static final String PAUSE = "pause";
    /**
     * The constant PLAY.
     */
    public static final String PLAY = "play";
    /**
     * The constant DEFAULT_FONT.
     */
    public static final String DEFAULT_FONT = "Arial";
    /**
     * The constant FONT_PATH.
     */
    public static final String FONT_PATH = "font/titleFont.ttf";
    /**
     * The constant WALLPAPER.
     */
    public static final Image WALLPAPER = new ImageIcon("img/tableroReplay.png").getImage();
    /**
     * The constant BUTTON.
     */
    public static final ImageIcon BUTTON = new ImageIcon("img/buttonyellow.png");
    /**
     * The constant WIDTH_FRAME.
     */
    public static final int WIDTH_FRAME = 879;
    /**
     * The constant HEIGHT_FRAME.
     */
    public static final int HEIGHT_FRAME = 569;
    /**
     * The constant PLAY_BUTTON.
     */
    public static final String PLAY_BUTTON = "Play";
    /**
     * The constant PAUSE_BUTTON.
     */
    public static final String PAUSE_BUTTON = "Pause";
    /**
     * The constant REPLAY_ENDED.
     */
    public static final String REPLAY_ENDED = "Repeticion Terminada!";
    /**
     * The constant WARNING_TITLE.
     */
    public static final String WARNING_TITLE = "Aviso";
    /**
     * The constant CUSTOM_BLUE.
     */
    public static final Color CUSTOM_BLUE = new Color(0, 94, 145);
    /**
     * The constant CUSTOM_YELLOW.
     */
    public static final Color CUSTOM_YELLOW = new Color(253, 216, 53);

    private JButton pause;
    private JButton play;
    private PaintPanel pausePanel;
    private PaintPanel playPanel;

    /**
     * Instantiates a new ReplayView.
     */
    public ReplayView() {
        super();
    }

    private PaintPanel getButtonPanel(JButton buttonAnimation, JLabel jLabel) {
        buttonAnimation.add(jLabel);
        buttonAnimation.setIcon(BUTTON);
        buttonAnimation.setOpaque(false);
        buttonAnimation.setContentAreaFilled(false);
        buttonAnimation.setBorderPainted(false);
        buttonAnimation.setFocusable(false);

        PaintPanel button = new PaintPanel(EMPTY);
        button.setLayout(new BorderLayout());
        button.setBackground(CUSTOM_YELLOW);
        buttonAnimation.setPreferredSize(new Dimension(50, 70));
        buttonAnimation.setMaximumSize(new Dimension(50, 70));
        button.add(buttonAnimation, BorderLayout.NORTH);

        return button;
    }

    private JLabel getLabelButton(String text) {
        JLabel jLabel = new JLabel(text);
        jLabel.setForeground(CUSTOM_BLUE);
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel.setFont(initiateFont());

        return jLabel;
    }

    private void changeLogsPanel() {
        logsPanel = new PaintPanel(EMPTY);
        logsPanel.setLayout(new BorderLayout());
        logsPanel.setPreferredSize(new Dimension(200, 70));
        logsPanel.setMaximumSize(new Dimension(200, 70));
        logsPanel.add(pausePanel, BorderLayout.NORTH);

        // Empty for eliminate LogsPanel
        logsTextArea = new JPanel();
    }

    /**
     * Method to show the dialog to end the game replayed.
     */
    public void endGame() {
        int resp = JOptionPane.showConfirmDialog(null, REPLAY_ENDED,
                WARNING_TITLE, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (resp == JOptionPane.OK_OPTION) {
            closeGame();
        }
    }

    @Override
    public void initiateWallpaperPanel() {
        mainPanel = new PaintedPanelGame(WALLPAPER, WIDTH_FRAME, HEIGHT_FRAME);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
    }

    @Override
    public void initiateGraphicsPanel() {
        graphicsPanel = new GraphicsPanel();
        graphicsPanel.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
        graphicsPanel.setPreferredSize(new Dimension(398, 500));
        graphicsPanel.setMaximumSize(new Dimension(398, 500));
        graphicsPanel.setOpaque(false);
        mainPanel.add(graphicsPanel, BorderLayout.EAST);
    }

    @Override
    public void initiateSouthPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(getLogsPanel(), BorderLayout.EAST);
        jPanel.setOpaque(false);
        jPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 100));
        mainPanel.add(jPanel, BorderLayout.SOUTH);
    }

    @Override
    public PaintPanel getLogsPanel() {
        JLabel pauseText = getLabelButton(PAUSE_BUTTON);
        JLabel playText = getLabelButton(PLAY_BUTTON);

        pause = new JButton();
        play = new JButton();
        pausePanel = getButtonPanel(pause, pauseText);
        playPanel = getButtonPanel(play, playText);
        changeLogsPanel();

        return logsPanel;
    }

    @Override
    public void updateVScrollBar() {

    }

    @Override
    public void paintFirstTime(Troop troop, int row, int column, int user) {
        String url = troop.getUrl(user);
        Image image = new ImageIcon(url).getImage();
        matrixSquaresPanels[row][column].repaintTroop(image);
    }

    @Override
    public void registerActionListener(ActionListener listener) {
        pause.setActionCommand(PAUSE);
        pause.addActionListener(listener);

        play.setActionCommand(PLAY);
        play.addActionListener(listener);

        settingsButton.setActionCommand(EVENT_SETTINGS);
        settingsButton.addActionListener(listener);
    }

    /**
     *  Method to turn on pause button.
     */
    public void turnOnPause() {
        pause.setVisible(true);
        play.setVisible(false);
        logsPanel.add(pausePanel, BorderLayout.NORTH);

        logsPanel.revalidate();
        logsPanel.repaint();
    }

    /**
     * Method to turn on play button.
     */
    public void turnOnPlay() {
        play.setVisible(true);
        pause.setVisible(false);
        logsPanel.add(playPanel, BorderLayout.NORTH);

        logsPanel.revalidate();
        logsPanel.repaint();
    }

    private Font initiateFont(){
        Font font;

        try {
            InputStream is =  new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(Font.BOLD, 17);
        } catch (Exception ex) {
            font = new Font(DEFAULT_FONT, Font.PLAIN, 14);
        }

        return font;
    }
}