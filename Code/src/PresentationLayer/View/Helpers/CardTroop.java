package PresentationLayer.View.Helpers;

import PresentationLayer.View.Listeners.SelectedTroopCardListener;
import BusinessLayer.Entities.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class CardTroop. It is a customized JPanel that uses the choosable troops that the user can play.
 */
public class CardTroop extends PaintedPanelGame implements MouseListener {
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 60;
    /**
     * The constant HEIGHT.
     */
    public static final int HEIGHT = 79;

    private final Troop troop;
    private final SelectedTroopCardListener listener;
    private boolean locked;
    private final Image defaultImage;
    private final Image imageSelected;

    /**
     * Instantiates a new Card troop.
     *
     * @param troop         the troop
     * @param defaultImage  the default image
     * @param imageSelected the image selected
     * @param listener      the listener
     */
    public CardTroop(Troop troop, Image defaultImage, Image imageSelected, SelectedTroopCardListener listener) {
        super(defaultImage, WIDTH, HEIGHT);
        this.locked = false;
        this.troop = troop;
        this.listener = listener;
        this.defaultImage = defaultImage;
        this.imageSelected = imageSelected;
        addMouseListener(this);
    }

    /**
     * Sets locked.
     */
    public void setLocked() {
        this.locked = true;
    }

    /**
     * Sets free.
     */
    public void setFree() {
        this.locked = false;
    }

    /**
     * Reset image.
     */
    public void resetImage() {
        setImage(defaultImage);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!locked) {
            if (listener != null) {
                listener.notifyTroopCardSelected(troop);
                setImage(imageSelected);
                repaint();
            }
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