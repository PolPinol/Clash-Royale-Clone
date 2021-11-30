package PresentationLayer.View.MainPanels;

import BusinessLayer.Entities.User;
import PresentationLayer.Controller.Listeners.OpenUserHistoryListener;
import PresentationLayer.View.Helpers.ButtonAnimation;
import PresentationLayer.View.Helpers.CustomTable;
import PresentationLayer.View.Helpers.PaintPanel;
import PresentationLayer.View.Listeners.ExtractDataListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Class that represents the RankingPanel.
 *
 * In this card we find a main jPanel that contains the background image and consists of a BorderLayout,
 * this contains a jPanel positioned in WEST that contains the buttons to return to the menu or open the
 * jDialog of the Settings and a jPanel that contains the jTable and panel title.
 *
 * The jTable is inside a JScrollPane which is also inside another jPanel. The jPanel center consists of
 * a BoxLayout so the jTable is below the jLabel of the title.
 */
public class RankingPanel extends PaintPanel implements ExtractDataListener {
    /**
     * The constant RANKING.
     */
    public static final String RANKING = "RANKING";
    /**
     * The constant BACK_ACTION.
     */
    public static final String BACK_ACTION = "BACK_ACTION";
    /**
     * The constant EVENT_SETTINGS.
     */
    public static final String EVENT_SETTINGS = "settingsClick";
    /**
     * The constant HEADER.
     */
    public static final String [] HEADER = {"Player", "Wins", "Win ratio"};
    /**
     * The constant EMPTY.
     */
    public static final Image EMPTY = new ImageIcon("img/empty3.png").getImage();
    /**
     * The constant WALLPAPER.
     */
    public static final Image WALLPAPER = new ImageIcon("img/snapseed2.png").getImage();
    /**
     * The constant CLOSE_BUTTON.
     */
    public static final ImageIcon CLOSE_BUTTON = new ImageIcon("img/close.png");
    /**
     * The constant AJUSTES.
     */
    public static final ImageIcon AJUSTES = new ImageIcon("img/ajustes.png");
    /**
     * The constant WIDTH_PANEL.
     */
    public static final int WIDTH_PANEL = 700;
    /**
     * The constant HEIGHT_PANEL.
     */
    public static final int HEIGHT_PANEL = 350;
    /**
     * The constant FONT_SIZE.
     */
    public static final int FONT_SIZE = 20;

    private JPanel westPanel;
    private JPanel centerPanel;
    private JPanel eastPanel;
    private ButtonAnimation backButton;
    private ButtonAnimation settingsButton;
    private DefaultTableModel tableModel;
    private CustomTable table;
    private OpenUserHistoryListener listener;

    /**
     * Instantiates a new RankingPanel.
     */
    public RankingPanel() {
        initiateAttributes();
        mainSettings();
        initializeTable();

        setEastPanel();
        setWestPanel();
        setCenterPanel();

        addPanels();
    }

    private void initiateAttributes() {
        westPanel = new PaintPanel(EMPTY);
        centerPanel = new PaintPanel(EMPTY);
        eastPanel = new PaintPanel(EMPTY);
        backButton = new ButtonAnimation();
    }

    private void mainSettings() {
        setWallpaperImage(WALLPAPER);
        initiateFont(FONT_SIZE);
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));
        setMinimumSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));
        setMaximumSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));
    }

    private void initializeTable(){
        String [][] data = {};

        tableModel = new DefaultTableModel(data, HEADER);
    }

    private void setSettingsButton(){
        settingsButton = new ButtonAnimation();
        settingsButton.setIcon(AJUSTES);
        settingsButton.setBorderPainted(false);
        settingsButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
    }

    private void setEastPanel(){
        eastPanel.setPreferredSize(new Dimension(100, 350));
        eastPanel.setMaximumSize(new Dimension(100, 350));
    }

    private JLabel returnLabelTitle(){
        JLabel labelTittle = new JLabel(RANKING);
        labelTittle.setFont(initiateFont(FONT_SIZE));
        labelTittle.setForeground(Color.WHITE);
        labelTittle.setAlignmentX(CENTER_ALIGNMENT);

        return labelTittle;
    }

    private void newData(ArrayList<User> elements) {
        for (int i = 0; i < elements.size(); i++) {
            String name = elements.get(i).getName();
            int points = elements.get(i).getPoints();
            int ratio = (int) elements.get(i).getWinrate();
            tableModel.addRow(new Object[]{name, points, ratio + "%"});
            revalidate();
        }
    }

    private CustomTable returnTable(){
        table = new CustomTable(tableModel);

        return table;
    }

    private void setCenterPanel() {
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setPreferredSize(new Dimension(350, 350));
        centerPanel.setMaximumSize(new Dimension(350, 350));

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(returnLabelTitle());
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(returnTable());
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private void setWestPanel() {
        setSettingsButton();
        westPanel.setLayout(new BorderLayout());
        westPanel.setPreferredSize(new Dimension(100, 350));
        westPanel.setMaximumSize(new Dimension(100, 350));

        backButton.setIcon(CLOSE_BUTTON);
        backButton.setBorderPainted(false);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        westPanel.add(backButton, BorderLayout.NORTH);
        westPanel.add(settingsButton, BorderLayout.SOUTH);
    }

    private void addPanels() {
        add(centerPanel, BorderLayout.CENTER);
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
    }

    /**
     * Method to notify that a history has to be opened.
     */
    public void actionSelectedCell() {
        String selectedCellValue = (String) tableModel.getValueAt(table.getTable().getSelectedRow(), 0);
        listener.notifyOpenHistory(selectedCellValue);
    }

    /**
     * Method to register the action listeners.
     *
     * @param listener the ActionListener
     * @param mouseListener the MouseListener
     */
    public void setActionListeners(ActionListener listener, MouseListener mouseListener) {
        backButton.setActionCommand(BACK_ACTION);
        backButton.addActionListener(listener);

        settingsButton.setActionCommand(EVENT_SETTINGS);
        settingsButton.addActionListener(listener);

        table.getTable().addMouseListener(mouseListener);
    }

    /**
     * Method to register open history listener.
     *
     * @param listener the OpenUserHistoryListener
     */
    public void registerOpenHistoryListener(OpenUserHistoryListener listener) {
        this.listener = listener;
    }

    @Override
    public void notifyExtraction(ArrayList<User> data) {
        newData(data);
    }

    @Override
    public void clearTable() {
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
    }
}