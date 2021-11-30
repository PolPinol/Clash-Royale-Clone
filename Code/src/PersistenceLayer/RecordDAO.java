package PersistenceLayer;

import BusinessLayer.Entities.Record;

import java.util.ArrayList;

/**
 * Interface that abstracts the persistence of records / games / replays from the rest of the code.
 *
 * In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 */
public interface RecordDAO {
    /**
     * Method that adds a record.
     *
     * @param record the record that is saving
     */
    void addRecord(Record record);

    /**
     * Method that removes a record.
     *
     * @param record the record that is eliminating
     */
    void removeRecord(Record record);

    /**
     * Method that gets all the records of the database of one user.
     *
     * @param userName the user name
     * @return a list with all the records / games saved
     */
    ArrayList<Record> getRecords(String userName);

    /**
     * Method that remove a record of the database of one user.
     *
     * @param userName the user name
     */
    void removeUserRecord(String userName);
}