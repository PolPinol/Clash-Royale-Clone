package PresentationLayer.View.Helpers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class EventButton. This is a class with the objective to customize the JButton.
 */
public class EventButton extends MouseAdapter {
    private float opacity;

    /**
     * New instance for EventButton.
     */
    public EventButton() {
        this.opacity = 1f;
    }

    /**
     * Set opacity.
     *
     * @param opacity the opacity
     */
    public void setOpacity(float opacity){
        this.opacity = opacity;
    }

    /**
     * Gets opacity.
     *
     * @return the opacity
     */
    public float getOpacity() {
        return opacity;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        animation(1f,1f, true);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        animation(0.5f,0.5f, false);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        animation(1f,1f, true);
    }

    private void animation(float index, float range, boolean event){
        for (float i = index; (event) ? i <= range: i >= range; i = (event) ? i + (float) 0.03 : i - (float) 0.03) {
            setOpacity(i);
        }
    }
}