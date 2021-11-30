package BusinessLayer.Model.Troops;

import BusinessLayer.Entities.Troop;
import BusinessLayer.Model.Listeners.ActionTroopListener;

/**
 * Class Offensive troop deployed. It extend from TroopDeployed and it can't be instantiated.
 * This class moves forward and can attack forward to nearly enemy.
 */
public abstract class OffensiveTroopDeployed extends TroopDeployed {

    /**
     * Instantiates a new Offensive troop deployed.
     *
     * @param id       the id
     * @param troop    the troop information
     * @param row      the row
     * @param column   the column
     * @param user     the user
     * @param listener the ActionTroopListener
     */
    public OffensiveTroopDeployed(int id, Troop troop, int row, int column, int user, ActionTroopListener listener) {
        super(id, troop, row, column, user, listener);
    }

    /**
     * Abstract Method that moves a troop on the game.
     *
     * @param destinyRow    the destiny row
     * @param destinyColumn the destiny column
     */
    public abstract void move(int destinyRow, int destinyColumn);
}
