package PresentationLayer.View;

import BusinessLayer.Entities.Troop;
import PresentationLayer.Controller.Listeners.DeployedTroopListener;
import PresentationLayer.Controller.Listeners.UserComputerWinnerListener;
import PresentationLayer.View.Helpers.*;
import PresentationLayer.View.Listeners.SelectedSquareListener;
import PresentationLayer.View.Listeners.SelectedTroopCardListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class GameView.
 *
 * This jFrame consists of a basic jPanel that sorts according to BorderLayout the four jPanels we have
 * added and also contains the background image of the game.
 *
 * The north panel contains the Settings button which consists of a basic jPanel containing the corresponding
 * button.
 *
 * In westPanel we have a jPanel that is organized with GridLayout in order to create an array for each jPanel
 * that forms each square of the game. Each jPanel is assigned a listener so you can track the positions of each
 * troop on the board.
 *
 * On the eastPanel side we have the panel that contains the graphs with the lives of the user and the AI.
 *
 * Finally we have the south panel which contains two jPanels. In the case of the jPanel added on the left
 * it contains the coins and letters of the troops. On the other hand we have the right panel organized with
 * BorderLayout that contains the messages of the Logs.
 */
public class GameView implements SelectedTroopCardListener, SelectedSquareListener {
    /**
     * The constant CLOSE_GAME.
     */
    public static final String CLOSE_GAME = "closeGame";
    /**
     * The constant EVENT_SETTINGS.
     */
    public static final String EVENT_SETTINGS = "settingsClick";
    /**
     * The constant CARD_SELECTED.
     */
    public static final String CARD_SELECTED = "Card selected: ";
    /**
     * The constant NOT_MONEY.
     */
    public static final String NOT_MONEY = "Not enough money for troop: ";
    /**
     * The constant CANT_SUMMON.
     */
    public static final String CANT_SUMMON = "Can't summon troop on this position! ";
    /**
     * The constant WALLPAPER.
     */
    public static final Image WALLPAPER = new ImageIcon("img/tablero.png").getImage();
    /**
     * The constant IMAGE_MONEY.
     */
    public static final Image IMAGE_MONEY = new ImageIcon("img/money.png").getImage();
    /**
     * The constant EMPTY.
     */
    public static final Image EMPTY = new ImageIcon("img/empty2.png").getImage();
    /**
     * The constant AJUSTES.
     */
    public static final ImageIcon AJUSTES = new ImageIcon("img/ajustes.png");
    /**
     * The constant ROWS.
     */
    public static final int ROWS = 10;
    /**
     * The constant COLUMNS.
     */
    public static final int COLUMNS = 7;
    /**
     * The constant NUMBER_TROOPS.
     */
    public static final int NUMBER_TROOPS = 6;
    /**
     * The constant WIDTH_FRAME.
     */
    public static final int WIDTH_FRAME = 879;
    /**
     * The constant HEIGHT_FRAME.
     */
    public static final int HEIGHT_FRAME = 663;
    /**
     * The constant CUSTOM_WHITE.
     */
    public static final Color CUSTOM_WHITE = new Color(225, 241, 239);
    /**
     * The constant CUSTOM_GREEN.
     */
    public static final Color CUSTOM_GREEN = new Color(136,176,75);
    /**
     * The constant CUSTOM_PURPLE.
     */
    public static final Color CUSTOM_PURPLE = new Color(101, 66, 138);
    /**
     * The constant CUSTOM_RED.
     */
    public static final Color CUSTOM_RED = new Color(155,27,48);
    /**
     * The constant CUSTOM_ORANGE.
     */
    public static final Color CUSTOM_ORANGE = new Color(203,160,82);

    private final JFrame frame;
    private CardTroop[] cardTroops;
    protected SquarePanel[][] matrixSquaresPanels;
    private JPanel troopsPanel;
    private JLabel moneyLabel;
    protected JPanel logsTextArea;
    private Troop troopSelected;
    private DeployedTroopListener listener;
    protected PaintedPanelGame mainPanel;
    private JScrollPane scrollPane;
    protected GraphicsPanel graphicsPanel;
    protected PaintPanel logsPanel;
    private SaveGameDialog saveGameDialog;
    protected ButtonAnimation settingsButton;

    /**
     * Instantiates a new GameView.
     *
     * @param listener       the listener
     * @param winnerListener the winner listener
     */
    public GameView(DeployedTroopListener listener, UserComputerWinnerListener winnerListener) {
        frame = new JFrame();
        frame.setUndecorated(true);
        this.listener = listener;
        saveGameDialog = new SaveGameDialog(this.frame, winnerListener);

        initiateWallpaperPanel();
        initiateNorthPanel();
        initiateGamePanel();
        initiateGraphicsPanel();
        initiateSouthPanel();

        setSettingsFrame();
    }

    /**
     * Constructor for Class Extended ReplayView
     */
    public GameView() {
        frame = new JFrame();

        initiateWallpaperPanel();
        initiateNorthPanel();
        initiateGamePanel();
        initiateGraphicsPanel();
        initiateSouthPanel();

        setSettingsFrame();
    }

    /**
     * Method that updates the scroll bar.
     */
    public void updateVScrollBar() {
        logsPanel.revalidate();
        JScrollBar vScrollBar = scrollPane.getVerticalScrollBar();
        vScrollBar.setValue(vScrollBar.getMaximum());
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Method that initiate wallpaper panel.
     */
    public void initiateWallpaperPanel() {
        mainPanel = new PaintedPanelGame(WALLPAPER, WIDTH_FRAME, HEIGHT_FRAME);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
    }

    private void setSettingsButton(){
        settingsButton = new ButtonAnimation();
        settingsButton.setIcon(AJUSTES);
        settingsButton.setBorderPainted(false);
        settingsButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
        settingsButton.setFocusable(false);
    }

    /**
     * Method that initiate the north panel.
     */
    public void initiateNorthPanel(){
        PaintPanel buttonsPanel;
        buttonsPanel = new PaintPanel(EMPTY);
        buttonsPanel.setLayout(new BorderLayout());

        setSettingsButton();

        PaintPanel button = new PaintPanel(EMPTY);
        button.setLayout(new BorderLayout());
        button.add(settingsButton, BorderLayout.NORTH);

        buttonsPanel.setPreferredSize(new Dimension(879, 115));
        buttonsPanel.setMaximumSize(new Dimension(879, 115));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(button, BorderLayout.EAST);
        mainPanel.add(buttonsPanel, BorderLayout.NORTH);
    }

    /**
     * Method that initiate graphics panel.
     */
    public void initiateGraphicsPanel() {
        graphicsPanel = new GraphicsPanel();

        graphicsPanel.setPreferredSize(new Dimension(398, 250));
        graphicsPanel.setMaximumSize(new Dimension(398, 250));
        graphicsPanel.setOpaque(false);
        mainPanel.add(graphicsPanel, BorderLayout.EAST);
    }

    /**
     * Method that initiate south panel.
     */
    public void initiateSouthPanel() {
        JPanel southPanel;

        southPanel = new JPanel();

        troopsPanel = new JPanel();

        cardTroops = new CardTroop[NUMBER_TROOPS];
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        troopsPanel.setLayout(new GridLayout(1, NUMBER_TROOPS + 1));
        troopsPanel.setBorder(BorderFactory.createEmptyBorder(100, 25, 0, 0));

        troopsPanel.setOpaque(false);
        southPanel.setOpaque(false);

        southPanel.add(troopsPanel);
        southPanel.add(getLogsPanel());
        mainPanel.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Method that return the money label and money panel.
     *
     * @return the money panel
     */
    public PaintedPanelGame getMoneyPanel() {
        PaintedPanelGame moneyImagePanel;
        moneyImagePanel = new PaintedPanelGame(IMAGE_MONEY, 60, 75);
        moneyLabel = new JLabel();
        moneyLabel.setFont(moneyImagePanel.initiateFont(20));
        moneyLabel.setForeground(Color.white);
        moneyLabel.setBorder(BorderFactory.createEmptyBorder(25, 5, 0, 0));

        moneyImagePanel.add(moneyLabel);
        moneyImagePanel.setOpaque(false);
        return moneyImagePanel;
    }

    /**
     * Method that initiate game panel.
     */
    public void initiateGamePanel() {
        JPanel gamePanel;
        JPanel matrixPanel;

        gamePanel = new JPanel();
        matrixPanel = new JPanel();
        matrixSquaresPanels = new SquarePanel[ROWS][COLUMNS];

        matrixPanel.setLayout(new GridLayout(ROWS,COLUMNS));
        gamePanel.setOpaque(false);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                matrixSquaresPanels[i][j] = new SquarePanel(this, i, j);
                matrixSquaresPanels[i][j].setPreferredSize(new Dimension(33, 34));
                matrixSquaresPanels[i][j].setMaximumSize(new Dimension(33, 34));
                matrixSquaresPanels[i][j].setOpaque(true);
                matrixPanel.setOpaque(false);
                matrixPanel.add(matrixSquaresPanels[i][j]);
            }
        }
        gamePanel.add(matrixPanel);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
    }

    /**
     * Method that gets the logs panel where jlabels are created to show messages during game.
     *
     * @return the logs panel
     */
    public PaintPanel getLogsPanel() {
        PaintPanel east = new PaintPanel(EMPTY);
        east.setPreferredSize(new Dimension(15, 180));
        east.setMaximumSize(new Dimension(15, 180));

        PaintPanel west = new PaintPanel(EMPTY);
        west.setPreferredSize(new Dimension(50, 180));
        west.setMaximumSize(new Dimension(50, 180));

        PaintPanel south = new PaintPanel(EMPTY);
        south.setPreferredSize(new Dimension(320, 30));
        south.setMaximumSize(new Dimension(320, 30));

        logsTextArea = new JPanel();
        logsTextArea.setLayout(new BoxLayout(logsTextArea, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(logsTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        scrollPane.getViewport().getView().setBackground(CUSTOM_WHITE);

        logsPanel = new PaintPanel(EMPTY);
        logsPanel.setLayout(new BorderLayout());
        logsPanel.setPreferredSize(new Dimension(450, 180));
        logsPanel.setMaximumSize(new Dimension(450, 180));
        logsPanel.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
        scrollPane.setPreferredSize(new Dimension(450, 180));
        scrollPane.setMaximumSize(new Dimension(450, 180));


        logsPanel.add(scrollPane, BorderLayout.CENTER);
        logsPanel.add(east, BorderLayout.EAST);
        logsPanel.add(west, BorderLayout.WEST);
        logsPanel.add(south, BorderLayout.SOUTH);
        return logsPanel;
    }

    private void setSettingsFrame() {
        frame.getContentPane().add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    /**
     * Method that shows the View of the game.
     */
    public void show() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Method that add all the troops to its card panel.
     *
     * @param index the index
     * @param troop the troop
     */
    public void addTroopsToChoose(int index, Troop troop) {
        cardTroops[index] = new CardTroop(troop, getImageDefault(troop), getImageSelected(troop), this);
        cardTroops[index].setBackground(Color.WHITE);
        troopsPanel.add(cardTroops[index]);
        troopsPanel.setPreferredSize(new Dimension(430, 180));
        troopsPanel.setMaximumSize(new Dimension(430, 180));

        // Add MoneyPanel at last position of gridLayout
        if (index == NUMBER_TROOPS-1) {
            troopsPanel.add(getMoneyPanel());
        }
    }

    /**
     * Method that paint the image of the troop for first time and show it for logsPanel.
     *
     * @param troop  the troop
     * @param row    the row
     * @param column the column
     * @param user   the user
     */
    public void paintFirstTime(Troop troop, int row, int column, int user) {
        String url = troop.getUrl(user);
        Image image = new ImageIcon(url).getImage();
        matrixSquaresPanels[row][column].repaintTroop(image);
        addLogsTroopSummoned(troop, row, column);
    }

    private void addLogsTroopSummoned(Troop troop, int row, int column) {
        JLabel message = new JLabel("Troop " + troop.getName() + " summoned on row " + row + " and column " + column + "!");
        message.setForeground(CUSTOM_GREEN);
        logsTextArea.add(message);
        updateVScrollBar();
        updateVScrollBar();
    }

    private void addLogsCardSelected(Troop troop) {
        JLabel message = new JLabel(CARD_SELECTED + troop.getName());
        message.setForeground(CUSTOM_PURPLE);
        logsTextArea.add(message);
        updateVScrollBar();
        updateVScrollBar();
    }

    /**
     * Method that add logs label saying that player has not enough money.
     *
     * @param troop the troop
     */
    public void addLogsNotEnoughMoney(Troop troop) {
        JLabel messageError = new JLabel(NOT_MONEY + troop.getName() + "!");
        messageError.setForeground(CUSTOM_RED);
        logsTextArea.add(messageError);
        updateVScrollBar();
        updateVScrollBar();
    }

    private void addLogsTroopDefeated(Troop troop) {
        JLabel message = new JLabel("Troop " + troop.getName() + " defeated!");
        message.setForeground(CUSTOM_ORANGE);
        logsTextArea.add(message);
        updateVScrollBar();
        updateVScrollBar();
    }

    /**
     * Method that add logs label saying that player is trying to create a barrier.
     */
    public void addLogsBarrier() {
        JLabel messageError = new JLabel(CANT_SUMMON);
        messageError.setForeground(CUSTOM_RED);
        logsTextArea.add(messageError);
        updateVScrollBar();
        updateVScrollBar();
    }

    /**
     * Method that add logs label saying that player is trying to deploy a troop on a non empty square.
     */
    public void addLogsSquareNotEmpty() {
        JLabel messageError = new JLabel(CANT_SUMMON);
        messageError.setForeground(CUSTOM_RED);
        logsTextArea.add(messageError);
        updateVScrollBar();
        updateVScrollBar();
    }

    private Image getImageDefault(Troop troop) {
        String url = troop.getUrlCardDefault();
        return new ImageIcon(url).getImage();
    }

    private Image getImageSelected(Troop troop) {
        String url = troop.getUrlCardSelected();
        return new ImageIcon(url).getImage();
    }

    /**
     * Method that repaints two specific cells of the gamePanel with another troop. It repaints
     * empty the origin of the position and paint the troop on the destiny position.
     *
     * @param troop                the troop
     * @param row                  the row
     * @param column               the column
     * @param rowOriginToRemove    the row origin to remove
     * @param columnOriginToRemove the column origin to remove
     * @param user                 the user
     */
    public void repaintGameMatrix(Troop troop, int row, int column, int rowOriginToRemove, int columnOriginToRemove, int user) {
        String url = troop.getUrl(user);
        Image image = new ImageIcon(url).getImage();
        matrixSquaresPanels[row][column].repaintTroop(image);
        matrixSquaresPanels[rowOriginToRemove][columnOriginToRemove].repaintEmpty();
    }

    /**
     * Method that repaint empty a specific cell of the gamePanel and add a logs of the troop defeated.
     *
     * @param row    the row
     * @param column the column
     * @param troop  the troop
     */
    public void repaintRemoveTroop(int row, int column, Troop troop) {
        matrixSquaresPanels[row][column].repaintEmpty();
        addLogsTroopDefeated(troop);
    }

    /**
     * Method that repaints money label.
     *
     * @param money the money
     */
    public void repaintMoneyLabel(int money) {
        moneyLabel.setText(String.valueOf(money));
    }

    /**
     * Method that show the save game dialog.
     */
    public void showEndGame() {
        saveGameDialog.start();
    }

    /**
     * Method to dispose the frame.
     */
    public void closeGame() {
        frame.dispose();
    }

    /**
     * Method that gets the replay name written on the saveGameDialog.
     *
     * @return the record name
     */
    public String getRecordName() {
        return saveGameDialog.getNameRepetition();
    }

    /**
     * Method to register action listener.
     *
     * @param listener the listener
     */
    public void registerActionListener(ActionListener listener) {
        settingsButton.setActionCommand(EVENT_SETTINGS);
        settingsButton.addActionListener(listener);

        saveGameDialog.registerActionListeners(listener);
    }

    /**
     * Method that change graphics lives.
     *
     * @param life the life
     * @param user the user
     */
    public void changeGraphicsLives(int life, int user) {
        graphicsPanel.changeWidthLives(life, user);
    }

    /**
     * Method that change graphics troops.
     *
     * @param user       the user
     * @param moreOrLess more or less
     */
    public void changeGraphicsTroops(int user, int moreOrLess) {
        graphicsPanel.changeWidthTroops(user, moreOrLess);
    }

    @Override
    public void notifyTroopCardSelected(Troop troop) {
        if (troopSelected == null) {
            troopSelected = troop;
            for (int i = 0; i < NUMBER_TROOPS; i++) {
                cardTroops[i].setLocked();
            }
        }

        addLogsCardSelected(troop);
    }

    @Override
    public void notifySquareSelected(int row, int column) {
        if (troopSelected != null) {
            listener.notifyDeployedTroopUser(troopSelected, row, column, 0);

            troopSelected = null;
            for (int i = 0; i < NUMBER_TROOPS; i++) {
                cardTroops[i].resetImage();
                cardTroops[i].setFree();
            }
        }
    }
}