package PresentationLayer.View.Helpers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Class Custom table. This is a class with the objective to customize the JTable.
 */
public class CustomTable extends JScrollPane {
    /**
     * The constant DEFAULT_FONT.
     */
    public static final String DEFAULT_FONT = "Arial";
    /**
     * The constant IMAGE.
     */
    public static final Image IMAGE = new ImageIcon("img/empty3.png").getImage();

    private final JTable table;

    /**
     * Instantiates a new Custom table.
     *
     * @param model the model
     */
    public CustomTable(DefaultTableModel model) {

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.setOpaque(false);
        this.getViewport().setOpaque(false);

        table = new JTable(model);
        table.setOpaque(false);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setShowHorizontalLines(false);
        table.setDefaultRenderer(Object.class, new CustomCellTable());
        table.getTableHeader().setDefaultRenderer(new CustomHeaderTable());
        table.setRowHeight(25);

        this.setViewportView(table);
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public JTable getTable() {
        return table;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(IMAGE, 0, 0, getWidth(), getHeight(), this);
        super.paintComponent(g);
    }
}