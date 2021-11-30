package PresentationLayer.Controller;


import PresentationLayer.Controller.Listeners.ShowInstructionsListener;
import PresentationLayer.Controller.Listeners.StartGameListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class InstructionsController that manages the logic from the InstructionsDialog and its button.
 */
public class InstructionsController implements ActionListener {
    private final StartGameListener startGameListener;
    private final ShowInstructionsListener listener;

    /**
     * Instantiates a new Instructions controller.
     *
     * @param startGameListener the start game listener
     * @param listener          the listener
     */
    public InstructionsController(StartGameListener startGameListener, ShowInstructionsListener listener) {
        this.startGameListener = startGameListener;
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.notifyStopInstruction();
        startGameListener.notifyStartGame();
    }
}
