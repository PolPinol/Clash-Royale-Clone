package BusinessLayer.Model;

import BusinessLayer.Entities.DataGame;
import BusinessLayer.Entities.Troop;
import BusinessLayer.Entities.User;
import PresentationLayer.Controller.Listeners.ActionRecordListener;
import PresentationLayer.Controller.Listeners.ChangesGameViewListener;
import PresentationLayer.Controller.Listeners.DeployedTroopListener;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Class ReplayDeployedTroop that manages when a troop has to be deployed on a replay. It is managed by a thread
 * comparing a current time with the time troops where deployed on the original game.
 */
public class ReplayDeployedTroop implements Runnable {
    private boolean threadRunning;
    private final Queue<DataGame> data;
    private final GameTime time;
    private final DeployedTroopListener deployedTroopListener;
    private final ChangesGameViewListener gameViewListener;
    private final ArrayList<Troop> troops;

    /**
     * Instantiates a new Replay deployed troop.
     *
     * @param listener the ActionRecordListener
     * @param time     the gameTime thread
     * @param list1    the DeployedTroopListener
     * @param list2    the ChangesGameViewListener
     * @param troops   the troops information
     */
    public ReplayDeployedTroop(ActionRecordListener listener, GameTime time, DeployedTroopListener list1,
                               ChangesGameViewListener list2, ArrayList<Troop> troops) {
        this.threadRunning = true;
        this.time = time;
        this.data = listener.getAllMovements();
        deployedTroopListener = list1;
        gameViewListener = list2;
        this.troops = troops;
    }

    // Method that compares a current time with the time troops where deployed on the original game.
    private synchronized void printTroopsInTime() {
        if (data.size() > 0) {
            DataGame first = data.peek();

            if (time.getMillis() == first.getMillis()) {
                DataGame actual = data.remove();
                Troop troop = getTroop(actual.getTroopName());
                if (actual.getIsUser() == User.PLAYER_USER) {
                    deployedTroopListener.notifyDeployedTroopUser(troop, actual.getRow(), actual.getColumn(), actual.getIsUser());
                } else {
                    gameViewListener.notifyDeployedTroopComputer(troop, actual.getRow(), actual.getColumn(), actual.getIsUser());
                }
            }
        }
    }

    @Override
    public void run() {
        while (threadRunning) {
            printTroopsInTime();
        }
    }

    /**
     * Stop thread.
     */
    public void stopThread() {
        threadRunning = false;
    }

    private Troop getTroop(String name) {
        Troop troop = null;

        for (Troop t : troops) {
            if (t.getName().equals(name)) {
                troop = t;
            }
        }

        return troop;
    }
}
