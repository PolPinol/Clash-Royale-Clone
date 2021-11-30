package PresentationLayer.View.Helpers;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Class Custom label. This is a class with the objective to customize the JTable.
 */
public class CustomLabel extends JLabel implements ListCellRenderer<JLabel> {
    /**
     * The constant DEFAULT_FONT.
     */
    public static final String DEFAULT_FONT = "Arial";
    /**
     * The constant FONT_PANTH.
     */
    public static final String FONT_PANTH = "font/titleFont.ttf";

    /**
     * Instantiates a new Custom label.
     */
    public CustomLabel() {
        super();
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends JLabel> list, JLabel value, int index, boolean isSelected, boolean cellHasFocus) {
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setBorder(new EmptyBorder(13, 10, 2, 0));
        setForeground(Color.WHITE);
        setFont(initFont(14));
        setText(value.getText());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }

    /**
     * Instantiates a new Custom label.
     *
     * @param name the name
     */
    public CustomLabel(String name) {
        super(name);
    }


    private Font initFont(int size){
        Font font;

        try {
            InputStream is =  new BufferedInputStream(new FileInputStream(FONT_PANTH));
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(Font.BOLD, size);
        } catch (Exception ex) {
            font = new Font(DEFAULT_FONT, Font.PLAIN, 14);
        }

        return font;
    }

    @Override
    public String toString() {
        return getText();
    }
}