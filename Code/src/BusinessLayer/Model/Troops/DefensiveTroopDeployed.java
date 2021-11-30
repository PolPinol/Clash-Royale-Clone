package BusinessLayer.Model.Troops;

import BusinessLayer.Entities.Troop;
import BusinessLayer.Model.Listeners.ActionTroopListener;

/**
 * Abstract Class DefensiveTroopDeployed. It extend from TroopDeployed and it can't be instantiated.
 * This class can't move forward and can attack forward and sides at same time.
 */
public abstract class DefensiveTroopDeployed extends TroopDeployed {

    /**
     * Instantiates a new Defensive troop deployed.
     *
     * @param id       the id
     * @param troop    the troop information
     * @param row      the row
     * @param column   the column
     * @param user     the user
     * @param listener the ActionTroopListener
     */
    public DefensiveTroopDeployed(int id, Troop troop, int row, int column, int user, ActionTroopListener listener) {
        super(id, troop, row, column, user, listener);
    }
}
