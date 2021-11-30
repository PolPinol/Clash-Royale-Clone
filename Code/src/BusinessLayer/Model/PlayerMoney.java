package BusinessLayer.Model;

import PresentationLayer.Controller.Listeners.ChangesGameViewListener;

/**
 * Class PlayerMoney that is a Thread that manages the money of the player.
 * Every two seconds gain passive money.
 */
public class PlayerMoney implements Runnable {
    /**
     * The constant MAXIMUM_VALUE.
     */
    public static final int MAXIMUM_VALUE = 10;
    /**
     * The constant MONEY_STARTING.
     */
    public static final int MONEY_STARTING = 6;

    private int money;
    private final ChangesGameViewListener listener;
    private boolean threadRunning;

    /**
     * Instantiates a new Player money.
     *
     * @param listener the ChangesGameViewListener
     */
    public PlayerMoney(ChangesGameViewListener listener) {
        this.money = MONEY_STARTING;
        this.listener = listener;
        this.threadRunning = true;
    }

    @Override
    public void run() {
        while (threadRunning) {
            try {
                moreMoney();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Gets money.
     *
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Reduce money.
     *
     * @param price the price
     */
    public synchronized void reduceMoney(int price) {
        money = money - price;
        listener.notifyMoneyHasChanged(money);
    }

    /**
     * More money.
     */
    public synchronized void moreMoney() {
        if (money != MAXIMUM_VALUE) {
            money++;
        }
        listener.notifyMoneyHasChanged(money);
    }

    /**
     * Stop thread.
     */
    public void stopThread() {
        threadRunning = false;
    }
}
