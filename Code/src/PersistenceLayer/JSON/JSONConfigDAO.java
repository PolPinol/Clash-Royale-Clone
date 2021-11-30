package PersistenceLayer.JSON;

import BusinessLayer.Entities.ConfigJson;
import PersistenceLayer.ConfigDAO;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Class that implements the interface ConfigDAO that extracts information
 * from a json to connect to the database.
 */
public class JSONConfigDAO implements ConfigDAO {
    /**
     * The constant URL_START.
     */
    public static final String URL_START = "jdbc:mysql://";
    /**
     * The constant URL_END.
     */
    public static final String URL_END = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    /**
     * The constant CONFIG_PATH.
     */
    public static final String CONFIG_PATH = "config.json";

    private final ConfigJson configJson;

    /**
     * Instantiates a new JsonConfigDao.
     */
    public JSONConfigDAO() {
        Gson gson = new Gson();
        configJson = gson.fromJson(readText(), ConfigJson.class);
    }

    @Override
    public String getUrl() {
        return URL_START  + configJson.getHost() + ":" + configJson.getPort() + "/" + configJson.getDb() + URL_END;
    }

    @Override
    public String getUsername() {
        return configJson.getUser();
    }

    @Override
    public String getPassword() {
        return configJson.getPass();
    }

    // Method to read the text from the json
    private String readText() {
        File arxiu = null;
        FileReader fr = null;
        BufferedReader br = null;
        StringBuilder text = new StringBuilder();

        try {
            arxiu = new File(CONFIG_PATH);
            fr = new FileReader(arxiu);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                text.append(linea);
            }
        } catch (Exception ignore) {
            // Exception ignored to managed when connecting BDD
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception ignore) {
                // Exception ignored to managed when connecting BDD
            }
        }

        return text.toString();
    }
}
