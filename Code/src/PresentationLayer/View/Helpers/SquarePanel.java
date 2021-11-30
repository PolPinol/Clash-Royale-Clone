package PresentationLayer.View.Helpers;

import PresentationLayer.View.GameView;
import PresentationLayer.View.Listeners.SelectedSquareListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class SquarePanel.
 *
 * This class will appear on a gridLayout to represent each cell of the gameMatrix.
 * Each cell of the gridLayout owner is a possible position of the troops deployed.
 */
public class SquarePanel extends PaintedPanelGame implements MouseListener {
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 33;
    /**
     * The constant HEIGHT.
     */
    public static final int HEIGHT = 34;

    private final int row;
    private final int column;
    private final SelectedSquareListener listener;

    /**
     * Instantiates a new SquarePanel.
     *
     * @param listener the listener
     * @param row      the row
     * @param column   the column
     */
    public SquarePanel(SelectedSquareListener listener, int row, int column) {
        super(null, WIDTH, HEIGHT);
        this.row = row;
        this.column = column;
        this.listener = listener;
        addMouseListener(this);
    }

    /**
     * Method to repaint this panel with an image of a troop.
     *
     * @param image the image
     */
    public void repaintTroop(Image image) {
        setImage(image);
        repaint();
    }

    /**
     * Method to repaint this panel as empty and not with an image of a troop.
     */
    public void repaintEmpty() {
        setImage(null);
        repaint();
    }

    private boolean notifyOnlyIfIsHalfMap() {
        return row >= GameView.ROWS/2;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (notifyOnlyIfIsHalfMap()) {
            if (listener != null)
                listener.notifySquareSelected(row, column);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}