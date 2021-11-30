package PersistenceLayer;

import BusinessLayer.Entities.Record;
import BusinessLayer.Entities.DataGame;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Interface that abstracts the persistence of datagame for a replay from the rest of the code.
 *
 * In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 */
public interface DataGameDAO {
    /**
     * Method that inserts data from a game to the database.
     *
     * @param data the data
     */
    void insertDataGame(ArrayList<DataGame> data);

    /**
     * Method that remove data from a game to the database.
     *
     * @param record the record
     */
    void removeDataGame(Record record);

    /**
     * Method that gets all data from a game from the database.
     *
     * @param name the name of the game / replay
     * @return the data game
     */
    Queue<DataGame> getDataGame(String name);
}