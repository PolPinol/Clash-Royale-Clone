package PresentationLayer.View.Helpers;

import javax.swing.*;
import java.awt.*;

/**
 * Class ButtonAnimation. Extend from JButton and has the objective to animate the button and be customized.
 */
public class ButtonAnimation extends JButton {
    private final EventButton event;

    /**
     * Instantiates a new Button animation.
     */
    public ButtonAnimation(){
        event = new EventButton();
        addMouseListener(event);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getOpacity()));

        super.paintComponent(g2);
    }

    /**
     * Gets opacity.
     *
     * @return the opacity
     */
    public float getOpacity() {
        return event.getOpacity();
    }
}