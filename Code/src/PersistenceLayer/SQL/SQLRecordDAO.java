package PersistenceLayer.SQL;

import BusinessLayer.Entities.Record;
import PersistenceLayer.RecordDAO;
import PresentationLayer.Controller.Listeners.SQLErrorListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class that implements the methods described in the RecordDAO interface.
 *
 * Specifically, it implements the record persistence in a SQL database.
 */
public class SQLRecordDAO implements RecordDAO {
    private final SQLErrorListener listener;

    /**
     * Instantiates a new SQLRecordDAO.
     *
     * @param listener SQLErrorListener that throws the error to the View
     */
    public SQLRecordDAO(SQLErrorListener listener) {
        this.listener = listener;
    }

    @Override
    public void addRecord(Record record) {
        String query = "Insert Into Record(name, date, hour, winner, userName) Values ('" +
                record.getName() + "', '" +
                record.getDate() + "', '" +
                record.getHour() + "', '" +
                record.getWinner() + "', '" +
                record.getUserName() + "');";

        SQLConnector.getInstance(listener).insertQuery(query);
    }

    @Override
    public void removeRecord(Record record) {
        String query = "Delete From Record Where name = '" + record.getName() + "';";

        SQLConnector.getInstance(listener).deleteQuery(query);
    }

    @Override
    public ArrayList<Record> getRecords(String userName) {
        ArrayList<Record> records = new ArrayList<>();
        String query = "Select id, name, date, winner, userName From Record Where userName = '" + userName + "'" +
                       " order by id desc;";
        ResultSet result = SQLConnector.getInstance(listener).selectQuery(query);

        try {
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String date = result.getString("date");
                int winner = result.getInt("winner");
                String nuserName = result.getString("userName");

                records.add(new Record(name, date, null, winner, nuserName, id));
            }
        } catch (SQLException e) {
            String message = "Problem when selecting records --> " + e.getSQLState() + " (" + e.getMessage() + ")";
            listener.notifyErrorConnectingSQL(message);
        }

        return records;
    }

    @Override
    public void removeUserRecord(String userName) {
        String query = "Delete From Record Where userName = '" + userName + "';";

        SQLConnector.getInstance(listener).deleteQuery(query);
    }
}