package r2d23.EU4resources;

import java.awt.Image;

/**
 * The <code>Country</code> class that models a tag country from EU4 game meta
 * data.
 * <p>
 * @author R2D2 3
 */
public class Country {

    /**
     * The tag of the Country. A <code>String</code> that MUST be a sequence of
     * three letters.
     */
    private String tag;
    /**
     * The flag of the Country. A <code>Image</code> that represents the flag
     * image of the country of the leading tag.
     */
    private Image flag;
    /**
     * The name of the Country. A <code>String</code> that represents the name
     * of the country, by default, in English. Provided from the EU4 data files.
     */
    private String name;

    /**
     * The main constructor.
     * <p>
     * This constructor will automatically fill on the flag and name info from
     * the static methods provided by <code>EU4resources.EU4Directories</code>
     * class. As such, the mentioned class must be initiated beforehand.
     * <p>
     * @param tag The tag of the country.
     */
    public Country(String tag) {
	if (!isValideTag(tag))
	    throw new IllegalArgumentException();
	tag = this.tag;
	setNameFromGameData();
    }

    /**
     * This private method will set both <code>flag</code> and <code>name</code>
     * fields of the new instanced <code>Country</code> object from EU4 game
     * directory using <code>EU4Directories</code> methods.
     */
    private void setNameFromGameData() {

    }

    /**
     * Validates if the <code>String</code> is a valid country tag.
     * <p>
     * @param tag The <code>String</code> that is about to be validated.
     * <p>
     * @return True if validated. False if otherwise.
     */
    private static boolean isValideTag(String tag) {
	return (tag.length() == 3);
    }
}
