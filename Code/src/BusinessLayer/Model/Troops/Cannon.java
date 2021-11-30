package BusinessLayer.Model.Troops;

import BusinessLayer.Model.Listeners.ActionTroopListener;
import BusinessLayer.Entities.Troop;

/**
 * Class Cannon. Extends from DefensiveTroopDeployed. Cannon Class can't move and attack forward and sides.
 */
public class Cannon extends DefensiveTroopDeployed {

    /**
     * Instantiates a new Cannon.
     *
     * @param id       the id
     * @param troop    the troop information
     * @param row      the row
     * @param column   the column
     * @param user     the user
     * @param listener the ActionTroopListener
     */
    public Cannon(int id, Troop troop, int row, int column, int user, ActionTroopListener listener) {
        super(id, troop, row, column, user, listener);
    }
}
