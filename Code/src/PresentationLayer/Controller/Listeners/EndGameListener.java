package PresentationLayer.Controller.Listeners;

/**
 * Interface EndGameListener that abstracts the method that manages the end of a game.
 */
public interface EndGameListener {
    /**
     * Method that will make the mainView visible and return the user to the MenuPanel.
     */
    void notifyEndGame();
}