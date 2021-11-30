package PresentationLayer.View.Helpers;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Class Painted panel game. This is a class with the objective to customize the JPanel with a wallpaper.
 */
public class PaintedPanelGame extends JPanel {
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

    private final int width;
    private final int height;
    private Image image;

    /**
     * Instantiates a new Painted panel game.
     *
     * @param image  the image
     * @param width  the width
     * @param height the height
     */
    public PaintedPanelGame(Image image, int width, int height) {
        this.image = image;
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image, 0, 0, width, height, this);
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
}