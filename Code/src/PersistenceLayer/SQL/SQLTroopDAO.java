package PersistenceLayer.SQL;

import BusinessLayer.Entities.Troop;
import PersistenceLayer.TroopDAO;
import PresentationLayer.Controller.Listeners.SQLErrorListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class that implements the methods described in the TroopDAO interface.
 *
 * Specifically, it implements the troop persistence in a SQL database.
 */
public class SQLTroopDAO implements TroopDAO {
    /**
     * Constant for the number of cards
     */
    public static final int NUM_CARDS = 6;

    private final SQLErrorListener listener;

    /**
     * Instantiates a new SQLTroopDAO.
     *
     * @param listener SQLErrorListener that throws the error to the View
     */
    public SQLTroopDAO(SQLErrorListener listener) {
        this.listener = listener;
    }

    @Override
    public ArrayList<Troop> getAllTroops() {
        ArrayList<Troop> troops = new ArrayList<>();
        String query = "SELECT price, name, life, type, urlCardDefault, urlCardSelected, urlTroop, attack, urlTroopIA FROM Troop;";
        ResultSet result = SQLConnector.getInstance(listener).selectQuery(query);

        try {
            while (result.next()) {
                int price = result.getInt("price");
                String name = result.getString("name");
                int life = result.getInt("life");
                int type = result.getInt("type");
                String urlCardDefault = result.getString("urlCardDefault");
                String urlCardSelected = result.getString("urlCardSelected");
                String urlTroop = result.getString("urlTroop");
                String urlTroopIA = result.getString("urlTroopIA");
                int attack = result.getInt("attack");

                troops.add(new Troop(name, price, life, attack, type, urlCardDefault, urlCardSelected, urlTroop, urlTroopIA));
            }
        } catch (SQLException e) {
            String message = "Problem when selecting troops --> " + e.getSQLState() + " (" + e.getMessage() + ")";
            listener.notifyErrorConnectingSQL(message);
        }
        if (troops.size() != NUM_CARDS) {
            String message = "Problem when selecting troops";
            listener.notifyErrorConnectingSQL(message);
        }
        return troops;
    }
}
