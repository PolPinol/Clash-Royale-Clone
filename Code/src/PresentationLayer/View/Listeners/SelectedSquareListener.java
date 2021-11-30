package PresentationLayer.View.Listeners;

/**
 * Interface SelectedSquareListener that abstracts the methods that manages
 * when a square panel is selected.
 */
public interface SelectedSquareListener {
    /**
     * Method that manages and notifies when a square panel is selected at the same time
     * a troop card was selected.
     *
     * @param row    the row of the square
     * @param column the column of the square
     */
    void notifySquareSelected(int row, int column);
}