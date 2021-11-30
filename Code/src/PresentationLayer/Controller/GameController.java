package PresentationLayer.Controller;

import BusinessLayer.Entities.DataGame;
import BusinessLayer.Entities.Troop;
import BusinessLayer.Entities.User;
import BusinessLayer.Model.BusinessGame;
import BusinessLayer.Model.Listeners.WinnerListener;
import PresentationLayer.Controller.Listeners.*;
import PresentationLayer.View.GameView;
import PresentationLayer.View.Helpers.SaveGameDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class GameController that manages the model and the gameView of a specific game.
 */
public class GameController implements DeployedTroopListener, ChangesGameViewListener, ActionListener, UserComputerWinnerListener {
    /**
     * The constant NUMBER_TROOPS that the user can choose.
     */
    public static final int NUMBER_TROOPS = 6;
    /**
     * The constant MORE_TROOP.
     */
    public static final int MORE_TROOP = 1;
    /**
     * The constant LESS_TROOP.
     */
    public static final int LESS_TROOP = -1;
    /**
     * The constant to announce that the winner is the computer.
     */
    public static final String WINNER_IA = "La IA es la ganadora";

    private final BusinessGame businessGame;
    private final GameView gameView;
    private final ReturnMenuPanelListener listener;
    private final EndGameListener endGameListener;
    private final WinnerListener winnerListener;
    private final ActionRecordListener recordListener;
    private final LoginActionListener loginActionListener;
    private ArrayList<DataGame> datumGames;
    private final SettingsListener openSettings;

    /**
     * Instantiates a new GameController.
     *
     * @param listener            the return menu panel listener
     * @param endGameListener     the end game listener
     * @param recordListener      the record listener
     * @param loginActionListener the login action listener
     * @param openSettings        the open settings listener
     * @param sqlErrorListener    the sql error listener
     */
    public GameController(ReturnMenuPanelListener listener, EndGameListener endGameListener, ActionRecordListener recordListener,
                          LoginActionListener loginActionListener, SettingsListener openSettings, SQLErrorListener sqlErrorListener) {
        businessGame = new BusinessGame(this, sqlErrorListener);
        gameView = new GameView(this, this);
        this.listener = listener;
        this.endGameListener = endGameListener;
        this.winnerListener = businessGame.getWinnerListener();
        this.recordListener = recordListener;
        this.loginActionListener = loginActionListener;
        this.openSettings = openSettings;
        registerGameViewPanel();
    }

    /**
     * Method to initiate and start a game.
     *
     * It will upload from the database all the playable troops, will initiate the
     * thread to record all the game and will make visible the gameView.
     */
    public void start() {
        initiateTroopsToChoose();
        gameView.show();
        datumGames = new ArrayList<>();
    }

    /**
     * Method that manages an early close of a game. It will notify the businessGame that
     * the game is ended and will make invisible the gameView, letting visible the mainView again.
     */
    public void earlyCloseGame() {
        businessGame.earlyEndGame();
        gameView.closeGame();
        endGameListener.notifyEndGame();
    }

    @Override
    public void notifyDeployedTroopUser(Troop troop, int row, int column, int user) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (businessGame.addNewTroopIfPossible(troop, row, column, user)) {
                    gameView.paintFirstTime(troop, row, column, user);
                    datumGames.add(new DataGame(null, row, column, troop.getName(), user, businessGame.getMillis()));
                }
            }
        });
    }

    @Override
    public void notifyDeployedTroopComputer(Troop troop, int row, int column, int user) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (businessGame.addNewTroopIfPossible(troop, row, column, user)) {
                    gameView.paintFirstTime(troop, row, column, user);
                    datumGames.add(new DataGame(null, row, column, troop.getName(), user, businessGame.getMillis()));
                }
            }
        });
    }

    @Override
    public void notifyChangesGameView(Troop troop, int row, int column, int rowOriginToRemove, int columnOriginToRemove, int user) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameView.repaintGameMatrix(troop, row, column, rowOriginToRemove, columnOriginToRemove, user);
            }
        });
    }

    @Override
    public void notifyRemoveTroop(int row, int column, Troop deadTroop) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameView.repaintRemoveTroop(row, column, deadTroop);
            }
        });
    }

    @Override
    public void notifyLogsNotMoney(Troop troop) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameView.addLogsNotEnoughMoney(troop);
            }
        });
    }

    @Override
    public void notifyLogsCreateBarrier() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameView.addLogsBarrier();
            }
        });
    }

    @Override
    public void notifyLogsSquareNotEmpty() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameView.addLogsSquareNotEmpty();
            }
        });
    }

    @Override
    public void notifyChangeLifeGraphics(int life, int user) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameView.changeGraphicsLives(life, user);
            }
        });
    }

    @Override
    public void notifyMoreTroopGraphics(int user) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameView.changeGraphicsTroops(user, MORE_TROOP);
            }
        });
    }

    @Override
    public void notifyLessTroopGraphics(int user) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameView.changeGraphicsTroops(user, LESS_TROOP);
            }
        });
    }

    @Override
    public void notifyMoneyHasChanged(int money) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameView.repaintMoneyLabel(money);
            }
        });
    }

    @Override
    public void notifyEndGame(int userWinner) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameView.showEndGame();
                listener.notifyReturnMenuPanel(true, userWinner);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case GameView.EVENT_SETTINGS -> openSettings.notifyStartDialog();
            case SaveGameDialog.YES_ANSWER -> saveGame();
            case SaveGameDialog.NO_ANSWER -> gameView.closeGame();
        }
    }

    @Override
    public String notifyWhoIsWinner() {
        int winner = winnerListener.getWinner();

        if (winner == User.PLAYER_USER) {
            return loginActionListener.getUserName() + " es el ganador";
        } else {
            return WINNER_IA;
        }
    }

    // Method that manages the logic to save a game
    private synchronized void saveGame() {
        boolean saved = recordListener.addNewRecordIfDoesntExist(gameView.getRecordName(), winnerListener.getWinner());
        if (saved) {
            recordListener.addNewRecordData(datumGames, gameView.getRecordName());
            gameView.closeGame();
        }
    }

    private void initiateTroopsToChoose() {
        for (int i = 0; i < NUMBER_TROOPS; i++) {
            Troop troop = businessGame.getTroopsToChoose(i);
            gameView.addTroopsToChoose(i, troop);
        }
    }

    /**
     * Register game view panel.
     */
    public void registerGameViewPanel() {
        gameView.registerActionListener(this);
    }

    /**
     * Method that returns the user name string.
     *
     * @return the user name
     */
    public String returnUserName(){
        return loginActionListener.getUserName();
    }
}