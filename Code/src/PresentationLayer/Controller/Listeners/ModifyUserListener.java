package PresentationLayer.Controller.Listeners;

/**
 * Interface ModifyUserListener that abstracts the methods that manages logout and deleting an account.
 */
public interface ModifyUserListener {
    /**
     * Method that eliminates the current user's information from the RAM, database and current executable.
     * It also will eliminate all replay from that user.
     */
    void notifyDeleteUser();

    /**
     * Method that eliminates the current user's information from the RAM and current executable.
     */
    void notifyLogoutUser();
}
