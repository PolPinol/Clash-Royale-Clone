package PresentationLayer.View.MainPanels;

import PresentationLayer.Controller.Listeners.LoginActionListener;
import PresentationLayer.View.Helpers.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Class that represents the UserPanel.
 *
 * The title consists of a jDialog. Below are two text fields that we have modified with
 * two classes (RoundJText and RoundJPassword).
 *
 * There are also listeners so that when the user has not written anything he can see what
 * he has to put in each section and in case he clicks on one he disappears and the text is
 * left and only what he has written remains.
 *
 * Then there is the jButton login which has also been modified to change shape and opacity, with this
 * we will access the program. Finally there is a jButton with a jLabel that shows the case to register,
 * from which you access the registration screen.
 */
public class UserPanel extends PaintPanel implements FocusListener {
    /**
     * The constant EVENT_PASSWORD.
     */
    public static final String EVENT_PASSWORD = "clickPassword";
    /**
     * The constant EVENT_EMAIL.
     */
    public static final String EVENT_EMAIL = "clickEmail";
    /**
     * The constant EVENT_LOGIN.
     */
    public static final String EVENT_LOGIN = "menuClick";
    /**
     * The constant EVENT_REGISTER.
     */
    public static final String EVENT_REGISTER = "menuRegistre";
    /**
     * The constant WALLPAPER.
     */
    public static final Image WALLPAPER = new ImageIcon("img/snapseed2.png").getImage();
    /**
     * The constant TITTLE.
     */
    public static final ImageIcon TITTLE = new ImageIcon("img/title.png");
    /**
     * The constant BUTTON.
     */
    public static final ImageIcon BUTTON = new ImageIcon("img/button3.gif");
    /**
     * The constant LOGIN.
     */
    public static final String LOGIN = "L O G I N";
    /**
     * The constant EMAIL_USER.
     */
    public static final String EMAIL_USER = "Email/User name";
    /**
     * The constant PASSWORD.
     */
    public static final String PASSWORD = "Password";
    /**
     * The constant QUESTION.
     */
    public static final String QUESTION = "¿No tienes una cuenta? ";
    /**
     * The constant REGISTER.
     */
    public static final String REGISTER = "Regístrate";
    /**
     * The constant CUSTOM_BLUE.
     */
    public static final Color CUSTOM_BLUE = new Color(0, 94, 145);
    /**
     * The constant CUSTOM_YELLOW.
     */
    public static final Color CUSTOM_YELLOW = new Color(253, 216, 53);

    private JLabel clash;
    private RoundJText userRoundJText;
    private RoundJPassword passwordRoundJPassword;
    private ButtonAnimation initiate;
    private RoundJPanel login;
    private JButton register;
    private LoginActionListener loginActionListener;

    /**
     * Instantiates a new UserPanel.
     */
    public UserPanel() {
        initiateAttributes();
        setSettings();

        setTitle();
        setUserPasswordPanels();
        setButtonLogin();
        setRegisterPanel();

        initiateListeners();

        addPanelsToFrame();
    }

    /**
     * Method that notifies to get the information to login.
     */
    public void getInformationUser() {
        loginActionListener.notifyLoginButton(userRoundJText.getText(), passwordRoundJPassword.getText());
    }

    private void setSettings() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setWallpaperImage(WALLPAPER);
    }

    private void addPanelsToFrame() {
        this.add(clash);
        this.add(userRoundJText);
        this.add(Box.createVerticalGlue());
        this.add(passwordRoundJPassword);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(login);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(register);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private void initiateAttributes() {
        clash = new JLabel();
        userRoundJText = new RoundJText(EMAIL_USER);
        passwordRoundJPassword = new RoundJPassword(PASSWORD);
        initiate = new ButtonAnimation();
        login = new RoundJPanel();
        register = new JButton();
    }

    private void setTitle() {
        clash.setIcon(TITTLE);
        clash.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void setUserPasswordPanels(){
        userRoundJText.setPreferredSize(new Dimension(260,30));
        userRoundJText.setMaximumSize(new Dimension(260,30));
        userRoundJText.setAlignmentX(CENTER_ALIGNMENT);

        passwordRoundJPassword.setPreferredSize(new Dimension(260,30));
        passwordRoundJPassword.setMaximumSize(new Dimension(260,30));
        passwordRoundJPassword.setAlignmentX(CENTER_ALIGNMENT);
        passwordRoundJPassword.setEchoChar((char)0);
    }

    private void setButtonLogin() {
        JLabel loginText = new JLabel(LOGIN);

        loginText.setForeground(CUSTOM_BLUE);
        loginText.setAlignmentX(CENTER_ALIGNMENT);
        loginText.setFont(initiateFont(15));

        initiate.setAlignmentX(CENTER_ALIGNMENT);
        initiate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        initiate.setIcon(BUTTON);
        initiate.add(loginText);

        login.setLayout(new BorderLayout());
        login.setMaximumSize(new Dimension(200,30));
        login.setPreferredSize(new Dimension(200,30));
        login.setBackground(CUSTOM_YELLOW);
        login.add(initiate, BorderLayout.CENTER);

        initiate.setOpaque(false);
        initiate.setContentAreaFilled(false);
        initiate.setBorderPainted(false);
    }

    private void setRegisterPanel() {
        JLabel question = new JLabel(QUESTION);
        question.setAlignmentX(CENTER_ALIGNMENT);
        question.setForeground(Color.WHITE);

        JLabel registrate = new JLabel(REGISTER);
        registrate.setAlignmentX(CENTER_ALIGNMENT);
        registrate.setForeground(CUSTOM_YELLOW);

        register.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        register.setAlignmentX(CENTER_ALIGNMENT);
        register.add(question);
        register.add(registrate);
        register.setBorder(new LineBorder(CUSTOM_BLUE));
        register.setMaximumSize(new Dimension(350,60));
        register.setPreferredSize(new Dimension(350,60));
        register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        register.setOpaque(false);
        register.setContentAreaFilled(false);
    }

    private void initiateListeners(){
        initiateEmailListener();
        initiatePasswordListener();
    }

    /**
     * Method to register action listeners.
     *
     * @param loginActionListener   the login action listener
     * @param listener              the ActionListener
     */
    public void registerActionListeners(LoginActionListener loginActionListener, ActionListener listener) {
        this.loginActionListener = loginActionListener;

        initiate.setActionCommand(EVENT_LOGIN);
        initiate.addActionListener(listener);

        register.setActionCommand(EVENT_REGISTER);
        register.addActionListener(listener);
    }

    private void initiateEmailListener(){
        userRoundJText.addFocusListener(this);
        userRoundJText.setActionCommand(EVENT_EMAIL);
    }

    private void initiatePasswordListener(){
        passwordRoundJPassword.addFocusListener(this);
        passwordRoundJPassword.setActionCommand(EVENT_PASSWORD);
    }

    /**
     * Method to clear all fields from the user panel.
     */
    public void clearFields() {
        userRoundJText.setText(EMAIL_USER);
        userRoundJText.setForeground(Color.gray);

        passwordRoundJPassword.setText(PASSWORD);
        passwordRoundJPassword.setForeground(Color.gray);
        passwordRoundJPassword.setEchoChar((char)0);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getComponent() == userRoundJText) {
            userRoundJText.setText("");
            userRoundJText.setForeground(Color.black);
        }
        if (e.getComponent() == passwordRoundJPassword) {
            passwordRoundJPassword.setText("");
            passwordRoundJPassword.setForeground(Color.black);
            passwordRoundJPassword.setEchoChar('*');
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getComponent() == userRoundJText) {
            if (userRoundJText.getText().equals("")) {
                userRoundJText.setText(EMAIL_USER);
                userRoundJText.setForeground(Color.gray);
            }
        }
        if (e.getComponent() == passwordRoundJPassword) {
            if (passwordRoundJPassword.getText().equals("")) {
                passwordRoundJPassword.setText(PASSWORD);
                passwordRoundJPassword.setForeground(Color.gray);
                passwordRoundJPassword.setEchoChar((char)0);
            }
        }
    }
}