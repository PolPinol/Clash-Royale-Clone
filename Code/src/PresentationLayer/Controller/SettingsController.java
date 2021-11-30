package PresentationLayer.Controller;

import PresentationLayer.Controller.Listeners.DeleteActionListener;
import PresentationLayer.Controller.Listeners.ModifyUserListener;
import PresentationLayer.Controller.Listeners.SettingsListener;
import PresentationLayer.Controller.Listeners.SwapCardListener;
import PresentationLayer.View.Helpers.SettingsDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class SettingsController that manages the settings dialog and its button.
 *
 * It will manage the the logout and deleting and account of a user.
 */
public class SettingsController implements ActionListener, DeleteActionListener {
    /**
     * The constant SWAP_TO_CARD_USER.
     */
    public static final String SWAP_TO_CARD_USER = "cardUser";

    private final ModifyUserListener modifyUserListener;
    private final SwapCardListener swapCardListener;
    private final SettingsListener closeSettings;

    /**
     * Instantiates a new SettingsController.
     *
     * @param swapCardListener   the swap card listener
     * @param modifyUserListener the modify user listener
     * @param closeSettings      the close settings
     */
    public SettingsController(SwapCardListener swapCardListener, ModifyUserListener modifyUserListener,
                              SettingsListener closeSettings){
        this.modifyUserListener = modifyUserListener;
        this.swapCardListener = swapCardListener;
        this.closeSettings = closeSettings;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(SettingsDialog.EVENT_LOGOUT)) {
            logoutAndBack();
        }
    }

    @Override
    public void notifyAction() {
        deleteUserAccountAndBack();
    }

    // Method that logout a user and returns to the UserPanel
    private void logoutAndBack() {
        swapCardListener.notifyNewCard(SWAP_TO_CARD_USER);
        closeSettings.notifyEndDialog();
        closeSettings.notifyEndGameDialog();
        modifyUserListener.notifyLogoutUser();
    }

    // Method that delete a user from the database and returns to the UserPanel
    private void deleteUserAccountAndBack() {
        swapCardListener.notifyNewCard(SWAP_TO_CARD_USER);
        closeSettings.notifyEndDialog();
        closeSettings.notifyEndGameDialog();
        modifyUserListener.notifyDeleteUser();
    }
}