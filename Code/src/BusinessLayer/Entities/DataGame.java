package BusinessLayer.Entities;

/**
 * Class DataGame. It has all information from a replay needed.
 */
public class DataGame {
    private String recordName;
    private int row;
    private int column;
    private final String troopName;
    private final int isUser;
    private final long millis;

    /**
     * Instantiates a new Data game.
     *
     * @param recordName the record name
     * @param row        the row
     * @param column     the column
     * @param troopName  the troop name
     * @param isUser     int if is user
     * @param millis     the millis
     */
    public DataGame(String recordName, int row, int column, String troopName, int isUser, long millis) {
        this.recordName = recordName;
        this.row = row;
        this.column = column;
        this.troopName = troopName;
        this.isUser = isUser;
        this.millis = millis;
    }

    /**
     * Gets record name.
     *
     * @return the record name
     */
    public String getRecordName() {
        return recordName;
    }

    /**
     * Sets record name.
     *
     * @param recordName the record name
     */
    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    /**
     * Gets row.
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets row.
     *
     * @param row the row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Gets column.
     *
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets column.
     *
     * @param column the column
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Gets troop name.
     *
     * @return the troop name
     */
    public String getTroopName() {
        return troopName;
    }

    /**
     * Gets int for user.
     *
     * @return int for its user
     */
    public int getIsUser() {
        return isUser;
    }

    /**
     * Gets millis.
     *
     * @return the millis
     */
    public long getMillis() {
        return millis;
    }
}