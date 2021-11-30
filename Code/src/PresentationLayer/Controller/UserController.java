package PresentationLayer.Controller;

import PresentationLayer.Controller.Listeners.*;
import BusinessLayer.Entities.User;
import BusinessLayer.Model.Managers.UserManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class UserController that manages the login of a user and the access to the register panel.
 */
public class UserController implements LoginActionListener, ModifyUserListener, ActionListener {
    /**
     * The constant EVENT_LOGIN.
     */
    public static final String EVENT_LOGIN = "menuClick";
    /**
     * The constant EVENT_REGISTER.
     */
    public static final String EVENT_REGISTER = "menuRegistre";
    /**
     * The constant SWAP_TO_CARD_MENU.
     */
    public static final String SWAP_TO_CARD_MENU = "cardMenu";
    /**
     * The constant SWAP_TO_CARD_REGISTER.
     */
    public static final String SWAP_TO_CARD_REGISTER = "cardRegister";
    /**
     * The constant PASSWORD_ERROR.
     */
    public static final int PASSWORD_ERROR = 0;
    /**
     * The constant USER_ERROR.
     */
    public static final int USER_ERROR = 1;

    private final UserErrorListener errorListener;
    private final GetInformationUserListener getInformationUserListener;
    private final UserManager userManager;
    private final SwapCardListener listener;
    private ActionRecordListener recordListener;
    private User user;

    /**
     * Instantiates a new UserController.
     *
     * @param userManager                   the user manager
     * @param listener                      the listener
     * @param errorListener                 the error listener
     * @param getInformationUserListener    the getInformationUserListener
     */
    public UserController(UserManager userManager, SwapCardListener listener, UserErrorListener errorListener,
                          GetInformationUserListener getInformationUserListener) {
        this.errorListener = errorListener;
        this.getInformationUserListener = getInformationUserListener;
        this.userManager = userManager;
        this.listener = listener;
        this.user = null;
    }

    @Override
    public void notifyLoginButton(String entry, String password) {
        login(entry, password);
    }

    // Method that tries to login a user giving a username or mail and a password
    private void login(String entry, String password) {
        if (userExists(entry)) {
            User user = userManager.getUser(entry);
            if (matchesPassword(user, password)) {
                this.user = user;
                listener.notifyNewCard(SWAP_TO_CARD_MENU);
            } else {
                errorListener.notifyErrorMessage(PASSWORD_ERROR);
            }
        } else {
            errorListener.notifyErrorMessage(USER_ERROR);
        }
    }

    private boolean matchesPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    private boolean userExists(String entry) {
        return userManager.checkUser(entry);
    }

    /**
     * Method that register the action record listener.
     *
     * @param recordListener the record listener
     */
    public void registerActionRecordListener(ActionRecordListener recordListener) {
        this.recordListener = recordListener;
    }

    @Override
    public String getUserName() {
        return user.getName();
    }

    @Override
    public void notifyDeleteUser() {
        recordListener.deleteUserRecords();
        userManager.removeUser(this.user);
        this.user = null;
    }

    @Override
    public void notifyLogoutUser() {
        this.user = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case EVENT_LOGIN -> getInformationUserListener.notifyPanelToGetInformation();
            case EVENT_REGISTER -> listener.notifyNewCard(SWAP_TO_CARD_REGISTER);
        }
    }
}