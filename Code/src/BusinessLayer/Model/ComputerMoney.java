package BusinessLayer.Model;

import BusinessLayer.Model.Listeners.ComputerLogicListener;

/**
 * Class ComputerMoney that is a Thread that manages the logic and money of the computer player.
 * Every two seconds gain passive money. Every second tries to deploy a defensive troop and every
 * four seconds tries to deploy an offensive troop.
 */
public class ComputerMoney implements Runnable {
    /**
     * The constant COLUMNS of the GameMatrix.
     */
    public static final int COLUMNS = 7;
    /**
     * The constant MAXIMUM_VALUE of the money.
     */
    public static final int MAXIMUM_VALUE = 10;
    /**
     * The constant MONEY_STARTING of the money.
     */
    public static final int MONEY_STARTING = 6;

    private int money;
    private final ComputerLogicListener listener;
    private boolean threadRunning;

    /**
     * Instantiates a new Computer money.
     *
     * @param listener the listener that listener when an action is produced.
     */
    public ComputerMoney(ComputerLogicListener listener) {
        this.money = MONEY_STARTING;
        this.listener = listener;
        this.threadRunning = true;
    }

    @Override
    public void run() {
        while (threadRunning) {
            try {
                checkForDefensiveTroopAndMoreMoney();
                Thread.sleep(1000);

                checkForDefensiveTroop();
                Thread.sleep(1000);

                checkForDefensiveTroopAndMoreMoney();
                Thread.sleep(1000);

                checkForTroop();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private synchronized void checkForDefensiveTroopAndMoreMoney() {
        if (threadRunning) {
            listener.tryDeployDefensiveTroop();
        }
        moreMoney();
    }

    private synchronized void checkForDefensiveTroop() {
        if (threadRunning) {
            listener.tryDeployDefensiveTroop();
        }
    }

    private synchronized void checkForTroop() {
        if (threadRunning) {
            listener.tryDeployDefensiveTroop();
            listener.tryDeployOffensiveTroop();
        }
    }

    /**
     * Gets money.
     *
     * @return the money
     */
    public synchronized int getMoney() {
        return money;
    }

    /**
     * Reduce money.
     *
     * @param price the price
     */
    public synchronized void reduceMoney(int price) {
        money = money - price;
    }

    /**
     * More money.
     */
    public synchronized void moreMoney() {
        if (money != MAXIMUM_VALUE) {
            money++;
        }
    }

    /**
     * Stop thread.
     */
    public void stopThread() {
        threadRunning = false;
    }
}
