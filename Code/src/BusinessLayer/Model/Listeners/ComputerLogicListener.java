package BusinessLayer.Model.Listeners;

/**
 * The interface Computer logic listener.
 */
public interface ComputerLogicListener {
    /**
     * Listener that notifies that computer want to deploy a defensive troop.
     */
    void tryDeployDefensiveTroop();

    /**
     * Listener that notifies that computer want to deploy a offensive troop.
     */
    void tryDeployOffensiveTroop();
}
