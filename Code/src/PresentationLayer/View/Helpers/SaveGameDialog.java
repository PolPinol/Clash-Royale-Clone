package PresentationLayer.View.Helpers;

import PresentationLayer.Controller.Listeners.UserComputerWinnerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Class SaveGameDialog. This is a class with the objective to customize the JDialog.
 *
 * This class will appear when a game is finished, and ask if the user wants to
 * save the game. It gives the option to introduce a name and save it or not.
 */
public class SaveGameDialog extends JDialog {
    /**
     * The constant DEFAULT_FONT.
     */
    public static final String DEFAULT_FONT = "Arial";
    /**
     * The constant FONT_PATH.
     */
    public static final String FONT_PATH = "font/titleFont.ttf";
    /**
     * The constant FONT_SIZE.
     */
    public static final int FONT_SIZE = 15;
    /**
     * The constant BUTTON.
     */
    public static final ImageIcon BUTTON = new ImageIcon("img/button3.gif");
    /**
     * The constant SECOND_TEXT.
     */
    public static final String SECOND_TEXT = "Guardar Partida?";
    /**
     * The constant YES_ANSWER.
     */
    public static final String YES_ANSWER = "dialogo_si";
    /**
     * The constant NO_ANSWER.
     */
    public static final String NO_ANSWER = "dialogo_no";
    /**
     * The constant YES_STRING.
     */
    public static final String YES_STRING = "Si";
    /**
     * The constant NO_STRING.
     */
    public static final String NO_STRING = "No";
    /**
     * The constant WIDTH_DIALOG.
     */
    public static final int WIDTH_DIALOG = 320;
    /**
     * The constant HEIGHT_DIALOG.
     */
    public static final int HEIGHT_DIALOG = 270;
    /**
     * The constant CUSTOM_BLUE.
     */
    public static final Color CUSTOM_BLUE = new Color(0, 94, 145);
    /**
     * The constant CUSTOM_YELLOW.
     */
    public static final Color CUSTOM_YELLOW = new Color(253, 216, 53);

    private RoundJText nameRoundJText;
    private ButtonAnimation yesButton;
    private ButtonAnimation noButton;
    private RoundJPanel yesPanel;
    private RoundJPanel noPanel;
    private JComponent component;
    private final UserComputerWinnerListener winnerListener;

    /**
     * Instantiates a new SaveGameDialog.
     *
     * @param owner          the owner
     * @param winnerListener the winner listener
     */
    public SaveGameDialog(JFrame owner, UserComputerWinnerListener winnerListener) {
        super(owner);
        this.setUndecorated(true);
        initObjects();
        setSettings();

        setNamePanel();
        setButtons();

        this.winnerListener = winnerListener;
    }

    /**
     * Method to show the JDialog.
     */
    public void start() {
        addToFrame();
        setLocationRelativeTo(getOwner());
        setVisible(true);
        this.revalidate();
    }

    private void initObjects() {
        nameRoundJText = new RoundJText("");
        component = new ComponentWithWallpaper();
        yesButton = new ButtonAnimation();
        yesPanel = new RoundJPanel();
        noButton = new ButtonAnimation();
        noPanel = new RoundJPanel();
    }

    private void setSettings() {
        setContentPane(component);
        setMinimumSize(new Dimension(WIDTH_DIALOG, HEIGHT_DIALOG));
        setPreferredSize(new Dimension(WIDTH_DIALOG, HEIGHT_DIALOG));
        setMaximumSize(new Dimension(WIDTH_DIALOG, HEIGHT_DIALOG));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        pack();
    }

    private void addToFrame() {
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(firstTextLabel());
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(secondTextLabel());
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(nameRoundJText);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(buttonsPanel());
    }

    private JLabel firstTextLabel() {
        String winner = winnerListener.notifyWhoIsWinner();
        JLabel jLabel = new JLabel(winner);

        jLabel.setFont(initiateFont(FONT_SIZE));
        jLabel.setForeground(Color.white);
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return jLabel;
    }

    private JLabel secondTextLabel() {
        JLabel jLabel = new JLabel(SECOND_TEXT);

        jLabel.setFont(initiateFont(FONT_SIZE));
        jLabel.setForeground(Color.white);
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return jLabel;
    }

    private void setNamePanel() {
        nameRoundJText.setPreferredSize(new Dimension(260,30));
        nameRoundJText.setMaximumSize(new Dimension(260,30));
        nameRoundJText.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void setButtons() {
        setOptionButton(YES_STRING, yesButton, yesPanel);
        setOptionButton(NO_STRING, noButton, noPanel);
    }

    private void setOptionButton(String string, ButtonAnimation button, RoundJPanel panel) {
        JLabel yesText = new JLabel(string);
        yesText.setForeground(CUSTOM_BLUE);
        yesText.setAlignmentX(CENTER_ALIGNMENT);
        yesText.setFont(initiateFont(15));

        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setIcon(BUTTON);
        button.add(yesText);

        panel.setLayout(new BorderLayout());
        panel.setMaximumSize(new Dimension(200,30));
        panel.setPreferredSize(new Dimension(200,30));
        panel.setBackground(CUSTOM_YELLOW);
        panel.add(button, BorderLayout.CENTER);

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    private JPanel buttonsPanel() {
        JPanel panel = new JPanel();

        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(yesButton);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(noButton);

        return panel;
    }

    private Font initiateFont(int size){
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
     * Method to register the action listeners from the buttons.
     *
     * @param listener the ActionListener
     */
    public void registerActionListeners(ActionListener listener) {
        yesButton.setActionCommand(YES_ANSWER);
        yesButton.addActionListener(listener);

        noButton.setActionCommand(NO_ANSWER);
        noButton.addActionListener(listener);
    }

    /**
     * Gets name repetition.
     *
     * @return the name repetition
     */
    public String getNameRepetition() {
        return nameRoundJText.getText();
    }
}