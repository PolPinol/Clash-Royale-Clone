package BusinessLayer.Entities;

/**
 * Class Record. It has all information necessary to show on a JList.
 */
public class Record {
    private final String name;
    private final String date;
    private final String hour;
    private final int winner;
    private final String userName;
    private final int id;

    /**
     * Instantiates a new Record.
     *
     * @param name     the name
     * @param date     the date
     * @param hour     the hour
     * @param winner   the winner
     * @param userName the user name
     */
    public Record(String name, String date, String hour, int winner, String userName) {
        this.name = name;
        this.date = date;
        this.hour = hour;
        this.winner = winner;
        this.userName = userName;
        this.id = 0;
    }

    /**
     * Instantiates a new Record.
     *
     * @param name     the name
     * @param date     the date
     * @param hour     the hour
     * @param winner   the winner
     * @param userName the user name
     * @param id       the id
     */
    public Record(String name, String date, String hour, int winner, String userName, int id) {
        this.name = name;
        this.date = date;
        this.hour = hour;
        this.winner = winner;
        this.userName = userName;
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets hour.
     *
     * @return the hour
     */
    public String getHour() {
        return hour;
    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public int getWinner() {
        return winner;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
}
