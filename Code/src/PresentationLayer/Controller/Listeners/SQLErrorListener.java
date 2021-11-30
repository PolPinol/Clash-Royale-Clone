package PresentationLayer.Controller.Listeners;

/**
 * Interface ReturnMenuPanelListener that abstracts the method that manages the
 * show of the errors from the database in a JOptionPane format.
 */
public interface SQLErrorListener {
    /**
     * Method that notifies to the view that a JOptionPane has to be shown
     * with a SQL query / connection error.
     *
     * @param message the message error
     */
    void notifyErrorConnectingSQL(String message);
}