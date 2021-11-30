package BusinessLayer.Model;

import BusinessLayer.Model.Listeners.ActionTroopListener;
import BusinessLayer.Model.Listeners.MoneyListener;
import BusinessLayer.Model.Listeners.WinnerListener;
import BusinessLayer.Model.Troops.*;
import PresentationLayer.Controller.Listeners.ChangesGameViewListener;
import BusinessLayer.Entities.*;

import java.util.ArrayList;

/**
 * Class GameMatrix that manages the matrix where the troops are deployed. This class is based of ab
 * integer matrix of size ROWS x COLUMNS and its logic.
 */
public class GameMatrix implements ActionTroopListener, WinnerListener {
    /**
     * The constant EMPTY of an square of the gameMatrix.
     */
    public static final int EMPTY = -1;
    /**
     * The constant ROWS of the matrix.
     */
    public static final int ROWS = 10;
    /**
     * The constant COLUMNS of the matrix.
     */
    public static final int COLUMNS = 7;
    /**
     * The constant HALF_BOARD_ROW of the matrix.
     */
    public static final int HALF_BOARD_ROW = ROWS/2 - 1;
    /**
     * The constant ONE_LESS_ROW.
     */
    public static final int ONE_LESS_ROW = -1;
    /**
     * The constant MAX_LIVES of the game.
     */
    public static final int MAX_LIVES = 250;

    private final ArrayList<Thread> troopsActions;
    private final ArrayList<TroopDeployed> troopsDeployed;
    private final int[][] matrix;
    private final ChangesGameViewListener listener;
    private final MoneyListener moneyListener;
    private int playerLives;
    private int computerLives;
    private boolean fixOneFrame;
    private int userWinner;

    /**
     * Instantiates a new Game matrix.
     *
     * @param listener      the listener that manages the changes that has to be made on the gameView
     * @param moneyListener the money listener that manages the changes on the user's money
     */
    public GameMatrix(ChangesGameViewListener listener, MoneyListener moneyListener) {
        matrix = new int[ROWS][COLUMNS];
        this.listener = listener;
        this.moneyListener = moneyListener;
        this.troopsDeployed = new ArrayList<>();
        this.troopsActions = new ArrayList<>();
        this.playerLives = MAX_LIVES;
        this.computerLives = MAX_LIVES;
        this.fixOneFrame = false;
        initiateMatrix();
    }

    /**
     * Method that manages setting the troop on the position given and start it movement and thread.
     *
     * @param troop  the troop information
     * @param row    the row to deploy the troop
     * @param column the column to deploy the troop
     * @param user   the user
     */
    public void setTroopAndStartMovement(Troop troop, int row, int column, int user) {
        Thread thread;
        TroopDeployed troopDeployed;
        int id = troopsDeployed.size();

        troopDeployed = instanceTroop(troop, id, row, column, user);

        matrix[row][column] = id;
        troopsDeployed.add(troopDeployed);

        thread = new Thread(troopDeployed);
        thread.start();
        troopsActions.add(thread);

        listener.notifyMoreTroopGraphics(user);
    }

    @Override
    public void notifyAction(int row, int column, int user) {
        int destinyRow;
        int lastRow;
        int idTroopDeployed;
        TroopDeployed troopDeployed;
        Troop troop;

        // Initiate values
        destinyRow = assignDestinyRow(user, row);
        lastRow = assignLastRow(user);
        idTroopDeployed = matrix[row][column];
        troopDeployed = lookForTroop(idTroopDeployed);
        troop = troopDeployed.getTroop();

        // Different actions depends of troop type
        if (troopDeployed instanceof OffensiveTroopDeployed) {
            if (destinyRow == lastRow) {
                attackBase(troop, user);
            } else if (matrix[destinyRow][column] != EMPTY) {
                int idFront = matrix[destinyRow][column];
                attackEnemy(user, troop, destinyRow, column, idFront);
            } else {
                moveToFront(troopDeployed, idTroopDeployed, troop, row, column, destinyRow, column, user);
            }
        } else {
            if (matrix[destinyRow][column] != EMPTY) {
                int idFront = matrix[destinyRow][column];
                attackEnemy(user, troop, destinyRow, column, idFront);
            }
            attackSides(row, column, user, troop);
        }
    }

    // Method that manages the logic of moving to the front
    private void moveToFront(TroopDeployed troopDeployed, int idTroopDeployed, Troop troop, int row, int column,
                             int destinyRow, int destinyColumn, int user) {
        // Implementation of different classes for each troop with the intention of improving functionalities in the future
        if (troopDeployed instanceof Wizard) {
            Wizard wizard = (Wizard) troopDeployed;
            wizard.move(destinyRow, destinyColumn);
        } else if (troopDeployed instanceof Archer) {
            Archer archer = (Archer) troopDeployed;
            archer.move(destinyRow, destinyColumn);
        } else if (troopDeployed instanceof Knight) {
            Knight knight = (Knight) troopDeployed;
            knight.move(destinyRow, destinyColumn);
        }

        matrix[destinyRow][destinyColumn] = idTroopDeployed;
        matrix[row][column] = EMPTY;
        listener.notifyChangesGameView(troop, destinyRow, destinyColumn, row, column, user);
    }

    // Method that manages the logic of attacking sides of the origin position
    private void attackSides(int row, int column, int user, Troop troop) {
        int leftColumn, rightColumn;

        leftColumn = column - 1;
        rightColumn = column + 1;

        if (0 <= leftColumn) {
            int idLeft = matrix[row][leftColumn];
            if (idLeft != EMPTY) {
                attackEnemy(user, troop, row, leftColumn, idLeft);
            }
        }

        if (rightColumn < COLUMNS) {
            int idRight = matrix[row][rightColumn];
            if (idRight != EMPTY) {
                attackEnemy(user, troop, row, rightColumn, idRight);
            }
        }
    }

    // Method that manages the logic of attacking the enemy base
    private void attackBase(Troop troop, int user) {
        int attack = troop.getAttack();

        if (user == User.PLAYER_USER) {
            computerLives = computerLives - attack;
            listener.notifyChangeLifeGraphics(computerLives, user);
        } else {
            playerLives = playerLives - attack;
            listener.notifyChangeLifeGraphics(playerLives, user);
        }

        if (computerLives < 0 || playerLives < 0) {
            userWinner = user;
            endGame();
        }
    }

    // Method that manages the logic of attacking an enemy
    private void attackEnemy(int user, Troop troop, int destinyRow, int destinyColumn, int id) {
        TroopDeployed troopDeployed = lookForTroop(id);
        if (troopDeployed.getUser() != user) {
            if (!troopDeployed.damaged(troop.getAttack())) {
                listener.notifyLessTroopGraphics(troopDeployed.getUser());
                troopsActions.get(id).interrupt();
                troopDeployed.stopThread();
                matrix[destinyRow][destinyColumn] = EMPTY;
                listener.notifyRemoveTroop(destinyRow, destinyColumn, troopDeployed.getTroop());
                moneyListener.notifyTroopEliminated(user);
            }
        }
    }

    // Method that change instance the Entities Troop to TroopDeplyed and its logic.
    private TroopDeployed instanceTroop(Troop troop, int id, int row, int column, int user) {
        TroopDeployed troopDeployed;
        String troopName = troop.getName();

        troopDeployed = switch (troopName) {
            case Troop.WIZARD -> new Wizard(id, troop, row, column, user, this);
            case Troop.ARCHER -> new Archer(id, troop, row, column, user, this);
            case Troop.KNIGHT -> new Knight(id, troop, row, column, user, this);
            case Troop.CANNON -> new Cannon(id, troop, row, column, user, this);
            case Troop.ICE    -> new Ice(id, troop, row, column, user, this);
            case Troop.GOLEM  -> new Golem(id, troop, row, column, user, this);

            // Default Troop if appears a SQL error
            default -> new Ice(id, troop, row, column, user, this);
        };

        return troopDeployed;
    }

    /**
     * Method that manages if the game is ended before its normal thread.
     */
    public void earlyEndGame() {
        for (int i = 0; i < troopsDeployed.size(); i++) {
            troopsDeployed.get(i).stopThread();
        }

        for (int i = 0; i < troopsActions.size(); i++) {
            if (troopsActions.get(i).isAlive()) {
                troopsActions.get(i).interrupt();
            }
        }
        moneyListener.stopAllThreads();
    }

    /**
     * Check is exists a player troop on the half board of the enemy.
     *
     * @return the boolean
     */
    public boolean existsPlayerTroopOnHalfBoard() {
        TroopDeployed troopDeployed;

        for (int i = 0; i < HALF_BOARD_ROW; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (matrix[i][j] != EMPTY) {
                    troopDeployed = lookForTroop(matrix[i][j]);
                    if (troopDeployed.getUser() == User.PLAYER_USER) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Method that asks if is there a barrier made by the user
     *
     * @param row       the row
     * @param user      the user
     * @return the boolean
     */
    public boolean createBarrier(int row, int user) {
        int squareOccupiedForUser = 0;
        int id;

        for (int i = 0; i < COLUMNS; i++) {
            id = matrix[row][i];
            if (id != EMPTY) {
                if (lookForTroop(id).getUser() == user) {
                    squareOccupiedForUser++;
                }
            }
        }

        return squareOccupiedForUser == (COLUMNS-1);
    }

    /**
     * Initiate the integers game matrix ROWS x COLUMNS.
     */
    public void initiateMatrix() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                matrix[i][j] = EMPTY;
            }
        }
    }

    /**
     * Check if the square of teh gameMatrix is empty.
     *
     * @param row    the row
     * @param column the column
     * @return the boolean
     */
    public boolean isSquareEmpty(int row, int column) {
        return matrix[row][column] == EMPTY;
    }

    // Assign the destiny row of the action depending of the user
    private int assignDestinyRow(int user, int row) {
        if (user == User.PLAYER_USER) {
            return row - 1;
        } else {
            return row + 1;
        }
    }

    // Assign the last row depending of the user
    private int assignLastRow(int user) {
        if (user == User.PLAYER_USER) {
            return ONE_LESS_ROW;
        } else {
            return ROWS;
        }
    }

    /**
     * Method that pauses the movements and threads.
     */
    public void pauseMovement() {
        for (int i = 0; i < troopsDeployed.size(); i++) {
            troopsDeployed.get(i).pauseThread();
        }
    }

    /**
     * Method that resume the movements and threads.
     */
    public void resumeMovement() {
        for (int i = 0; i < troopsDeployed.size(); i++) {
            troopsDeployed.get(i).resumeThread();
        }
    }

    private TroopDeployed lookForTroop(int id) {
        return troopsDeployed.get(id);
    }

    // Method that manages that only ONE dialog is opened.
    private synchronized void openConfirmationSaveGame() {
        if (!fixOneFrame) {
            fixOneFrame = true;
            listener.notifyEndGame(userWinner);
        }
    }

    private void endGame() {
        earlyEndGame();
        openConfirmationSaveGame();
    }

    @Override
    public int getWinner() {
        return userWinner;
    }
}