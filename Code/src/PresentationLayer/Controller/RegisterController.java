package PresentationLayer.Controller;

import BusinessLayer.Entities.User;
import BusinessLayer.Model.Managers.UserManager;
import PresentationLayer.Controller.Listeners.NextButtonListener;
import PresentationLayer.Controller.Listeners.RegisterActionListener;
import PresentationLayer.Controller.Listeners.SwapCardListener;
import PresentationLayer.Controller.Listeners.UserErrorListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class RegisterController that manages the register / sign up of an user and its view.
 *
 * It will manage to ask to the database if the user exists and if the email exists. If
 * neither of these two doesn't exist, then it will create a new user and add to the database.
 * If the user exists or the email exists will throw an error view warning the current user
 * of the problem.
 */
public class RegisterController implements RegisterActionListener, MouseListener {
    /**
     * The constant SWAP_TO_CARD_LOGIN.
     */
    public static final String SWAP_TO_CARD_LOGIN = "cardUser";
    /**
     * The constant EMAIL_REGISTER_ERROR.
     */
    public static final int EMAIL_REGISTER_ERROR = 2;
    /**
     * The constant USER_REGISTER_ERROR.
     */
    public static final int USER_REGISTER_ERROR = 3;
    /**
     * The constant INITIAL_POINTS.
     */
    public static final int INITIAL_POINTS = 0;
    /**
     * The constant INITIAL_PLAYS.
     */
    public static final int INITIAL_PLAYS = 0;

    private final UserErrorListener errorListener;
    private final SwapCardListener listener;
    private final UserManager userManager;
    private final NextButtonListener nextButtonListener;

    /**
     * Instantiates a new RegisterController.
     *
     * @param userManager           the user manager
     * @param listener              the listener
     * @param errorListener         the error listener
     * @param nextButtonListener    nextButtonListener
     */
    public RegisterController(UserManager userManager, SwapCardListener listener, UserErrorListener errorListener,
                              NextButtonListener nextButtonListener) {
        this.userManager = userManager;
        this.listener = listener;
        this.errorListener = errorListener;
        this.nextButtonListener = nextButtonListener;
    }

    @Override
    public void notifyRegisterButton(String name, String email, String password) {
        register(name, email, password);
    }

    // Method that registers a new user to the database
    private void register(String name, String email, String password) {
        if (!userExists(name) ) {
            if (!userExists(email)) {
                userManager.addUser(new User(name, password, email, INITIAL_POINTS, INITIAL_PLAYS));
                    listener.notifyNewCard(SWAP_TO_CARD_LOGIN);
            } else {
                errorListener.notifyErrorMessage(EMAIL_REGISTER_ERROR);
            }
        } else {
            errorListener.notifyErrorMessage(USER_REGISTER_ERROR);
        }
    }

    // Method that checks on the database if the user exists
    private boolean userExists(String entry) {
        return userManager.checkUser(entry);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        nextButtonListener.notifyButtonPressed();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
