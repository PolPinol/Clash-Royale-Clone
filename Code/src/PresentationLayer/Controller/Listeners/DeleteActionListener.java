package PresentationLayer.Controller.Listeners;

/**
 * Interface DeleteActionListener that abstracts the methods that manages the elimination of an account / user.
 */
public interface DeleteActionListener {
    /**
     * Method that close all vies and return to the userPanel to log in in the program.
     * It also will eliminate all current user's information from the database and from
     * the program and RAM.
     */
    void notifyAction();
}
