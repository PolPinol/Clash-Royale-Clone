package BusinessLayer.Model.Managers;

import BusinessLayer.Entities.Record;
import BusinessLayer.Entities.DataGame;
import PersistenceLayer.DataGameDAO;
import PersistenceLayer.SQL.SQLDataGameDAO;
import PresentationLayer.Controller.Listeners.SQLErrorListener;

import java.util.ArrayList;
import java.util.Queue;

/**
 * The DataGameManager class will act as the system's model, storing and
 * managing the information when a game is recorded.
 *
 * The DataGameManager class will use the system's DAOs to manage
 * to abstractly access and modify the information.
 */
public class DataGameManager {
    private final DataGameDAO dataGameDAO;

    /**
     * Instantiates a new Data game manager.
     *
     * @param sqlErrorListener the sql error listener
     */
    public DataGameManager(SQLErrorListener sqlErrorListener) {
        dataGameDAO = new SQLDataGameDAO(sqlErrorListener);
    }

    /**
     * Insert data from a game.
     *
     * @param data the data
     */
    public void insertDataGame(ArrayList<DataGame> data) {
        dataGameDAO.insertDataGame(data);
    }

    /**
     * Remove data from a game.
     *
     * @param record the record
     */
    public void removeDataGame(Record record) {
        dataGameDAO.removeDataGame(record);
    }

    /**
     * Gets data from a game.
     *
     * @param name the name
     * @return the data
     */
    public Queue<DataGame> getData(String name) {
        return dataGameDAO.getDataGame(name);
    }
}
