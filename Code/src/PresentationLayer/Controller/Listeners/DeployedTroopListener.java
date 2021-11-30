package PresentationLayer.Controller.Listeners;

import BusinessLayer.Entities.Troop;

/**
 * Interface DeleteActionListener that abstracts the methods that manages the elimination of an account / user.
 */
public interface DeployedTroopListener {
    /**
     * Method that manages and try to deploy a given troop on a specific position by the user.
     * If the position is correct to deploy the troop, it will be shown on the GameView
     * and it will be save it to a possible replay for the game.
     *
     * @param troop  the troop information
     * @param row    the row
     * @param column the column
     * @param user   the user
     */
    void notifyDeployedTroopUser(Troop troop, int row, int column, int user);
}
