package PresentationLayer.Controller.Listeners;

/**
 * Interface StartReplayListener that abstracts the method that manages the start of a replay.
 */
public interface StartReplayListener {
    /**
     * Method that makes the mainView set invisible, initiates the replay controller and start
     * the game and its logic and business.
     */
    void notifyStartReplay();
}
