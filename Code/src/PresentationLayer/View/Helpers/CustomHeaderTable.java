package PresentationLayer.View.Helpers;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;


/**
 * Class Custom header table. This is a class with the objective to customize the JTable.
 */
public class CustomHeaderTable extends JLabel implements TableCellRenderer {
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
     * Instantiates a new Custom header table.
     */
    public CustomHeaderTable() {
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setFont(initiateFont());
        this.setForeground(Color.BLACK);
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }

    private Font initiateFont(){
        Font font;

        try {
            InputStream is =  new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(Font.BOLD, CustomHeaderTable.FONT_SIZE);
        } catch (Exception ex) {
            font = new Font(DEFAULT_FONT, Font.PLAIN, FONT_SIZE);
        }

        return font;
    }
}
