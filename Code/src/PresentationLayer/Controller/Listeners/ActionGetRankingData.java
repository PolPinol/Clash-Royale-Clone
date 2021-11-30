package PresentationLayer.Controller.Listeners;

/**
 * Interface ChangesGameViewListener that abstracts the methods that manages the
 * changes that have to be made on the View.
 */
public interface ActionGetRankingData {
    /**
     * Method clear the data from the JTable and load / update new data accessing from the database.
     */
    void loadRankingTable();
}
