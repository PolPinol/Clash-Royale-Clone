package PresentationLayer.View.MainPanels;

import PresentationLayer.Controller.Listeners.RegisterActionListener;
import PresentationLayer.View.Helpers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

/**
 * Class that represents the RegisterPanel.
 *
 * This card consists of a main jPanel that contains two more panels that each divide the total contents
 * of the card, this panel is assigned a BoxLayout in X_AXIS.
 *
 * In the jPanel on the left we find all the elements to write the information of the register, these are
 * the same as in the case of the login. This panel also consists of a BoxLayout so that each added item
 * is below each other.
 *
 * Finally we find the "next" button to save the information.
 */
public class RegisterPanel extends PaintPanel implements FocusListener {
    /**
     * The constant EVENT_USER.
     */
    public static final String EVENT_USER = "userClick";
    /**
     * The constant EVENT_EMAIL.
     */
    public static final String EVENT_EMAIL = "emailClick";
    /**
     * The constant EVENT_PASSWORD.
     */
    public static final String EVENT_PASSWORD = "passwordClick";
    /**
     * The constant EVENT_CONFIRMATION.
     */
    public static final String EVENT_CONFIRMATION = "confirmationClick";
    /**
     * The constant PRESSED.
     */
    public static final String PRESSED = "nextClick";
    /**
     * The constant PASSWORD_ERROR_TITLE.
     */
    public static final String PASSWORD_ERROR_TITLE = "PASSWORD ERROR!";
    /**
     * The constant INFO_ERROR_TITLE.
     */
    public static final String INFO_ERROR_TITLE = "INFO ERROR!";
    /**
     * The constant ERROR_MESSAGE.
     */
    public static final String ERROR_MESSAGE = "Falta introducir:\n";
    /**
     * The constant ERROR_NUMBER.
     */
    public static final String ERROR_NUMBER = "- Número\n";
    /**
     * The constant ERROR_CAPITAL_LETTER.
     */
    public static final String ERROR_CAPITAL_LETTER = "- Carácter/-es en mayuscula\n";
    /**
     * The constant ERROR_LOWER_CASE.
     */
    public static final String ERROR_LOWER_CASE = "- Carácter/-es en minúscula\n";
    /**
     * The constant ERROR_LARGE_PASSWORD.
     */
    public static final String ERROR_LARGE_PASSWORD = "- 8 carácteres mínimos";
    /**
     * The constant ERROR_MAIL.
     */
    public static final String ERROR_MAIL = "Email incorrecto";
    /**
     * The constant ERROR_MAIL_TITLE.
     */
    public static final String ERROR_MAIL_TITLE = "EMAIL ERROR!";
    /**
     * The constant ERROR_USER_NAME.
     */
    public static final String ERROR_USER_NAME = "- Nombre de usuario\n";
    /**
     * The constant ERROR_USER_EMAIL.
     */
    public static final String ERROR_USER_EMAIL = "- Email\n";
    /**
     * The constant ERROR_USER_PASSWORD.
     */
    public static final String ERROR_USER_PASSWORD = "- Contraseña\n";
    /**
     * The constant ERROR_USER_CONFIRMATION_PASS.
     */
    public static final String ERROR_USER_CONFIRMATION_PASS = "- Confirmacion de la contraseña";
    /**
     * The constant ERROR_PASS_NOT_MATCH.
     */
    public static final String ERROR_PASS_NOT_MATCH = "Las contraseñas no coinciden!";
    /**
     * The constant ACCOUNT_MESSAGE.
     */
    public static final String ACCOUNT_MESSAGE = "Crea una cuenta";
    /**
     * The constant USER.
     */
    public static final String USER = "User name";
    /**
     * The constant EMAIL.
     */
    public static final String EMAIL = "Email";
    /**
     * The constant PASSWORD.
     */
    public static final String PASSWORD = "Password";
    /**
     * The constant PASSWORD_CONFIRMATION.
     */
    public static final String PASSWORD_CONFIRMATION = "Repeat password";
    /**
     * The constant NEXT.
     */
    public static final String NEXT = "Siguiente";
    /**
     * The constant WALLPAPER.
     */
    public static final Image WALLPAPER = new ImageIcon("img/snapseed2.png").getImage();
    /**
     * The constant TITLE.
     */
    public static final ImageIcon TITLE = new ImageIcon("img/title.png");
    /**
     * The constant BUTTON.
     */
    public static final ImageIcon BUTTON = new ImageIcon("img/button3.gif");
    /**
     * The constant ILLUSTRATION.
     */
    public static final ImageIcon ILLUSTRATION = new ImageIcon("img/ingeniero2.png");
    /**
     * The constant HEIGHT_BOX.
     */
    public static final int HEIGHT_BOX = 30;
    /**
     * The constant WIDTH_BOX.
     */
    public static final int WIDTH_BOX = 260;
    /**
     * The constant WIDTH_PANEL.
     */
    public static final int WIDTH_PANEL = 700;
    /**
     * The constant HEIGHT_PANEL.
     */
    public static final int HEIGHT_PANEL = 350;
    /**
     * The constant CUSTOM_BLUE.
     */
    public static final Color CUSTOM_BLUE = new Color(0, 137, 201);
    /**
     * The constant CUSTOM_YELLOW.
     */
    public static final Color CUSTOM_YELLOW = new Color(253, 216, 53);

    private ButtonAnimation nextButton;
    private RoundJText userRoundJText;
    private RoundJText emailRoundJText;
    private RoundJPassword passwordRoundJPassword;
    private RoundJPassword confirmationRoundJPassword;
    private JPanel panelButton;
    private PaintPanel informationPanel;
    private PaintPanel characterPanel;
    private RegisterActionListener registerActionListener;

    /**
     * Instantiates a new RegisterPanel.
     */
    public RegisterPanel(){
        initiateAttributes();
        setSettings();

        setInformationPaintPanel();
        setImagePaintPanel();

        registerLocalListeners();

        addPanelsToFrame();
    }

    private void addPanelsToFrame() {
        this.add(informationPanel);
        this.add(characterPanel);
    }

    private void setSettings() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));
        this.setPreferredSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));
    }

    private void initiateAttributes() {
        informationPanel = new PaintPanel(WALLPAPER);
        characterPanel = new PaintPanel(WALLPAPER);
        userRoundJText = new RoundJText(USER);
        emailRoundJText = new RoundJText(EMAIL);
        passwordRoundJPassword = new RoundJPassword(PASSWORD);
        confirmationRoundJPassword = new RoundJPassword(PASSWORD_CONFIRMATION);
        nextButton = new ButtonAnimation();
        panelButton = new RoundJPanel();
    }

    private JLabel getJLabelTitle() {
        JLabel clash;
        clash = new JLabel();
        clash.setIcon(TITLE);
        clash.setAlignmentX(CENTER_ALIGNMENT);

        return clash;
    }

    private JLabel getJLabelSubTitle() {
        JLabel account;
        account = new JLabel(ACCOUNT_MESSAGE);
        account.setAlignmentX(CENTER_ALIGNMENT);
        account.setFont(initiateFont(15));
        account.setForeground(Color.white);

        return account;
    }

    private void setAllBoxes() {
        setBoxTextField(userRoundJText);
        setBoxTextField(emailRoundJText);
        setBoxPasswordField(passwordRoundJPassword);
        setBoxPasswordField(confirmationRoundJPassword);
    }

    private void setBoxPasswordField(RoundJPassword box) {
        box.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box.setPreferredSize(new Dimension(WIDTH_BOX, HEIGHT_BOX));
        box.setMaximumSize(new Dimension(WIDTH_BOX, HEIGHT_BOX));
    }

    private void setBoxTextField(RoundJText box) {
        box.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box.setPreferredSize(new Dimension(WIDTH_BOX, HEIGHT_BOX));
        box.setMaximumSize(new Dimension(WIDTH_BOX, HEIGHT_BOX));
    }

    private void setButtonNext() {
        JLabel nextMessage;
        nextMessage = new JLabel(NEXT, SwingConstants.CENTER);
        nextMessage.setAlignmentX(CENTER_ALIGNMENT);
        nextMessage.setForeground(CUSTOM_BLUE);
        nextMessage.setFont(initiateFont(9));

        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextButton.setEnabled(false);
        nextButton.setIcon(BUTTON);
        nextButton.setDisabledIcon(BUTTON);
        nextButton.add(nextMessage);

        panelButton.setLayout(new BorderLayout());
        panelButton.setBackground(CUSTOM_YELLOW);
        panelButton.add(nextButton, BorderLayout.CENTER);
        panelButton.setPreferredSize(new Dimension(100,30));
        panelButton.setMaximumSize(new Dimension(100,30));
    }

    private void setInformationPaintPanel(){
        setAllBoxes();
        setButtonNext();

        setSettingsInformationPaintPanel();

        addPanelsToInformationPaintPanel();
    }

    private void setSettingsInformationPaintPanel() {
        informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
        informationPanel.setPreferredSize(new Dimension(370,350));
        informationPanel.setMaximumSize(new Dimension(370,350));
    }

    private void addPanelsToInformationPaintPanel() {
        informationPanel.add(getJLabelTitle());
        informationPanel.add(getJLabelSubTitle());
        informationPanel.add(Box.createVerticalGlue());
        informationPanel.add(userRoundJText);
        informationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        informationPanel.add(emailRoundJText);
        informationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        informationPanel.add(passwordRoundJPassword);
        informationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        informationPanel.add(confirmationRoundJPassword);
        informationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        informationPanel.add(panelButton);
        informationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void setImagePaintPanel() {
        characterPanel.add(getCharacterDrawn());
        characterPanel.setPreferredSize(new Dimension(330,350));
        characterPanel.setMaximumSize(new Dimension(330,350));
    }

    private JLabel getCharacterDrawn() {
        JLabel character;
        character = new JLabel();
        character.setPreferredSize(new Dimension(330,350));
        character.setMaximumSize(new Dimension(330,350));
        character.setIcon(ILLUSTRATION);

        return character;
    }

    private void registerLocalListeners() {
        registerUserListener();
        registerEmailListener();
        registerPasswordListener();
        registerPasswordConfirmationListener();
    }

    private void registerUserListener() {
        userRoundJText.addFocusListener(this);
        userRoundJText.setActionCommand(EVENT_USER);
    }

    private void registerEmailListener() {
        emailRoundJText.addFocusListener(this);
        emailRoundJText.setActionCommand(EVENT_EMAIL);
    }

    private void registerPasswordListener() {
        passwordRoundJPassword.addFocusListener(this);
        passwordRoundJPassword.setActionCommand(EVENT_PASSWORD);
    }

    private void registerPasswordConfirmationListener() {
        confirmationRoundJPassword.addFocusListener(this);
        confirmationRoundJPassword.setActionCommand(EVENT_CONFIRMATION);
    }

    /**
     * Method to register listeners.
     *
     * @param registerActionListener    the register action listener
     * @param mouseListener             the MouseListener
     */
    public void registerListeners(RegisterActionListener registerActionListener, MouseListener mouseListener) {
        this.registerActionListener = registerActionListener;

        nextButton.addMouseListener(mouseListener);
        nextButton.setActionCommand(PRESSED);
    }

    /**
     * Method that manages the action to throw different errors on the register actions.
     */
    public void actionButton() {
        String allPossibleErrors = getAllErrors();

        if (!allPossibleErrors.isEmpty()) {
            String message = ERROR_MESSAGE + allPossibleErrors;
            JOptionPane.showMessageDialog(null, message,INFO_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
        }

        if (allPossibleErrors.isEmpty() && isPasswordCorrect()) {
            if (isPasswordSameAsConfirmationPassword()) {
                registerActionListener.notifyRegisterButton(userRoundJText.getText(), emailRoundJText.getText(), passwordRoundJPassword.getText());
            } else {
                JOptionPane.showMessageDialog(null, ERROR_PASS_NOT_MATCH,
                        PASSWORD_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isPasswordCorrect() {
        boolean characterLowerCase = false;
        boolean characterCapitalLetter = false;
        boolean numbers = false;
        boolean largePassword = false;

        String allPossibleErrors;
        String errorMessage;
        String passwordText = passwordRoundJPassword.getText();
        char character;

        for (int i = 0; i < passwordText.length(); i++) {
            character = passwordText.charAt(i);

            if ((character - '0') >= 0 && (character - '0') <= 9) {
                numbers = true;
            }
            if(character >= 'A' && character <= 'Z'){
                characterCapitalLetter = true;
            }
            if(character >= 'a' && character <= 'z'){
                characterLowerCase = true;
            }
        }
        if (passwordText.length() >= 8) {
            largePassword = true;
        }

        allPossibleErrors = getAllPasswordErrors(numbers, characterCapitalLetter, characterLowerCase, largePassword);
        if (!allPossibleErrors.isEmpty() && !passwordText.equals(PASSWORD)) {
            errorMessage = ERROR_MESSAGE + allPossibleErrors;
            JOptionPane.showMessageDialog(null, errorMessage, PASSWORD_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
        }

        return numbers && characterCapitalLetter && characterLowerCase && largePassword;
    }

    private String getAllPasswordErrors(boolean numbers, boolean characterCapitalLetter, boolean characterLowerCase, boolean largePassword) {
        String allPossibleErrors = "";

        if (!numbers) {
            allPossibleErrors = ERROR_NUMBER;
        }
        if (!characterCapitalLetter) {
            allPossibleErrors = allPossibleErrors + ERROR_CAPITAL_LETTER;
        }
        if (!characterLowerCase) {
            allPossibleErrors = allPossibleErrors + ERROR_LOWER_CASE;
        }
        if (!largePassword) {
            allPossibleErrors = allPossibleErrors + ERROR_LARGE_PASSWORD;
        }

        return allPossibleErrors;
    }

    private void setFocusGainedPasswordField(RoundJPassword roundJPassword) {
        char[] password = roundJPassword.getPassword();
        String text = new String(password);

        if (text.equals(PASSWORD) || text.equals(PASSWORD_CONFIRMATION)) {
            roundJPassword.setEchoChar('*');
            roundJPassword.setText("");
            roundJPassword.setForeground(Color.BLACK);
        }
    }

    private void setFocusGainedTextField(RoundJText roundJText) {
        String text = roundJText.getText();
        if (text.equals(USER) || text.equals(EMAIL)) {
            roundJText.setText("");
            roundJText.setForeground(Color.BLACK);
        }
    }

    private void setFocusLost(FocusEvent e) {
        if (e.getComponent() == userRoundJText) {
            resetTextField(USER, userRoundJText);
        } else if (e.getComponent() == confirmationRoundJPassword) {
            resetPasswordField(PASSWORD_CONFIRMATION, confirmationRoundJPassword);
        } else if (e.getComponent() == passwordRoundJPassword) {
            resetPasswordField(PASSWORD, passwordRoundJPassword);
        } else {
            resetTextField(EMAIL, emailRoundJText);

            if (!emailRoundJText.getText().equals(EMAIL) && !emailRoundJText.getText().contains("@")) {
                JOptionPane.showMessageDialog(null, ERROR_MAIL, ERROR_MAIL_TITLE, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetTextField(String defaultText, RoundJText roundJText) {
        String text;
        text = roundJText.getText();
        if (text.equals("")) {
            roundJText.setText(defaultText);
            roundJText.setForeground(Color.gray);
        }
    }

    private void resetPasswordField(String defaultText, RoundJPassword passwordRoundJPassword) {
        String text;
        char[] password = passwordRoundJPassword.getPassword();
        text = new String(password);
        if (text.equals("")) {
            passwordRoundJPassword.setText(defaultText);
            passwordRoundJPassword.setForeground(Color.gray);
            passwordRoundJPassword.setEchoChar((char)0);
        }
    }

    private boolean isPasswordSameAsConfirmationPassword() {
        char[] passwordConfirm = confirmationRoundJPassword.getPassword();
        String password1 = new String(passwordConfirm);

        char[] password = passwordRoundJPassword.getPassword();
        String password2 = new String(password);

        return password1.equals(password2);
    }

    private String getAllErrors() {
        String userError = "";
        String emailError = "";
        String passwordError = "";
        String passwordConfirmationError = "";
        String userText = userRoundJText.getText();
        String emailText = emailRoundJText.getText();

        char[] password = passwordRoundJPassword.getPassword();
        String passwordText = new String(password);

        char[] passwordConfirm = confirmationRoundJPassword.getPassword();
        String confirmationText = new String(passwordConfirm);

        if (userText.equals(USER) || userText.isEmpty()) {
            userError = ERROR_USER_NAME;
        }

        if (emailText.equals(EMAIL) || emailText.isEmpty()) {
            emailError = ERROR_USER_EMAIL;
        }

        if (passwordText.equals(PASSWORD) || passwordText.isEmpty()) {
            passwordError = ERROR_USER_PASSWORD;
        }

        if (confirmationText.equals(PASSWORD_CONFIRMATION) || confirmationText.isEmpty()) {
            passwordConfirmationError = ERROR_USER_CONFIRMATION_PASS;
        }

        return userError + emailError + passwordError + passwordConfirmationError;
    }

    /**
     * Method to clear all fields on the register panel.
     */
    public void clearFields() {
        userRoundJText.setText(USER);
        userRoundJText.setForeground(Color.gray);

        emailRoundJText.setText(EMAIL);
        emailRoundJText.setForeground(Color.gray);

        passwordRoundJPassword.setText(PASSWORD);
        passwordRoundJPassword.setForeground(Color.gray);
        passwordRoundJPassword.setEchoChar((char)0);

        confirmationRoundJPassword.setText(PASSWORD_CONFIRMATION);
        confirmationRoundJPassword.setForeground(Color.gray);
        confirmationRoundJPassword.setEchoChar((char)0);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getComponent() == passwordRoundJPassword || e.getComponent() == confirmationRoundJPassword) {
            setFocusGainedPasswordField((RoundJPassword) e.getComponent());
        } else {
            setFocusGainedTextField((RoundJText) e.getComponent());
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        setFocusLost(e);
    }
}