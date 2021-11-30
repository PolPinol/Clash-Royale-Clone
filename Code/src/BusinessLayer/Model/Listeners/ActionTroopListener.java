package BusinessLayer.Model.Listeners;

/**
 * The interface ActionTroopListener.
 */
public interface ActionTroopListener {
    /**
     * Notify that a troop wants to do an action (either move or attack).
     *
     * @param row    the origin row
     * @param column the origin column
     * @param user   the user from the troop
     */
    void notifyAction(int row, int column, int user);
}
