package r2d23.EU4resources;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A static class that encompasses utility calls for EU4 game files connection.
 * <p>
 * @author LuisArturo
 */
public class EU4Directories {

    /**
     * The default path of the game installation.
     */
    private static final String DEFAULT_PATH
	    = "C:\\Program Files (x86)\\Steam\\steamapps\\common\\Europa Universalis IV";
    /**
     * The actual path of the game installation.
     */
    private static Path gamePath;

    /**
     * Before any static call, the class will attempt to find and/or verify the
     * game files exist. Later versions may prompt a pop-up asking for the user
     * the location of the game installation if default doesn't work.
     */
    static {
	gamePath = Paths.get(DEFAULT_PATH);
	try {
	    verify();
	} catch (Exception ex) {
	    Logger.getLogger(EU4Directories.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    /**
     * Verifies if the path does locate the game files. This call looks for the
     * EU4.exe game file. If the <code>gamePath</code> field remains null, it
     * will also set it to default values beforehand.
     * <p>
     * @throws Exception If the game file isn't found.
     */
    private static void verify() throws Exception {
	if (gamePath == null)
	    gamePath = Paths.get(DEFAULT_PATH);
	if (!gamePath.resolve("eu4.exe").toFile().canRead())
	    throw new Exception();
    }

    /**
     * Gets the game path.
     * <p>
     * @return The game path.
     */
    public static Path getPath() {
	return gamePath;
    }

    /**
     * Returns the path of the Country Names. A simple call that joins the
     * <code>gamePath</code> and the known location of the country names in the
     * game files. Future versions may deliver different results according to
     * the main app localization.
     * <p>
     * @return The path where the country names, in YML format, is.
     */
    public static Path getCountryNamesPath() {
	return gamePath.resolve("localisation\\countries_l_english.yml");
    }
}
