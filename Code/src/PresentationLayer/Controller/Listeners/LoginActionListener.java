package PresentationLayer.Controller.Listeners;

/**
 * Interface LoginActionListener that abstracts the methods that manages login actions.
 */
public interface LoginActionListener {
    /**
     * Method that manages the login of a name and a password.
     *
     * If the name and the password matches with the information on the database
     * the program will continue, and if not it will appear an error depending
     * on the error situation.
     *
     * @param entry    the name
     * @param password the password
     */
    void notifyLoginButton(String entry, String password);

    /**
     * Method that gets the user name from the current player.
     *
     * @return the user name
     */
    String getUserName();
}