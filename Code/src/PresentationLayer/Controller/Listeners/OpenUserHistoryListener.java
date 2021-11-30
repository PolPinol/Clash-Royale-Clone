package PresentationLayer.Controller.Listeners;

/**
 * Interface OpenUserHistoryListener that abstracts the method that manages the opened of another history user.
 */
public interface OpenUserHistoryListener {
    /**
     * Method that ask fot the history of a user to the database ans swap to the history view.
     *
     * @param user the user that the player wants to see its history
     */
    void notifyOpenHistory(String user);
}
