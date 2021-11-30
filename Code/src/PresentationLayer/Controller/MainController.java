package PresentationLayer.Controller;

import BusinessLayer.Model.Managers.UserManager;
import BusinessLayer.Entities.User;
import PresentationLayer.Controller.Listeners.*;
import PresentationLayer.View.MainView;

/**
 * Class MainController that has and manages all program controllers. It will implements the necessary listeners
 * to controls the program in an efficient way and will act as the system's controller (as described in
 * the GRASP patterns), managing the model and the view, separating them from the rest.
 */
public class MainController implements SwapCardListener, StartGameListener, UserErrorListener,
        ReturnMenuPanelListener, EndGameListener, StartReplayListener, ShowInstructionsListener, SettingsListener,
        OpenUserHistoryListener, SQLErrorListener, NextButtonListener, GetInformationUserListener, GetDataTableClicked {

    private final UserManager userManager;
    private final MainView mainView;
    private LogoController logoController;
    private UserController userController;
    private MenuController menuController;
    private RegisterController registerController;
    private InstructionsController infoController;
    private HistoryController historyController;
    private SettingsController settingsController;
    private GameController gameController;
    private RankingController rankingController;
    private ReplayController replayController;

    /**
     * Instantiates a new MainController.
     *
     * @param mainView    the main view
     * @param userManager the user manager
     */
    public MainController(MainView mainView, UserManager userManager) {
        this.userManager = userManager;
        this.mainView = mainView;

        initiateControllers();
        registerListeners();
    }

    private void initiateControllers() {
        logoController = new LogoController(this);
        userController = new UserController(userManager, this, this, this);
        historyController = new HistoryController(mainView.getRecordListener(), this, userController, this, this, this);
        rankingController = new RankingController(this, this, userManager, mainView.getDataListener(), this);
        menuController = new MenuController(this, this, historyController, this, rankingController);
        registerController = new RegisterController(userManager,this, this, this);
        infoController = new InstructionsController(this, this);
        settingsController = new SettingsController(this, userController, this);
    }

    private void registerListeners() {
        mainView.registerLogoPanel(logoController);
        mainView.registerUserPanel(userController, userController);
        mainView.registerMenuPanel(menuController);
        mainView.registerRegisterPanel(registerController, registerController);
        mainView.registerInfoListener(infoController);
        mainView.setRecordListeners(historyController, historyController);
        mainView.registerSettingsDialog(settingsController, settingsController);
        userManager.initiateUserDAO(this);
        mainView.registerRankingPanel(rankingController, this, rankingController);
        userController.registerActionRecordListener(historyController);
    }

    @Override
    public void notifyNewCard(String card) {
        mainView.showCard(card);
    }

    @Override
    public void notifyStartGame() {
        mainView.setVisibleFalse();

        gameController = new GameController(this, this, historyController, userController, this, this);
        gameController.start();
    }

    @Override
    public void notifyStartReplay() {
        mainView.setVisibleFalse();

        replayController = new ReplayController(this, historyController, this, this, this);
        replayController.start();
    }

    @Override
    public void notifyReturnMenuPanel(boolean isNotReplay, int winner) {
        mainView.setVisibleTrue();
        mainView.showCard(MainView.CARD_MENU);

        if (isNotReplay) {
            userManager.addNewPlay(gameController.returnUserName(), winner);
        }
    }

    @Override
    public void notifyErrorMessage(int error) {
        switch (error) {
            case UserController.USER_ERROR -> mainView.showErrorExistingUserLogin();
            case UserController.PASSWORD_ERROR -> mainView.showErrorMatchingPassword();
            case RegisterController.EMAIL_REGISTER_ERROR -> mainView.showErrorExistingEmailRegister();
            case RegisterController.USER_REGISTER_ERROR-> mainView.showErrorExistingUserRegister();
        }
    }

    @Override
    public void notifyEndGame() {
        mainView.setVisibleTrue();
        menuController.backToUserPanel();
    }

    @Override
    public void notifyShowInstruction() {
        mainView.showInfoDialog();
    }

    @Override
    public void notifyStopInstruction() {
        mainView.stopInfoDialog();
    }

    @Override
    public void notifyStartDialog() {
        mainView.startDialog();
    }

    @Override
    public void notifyEndDialog() {
        mainView.closeDialog();
    }

    @Override
    public void notifyEndGameDialog() {
        if (replayController != null) {
            replayController.earlyCloseGame();
            replayController = null;
        } else if (gameController != null) {
            userManager.addNewPlay(gameController.returnUserName(), User.IA_USER);
            gameController.earlyCloseGame();
            gameController = null;
        }
    }

    @Override
    public void notifyOpenHistory(String user) {
        historyController.updateHistory(user);
        mainView.showCard(MainView.CARD_HISTORY);
    }

    @Override
    public void notifyErrorConnectingSQL(String message) {
        mainView.existsSQLProblem(message);
    }

    @Override
    public void notifyButtonPressed() {
        mainView.actionButtonRegister();
    }

    @Override
    public void notifyPanelToGetInformation() {
        mainView.getInformationUser();
    }

    @Override
    public void notifyDataTableClicked() {
        mainView.actionSelectedCell();
    }
}