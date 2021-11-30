package BusinessLayer.Entities;

/**
 * Class Troop. It has all information needed to play games.
 */
public class Troop {
    /**
     * The constant OFF.
     */
    public static final int OFF = 0;
    /**
     * The constant DEF.
     */
    public static final int DEF = 1;
    /**
     * The constant WIZARD.
     */
    public static final String WIZARD = "Mago";
    /**
     * The constant KNIGHT.
     */
    public static final String KNIGHT = "Caballero";
    /**
     * The constant ARCHER.
     */
    public static final String ARCHER = "Arquera";
    /**
     * The constant CANNON.
     */
    public static final String CANNON = "Torre";
    /**
     * The constant ICE.
     */
    public static final String ICE = "Hielo";
    /**
     * The constant GOLEM.
     */
    public static final String GOLEM = "Golem";

    private final int price;
    private final String name;
    private final int life;
    private final int type;
    private final String urlCardDefault;
    private final String urlCardSelected;
    private final String urlTroopPlayer;
    private final String urlTroopIA;
    private final int attack;

    /**
     * Instantiates a new Troop.
     *
     * @param name            the name
     * @param price           the price
     * @param life            the life
     * @param attack          the attack
     * @param type            the type
     * @param urlCardDefault  the url card default
     * @param urlCardSelected the url card selected
     * @param urlTroopPlayer  the url troop player
     * @param urlTroopIA      the url troop ia
     */
    public Troop(String name, int price, int life, int attack, int type, String urlCardDefault, String urlCardSelected, String urlTroopPlayer, String urlTroopIA) {
        this.price = price;
        this.name = name;
        this.life = life;
        this.type = type;
        this.urlCardDefault = urlCardDefault;
        this.urlCardSelected = urlCardSelected;
        this.urlTroopPlayer = urlTroopPlayer;
        this.urlTroopIA = urlTroopIA;
        this.attack = attack;
    }

    /**
     * Gets attack.
     *
     * @return the attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
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
     * Gets life.
     *
     * @return the life
     */
    public int getLife() {
        return life;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * Gets url card default.
     *
     * @return the url card default
     */
    public String getUrlCardDefault() {
        return urlCardDefault;
    }

    /**
     * Gets url card selected.
     *
     * @return the url card selected
     */
    public String getUrlCardSelected() {
        return urlCardSelected;
    }

    /**
     * Gets url.
     *
     * @param user the user
     * @return the url
     */
    public String getUrl(int user) {
        if (user == User.PLAYER_USER) {
            return urlTroopPlayer;
        } else {
            return urlTroopIA;
        }
    }
}
