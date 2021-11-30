package PersistenceLayer;

/**
 * Interface that abstracts the persistence of config.json from the rest of the code.
 *
 * In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 */
public interface ConfigDAO {
    /**
     * Method that gets the url to connect to the database.
     *
     * @return the url
     */
    String getUrl();

    /**
     * Method that gets username to connect to the database.
     *
     * @return the username
     */
    String getUsername();

    /**
     * Method that gets the password to connect to the database.
     *
     * @return the password
     */
    String getPassword();
}
