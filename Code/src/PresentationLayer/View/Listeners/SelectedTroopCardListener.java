package PresentationLayer.View.Listeners;

import BusinessLayer.Entities.*;

/**
 * Interface SelectedTroopCardListener that abstracts the methods that manages
 * when a troop card panel is selected.
 */
public interface SelectedTroopCardListener {
    /**
     * Method that manages the change of the color when a troop card is selected.
     * It also manages that just only one card is selected at the same time.
     *
     * @param troop the troop from the troopCard
     */
    void notifyTroopCardSelected(Troop troop);
}