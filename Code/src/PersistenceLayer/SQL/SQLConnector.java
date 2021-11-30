package PersistenceLayer.SQL;

import PersistenceLayer.ConfigDAO;
import PersistenceLayer.JSON.JSONConfigDAO;
import PresentationLayer.Controller.Listeners.SQLErrorListener;

import java.sql.*;

/**
 * The SQLConnector class will abstract the specifics of the connection to a MySQL database.
 *
 * This class follows the Singleton design pattern to facilitate outside access while maintaining
 * a single instance, as having multiple connectors to a database is generally discouraged.
 *
 */
public class SQLConnector {
    private static SQLConnector instance = null;
    private ConfigDAO configDAO;


    /**
     * Static method that returns the shared instance managed by the singleton.
     *
     * @param listener SQLErrorListener that throws the error to the View
     * @return The shared SQLConnector instance.
     */
    public static SQLConnector getInstance(SQLErrorListener listener){
        if (instance == null ){
            instance = new SQLConnector(new JSONConfigDAO(), listener);
            instance.connect();
        }
        return instance;
    }

    private final String username;
    private final String password;
    private final String url;
    private final SQLErrorListener listener;
    private Connection conn;

    // Parametrized constructor
    private SQLConnector(ConfigDAO configDao, SQLErrorListener listener) {
        this.username = configDao.getUsername();
        this.password = configDao.getPassword();
        this.url = configDao.getUrl();
        this.listener = listener;
    }

    /**
     * Method that starts the inner connection to the database.
     */
    public void connect() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            String message = "Couldn't connect to --> " + url + " (" + e.getMessage() + ")";
            listener.notifyErrorConnectingSQL(message);
        }
    }

    /**
     * Method that executes an insertion query to the connected database.
     *
     * @param query String representation of the query to execute.
     */
    public void insertQuery(String query){
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            String message = "Problem when inserting --> " + e.getSQLState() + " (" + e.getMessage() + ")";
            listener.notifyErrorConnectingSQL(message);
        }
    }

    /**
     * Method that executes a deletion query to the connected database.
     *
     * @param query String representation of the query to execute.
     */
    public void deleteQuery(String query){
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            String message = "Problem when deleting --> " + e.getSQLState() + " (" + e.getMessage() + ")";
            listener.notifyErrorConnectingSQL(message);
        }

    }

    /**
     * Method that executes a selection query to the connected database.
     *
     * @param query String representation of the query to execute.
     * @return The results of the selection.
     */
    public ResultSet selectQuery(String query){
        ResultSet rs = null;
        try {
            Statement s = conn.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            String message = "Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")";
            listener.notifyErrorConnectingSQL(message);
        }
        return rs;
    }
}
