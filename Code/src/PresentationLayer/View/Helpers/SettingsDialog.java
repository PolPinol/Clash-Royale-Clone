package PresentationLayer.View.Helpers;

import PresentationLayer.Controller.Listeners.DeleteActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Class SaveGameDialog. This is a class with the objective to customize the JDialog.
 *
 * This class will appear when a the settings icon is clicked, and ask if the user wants to
 * delete the current account or logout. It will ask for confirmation with a JOptionPane if
 * the option to delete the account is selected.
 */
public class SettingsDialog extends JDialog implements ActionListener {
    /**
     * The constant EVENT_LOGOUT.
     */
    public static final String EVENT_LOGOUT = "logoutUser";
    /**
     * The constant EVENT_DELETE.
     */
    public static final String EVENT_DELETE = "deleteUser";
    /**
     * The constant WALLPAPER.
     */
    public static final Image WALLPAPER = new ImageIcon("img/snapseed2.png").getImage();
    /**
     * The constant FONT_SIZE.
     */
    public static final int FONT_SIZE = 11;
    /**
     * The constant FONT_PATH.
     */
    public static final String FONT_PATH = "font/titleFont.ttf";
    /**
     * The constant DEFAULT_FONT.
     */
    public static final String DEFAULT_FONT = "Arial";
    /**
     * The constant LOGOUT.
     */
    public static final String LOGOUT = "Logout";
    /**
     * The constant DELETE.
     */
    public static final String DELETE = "Delete account";
    /**
     * The constant WARNING_TITLE.
     */
    public static final String WARNING_TITLE = "WARNING";
    /**
     * The constant WARNING.
     */
    public static final String WARNING = "¿Estas seguro de eliminar tu cuenta?";
    /**
     * The constant CUSTOM_BLUE.
     */
    public static final Color CUSTOM_BLUE = new Color(0, 94, 145);
    /**
     * The constant CUSTOM_YELLOW.
     */
    public static final Color CUSTOM_YELLOW = new Color(253, 216, 53);

    private ButtonAnimation logoutButton;
    private ButtonAnimation deleteButton;
    private PaintPanel mainPanel;
    private DeleteActionListener deleteActionListener;

    /**
     * Instantiates a new SettingsDialog.
     */
    public SettingsDialog(){
        this.setModal(true);
        initiateAttributes();

        this.setSize(new Dimension(220,190));
        setMainPanel();
    }

    /**
     * Method to show the JDialog.
     */
    public void start() {
        setLocationRelativeTo(getOwner());
        setVisible(true);
    }

    private void initiateAttributes(){
        logoutButton = new ButtonAnimation();
        deleteButton = new ButtonAnimation();
    }

    private void getLogoutButton(){
        RoundJPanel panel = getPersonalizedMenuJPanel(LOGOUT, logoutButton);
        panel.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(panel);
    }

    private void getDeleteButton(){
        mainPanel.add(getPersonalizedMenuJPanel(DELETE, deleteButton));
    }

    private RoundJPanel getPersonalizedMenuJPanel(String text, ButtonAnimation button) {
        RoundJPanel jPanel;
        JLabel jLabel;

        jPanel = new RoundJPanel();
        jLabel = new JLabel(text);

        jLabel.setFont(initiateFont());
        jLabel.setAlignmentX(CENTER_ALIGNMENT);
        jLabel.setForeground(CUSTOM_BLUE);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.add(jLabel);
        button.setBackground(CUSTOM_YELLOW);

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        jPanel.setLayout(new BorderLayout());
        jPanel.setMaximumSize(new Dimension(290,40));
        jPanel.setPreferredSize(new Dimension(290,40));
        jPanel.setBackground(CUSTOM_YELLOW);
        jPanel.add(button, BorderLayout.CENTER);

        return jPanel;
    }

    private void setMainPanel(){
        mainPanel = new PaintPanel(WALLPAPER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        getLogoutButton();
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        getDeleteButton();

        this.add(mainPanel);
    }

    private Font initiateFont(){
        Font font;

        try {
            InputStream is =  new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(Font.BOLD, SettingsDialog.FONT_SIZE);
        } catch (Exception ex) {
            font = new Font(DEFAULT_FONT, Font.PLAIN, 14);
        }

        return font;
    }

    /**
     * Method to register all the listeners needed.
     *
     * @param listener             the ActionListener
     * @param deleteActionListener the DeleteActionListener
     */
    public void registerActionListeners(ActionListener listener, DeleteActionListener deleteActionListener) {
        logoutButton.setActionCommand(EVENT_LOGOUT);
        logoutButton.addActionListener(listener);

        deleteButton.setActionCommand(EVENT_DELETE);
        deleteButton.addActionListener(this);

        this.deleteActionListener = deleteActionListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(EVENT_DELETE)) {
            if (JOptionPane.showConfirmDialog(this, WARNING, WARNING_TITLE,
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                deleteActionListener.notifyAction();
            }
        }
    }
}