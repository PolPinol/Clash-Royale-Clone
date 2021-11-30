package BusinessLayer.Model.Managers;

import BusinessLayer.Entities.User;
import PersistenceLayer.SQL.SQLUserDAO;
import PersistenceLayer.UserDAO;
import PresentationLayer.Controller.Listeners.SQLErrorListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The UserManager class will act as the system's model, storing and
 * managing the information the user enters.
 *
 * The UserManager class will use the system's DAOs to manage
 * to abstractly access and modify the information.
 */
public class UserManager {
    private UserDAO userDao;

    /**
     * Instantiates a new User manager.
     */
    public UserManager() {

    }

    /**
     * Register the SQLErrorListener.
     *
     * @param listener the SQLErrorListener
     */
    public void initiateUserDAO(SQLErrorListener listener) {
        userDao = new SQLUserDAO(listener);
    }

    /**
     * Check if user exists.
     *
     * @param entry the name of the user
     * @return the boolean if the user exists
     */
    public boolean checkUser(String entry) {
        return userDao.checkUser(entry);
    }

    /**
     * Gets user.
     *
     * @param entry the entry
     * @return the user
     */
    public User getUser(String entry) {
        return userDao.getUser(entry);
    }

    /**
     * Get users array list sorted by winrate.
     *
     * @return the array list
     */
    public ArrayList<User> getUsers(){
        ArrayList<User> users = userDao.getAllUsers();
        Collections.sort(users);
        return users;
    }

    /**
     * Add new play on the user history.
     *
     * @param name   the name
     * @param winner the winner
     */
    public void addNewPlay(String name, int winner){
        User user = getUser(name);

        if (winner == User.PLAYER_USER) {
            user.morePoints();
            userDao.updateUserNumWins(name, user.getPoints());
        }

        user.setNewPlay();
        userDao.updateUserNumPlays(name, user.getNumPlays());
    }

    /**
     * Add a new user.
     *
     * @param user the user
     */
    public void addUser(User user) {
        userDao.addUser(user);
    }

    /**
     * Remove an existing user.
     *
     * @param user the user
     */
    public void removeUser(User user) {
        userDao.removeUser(user);
    }
}
