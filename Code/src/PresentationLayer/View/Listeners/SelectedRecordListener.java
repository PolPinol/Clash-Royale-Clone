package PresentationLayer.View.Listeners;

/**
 * Interface SelectedRecordListener that abstracts the methods that manages all
 * the information of the JList of the history panel.
 */
public interface SelectedRecordListener {
    /**
     * Method that adds a new reply on the jlist of the history panel.
     *
     * @param name   the name
     * @param date   the date
     * @param winner the winner
     */
    void notifyNewRecord(String name, String date, int winner);

    /**
     * Method that shows an error that the name of the replay already exists.
     */
    void notifyRecordExists();

    /**
     * Method that shows an error that the name is empty.
     */
    void notifyNameEmpty();

    /**
     * Method that remove a replay from the JList.
     *
     * @param id the id of the replay
     */
    void notifyDeleteRecord(int id);

    /**
     * Method that enables the play and trash button of the replay.
     */
    void notifyRecordSelected();

    /**
     * Method that disables the play and trash button of the replay.
     */
    void notifyRecordNonSelected();

    /**
     * Method that gets the select item from the JList.
     *
     * @return the select item
     */
    int getSelectItem();

    /**
     * Method that gets the select game from the JList.
     *
     * @return the select game
     */
    String getNameGame();

    /**
     * Method that clears all the list.
     */
    void clearAllList();
}
