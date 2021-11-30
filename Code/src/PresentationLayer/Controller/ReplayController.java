package PresentationLayer.Controller;

import BusinessLayer.Entities.Troop;
import BusinessLayer.Model.BusinessReplay;
import PresentationLayer.Controller.Listeners.*;
import PresentationLayer.View.ReplayView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class ReplayController that manages the model and the replayView of a specific replay.
 */
public class ReplayController implements DeployedTroopListener, ChangesGameViewListener, ActionListener {
    /**
     * The constant IGNORED.
     */
    public static final int IGNORED = -1;
    /**
     * The constant MORE_TROOP.
     */
    public static final int MORE_TROOP = 1;
    /**
     * The constant LESS_TROOP.
     */
    public static final int LESS_TROOP = -1;

    private final BusinessReplay businessReplay;
    private final ReplayView replayView;
    private final ReturnMenuPanelListener listener;
    private final SettingsListener openSettings;
    private final EndGameListener endGameListener;

    /**
     * Instantiates a new ReplayController.
     *
     * @param listener         the listener
     * @param recordListener   the record listener
     * @param sqlErrorListener the sql error listener
     * @param openSettings     the open settings
     * @param endGameListener  the end game listener
     */
    public ReplayController(ReturnMenuPanelListener listener, ActionRecordListener recordListener,
                            SQLErrorListener sqlErrorListener, SettingsListener openSettings, EndGameListener endGameListener) {
        businessReplay = new BusinessReplay(this, recordListener, this, sqlErrorListener);
        replayView = new ReplayView();
        this.listener = listener;
        this.openSettings = openSettings;
        this.endGameListener = endGameListener;
        registerReplayViewPanel();
    }

    /**
     * Method to initiate and start a replay.
     */
    public void start() {
        replayView.show();
    }

    /**
     * Method that manages an early close of a replay. It will notify the businessReplay that
     * the replay is ended and will make invisible the replayView, letting visible the mainView again.
     */
    public void earlyCloseGame() {
        businessReplay.earlyEndGame();
        replayView.closeGame();
        endGameListener.notifyEndGame();
    }

    @Override
    public void notifyChangeLifeGraphics(int life, int user) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                replayView.changeGraphicsLives(life, user);
            }
        });
    }

    @Override
    public void notifyMoreTroopGraphics(int user) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                replayView.changeGraphicsTroops(user, MORE_TROOP);
            }
        });
    }

    @Override
    public void notifyLessTroopGraphics(int user) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                replayView.changeGraphicsTroops(user, LESS_TROOP);
            }
        });
    }

    @Override
    public void notifyDeployedTroopComputer(Troop troop, int row, int column, int user) {
        if (businessReplay.addNewTroopIfPossible(troop, row, column, user)) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    replayView.paintFirstTime(troop, row, column, user);
                }
            });
        }
    }

    @Override
    public void notifyChangesGameView(Troop troop, int row, int column, int rowOriginToRemove, int columnOriginToRemove, int user) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                replayView.repaintGameMatrix(troop, row, column, rowOriginToRemove, columnOriginToRemove, user);
            }
        });
    }

    @Override
    public void notifyRemoveTroop(int row, int column, Troop deadTroop) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                replayView.repaintRemoveTroop(row, column, deadTroop);
            }
        });
    }

    @Override
    public void notifyEndGame(int userWinner) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                replayView.endGame();
                listener.notifyReturnMenuPanel(false, IGNORED);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ReplayView.PAUSE -> pauseGame();
            case ReplayView.PLAY -> resumeGame();
            case ReplayView.CLOSE_GAME -> earlyCloseGame();
            case ReplayView.EVENT_SETTINGS -> openSettings.notifyStartDialog();
        }
    }

    @Override
    public void notifyDeployedTroopUser(Troop troop, int row, int column, int user) {
        if (businessReplay.addNewTroopIfPossible(troop, row, column, user)) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    replayView.paintFirstTime(troop, row, column, user);
                }
            });
        }
    }

    private void pauseGame() {
        replayView.turnOnPlay();
        businessReplay.pauseGame();
    }

    private void resumeGame() {
        replayView.turnOnPause();
        businessReplay.playGame();
    }

    private void registerReplayViewPanel() {
        replayView.registerActionListener(this);
    }

    @Override
    public void notifyLogsNotMoney(Troop troop) {
        // Ignored on the Replay Logic
    }

    @Override
    public void notifyLogsCreateBarrier() {
        // Ignored on the Replay Logic
    }

    @Override
    public void notifyLogsSquareNotEmpty() {
        // Ignored on the Replay Logic
    }

    @Override
    public void notifyMoneyHasChanged(int money) {
        // Ignored on the Replay Logic
    }
}
