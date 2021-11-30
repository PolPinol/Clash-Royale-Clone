package PresentationLayer.Controller;

import PresentationLayer.Controller.Listeners.*;
import PresentationLayer.View.MainPanels.MenuPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class MenuController that manages the different options that are on the MenuPanel.
 *
 * It will manage basically the four buttons that appear on the its view. One button will
 * make the game star, the other button will swap to the ranking panel, the other will swap
 * to the history card and the last one will open the settings dialog.
 */
public class MenuController implements ActionListener {
    /**
     * The constant SWAP_TO_CARD_HISTORY.
     */
    public static final String SWAP_TO_CARD_HISTORY = "cardRecords";
    /**
     * The constant SWAP_TO_CARD_RANKING.
     */
    public static final String SWAP_TO_CARD_RANKING = "cardRanking";
    /**
     * The constant SWAP_TO_CARD_USER.
     */
    public static final String SWAP_TO_CARD_USER = "cardUser";

    private final SwapCardListener swapCardListener;
    private final ActionRecordListener recordListener;
    private final ActionGetRankingData rankingListener;
    private final ShowInstructionsListener instructionListenerListener;
    private final SettingsListener openSettings;

    /**
     * Instantiates a new MenuController.
     *
     * @param instructionListenerListener the instruction listener listener
     * @param swapCardListener            the swap card listener
     * @param recordListener              the record listener
     * @param openSettings                the open settings listener
     * @param rankingListener             the ranking listener
     */
    public MenuController(ShowInstructionsListener instructionListenerListener, SwapCardListener swapCardListener,
                          ActionRecordListener recordListener, SettingsListener openSettings,
                          ActionGetRankingData rankingListener) {
        this.swapCardListener = swapCardListener;
        this.recordListener = recordListener;
        this.instructionListenerListener = instructionListenerListener;
        this.openSettings = openSettings;
        this.rankingListener = rankingListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case MenuPanel.EVENT_PLAY_CLICK -> instructionListenerListener.notifyShowInstruction();
            case MenuPanel.EVENT_RANKING -> loadAndSwapCardRanking();
            case MenuPanel.EVENT_HISTORY -> loadAndSwapCardHistory();
            case MenuPanel.EVENT_SETTINGS -> openSettings.notifyStartDialog();
        }
    }

    private void loadAndSwapCardHistory() {
        recordListener.loadHistoryList();
        swapCardListener.notifyNewCard(SWAP_TO_CARD_HISTORY);
    }

    private void loadAndSwapCardRanking() {
        rankingListener.loadRankingTable();
        swapCardListener.notifyNewCard(SWAP_TO_CARD_RANKING);
    }

    /**
     * Method that notifies that the UserPanel has to be shown.
     */
    public void backToUserPanel() {
        swapCardListener.notifyNewCard(SWAP_TO_CARD_USER);
    }
}