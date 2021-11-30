package PresentationLayer.Controller.Listeners;

import BusinessLayer.Entities.Troop;

/**
 * Interface ChangesGameViewListener that abstracts the methods that manages the
 * changes that have to be made on the View.
 */
public interface ChangesGameViewListener {
    /**
     * Method that manages and try to deploy a given troop on a specific position by the IA.
     * If the position is correct to deploy the troop, it will be shown on the GameView
     * and it will be save it to a possible replay for the game.
     *
     * @param troop  the troop information
     * @param row    the row
     * @param column the column
     * @param user   the user
     */
    void notifyDeployedTroopComputer(Troop troop, int row, int column, int user);

    /**
     * Method that manages when a troop is moving. It will erase the troop from the origin
     * position and it will be shown on the View on the destiny position.
     *
     * @param troop                the troop information
     * @param row                  the destiny row
     * @param column               the destiny column
     * @param rowOriginToRemove    the row origin to remove
     * @param columnOriginToRemove the column origin to remove
     * @param user                 the user
     */
    void notifyChangesGameView(Troop troop, int row, int column, int rowOriginToRemove, int columnOriginToRemove, int user);

    /**
     * Method that notifies to the View that the money from the player has changed
     * to repaint the label.
     *
     * @param money the money from the player
     */
    void notifyMoneyHasChanged(int money);

    /**
     * Method that notifies to the View that the game has end. Then, the GameView will close
     * and the Menu will be visible again to offer the user the menu's options.
     *
     * @param userWinner the user winner
     */
    void notifyEndGame(int userWinner);

    /**
     * Method that notifies to the View that a troop has to be remove from a position given.
     *
     * @param row    the row
     * @param column the column
     * @param troop  the troop information
     */
    void notifyRemoveTroop(int row, int column, Troop troop);

    /**
     * Method that notifies to the Logs that the player has no money to send the troop given.
     *
     * @param troop the troop that the player can't send
     */
    void notifyLogsNotMoney(Troop troop);

    /**
     * Method that notifies to the Logs that the player is trying to create a barrier.
     */
    void notifyLogsCreateBarrier();

    /**
     * Method that notifies to the Logs that the player is trying to deploy a troop
     * on a non empty square / position.
     */
    void notifyLogsSquareNotEmpty();

    /**
     * Method that notifies to the Graphics that life from the player has changed.
     *
     * @param life the total life
     * @param user the user
     */
    void notifyChangeLifeGraphics(int life, int user);

    /**
     * Method that notifies to the Graphics that a troop have been deployed.
     *
     * @param user the user
     */
    void notifyMoreTroopGraphics(int user);

    /**
     * Method that notifies to the Graphics that a troop have been eliminated.
     *
     * @param user the user
     */
    void notifyLessTroopGraphics(int user);
}
