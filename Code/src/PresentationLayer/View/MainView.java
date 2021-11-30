package PresentationLayer.View;

import PresentationLayer.Controller.Listeners.DeleteActionListener;
import PresentationLayer.Controller.Listeners.LoginActionListener;
import PresentationLayer.Controller.Listeners.OpenUserHistoryListener;
import PresentationLayer.Controller.Listeners.RegisterActionListener;
import PresentationLayer.View.Helpers.SettingsDialog;
import PresentationLayer.View.Listeners.ExtractDataListener;
import PresentationLayer.View.Listeners.SelectedRecordListener;
import PresentationLayer.View.MainPanels.*;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;


/**
 * Class MainView.
 *
 * This class is a jFrame that contains all the panels (home screen, login, register, menu, ranking, history).
 * A CardLayout was used to have all these panels in the same jFrame.
 */
public class MainView {
    /**
     * The constant CARD_LOGO.
     */
    public static final String CARD_LOGO = "cardLogo";
    /**
     * The constant CARD_MENU.
     */
    public static final String CARD_MENU = "cardMenu";
    /**
     * The constant CARD_USER.
     */
    public static final String CARD_USER = "cardUser";
    /**
     * The constant CARD_REGISTER.
     */
    public static final String CARD_REGISTER = "cardRegister";
    /**
     * The constant CARD_HISTORY.
     */
    public static final String CARD_HISTORY = "cardRecords";
    /**
     * The constant CARD_RANKING.
     */
    public static final String CARD_RANKING = "cardRanking";
    /**
     * The constant ERROR_USER_DOESNT_EXIST.
     */
    public static final String ERROR_USER_DOESNT_EXIST = "El usuario no existe!";
    /**
     * The constant ERROR_USER_TITLE.
     */
    public static final String ERROR_USER_TITLE = "USER ERROR!";
    /**
     * The constant ERROR_PASSWORD.
     */
    public static final String ERROR_PASSWORD= "La contraseña no es válida!";
    /**
     * The constant ERROR_PASSWORD_TITLE.
     */
    public static final String ERROR_PASSWORD_TITLE = "PASSWORD ERROR!";
    /**
     * The constant ERROR_USER_EXISTS.
     */
    public static final String ERROR_USER_EXISTS = "El nombre de usuario ya existe!";
    /**
     * The constant ERROR_MAIL_EXISTS.
     */
    public static final String ERROR_MAIL_EXISTS = "El email de usuario ya existe!";
    /**
     * The constant ERROR_TITLE.
     */
    public static final String ERROR_TITLE = "ERROR!";
    /**
     * The constant CUSTOM_BLUE.
     */
    public static final Color CUSTOM_BLUE = new Color(0, 75, 133);

    private JFrame frame;
    private CardLayout cardManager;
    private LogoPanel logoPanel;
    private UserPanel userPanel;
    private MenuPanel menuPanel;
    private HistoryPanel historyPanel;
    private RankingPanel rankingPanel;
    private RegisterPanel registerPanel;
    private InstructionsDialog instructionsDialog;
    private SettingsDialog settings;

    /**
     * Instantiates a new MainView.
     */
    public MainView() {
        initiateAttributes();
        initiatePanels();
        settingsFrame();
        initiateRegisterPanelCard();
    }

    /**
     * Method that starts the first view and show visible the frame.
     */
    public void show() {
        showCard(CARD_LOGO);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Method that swap card using the CardLayout.
     *
     * @param card the card
     */
    public void showCard(String card) {
        switch (card) {
            case CARD_LOGO -> showCardLogo();
            case CARD_MENU -> showCardMenu();
            case CARD_USER -> showCardUser();
            case CARD_REGISTER -> showCardRegister();
            case CARD_HISTORY -> showCardHistory();
            case CARD_RANKING -> showCardRanking();
        }
    }

    private void initiateAttributes() {
        frame = new JFrame();
        cardManager = new CardLayout();
        frame.setLayout(cardManager);
    }

    private void initiatePanels() {
        initiateLogoPanelCard();
        initiateMenuPanelCard();
        initiateUserPanelCard();
        initiateRecordPanelCard();
        initiateRankingPanelCard();
        initInfoDialog();
        initiateSettingsDialog();
    }

    private void settingsFrame() {
        frame.setBackground(CUSTOM_BLUE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    private void showCardLogo() {
        cardManager.show(frame.getContentPane(), CARD_LOGO);
    }

    private void showCardMenu() {
        cardManager.show(frame.getContentPane(), CARD_MENU);
    }

    private void showCardUser() {
        userPanel.clearFields();
        cardManager.show(frame.getContentPane(), CARD_USER);
    }

    private void showCardRegister() {
        registerPanel.clearFields();
        cardManager.show(frame.getContentPane(), CARD_REGISTER);
    }

    private void showCardHistory() {
        cardManager.show(frame.getContentPane(), CARD_HISTORY);
    }

    private void showCardRanking() {
        cardManager.show(frame.getContentPane(), CARD_RANKING);
    }

    private void initiateSettingsDialog(){
        settings = new SettingsDialog();
    }

    private void initiateLogoPanelCard() {
        logoPanel = new LogoPanel();
        frame.getContentPane().add(logoPanel, CARD_LOGO);
    }

    private void initiateMenuPanelCard() {
        menuPanel = new MenuPanel();
        frame.getContentPane().add(menuPanel, CARD_MENU);
    }

    private void initiateUserPanelCard(){
        userPanel = new UserPanel();
        frame.getContentPane().add(userPanel, CARD_USER);
    }

    private void initiateRecordPanelCard(){
        historyPanel = new HistoryPanel();
        frame.getContentPane().add(historyPanel, CARD_HISTORY);
    }

    private void initiateRankingPanelCard(){
        rankingPanel = new RankingPanel();
        frame.getContentPane().add(rankingPanel, CARD_RANKING);
    }

    /**
     * Method to activate the action of the button.
     */
    public void actionButtonRegister() {
        registerPanel.actionButton();
    }

    /**
     * Initiate register panel card.
     */
    public void initiateRegisterPanelCard(){
        registerPanel = new RegisterPanel();
        frame.getContentPane().add(registerPanel, CARD_REGISTER);
    }

    /**
     * Method to activate the action when a cell  is selected.
     */
    public void actionSelectedCell() {
        rankingPanel.actionSelectedCell();
    }

    /**
     * Method to get eh information from the user to login.
     */
    public void getInformationUser() {
        userPanel.getInformationUser();
    }

    /**
     * Initiate information dialog.
     */
    public void initInfoDialog() {
        instructionsDialog = new InstructionsDialog(frame);
    }

    /**
     * Start settings dialog.
     */
    public void startDialog() {
        settings.start();
    }

    /**
     * Close settings dialog.
     */
    public void closeDialog(){
        settings.setVisible(false);
    }

    /**
     * Register listeners settings dialog.
     *
     * @param listener             the listener
     * @param deleteActionListener the delete action listener
     */
    public void registerSettingsDialog(ActionListener listener, DeleteActionListener deleteActionListener){
        settings.registerActionListeners(listener, deleteActionListener);
    }

    /**
     * Register listeners logo panel.
     *
     * @param listener the listener
     */
    public void registerLogoPanel(MouseListener listener){
        logoPanel.registerActionListeners(listener);
    }

    /**
     * Register listeners user panel.
     *
     * @param listener the listener
     * @param actionListener the actionListener
     */
    public void registerUserPanel(LoginActionListener listener, ActionListener actionListener) {
        userPanel.registerActionListeners(listener, actionListener);
    }

    /**
     * Register listeners menu panel.
     *
     * @param listener the listener
     */
    public void registerMenuPanel(ActionListener listener) {
        menuPanel.registerActionListeners(listener);
    }

    /**
     * Sets record listeners.
     *
     * @param listener     the listener
     * @param listListener the list listener
     */
    public void setRecordListeners(ActionListener listener, ListSelectionListener listListener) {
        historyPanel.setActionListeners(listener);
        historyPanel.setSelectionListener(listListener);
    }

    /**
     * Register listeners ranking panel.
     *
     * @param listener                the listener
     * @param openUserHistoryListener the open user history listener
     * @param mouseListener           the mouseListener
     */
    public void registerRankingPanel(ActionListener listener, OpenUserHistoryListener openUserHistoryListener,
                                     MouseListener mouseListener){
        rankingPanel.setActionListeners(listener, mouseListener);
        rankingPanel.registerOpenHistoryListener(openUserHistoryListener);
    }

    /**
     * Register listeners InstructionsDialog.
     *
     * @param listener the listener
     */
    public void registerInfoListener(ActionListener listener) {
        instructionsDialog.registerActionListeners(listener);
    }

    /**
     * Gets record listener.
     *
     * @return the record listener
     */
    public SelectedRecordListener getRecordListener() {
        return historyPanel;
    }

    /**
     * Get data listener extract data listener.
     *
     * @return the extract data listener
     */
    public ExtractDataListener getDataListener(){
        return rankingPanel;
    }

    /**
     * Show InstructionsDialog.
     */
    public void showInfoDialog() {
        instructionsDialog.showTrue();
    }

    /**
     * Close InstructionsDialog.
     */
    public void stopInfoDialog() {
        instructionsDialog.showFalse();
    }

    /**
     * Sets visible true.
     */
    public void setVisibleTrue() {
        frame.setVisible(true);
    }

    /**
     * Sets visible false.
     */
    public void setVisibleFalse() {
        frame.setVisible(false);
    }

    /**
     * Register register panel.
     *
     * @param listener      the listener
     * @param mouseListener the mouseListener
     */
    public void registerRegisterPanel(RegisterActionListener listener, MouseListener mouseListener) {
        registerPanel.registerListeners(listener, mouseListener);
    }

    /**
     * Show error existing user login.
     */
    public void showErrorExistingUserLogin() {
        JOptionPane.showMessageDialog(null, ERROR_USER_DOESNT_EXIST,
                ERROR_USER_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show error matching password.
     */
    public void showErrorMatchingPassword() {
        JOptionPane.showMessageDialog(null, ERROR_PASSWORD,
                ERROR_PASSWORD_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show error existing user register.
     */
    public void showErrorExistingUserRegister() {
        JOptionPane.showMessageDialog(null, ERROR_USER_EXISTS,
                ERROR_USER_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show error existing email register.
     */
    public void showErrorExistingEmailRegister() {
        JOptionPane.showMessageDialog(null, ERROR_MAIL_EXISTS,
                ERROR_USER_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Method that shows with a JOptionPane that exists sql problems.
     *
     * @param message the message
     */
    public void existsSQLProblem(String message) {
        this.setVisibleFalse();
        JOptionPane.showMessageDialog(null, message, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }
}
