package PersistenceLayer;

import BusinessLayer.Entities.Troop;

import java.util.ArrayList;

/**
 * Interface that abstracts the persistence of troops from the rest of the code.
 *
 * In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 */
public interface TroopDAO {
    /**
     * Method that gets all the troops information.
     *
     * @return a list of all the playable troops information
     */
    ArrayList<Troop> getAllTroops();
}
