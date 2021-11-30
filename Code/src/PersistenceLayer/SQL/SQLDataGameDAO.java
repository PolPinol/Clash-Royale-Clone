package PersistenceLayer.SQL;

import BusinessLayer.Entities.Record;
import BusinessLayer.Entities.DataGame;
import PersistenceLayer.DataGameDAO;
import PresentationLayer.Controller.Listeners.SQLErrorListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class that implements the methods described in the DataGameDAO interface.
 *
 * Specifically, it implements the data of a game persistence in a SQL database.
 */
public class SQLDataGameDAO implements DataGameDAO {
    private final SQLErrorListener listener;

    /**
     * Instantiates a new SQLDataGameDAO.
     *
     * @param listener SQLErrorListener that throws the error to the View
     */
    public SQLDataGameDAO(SQLErrorListener listener) {
        this.listener = listener;
    }

    @Override
    public void insertDataGame(ArrayList<DataGame> data) {
        for (DataGame r : data) {
            String query = "Insert Into RecordData(recordName, rowPos, columnPos, troopName, isUser, millis) Values ('" +
                    r.getRecordName() + "', " +
                    r.getRow() + ", " +
                    r.getColumn() + ", '" +
                    r.getTroopName() + "', " +
                    r.getIsUser() + ", " +
                    r.getMillis() + ");";

            SQLConnector.getInstance(listener).insertQuery(query);
        }
    }

    @Override
    public void removeDataGame(Record record) {
        String query = "Delete From RecordData Where recordName = '" + record.getName() + "';";

        SQLConnector.getInstance(listener).deleteQuery(query);
    }

    @Override
    public Queue<DataGame> getDataGame(String name) {
        Queue<DataGame> data = new LinkedList<>();
        String query = "Select recordName, rowPos, columnPos, troopName, isUser, cast(millis as signed) as casted_column From RecordData Where recordName = '" + name + "' Order By casted_column asc;";
        ResultSet result = SQLConnector.getInstance(listener).selectQuery(query);

        try {
            while (result.next()) {
                String recordName = result.getString("recordName");
                int row = result.getInt("rowPos");
                int column = result.getInt("columnPos");
                String troop = result.getString("troopName");
                int user = result.getInt("isUser");
                long millis = result.getLong("casted_column");

                data.add(new DataGame(recordName, row, column, troop, user, millis));
            }
        } catch (SQLException e) {
            String message = "Problem when selecting record data --> " + e.getSQLState() + " (" + e.getMessage() + ")";
            listener.notifyErrorConnectingSQL(message);
        }

        return data;
    }
}