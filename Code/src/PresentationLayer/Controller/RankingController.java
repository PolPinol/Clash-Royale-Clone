package PresentationLayer.Controller;

import BusinessLayer.Entities.User;
import BusinessLayer.Model.Managers.UserManager;
import PresentationLayer.Controller.Listeners.ActionGetRankingData;
import PresentationLayer.Controller.Listeners.GetDataTableClicked;
import PresentationLayer.Controller.Listeners.SettingsListener;
import PresentationLayer.Controller.Listeners.SwapCardListener;
import PresentationLayer.View.Listeners.ExtractDataListener;
import PresentationLayer.View.MainPanels.HistoryPanel;
import PresentationLayer.View.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Class RankingController that manages the ranking model and its view.
 *
 * It will manage the ask for the information on the database and show
 * it on the JTable on the RankingPanel.
 */
public class RankingController implements ActionListener, ActionGetRankingData, MouseListener {
    private final SwapCardListener swapCardListener;
    private final SettingsListener openSettings;
    private final UserManager users;
    private final ExtractDataListener rankingData;
    private final GetDataTableClicked getDataTableClicked;

    /**
     * Instantiates a new RankingController.
     *
     * @param swapCardListener      the swap card listener
     * @param openSettings          the open settings listener
     * @param userManager           the user manager
     * @param rankingData           the ranking data
     * @param getDataTableClicked   getDataTableClicked
     */
    public RankingController(SwapCardListener swapCardListener, SettingsListener openSettings,UserManager userManager,
                             ExtractDataListener rankingData, GetDataTableClicked getDataTableClicked){
        this.swapCardListener = swapCardListener;
        this.openSettings = openSettings;
        this.users = userManager;
        this.rankingData = rankingData;
        this.getDataTableClicked = getDataTableClicked;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case HistoryPanel.BACK_ACTION -> swapCardListener.notifyNewCard(MainView.CARD_MENU);
            case HistoryPanel.EVENT_SETTINGS -> openSettings.notifyStartDialog();
        }
    }

    @Override
    public void loadRankingTable() {
        ArrayList<User> data = users.getUsers();

        rankingData.clearTable();
        rankingData.notifyExtraction(data);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        getDataTableClicked.notifyDataTableClicked();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}