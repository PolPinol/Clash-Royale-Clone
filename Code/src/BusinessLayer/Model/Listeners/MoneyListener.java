package BusinessLayer.Model.Listeners;

/**
 * The interface Money listener.
 */
public interface MoneyListener {
    /**
     * Notify that a troop is eliminated.
     *
     * @param userToEarnMoney the user to earn money
     */
    void notifyTroopEliminated(int userToEarnMoney);

    /**
     * Notify to stop all threads.
     */
    void stopAllThreads();
}
