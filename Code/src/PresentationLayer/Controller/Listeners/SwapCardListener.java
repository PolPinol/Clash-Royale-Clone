package PresentationLayer.Controller.Listeners;

/**
 * Interface SwapCardListener that abstracts the method that manages the swap
 * of the cards of the frame of the mainView that is using CardLayout.
 */
public interface SwapCardListener {
    /**
     * Method that notifies to the mainView that the panels has to be changed
     * by using the CardLayout.
     *
     * @param card string of the card to swap in
     */
    void notifyNewCard(String card);
}