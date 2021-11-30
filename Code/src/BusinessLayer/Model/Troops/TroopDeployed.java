package BusinessLayer.Model.Troops;

import BusinessLayer.Entities.Troop;
import BusinessLayer.Model.Listeners.ActionTroopListener;

/**
 * Class TroopDeployed. This class implements Runnable and notifies every
 * second that the troop wants to do an action.
 */
public abstract class TroopDeployed implements Runnable {
    private final int id;
    private final Troop troop;
    private int row;
    private int column;
    private final ActionTroopListener listener;
    private final int user;
    private int life;
    private boolean threadRunning;
    private boolean play;

    /**
     * Instantiates a new Troop deployed.
     *
     * @param id       the id
     * @param troop    the troop information
     * @param row      the row
     * @param column   the column
     * @param user     the user
     * @param listener the ActionTroopListener
     */
    public TroopDeployed(int id, Troop troop, int row, int column, int user, ActionTroopListener listener) {
        this.id = id;
        this.troop = troop;
        this.row = row;
        this.column = column;
        this.listener = listener;
        this.user = user;
        this.life = troop.getLife();
        this.threadRunning = true;
        this.play = true;
    }

    /**
     * Method that damage the troop and check is is alive.
     *
     * @param attack the attack received
     * @return the boolean that manages if the troop is alive
     */
    public boolean damaged(int attack) {
        this.life = life - attack;

        return isAlive();
    }

    private synchronized boolean isAlive() {
        return life > 0;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public int getUser() {
        return user;
    }

    /**
     * Gets troop.
     *
     * @return the troop
     */
    public Troop getTroop() {
        return troop;
    }

    /**
     * Sets row.
     *
     * @param row the row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Sets column.
     *
     * @param column the column
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Stop thread.
     */
    public void stopThread() {
        threadRunning = false;
    }

    /**
     * Resume thread.
     */
    public void resumeThread() {
        play = true;
    }

    /**
     * Pause thread.
     */
    public void pauseThread() {
        play = false;
    }

    private void notifyAction() {
        if (threadRunning) {
            listener.notifyAction(row, column, user);
        }
    }

    private void logic() {
        try {
            Thread.sleep(1000);
            if (play) {
                notifyAction();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        while (threadRunning) {
            logic();
        }
    }
}