package BusinessLayer.Model.Managers;

import BusinessLayer.Entities.Record;
import PersistenceLayer.RecordDAO;
import PersistenceLayer.SQL.SQLRecordDAO;
import PresentationLayer.Controller.Listeners.SQLErrorListener;

import java.util.ArrayList;

/**
 * The RecordManager class will act as the system's model, storing and
 * managing the information from the history.
 *
 * The RecordManager class will use the system's DAOs to manage
 * to abstractly access and modify the information.
 */
public class RecordManager {
    private final RecordDAO recordDAO;

    /**
     * Instantiates a new Record manager.
     *
     * @param sqlErrorListener the sql error listener
     */
    public RecordManager(SQLErrorListener sqlErrorListener) {
        recordDAO = new SQLRecordDAO(sqlErrorListener);
    }

    /**
     * Add a record.
     *
     * @param record the record
     */
    public void addRecord(Record record) {
        recordDAO.addRecord(record);
    }

    /**
     * Remove a record.
     *
     * @param record the record
     */
    public void removeRecord(Record record) {
        recordDAO.removeRecord(record);
    }

    /**
     * Gets records.
     *
     * @param userName the user name
     * @return the records
     */
    public ArrayList<Record> getRecords(String userName) {
        return recordDAO.getRecords(userName);
    }

    /**
     * Remove a user record.
     *
     * @param userName the user name
     */
    public void removeUserRecord(String userName) {
        recordDAO.removeUserRecord(userName);
    }
}