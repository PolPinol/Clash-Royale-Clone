package BusinessLayer.Model.Troops;

import BusinessLayer.Model.Listeners.ActionTroopListener;
import BusinessLayer.Entities.Troop;

/**
 * Class Wizard. Extends from OffensiveTroopDeployed. Wizard Class moves forward and
 * attack only forward nearliest enemy.
 */
public class Wizard extends OffensiveTroopDeployed {

    /**
     * Instantiates a new Wizard.
     *
     * @param id       the id
     * @param troop    the troop information
     * @param row      the row
     * @param column   the column
     * @param user     the user
     * @param listener the ActionTroopListener
     */
    public Wizard(int id, Troop troop, int row, int column, int user, ActionTroopListener listener) {
        super(id, troop, row, column, user, listener);
    }

    // Implementation of different classes for each troop with the intention of improving functionalities in the future
    public synchronized void move(int destinyRow, int destinyColumn) {
        setRow(destinyRow);
        setColumn(destinyColumn);
    }
}
