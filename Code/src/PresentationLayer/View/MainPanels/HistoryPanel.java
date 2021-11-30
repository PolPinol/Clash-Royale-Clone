package PresentationLayer.View.MainPanels;

import PresentationLayer.View.Helpers.ButtonAnimation;
import PresentationLayer.View.Helpers.CustomLabel;
import PresentationLayer.View.Helpers.PaintPanel;
import PresentationLayer.View.Listeners.SelectedRecordListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Class that represents the HistoryPanel.
 *
 * On this card we have the main jPanel which consists of the wallpaper image and
 * a BorderLayout to position the three corresponding jPanels we have added.
 *
 * In the left panel we find two jButtons, one to return to the menu and one to go
 * to Settings. In the center panel is the jList with a list of interactive jDialogs
 * that open the corresponding game.
 */
public class HistoryPanel extends PaintPanel implements SelectedRecordListener {
    /**
     * The constant HISTORY.
     */
    public static final String HISTORY = "HISTORY";
    /**
     * The constant BACK_ACTION.
     */
    public static final String BACK_ACTION = "BACK_ACTION";
    /**
     * The constant DELETE_ACTION.
     */
    public static final String DELETE_ACTION = "DELETE_ACTION";
    /**
     * The constant PLAY_ACTION.
     */
    public static final String PLAY_ACTION = "PLAY_ACTION";
    /**
     * The constant EVENT_SETTINGS.
     */
    public static final String EVENT_SETTINGS = "settingsClick";
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
     * The constant BUTTON.
     */
    public static final ImageIcon BUTTON = new ImageIcon("img/button3.gif");
    /**
     * The constant TRASHCAN.
     */
    public static final ImageIcon TRASHCAN = new ImageIcon("img/trash2.png");
    /**
     * The constant AJUSTES.
     */
    public static final ImageIcon AJUSTES = new ImageIcon("img/ajustes.png");
    /**
     * The constant NAME_EMPTY.
     */
    public static final String NAME_EMPTY = "El nombre esta vacio!";
    /**
     * The constant NAME_ALREADY_EXISTS.
     */
    public static final String NAME_ALREADY_EXISTS = "Nombre ya existe!";
    /**
     * The constant PLAY.
     */
    public static final String PLAY = "Play";
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
    private ButtonAnimation playButton;
    private ButtonAnimation backButton;
    private ButtonAnimation deleteButton;
    private DefaultListModel<CustomLabel> listModel;
    private JList<CustomLabel> list;
    private ButtonAnimation settingsButton;

    /**
     * Instantiates a new HistoryPanel.
     */
    public HistoryPanel() {
        initiateAttributes();
        mainSettings();

        setList();
        setWestPanel();
        setCenterPanel();
        setEastPanel();

        addPanels();
    }

    private void initiateAttributes() {
        westPanel = new PaintPanel(EMPTY);
        centerPanel = new PaintPanel(EMPTY);
        eastPanel = new PaintPanel(EMPTY);
        backButton = new ButtonAnimation();
        playButton = new ButtonAnimation();
        deleteButton = new ButtonAnimation();
        listModel = new DefaultListModel<>();
    }

    private void mainSettings() {
        setWallpaperImage(WALLPAPER);
        initiateFont(FONT_SIZE);
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));
        setMinimumSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));
        setMaximumSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));
    }

    private void setList() {
        list = new JList<>(listModel);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setOpaque(false);
        list.setBackground(new Color(0,0,0,0));
        list.setFont(initFont(14));
        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        list.setForeground(Color.WHITE);
        list.setCellRenderer(new CustomLabel());
    }

    private void setSettingsButton(){
        settingsButton = new ButtonAnimation();
        settingsButton.setIcon(AJUSTES);
        settingsButton.setBorderPainted(false);
        settingsButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
    }

    private void setCenterPanel() {
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));

        JLabel labelTittle = new JLabel(HISTORY);
        labelTittle.setFont(initiateFont(FONT_SIZE));
        labelTittle.setForeground(Color.WHITE);
        labelTittle.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTittle.setAlignmentY(Component.CENTER_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(labelTittle);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(scrollPane);
    }

    private void setWestPanel() {
        setSettingsButton();
        westPanel.setLayout(new BorderLayout());

        backButton.setIcon(CLOSE_BUTTON);
        backButton.setBorderPainted(false);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        westPanel.add(backButton, BorderLayout.NORTH);
        westPanel.add(settingsButton, BorderLayout.SOUTH);
    }

    private void setEastPanel() {
        JLabel playText = new JLabel(PLAY);
        playText.setFont(initFont(15));
        playText.setAlignmentX(CENTER_ALIGNMENT);
        eastPanel.setLayout(new BorderLayout());

        deleteButton.setIcon(TRASHCAN);
        deleteButton.setBorderPainted(false);
        deleteButton.setOpaque(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setEnabled(false);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        playButton.add(playText);
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setEnabled(false);
        playButton.setIcon(BUTTON);

        PaintPanel playPanel = new PaintPanel(EMPTY);
        playPanel.setOpaque(false);
        playPanel.add(playButton);
        playPanel.setFocusable(false);

        eastPanel.add(deleteButton, BorderLayout.SOUTH);
        eastPanel.add(playPanel, BorderLayout.NORTH);
    }

    private void newRecord(CustomLabel label) {
        listModel.addElement(label);
        revalidate();
    }

    private void deleteRecord(int id) {
        listModel.remove(id);
        revalidate();
    }

    private void nameAlreadyExists() {
        JOptionPane.showMessageDialog(null, NAME_ALREADY_EXISTS);
    }

    private CustomLabel historyLabelCreator(String name, String date, int winner) {
        String nameRecord;

        if (winner == 0) {
            nameRecord = name + "      " + date + "      " + "Ganada";
        } else {
            nameRecord = name + "      " + date + "      " + "Perdida";
        }

        CustomLabel label = new CustomLabel(nameRecord);

        label.setFont(initiateFont(14));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(13, 0, 2, 0));

        return label;
    }

    private void addPanels() {
        add(centerPanel, BorderLayout.CENTER);
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
    }

    private Font initFont(int size){
        Font font;

        try {
            InputStream is =  new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(Font.BOLD, size);
        } catch (Exception ex) {
            font = new Font(DEFAULT_FONT, Font.PLAIN, FONT_SIZE);
        }

        return font;
    }

    /**
     * Method to register the selection listener.
     *
     * @param listener the ListSelectionListener
     */
    public void setSelectionListener(ListSelectionListener listener) {
        list.addListSelectionListener(listener);
    }

    /**
     * Method to register the action listener.
     *
     * @param listener the ActionListener
     */
    public void setActionListeners(ActionListener listener) {
        backButton.setActionCommand(BACK_ACTION);
        backButton.addActionListener(listener);

        deleteButton.setActionCommand(DELETE_ACTION);
        deleteButton.addActionListener(listener);

        playButton.setActionCommand(PLAY_ACTION);
        playButton.addActionListener(listener);

        settingsButton.setActionCommand(EVENT_SETTINGS);
        settingsButton.addActionListener(listener);
    }

    @Override
    public void notifyNewRecord(String name, String date, int winner) {
        newRecord(historyLabelCreator(name, date, winner));
    }

    @Override
    public void notifyRecordExists() {
        nameAlreadyExists();
    }

    @Override
    public void notifyNameEmpty() {
        JOptionPane.showMessageDialog(null, NAME_EMPTY);
    }

    @Override
    public void notifyDeleteRecord(int id) {
        deleteRecord(id);
    }

    @Override
    public void notifyRecordSelected() {
        deleteButton.setEnabled(true);
        playButton.setEnabled(true);
        this.repaint();
        this.revalidate();
    }

    @Override
    public void notifyRecordNonSelected() {
        deleteButton.setEnabled(false);
        playButton.setEnabled(false);
        this.repaint();
        this.revalidate();
    }

    @Override
    public int getSelectItem() {
        return list.getSelectedIndex();
    }

    @Override
    public String getNameGame() {
        String text = listModel.get(list.getSelectedIndex()).toString();
        String[] parts = text.split(" ");

        return parts[0];
    }

    @Override
    public void clearAllList() {
        listModel.clear();
    }
}
