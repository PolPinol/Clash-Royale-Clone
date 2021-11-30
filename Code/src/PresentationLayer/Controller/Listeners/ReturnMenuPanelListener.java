package PresentationLayer.Controller.Listeners;

/**
 * Interface ReturnMenuPanelListener that abstracts the method that manages the return of the view to the menu panel.
 */
public interface ReturnMenuPanelListener {
    /**
     * Method that makes the view return to the menu panel.
     *
     * If the origin of the action was a game (and not a replay), the method will add game played
     * to the database of the current user. It also will add a win if it's the case. If the origin
     * of the action is a replay, it will ignored the cases explained before.
     *
     * @param isNotReplay boolean for knowing if is not a replay
     * @param userWinner  the user winner
     */
    void notifyReturnMenuPanel(boolean isNotReplay, int userWinner);
}
