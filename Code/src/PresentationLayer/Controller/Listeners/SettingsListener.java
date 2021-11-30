package PresentationLayer.Controller.Listeners;

/**
 * Interface SettingsListener that abstracts the method that manages the settings dialog.
 * These are: showing the dialog, making invisible the dialog, and manage the logic of an
 * early close of the game.
 */
public interface SettingsListener {
    /**
     * Method that notifies the View to show the settings dialog.
     */
    void notifyStartDialog();

    /**
     * Method that notifies the View to make invisible the settings dialog.
     */
    void notifyEndDialog();

    /**
     * Method that closes the game or the replay if they are not null.
     */
    void notifyEndGameDialog();
}