package PresentationLayer.Controller.Listeners;

/**
 * Interface RegisterActionListener that abstracts the method that manages the register of a new user.
 */
public interface RegisterActionListener {
    /**
     * Method that registers a new user to the database.
     *
     * It will ask to the database if the user exists and if the email exists. If neither of these
     * two doesn't exist, then it will create a new user and add to the database. If the user exists
     * or the email exists will throw an error view warning the current user of the problem.
     *
     * @param name     the name
     * @param email    the email
     * @param password the password
     */
    void notifyRegisterButton(String name, String email, String password);
}