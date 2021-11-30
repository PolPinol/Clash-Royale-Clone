package PresentationLayer.Controller;

import BusinessLayer.Entities.DataGame;
import BusinessLayer.Entities.Record;
import BusinessLayer.Model.Managers.DataGameManager;
import BusinessLayer.Model.Managers.RecordManager;
import PresentationLayer.Controller.Listeners.*;
import PresentationLayer.View.Listeners.SelectedRecordListener;
import PresentationLayer.View.MainPanels.HistoryPanel;
import PresentationLayer.View.MainView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Class HistoryController that manages the history model and its view.
 *
 * It will manage the ask for the information on the database and show
 * it on the JList on the RankingPanel.
 */
public class HistoryController implements ActionListener, ListSelectionListener, ActionRecordListener {
    /**
     * The constant NO_SELECTED item from the JList.
     */
    public static final int NO_SELECTED = -1;
    /**
     * The constant DTF from date format.
     */
    public static final String DTF = "dd/MM/yyyy";
    /**
     * The constant DTFH from hour format.
     */
    public static final String DTFH = "hh:mm";

    private final SelectedRecordListener selectedRecordListener;
    private final SwapCardListener swapCardListener;
    private final LoginActionListener loginListener;
    private final RecordManager recordManager;
    private final DataGameManager dataGameManager;
    private final StartReplayListener startReplayListener;
    private final SettingsListener openSettings;

    /**
     * Instantiates a new HistoryController.
     *
     * @param selectedRecordListener the selected record listener
     * @param swapCardListener       the swap card listener
     * @param loginListener          the login listener
     * @param startReplayListener    the start replay listener
     * @param openSettings           the open settings listener
     * @param sqlErrorListener       the sql error listener
     */
    public HistoryController(SelectedRecordListener selectedRecordListener, SwapCardListener swapCardListener,
                             LoginActionListener loginListener, StartReplayListener startReplayListener,
                             SettingsListener openSettings, SQLErrorListener sqlErrorListener){
        this.loginListener = loginListener;
        this.startReplayListener = startReplayListener;
        this.recordManager = new RecordManager(sqlErrorListener);
        this.dataGameManager = new DataGameManager(sqlErrorListener);
        this.selectedRecordListener = selectedRecordListener;
        this.swapCardListener = swapCardListener;
        this.openSettings = openSettings;
    }

    /**
     * Method that ask for its manager to get all the replays of the current user.
     * They will be sorted by its hour and date, and will be shown on a JList on
     * the historyPanel.
     *
     * @param userName the user name
     */
    public void updateHistory(String userName) {
        String gameName;
        String gameDate;
        int isWinner;

        ArrayList<Record> records = recordManager.getRecords(userName);
        selectedRecordListener.clearAllList();

        for (int i = 0; i < records.size(); i++) {
            gameName = records.get(i).getName();
            gameDate = records.get(i).getDate();
            isWinner = records.get(i).getWinner();
            selectedRecordListener.notifyNewRecord(gameName, gameDate, isWinner);
        }
    }

    @Override
    public void loadHistoryList() {
        String userName = loginListener.getUserName();
        updateHistory(userName);
    }

    @Override
    public boolean addNewRecordIfDoesntExist(String name, int winner) {
        boolean found;
        String userName = loginListener.getUserName();
        ArrayList<Record> records = recordManager.getRecords(userName);

        if (name.equals("")) {
            selectedRecordListener.notifyNameEmpty();
            return false;
        } else {
            found = isThereARecordWithSameName(records, name);

            if (!found) {
                addRecord(name, winner, userName);
                return true;
            } else {
                selectedRecordListener.notifyRecordExists();
                return false;
            }
        }
    }

    @Override
    public void addNewRecordData(ArrayList<DataGame> data, String recordName) {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setRecordName(recordName);
        }

        dataGameManager.insertDataGame(data);
    }

    @Override
    public void deleteUserRecords() {
        String userName = loginListener.getUserName();
        ArrayList<Record> records = recordManager.getRecords(userName);

        for (Record r : records) {
            dataGameManager.removeDataGame(r);
        }
        recordManager.removeUserRecord(userName);
    }

    @Override
    public Queue<DataGame> getAllMovements() {
        return dataGameManager.getData(selectedRecordListener.getNameGame());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case HistoryPanel.BACK_ACTION -> swapCardListener.notifyNewCard(MainView.CARD_MENU);
            case HistoryPanel.DELETE_ACTION -> deleteRecordData();
            case HistoryPanel.PLAY_ACTION -> startReplayListener.notifyStartReplay();
            case HistoryPanel.EVENT_SETTINGS -> openSettings.notifyStartDialog();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (selectedRecordListener.getSelectItem() != NO_SELECTED) {
                selectedRecordListener.notifyRecordSelected();
            } else {
                selectedRecordListener.notifyRecordNonSelected();
            }
        }
    }

    private void addRecord(String name, int winner, String userName) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DTF);
        DateTimeFormatter dtfh = DateTimeFormatter.ofPattern(DTFH);
        LocalDateTime now = LocalDateTime.now();

        recordManager.addRecord(new Record(name, dtf.format(now), dtfh.format(now), winner, userName));
    }

    private boolean isThereARecordWithSameName(ArrayList<Record> records, String name) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    private void deleteRecord(int id) {
        String userName = loginListener.getUserName();
        ArrayList<Record> records = recordManager.getRecords(userName);

        recordManager.removeRecord(records.get(id));
        selectedRecordListener.notifyDeleteRecord(id);
    }

    private void deleteRecordData() {
        String userName = loginListener.getUserName();
        ArrayList<Record> records = recordManager.getRecords(userName);

        dataGameManager.removeDataGame(records.get(selectedRecordListener.getSelectItem()));
        deleteRecord(selectedRecordListener.getSelectItem());
    }
}