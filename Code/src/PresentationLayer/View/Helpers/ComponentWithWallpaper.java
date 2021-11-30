package PresentationLayer.View.Helpers;

import javax.swing.*;
import java.awt.*;

/**
 * Class Component with wallpaper.
 */
public class ComponentWithWallpaper extends JComponent {
    /**
     * The constant WALLPAPER.
     */
    public static final Image WALLPAPER = new ImageIcon("img/snapseed2.png").getImage();

    /**
     * Instantiates a new Component with wallpaper.
     */
    public ComponentWithWallpaper() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(WALLPAPER, 0, 0, this);
    }
}
