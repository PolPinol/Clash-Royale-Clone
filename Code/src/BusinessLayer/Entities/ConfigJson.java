package BusinessLayer.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Class ConfigJson. It has all configJson information needed.
 */
public class ConfigJson {
    @SerializedName("host")
    private String host;

    @SerializedName("port")
    private String port;

    @SerializedName("db")
    private String db;

    @SerializedName("user")
    private String user;

    @SerializedName("pass")
    private String pass;

    /**
     * Instantiates a new Config json.
     */
    public ConfigJson() {

    }

    /**
     * Gets db.
     *
     * @return the db
     */
    public String getDb() {
        return db;
    }

    /**
     * Gets host.
     *
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * Gets pass.
     *
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public String getUser() {
        return user;
    }
}
