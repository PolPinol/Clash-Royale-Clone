package BusinessLayer.Model.Troops;

import BusinessLayer.Model.Listeners.ActionTroopListener;
import BusinessLayer.Entities.Troop;

/**
 * Class Golem. Extends from DefensiveTroopDeployed. Golem Class can't move and attack forward and sides.
 */
public class Golem extends DefensiveTroopDeployed {

    /**
     * Instantiates a new Golem.
     *
     * @param id       the id
     * @param troop    the troop information
     * @param row      the row
     * @param column   the column
     * @param user     the user
     * @param listener the ActionTroopListener
     */
    public Golem(int id, Troop troop, int row, int column, int user, ActionTroopListener listener) {
        super(id, troop, row, column, user, listener);
    }
}
