package PresentationLayer.Controller.Listeners;

/**
 * Interface ShowInstructionsListener that abstracts the methods that manages
 * opened and close of the instructions dialog.
 */
public interface ShowInstructionsListener {
    /**
     * Method that notifies that the InstructionsDialog has to be shown.
     */
    void notifyShowInstruction();

    /**
     * Method that notifies that the InstructionsDialog has to be closed.
     */
    void notifyStopInstruction();
}
