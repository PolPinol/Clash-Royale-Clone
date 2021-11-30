package PresentationLayer.View.MainPanels;

import PresentationLayer.View.Helpers.PaintPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class that represents the LogoPanel.
 *
 * In this card of the jFrame we have used only one jPanel with an image and
 * we have put a listener in the same panel so that it happens to the following one.
 */
public class LogoPanel extends PaintPanel {
    /**
     * The constant WALLPAPER.
     */
    public static final Image WALLPAPER = new ImageIcon("img/portada2.jpeg").getImage();
    /**
     * The constant START.
     */
    public static final ImageIcon START = new ImageIcon("img/start.gif");
    /**
     * The constant WIDTH_PANEL.
     */
    public static final int WIDTH_PANEL= 700;
    /**
     * The constant HEIGHT_PANEL.
     */
    public static final int HEIGHT_PANEL = 350;

    /**
     * Instantiates a new LogoPanel.
     */
    public LogoPanel() {
        initiateLayout();
        initiateStartGif();
        initiateSettings();

        this.setWallpaperImage(WALLPAPER);
    }

    private void initiateStartGif() {
        JLabel start = new JLabel();
        start.setIcon(START);
        start.setHorizontalAlignment(0);
        this.add(start, BorderLayout.NORTH);
    }

    private void initiateLayout() {
        this.setLayout(new BorderLayout());
    }

    private void initiateSettings() {
        this.setPreferredSize(new Dimension(WIDTH_PANEL, HEIGHT_PANEL));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * Method to register MouseListener.
     *
     * @param listener the MouseListener
     */
    public void registerActionListeners(MouseListener listener) {
        this.addMouseListener(listener);
    }
}
