package BusinessLayer.Model.Managers;

import BusinessLayer.Entities.Troop;
import PersistenceLayer.SQL.SQLTroopDAO;
import PersistenceLayer.TroopDAO;
import PresentationLayer.Controller.Listeners.SQLErrorListener;

import java.util.ArrayList;

/**
 * The TroopManager class will act as the system's model, storing and
 * managing the information from troops to play a game or reply a game.
 *
 * The TroopManager class will use the system's DAOs to manage
 * to abstractly access and modify the information.
 */
public class TroopManager {
    private final TroopDAO troopDao;

    /**
     * Instantiates a new Troop manager.
     *
     * @param listener the listener
     */
    public TroopManager(SQLErrorListener listener) {
        troopDao = new SQLTroopDAO(listener);
    }

    /**
     * Get the troops that can be played array list.
     *
     * @return the array list
     */
    public ArrayList<Troop> getTroops(){
        return troopDao.getAllTroops();
    }
}
