package PresentationLayer.View.Helpers;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Class Custom cell table. This is a class with the objective to customize the JTable.
 */
public class CustomCellTable extends DefaultTableCellRenderer {
    /**
     * The constant FONT_SIZE.
     */
    public static final int FONT_SIZE = 17;
    /**
     * The constant FONT_PATH.
     */
    public static final String FONT_PATH = "font/titleFont.ttf";
    /**
     * The constant DEFAULT_FONT.
     */
    public static final String DEFAULT_FONT = "Arial";

    /**
     * Instantiates a new Custom cell table.
     */
    public CustomCellTable() {
        this.setOpaque(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(false);
        setFont(initiateFont());
        setBorder(null);
        setForeground(Color.white);

        return cellComponent;
    }

    private Font initiateFont(){
        Font font;

        try {
            InputStream is =  new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(Font.BOLD, CustomCellTable.FONT_SIZE);
        } catch (Exception ex) {
            font = new Font(DEFAULT_FONT, Font.PLAIN, FONT_SIZE);
        }

        return font;
    }
}