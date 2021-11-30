package PresentationLayer.Controller;

import PresentationLayer.Controller.Listeners.SwapCardListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class LogoController that manages the logic from the first view of the program.
 */
public class LogoController implements MouseListener {
    /**
     * The constant SWAP_TO_CARD_USER.
     */
    public static final String SWAP_TO_CARD_USER = "cardUser";

    private final SwapCardListener listener;

    /**
     * Instantiates a new LogoController.
     *
     * @param listener the listener
     */
    public LogoController(SwapCardListener listener) {
        this.listener = listener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        listener.notifyNewCard(SWAP_TO_CARD_USER);
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
