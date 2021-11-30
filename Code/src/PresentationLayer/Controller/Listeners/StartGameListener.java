package PresentationLayer.Controller.Listeners;

/**
 * Interface ReturnMenuPanelListener that abstracts the method that manages the start of a game.
 */
public interface StartGameListener {
    /**
     * Method that makes the mainView set invisible, initiates the game controller and start the game
     * and its logic and business.
     */
    void notifyStartGame();
}