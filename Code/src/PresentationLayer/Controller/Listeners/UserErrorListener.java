package PresentationLayer.Controller.Listeners;

/**
 * Interface UserErrorListener that abstracts the methods that notifies to the mainView
 * which is the error that is causing the program not to let login or register the user.
 */
public interface UserErrorListener {
    /**
     * Method that notifies to the mainView which is the error that is causing
     * the program not to let login or register the user.
     *
     * For the login can be if the user (name or mail) does'nt exists or if the
     * password is not the correct one. For the register can be if the user
     * (name or mail) already exists.
     *
     * @param error the error
     */
    void notifyErrorMessage(int error);
}