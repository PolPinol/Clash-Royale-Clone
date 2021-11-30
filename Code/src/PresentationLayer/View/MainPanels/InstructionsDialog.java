package PresentationLayer.View.MainPanels;

import PresentationLayer.View.Helpers.ButtonAnimation;
import PresentationLayer.View.Helpers.ComponentWithWallpaper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Class that represents the InstructionsDialog.
 *
 * This card contains the background image of the instructions plus a jButton to access
 * the game. The jButton contains a jDialog with its text.
 */
public class InstructionsDialog extends JDialog {
    /**
     * The constant DEFAULT_FONT.
     */
    public static final String DEFAULT_FONT = "Arial";
    /**
     * The constant FONT_PATH.
     */
    public static final String FONT_PATH = "font/titleFont.ttf";
    /**
     * The constant EVENT_PLAY_CLICK.
     */
    public static final String EVENT_PLAY_CLICK = "click";
    /**
     * The constant UNDERSTOOD.
     */
    public static final String UNDERSTOOD = "Entendido";
    /**
     * The constant INSTRUCTION.
     */
    public static final Image INSTRUCTION = new ImageIcon("img/instrucciones.png").getImage();
    /**
     * The constant BUTTON.
     */
    public static final ImageIcon BUTTON = new ImageIcon("img/button3.gif");
    /**
     * The constant WIDTH_DIALOG.
     */
    public static final int WIDTH_DIALOG = 600;
    /**
     * The constant HEIGHT_DIALOG.
     */
    public static final int HEIGHT_DIALOG = 350;
    /**
     * The constant FONT_SIZE.
     */
    public static final int FONT_SIZE = 15;

    private ButtonAnimation next;
    private JComponent component;

    /**
     * Instantiates a new InstructionsDialog.
     *
     * @param owner the owner
     */
    public InstructionsDialog(JFrame owner) {
        super(owner);
        initiateAttributes();
        setSettings();

        initiateMainPanel();
    }

    private void initiateAttributes() {
        next = new ButtonAnimation();
        component = new ComponentWithWallpaper();
    }

    private void setSettings() {
        setContentPane(component);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH_DIALOG, HEIGHT_DIALOG));
        setModal(true);
        setLayout(new BorderLayout());
        pack();
    }

    private void initiateMainPanel() {
        JLabel instructions = new JLabel(new ImageIcon(INSTRUCTION));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(instructions, BorderLayout.CENTER);

        initiateButton();

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(next);

        add(panel, BorderLayout.SOUTH);
    }

    private void initiateButton(){
        JLabel jLabel = new JLabel(UNDERSTOOD);

        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel.setFont(initiateFont());
        jLabel.setForeground(Color.BLACK);

        next.setIcon(BUTTON);
        next.setVisible(true);
        next.setOpaque(false);
        next.setContentAreaFilled(false);
        next.setBorderPainted(false);
        next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        next.add(jLabel);
    }

    /**
     * Method that shows the dialog.
     */
    public void showTrue() {
        setLocationRelativeTo(getOwner());
        setVisible(true);
    }

    /**
     * Method that makes invisible the dialog.
     */
    public void showFalse() {
        setVisible(false);
    }

    /**
     * Method that register action listeners.
     *
     * @param listener the listener
     */
    public void registerActionListeners(ActionListener listener) {
        next.setActionCommand(EVENT_PLAY_CLICK);
        next.addActionListener(listener);
    }

    private Font initiateFont(){
        Font font;

        try {
            InputStream is =  new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(Font.BOLD, InstructionsDialog.FONT_SIZE);
        } catch (Exception ex) {
            font = new Font(DEFAULT_FONT, Font.PLAIN, FONT_SIZE);
        }

        return font;
    }
}