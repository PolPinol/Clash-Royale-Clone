package BusinessLayer.Model;

import BusinessLayer.Entities.Troop;
import BusinessLayer.Entities.User;
import BusinessLayer.Model.Listeners.ComputerLogicListener;
import BusinessLayer.Model.Listeners.MoneyListener;
import BusinessLayer.Model.Listeners.WinnerListener;
import BusinessLayer.Model.Managers.TroopManager;
import PresentationLayer.Controller.Listeners.ChangesGameViewListener;
import PresentationLayer.Controller.Listeners.SQLErrorListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class BusinessGame that manages the logic of a game.
 */
public class BusinessGame implements ComputerLogicListener, MoneyListener {
    private final ChangesGameViewListener listener;
    private final ComputerMoney computerMoney;
    private final PlayerMoney playerMoney;
    private ArrayList<Troop> playableTroops;
    private final GameMatrix gameMatrix;
    private final TroopManager troopManager;
    private final GameTime gameTime;

    /**
     * Instantiates a new BusinessGame.
     *
     * @param changesGameViewListener the changes to the view listener
     * @param sqlErrorListener        the sql error listener
     */
    public BusinessGame(ChangesGameViewListener changesGameViewListener, SQLErrorListener sqlErrorListener) {
        this.troopManager = new TroopManager(sqlErrorListener);
        this.gameMatrix = new GameMatrix(changesGameViewListener, this);

        getTroopsFromSQL();

        this.playerMoney = new PlayerMoney(changesGameViewListener);
        new Thread(this.playerMoney).start();

        this.computerMoney = new ComputerMoney(this);
        new Thread(this.computerMoney).start();

        this.gameTime = new GameTime();
        new Thread(this.gameTime).start();

        this.listener = changesGameViewListener;
    }

    /**
     * Method that manages and execute if a new troop can be deployed on a certain position (row, column) on
     * the gameMatrix. Checks is the position given is empty, if the player who is deploying has money to send
     * the troop and if the player is not creating a barrier of troops. If one of these is not true, then is
     * notify by a listener to the gameView by a message on the logs.
     *
     * @param troop  the troop information
     * @param row    the row to deploy the troop
     * @param column the column to deploy the troop
     * @param user   the user that is deploying the troop
     * @return boolean that checks if it has been deployed or not
     */
    public boolean addNewTroopIfPossible(Troop troop, int row, int column, int user) {
        if (gameMatrix.isSquareEmpty(row, column)) {
            if (!gameMatrix.createBarrier(row, user)) {
                if (playerHasMoneyToSetTroop(troop, user)) {
                    gameMatrix.setTroopAndStartMovement(troop, row, column, user);
                    return true;
                } else if (user == User.PLAYER_USER) {
                    listener.notifyLogsNotMoney(troop);
                }
            } else if (user == User.PLAYER_USER) {
                listener.notifyLogsCreateBarrier();
            }
        } else if (user == User.PLAYER_USER) {
            listener.notifyLogsSquareNotEmpty();
        }
        return false;
    }

    /**
     * Method that checks if the user has money to send a troop. If the user has money
     * to send the troop, it is reduced and returned true.
     *
     * @param troop the troop information
     * @param user  the user that wants to deploy the troop
     * @return the boolean that checks if user has money
     */
    public boolean playerHasMoneyToSetTroop(Troop troop, int user) {
        int price = troop.getPrice();
        int money;

        if (user == User.PLAYER_USER) {
            money = playerMoney.getMoney();

            if (price <= money) {
                playerMoney.reduceMoney(price);
                return true;
            } else {
                return false;
            }
        } else {
            money = computerMoney.getMoney();

            if (price <= money) {
                computerMoney.reduceMoney(price);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void tryDeployDefensiveTroop() {
        Troop defensiveRandomTroop = null;
        ArrayList<Troop> clonePlayableTroops = new ArrayList<>(playableTroops);
        Collections.shuffle(clonePlayableTroops);

        for (int i = 0; i < clonePlayableTroops.size(); i++) {
            if (clonePlayableTroops.get(i).getType() == Troop.DEF) {
                defensiveRandomTroop = clonePlayableTroops.get(i);
            }
        }

        int randomRow = (int) (Math.random()*2);
        ArrayList<Integer> randomColumns = getRangeList(GameMatrix.COLUMNS);
        Collections.shuffle(randomColumns);

        for (int i = 0; i < randomColumns.size(); i++) {
            if (gameMatrix.isSquareEmpty(randomRow, randomColumns.get(i)) && !gameMatrix.createBarrier(randomRow, User.IA_USER)) {
                if (gameMatrix.existsPlayerTroopOnHalfBoard()) {
                    listener.notifyDeployedTroopComputer(defensiveRandomTroop, randomRow, randomColumns.get(i), User.IA_USER);
                }
                break;
            }
        }
    }

    @Override
    public void tryDeployOffensiveTroop() {
        Troop offensiveRandomTroop = null;
        ArrayList<Troop> clonePlayableTroops = new ArrayList<>(playableTroops);
        Collections.shuffle(clonePlayableTroops);

        for (int i = 0; i < clonePlayableTroops.size(); i++) {
            if (clonePlayableTroops.get(i).getType() == Troop.OFF) {
                offensiveRandomTroop = clonePlayableTroops.get(i);
            }
        }

        ArrayList<Integer> randomColumns = getRangeList(GameMatrix.COLUMNS);
        Collections.shuffle(randomColumns);

        for (int i = 0; i < randomColumns.size(); i++) {
            if (gameMatrix.isSquareEmpty(GameMatrix.HALF_BOARD_ROW, randomColumns.get(i))) {
                listener.notifyDeployedTroopComputer(offensiveRandomTroop, GameMatrix.HALF_BOARD_ROW, randomColumns.get(i), User.IA_USER);
                break;
            }
        }
    }

    @Override
    public void notifyTroopEliminated(int userToEarnMoney) {
        if (userToEarnMoney == User.PLAYER_USER) {
            playerMoney.moreMoney();
        } else {
            computerMoney.moreMoney();
        }
    }

    @Override
    public void stopAllThreads() {
        computerMoney.stopThread();
        playerMoney.stopThread();
        gameTime.stopThread();
    }

    /**
     * Gets range list.
     *
     * @param n the n
     * @return the range list
     */
    public ArrayList<Integer> getRangeList(int n) {
        ArrayList<Integer> array = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            array.add(i);
        }

        return array;
    }

    /**
     * Early end game.
     */
    public void earlyEndGame() {
        gameMatrix.earlyEndGame();
    }

    /**
     * Gets winner listener.
     *
     * @return the winner listener
     */
    public WinnerListener getWinnerListener() {
        return gameMatrix;
    }

    /**
     * Gets millis.
     *
     * @return the millis
     */
    public long getMillis() {
        return gameTime.getMillis();
    }

    /**
     * Gets troops from SQL that can be played.
     *
     * @param index the index of the troop
     * @return the troops to play
     */
    public Troop getTroopsToChoose(int index) {
        return playableTroops.get(index);
    }

    private void getTroopsFromSQL() {
        playableTroops = troopManager.getTroops();
    }
}