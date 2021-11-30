package PresentationLayer.View.MainPanels;

import PresentationLayer.View.Helpers.ButtonAnimation;
import PresentationLayer.View.Helpers.PaintPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class that represents the MenuPanel.
 *
 * This panel consists of a main jPanel that has an assigned BorderLayout in order to position
 * the elements easily. This same jPanel contains the background image, the rest of jPanels have
 * put a transparent image to unify the whole panel and leave the same image.
 *
 * Starting on the left is a jPanel to which we have also assigned a BorderLayout, this one only
 * contains the Settings button at the bottom of the panel. In the center of the screen we find
 * the jPanel that contains the title and the three buttons with the menu options.
 */
public class MenuPanel extends PaintPanel {
    /**
     * The constant EVENT_PLAY_CLICK.
     */
    public static final String EVENT_PLAY_CLICK = "playClick";
    /**
     * The constant EVENT_RANKING.
     */
    public static final String EVENT_RANKING = "rankingPanel";
    /**
     * The constant EVENT_HISTORY.
     */
    public static final String EVENT_HISTORY = "historyPanel";
    /**
     * The constant EVENT_SETTINGS.
     */
    public static final String EVENT_SETTINGS = "settingsClick";
    /**
     * The constant EMPTY.
     */
    public static final Image EMPTY = new ImageIcon("img/empty3.png").getImage();
    /**
     * The constant WALLPAPER_NO_TITLE.
     */
    public static final Image WALLPAPER_NO_TITLE = new ImageIcon("img/snapseed2.png").getImage();
    /**
     * The constant BUTTON.
     */
    public static final ImageIcon BUTTON = new ImageIcon("img/button3.gif");
    /**
     * The constant AJUSTES.
     */
    public static final ImageIcon AJUSTES = new ImageIcon("img/ajustes.png");
    /**
     * The constant MENU.
     */
    public static final String MENU = "MENU";
    /**
     * The constant JUGAR.
     */
    public static final String JUGAR = "P L A Y";
    /**
     * The constant HISTORIAL.
     */
    public static final String HISTORIAL = "H I S T O R Y";
    /**
     * The constant RANQUING.
     */
    public static final String RANQUING = "R A N K I N G";
    /**
     * The constant FONT_SIZE.
     */
    public static final int FONT_SIZE = 20;
    /**
     * The constant WIDTH_PANEL.
     */
    public static final int WIDTH_PANEL= 700;
    /**
     * The constant HEIGHT_PANEL.
     */
    public static final int HEIGHT_PANEL = 350;
    /**
     * The constant YELLOW_CUSTOM.
     */
    public static final Color YELLOW_CUSTOM = new Color(253, 216, 53);

    private ButtonAnimation playButton;
    private ButtonAnimation rankingButton;
    private ButtonAnimation historyButton;
    private PaintPanel westPanel;
    private PaintPanel centerPanel;
    private PaintPanel eastPanel;
    private ButtonAnimation settingsButton;

    /**
     * Instantiates a new MenuPanel.
     */
    public MenuPanel() {
        this.setWallpaperImage(WALLPAPER_NO_TITLE);
        initiateFont(FONT_SIZE);

        initiateAttributes();
        initiateSettings();

        setCenterPanel();
        setWestPanel();
        setEastPanel();

        addPanelsToFrame();
    }

    private void setWestPanel() {
        setSettingsButton();
        westPanel.setLayout(new BorderLayout());
        westPanel.setPreferredSize(new Dimension(100, 350));
        westPanel.setMaximumSize(new Dimension(100, 350));
        westPanel.add(settingsButton, BorderLayout.SOUTH);
    }

    private void setEastPanel() {
        eastPanel.setPreferredSize(new Dimension(100, 350));
        eastPanel.setMaximumSize(new Dimension(100, 350));
        eastPanel.setLayout(new BorderLayout());
    }

    private void addPanelsToFrame() {
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(westPanel, BorderLayout.WEST);
        this.add(eastPanel, BorderLayout.EAST);
    }

    private void initiateSettings() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));
    }

    private void initiateAttributes() {
        centerPanel = new PaintPanel(EMPTY);
        westPanel = new PaintPanel(EMPTY);
        eastPanel = new PaintPanel(EMPTY);
        historyButton = new ButtonAnimation();
        playButton = new ButtonAnimation();
        rankingButton = new ButtonAnimation();
    }

    private JLabel returnLabelTitle() {
        JLabel jLabel;

        jLabel = new JLabel(MENU);

        jLabel.setFont(initiateFont(20));
        jLabel.setForeground(Color.white);
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return jLabel;
    }

    private JPanel getPlayJPanel() {
        return getPersonalizedMenuJPanel(JUGAR, playButton);
    }

    private JPanel getHistoryJPanel() {
        return getPersonalizedMenuJPanel(HISTORIAL, historyButton);
    }

    private JPanel getRankingJPanel() {
        return getPersonalizedMenuJPanel(RANQUING, rankingButton);
    }

    private JPanel getPersonalizedMenuJPanel(String text, ButtonAnimation button) {
        JPanel jPanel;
        JLabel jLabel;

        jPanel = new JPanel();
        jLabel = new JLabel(text);

        jLabel.setFont(initiateFont(FONT_SIZE));
        jLabel.setAlignmentX(CENTER_ALIGNMENT);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.add(jLabel);
        button.setIcon(BUTTON);

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        jPanel.setLayout(new BorderLayout());
        jPanel.setMaximumSize(new Dimension(270,40));
        jPanel.setPreferredSize(new Dimension(270,40));
        jPanel.setBackground(YELLOW_CUSTOM);
        jPanel.add(button, BorderLayout.CENTER);

        return jPanel;
    }

    private void setCenterPanel() {
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setPreferredSize(new Dimension(350, 350));
        centerPanel.setMaximumSize(new Dimension(350, 350));

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(returnLabelTitle());
        centerPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        centerPanel.add(getPlayJPanel());
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        centerPanel.add(getRankingJPanel());
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        centerPanel.add(getHistoryJPanel());
        centerPanel.add(Box.createVerticalGlue());
    }

    /**
     * Method to register action listeners.
     *
     * @param listener the listener
     */
    public void registerActionListeners(ActionListener listener) {
        playButton.setActionCommand(EVENT_PLAY_CLICK);
        playButton.addActionListener(listener);

        historyButton.setActionCommand(EVENT_HISTORY);
        historyButton.addActionListener(listener);

        settingsButton.setActionCommand(EVENT_SETTINGS);
        settingsButton.addActionListener(listener);

        rankingButton.setActionCommand(EVENT_RANKING);
        rankingButton.addActionListener(listener);
    }

    private void setSettingsButton(){
        settingsButton = new ButtonAnimation();
        settingsButton.setIcon(AJUSTES);
        settingsButton.setBorderPainted(false);
        settingsButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
    }
}