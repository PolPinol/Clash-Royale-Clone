import BusinessLayer.Model.Managers.UserManager;
import PresentationLayer.Controller.MainController;
import PresentationLayer.View.MainView;

/**
 * Class Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // Thread that is not the Event-Dispatch-Thread.
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Create the model
            UserManager model = new UserManager();

            // Create the main view
            MainView mainView = new MainView();

            // Create the controller
            MainController mainController = new MainController(mainView, model);

            // Show Main BusinessLayer.Controller.View
            mainView.show();
        });
    }
}