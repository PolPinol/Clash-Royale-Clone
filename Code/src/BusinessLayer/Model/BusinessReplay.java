package BusinessLayer.Model;

import BusinessLayer.Entities.Troop;
import BusinessLayer.Model.Listeners.MoneyListener;
import BusinessLayer.Model.Managers.TroopManager;
import PresentationLayer.Controller.Listeners.ActionRecordListener;
import PresentationLayer.Controller.Listeners.ChangesGameViewListener;
import PresentationLayer.Controller.Listeners.DeployedTroopListener;
import PresentationLayer.Controller.Listeners.SQLErrorListener;

import java.util.ArrayList;

/**
 * Class BusinessReplay that manages the logic of a game replayed.
 */
public class BusinessReplay implements MoneyListener {
    private final GameMatrix gameMatrix;
    private GameTime gameTime;
    private final ReplayDeployedTroop deployTroops;

    /**
     * Instantiates a new Business replay.
     *
     * @param changesGameViewListener the changes game view listener
     * @param recordListener          the record listener
     * @param deployedListener        the deployed listener
     * @param sqlErrorListener        the sql error listener
     */
    public BusinessReplay(ChangesGameViewListener changesGameViewListener, ActionRecordListener recordListener,
                          DeployedTroopListener deployedListener, SQLErrorListener sqlErrorListener) {
        TroopManager troopManager = new TroopManager(sqlErrorListener);
        ArrayList<Troop> playableTroops = troopManager.getTroops();

        this.gameMatrix = new GameMatrix(changesGameViewListener, this);

        startThreadGameTime();
        this.deployTroops = new ReplayDeployedTroop(recordListener, gameTime, deployedListener,
                changesGameViewListener, playableTroops);
        startThreadDeployedTroops();
    }

    /**
     * Method that manages and execute if a new troop can be deployed on a certain position (row, column) on
     * the gameMatrix when the game is replayed. Checks is the position given is empty (to avoid some possible bugs).
     *
     * @param troop  the troop information
     * @param row    the row to deploy the troop
     * @param column the column to deploy the troop
     * @param user   the user that is deploying the troop
     * @return boolean that checks if it has been deployed or not
     */
    public boolean addNewTroopIfPossible(Troop troop, int row, int column, int user) {
        // To avoid some bugs
        if (gameMatrix.isSquareEmpty(row, column)) {
            gameMatrix.setTroopAndStartMovement(troop, row, column, user);
            return true;
        } else {
            return false;
        }
    }

    // Method that start the Thread that manages the time for deploy the troops when is their time to be deployed.
    private void startThreadGameTime() {
        this.gameTime = new GameTime();
        Thread timeThread = new Thread(this.gameTime);
        timeThread.start();
    }

    // Method that start the Thread of each DeployedTroop when the game is a reply.
    private void startThreadDeployedTroops() {
        Thread troopsThread = new Thread(this.deployTroops);
        troopsThread.start();
    }

    /**
     * Method that pauses the game. All Threads are paused.
     */
    public void pauseGame() {
        gameMatrix.pauseMovement();
        gameTime.pauseThread();
    }

    /**
     * Method that resumes the game. All Threads are resumed again.
     */
    public void playGame() {
        gameMatrix.resumeMovement();
        gameTime.resumeThread();
    }

    /**
     * Method that manages if the games is closed before the reply is ended.
     */
    public void earlyEndGame() {
        gameMatrix.earlyEndGame();
    }

    @Override
    public void notifyTroopEliminated(int userToEarnMoney) {
        // Method ignored on BusinessReplay, only works on BusinessGame
    }

    @Override
    public void stopAllThreads() {
        deployTroops.stopThread();
        gameTime.stopThread();
    }
}
