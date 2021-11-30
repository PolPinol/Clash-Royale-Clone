package PresentationLayer.Controller.Listeners;

/**
 * Interface UserComputerWinnerListener that abstracts the method that notifies who is the winner of a game.
 */
public interface UserComputerWinnerListener {
    /**
     * Method that ask for the user of a game ended and return a string
     * explaining who si the winner: the current user or the computer.
     *
     * @return the string of the sentence exposing the winner
     */
    String notifyWhoIsWinner();
}