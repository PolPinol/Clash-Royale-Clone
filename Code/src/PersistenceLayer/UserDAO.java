package PersistenceLayer;

import BusinessLayer.Entities.User;

import java.util.ArrayList;

/**
 * Interface that abstracts the persistence of users from the rest of the code.
 *
 * In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 */
public interface UserDAO {
    /**
     * Method that adds a user.
     *
     * @param user the user
     */
    void addUser(User user);

    /**
     * Method that removes a user.
     *
     * @param user the user
     */
    void removeUser(User user);

    /**
     * Method that gets a user by its name.
     *
     * @param entry the name of the user
     * @return the user
     */
    User getUser(String entry);

    /**
     * Method that gets all the users existing on the database.
     *
     * @return a list of all users
     */
    ArrayList<User> getAllUsers();

    /**
     * Method that checks if the user exists by its name.
     *
     * @param entry the name of the user
     * @return the boolean
     */
    boolean checkUser(String entry);

    /**
     * Method that update the number of wins of the user.
     *
     * @param entry  the name of the user
     * @param points the total of wins
     */
    void updateUserNumWins(String entry, int points);

    /**
     * Method that update the number of games of the user.
     *
     * @param entry    the entry
     * @param numPlays the total of games
     */
    void updateUserNumPlays(String entry, int numPlays);
}