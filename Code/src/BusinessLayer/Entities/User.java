package BusinessLayer.Entities;

/**
 * Class User. It has all information needed to manage the logic of login and sign up.
 */
public class User implements Comparable<User> {
    /**
     * The constant to identify PLAYER_USER.
     */
    public static final int PLAYER_USER = 0;
    /**
     * The constant to identify IA_USER.
     */
    public static final int IA_USER = 1;

    private final String name;
    private final String password;
    private final String email;
    private int points;
    private int numPlays;
    private float winrate;

    /**
     * Instantiates a new User.
     *
     * @param name     the name
     * @param password the password
     * @param email    the email
     * @param points   the points
     * @param numPlays the num plays
     */
    public User(String name, String password, String email, int points, int numPlays) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.points = points;
        this.numPlays = numPlays;
        uploadWinrate();
    }

    private void uploadWinrate() {
        if (points > 0) {
            this.winrate = (float) (points * 100) / numPlays;
        } else {
            this.winrate = 0;
        }
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
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets points.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Gets num plays.
     *
     * @return the num plays
     */
    public int getNumPlays() {
        return numPlays;
    }

    /**
     * Gets winrate.
     *
     * @return the winrate
     */
    public float getWinrate() {
        return winrate;
    }

    /**
     * User gets more plays.
     */
    public void setNewPlay() {
        this.numPlays = this.numPlays + 1;
        uploadWinrate();
    }

    /**
     * User gets more points.
     */
    public void morePoints() {
        this.points = this.points + 1;
        uploadWinrate();
    }

    @Override
    public int compareTo(User o) {
        return (int) (o.winrate - this.winrate);
    }
}