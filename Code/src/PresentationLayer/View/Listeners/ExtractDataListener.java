package PresentationLayer.View.Listeners;

import BusinessLayer.Entities.User;
import java.util.ArrayList;

/**
 * Interface ChangesGameViewListener that abstracts the methods that manages the
 * load of information of the ranking panel.
 */
public interface ExtractDataListener {
    /**
     * Method that adds the information to the JTable of the ranking panel.
     *
     * @param users the users
     */
    void notifyExtraction(ArrayList<User> users);

    /**
     * Method that clears the information of the JTable of the ranking panel.
     */
    void clearTable();
}