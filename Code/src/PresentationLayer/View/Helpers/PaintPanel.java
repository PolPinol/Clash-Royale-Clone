package PresentationLayer.View.Helpers;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Class Paint panel. This is a class with the objective to customize the JPanel with a wallpaper.
 */
public class PaintPanel extends JPanel {
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
    public static final int FONT_SIZE = 14;
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 700;
    /**
     * The constant HEIGHT.
     */
    public static final int HEIGHT = 350;

    private Image image;


    /**
     * Instantiates a new Paint panel.
     *
     * @param image the image
     */
    public PaintPanel(Image image) {
        this.image = image;
    }

    /**
     * Instantiates a new Paint panel.
     */
    public PaintPanel() {

    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image, 0, 0, WIDTH, HEIGHT, this);
        setOpaque(false);
        super.paint(g);
        this.setBackground(Color.gray);
    }

    /**
     * Initiate font.
     *
     * @param size the size
     * @return the font
     */
    public Font initiateFont(int size){
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
     * Set wallpaper image.
     *
     * @param image the image
     */
    public void setWallpaperImage(Image image){
        this.image = image;
    }
}