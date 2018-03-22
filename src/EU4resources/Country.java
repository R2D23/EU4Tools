package EU4resources;

import java.awt.Image;

/**
 * The <code>Country</code> class models a tag country from EU4 game meta.
 * <p>
 * @author R2D2 3
 */
public class Country {

    private String tag;
    private Image flag;
    private String name;

    /**
     * This constructor will automatically fill on the flag and name info from
     * the static methods provided by <code>EU4resources.EU4Directories</code>
     * class. As such, the mentioned class must be initiated beforehand.
     * <p>
     * @param tag The tag of the country.
     * <p>
     * @author R2D2 3
     */
    public Country(String tag) {
	tag = this.tag;
	setNameFromGameData();
    }

    public static void setNameFromGameData() {

    }
}
