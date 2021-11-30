package PresentationLayer.View.Helpers;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Class RoundJPanel. This is a class with the objective to customize the JPanel.
 */
public class RoundJPanel extends JPanel {
    /**
     * The constant ARCW.
     */
    public static final int ARCW = 10;
    /**
     * The constant ARCH.
     */
    public static final int ARCH = 10;
    /**
     * The constant TOP.
     */
    public static final int TOP = 0;
    /**
     * The constant LEFT.
     */
    public static final int LEFT = 5;
    /**
     * The constant BOTTOM.
     */
    public static final int BOTTOM = 0;
    /**
     * The constant RIGHT.
     */
    public static final int RIGHT = 5;

    /**
     * Instantiates a new RoundJPanel.
     */
    public RoundJPanel() {
        setBorder(new EmptyBorder(TOP, LEFT, BOTTOM, RIGHT));
        setBorder(null);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Paint oldPaint = g2.getPaint();

        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0,0, getWidth(), getHeight(), ARCW, ARCH);
        g2.clip(r2d);

        g2.setPaint(new GradientPaint(0.0f, 0.0f, getBackground(), 0.0f, getHeight(), getBackground()));
        g2.fillRect(0,0,getWidth(),getHeight());

        g2.setPaint(new GradientPaint(0.0f, 0.0f, Color.BLACK, 0.0f, getHeight(), Color.BLACK));
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), ARCW, ARCH);

        g2.setPaint(oldPaint);
        super.paintComponent(g);
    }
}
