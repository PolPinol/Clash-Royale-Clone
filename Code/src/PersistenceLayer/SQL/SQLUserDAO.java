package PersistenceLayer.SQL;

import BusinessLayer.Entities.User;
import PersistenceLayer.UserDAO;
import PresentationLayer.Controller.Listeners.SQLErrorListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class that implements the methods described in the UserDAO interface.
 *
 * Specifically, it implements the user persistence in a SQL database.
 */
public class SQLUserDAO implements UserDAO {
    private final SQLErrorListener listener;

    /**
     * Instantiates a new SQLUserDAO.
     *
     * @param listener SQLErrorListener that throws the error to the View
     */
    public SQLUserDAO(SQLErrorListener listener) {
        this.listener = listener;
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO User(name, password, email, points, numPlays) VALUES ('" +
                user.getName() + "', '" +
                user.getPassword() + "', '" +
                user.getEmail() + "', " +
                user.getPoints() + ", " +
                user.getNumPlays() +
                ");";

        SQLConnector.getInstance(listener).insertQuery(query);
    }

    @Override
    public void removeUser(User user) {
        String query = "DELETE FROM User WHERE name = '" + user.getName() + "';";
        SQLConnector.getInstance(listener).deleteQuery(query);
    }

    @Override
    public void updateUserNumWins(String entry, int points) {
        String query = "UPDATE User SET points = " + points +
                " WHERE name = '" + entry + "' OR email = '" + entry + "';" ;

        SQLConnector.getInstance(listener).insertQuery(query);
    }


    @Override
    public void updateUserNumPlays(String entry, int numPlays) {
        String query = "UPDATE User SET numPlays = " + numPlays +
                       " WHERE name = '" + entry + "' OR email = '" + entry + "';" ;

        SQLConnector.getInstance(listener).insertQuery(query);
    }

    @Override
    public User getUser(String entry) {
        String query = "SELECT name, password, email, points, numPlays FROM User" +
                       " WHERE name = '" + entry + "' OR email = '" + entry + "';";
        ResultSet result = SQLConnector.getInstance(listener).selectQuery(query);

        try {
            result.next();
            String name = result.getString("name");
            String password = result.getString("password");
            String email = result.getString("email");
            int points = result.getInt("points");
            int numPlays = result.getInt("numPlays");
            return new User(name, password, email, points, numPlays);
        } catch (SQLException e) {
            String message = "Problem when selecting user --> " + e.getSQLState() + " (" + e.getMessage() + ")";
            listener.notifyErrorConnectingSQL(message);
        }

        return null;
    }


    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT name, password, email, points, numPlays FROM User;";
        ResultSet result = SQLConnector.getInstance(listener).selectQuery(query);

        try {
            while(result.next()) {
                String name = result.getString("name");
                String password = result.getString("password");
                String email = result.getString("email");
                int points = result.getInt("points");
                int numPlays = result.getInt("numPlays");
                users.add(new User(name, password, email, points, numPlays));
            }

            return users;
        } catch (SQLException e) {
            String message = "Problem when selecting user --> " + e.getSQLState() + " (" + e.getMessage() + ")";
            listener.notifyErrorConnectingSQL(message);
        }

        return null;
    }

    @Override
    public boolean checkUser(String entry) {
        String query = "SELECT s.name, s.password, s.email, s.points FROM User AS s" +
                       " WHERE s.name = '" + entry + "' OR s.email = '" + entry + "';";
        ResultSet result = SQLConnector.getInstance(listener).selectQuery(query);

        try {
            return result.isBeforeFirst();
        } catch (SQLException e) {
            String message = "Problem when checking user --> " + e.getSQLState() + " (" + e.getMessage() + ")";
            listener.notifyErrorConnectingSQL(message);
        }

        return false;
    }
}
