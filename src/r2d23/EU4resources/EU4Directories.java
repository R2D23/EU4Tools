package r2d23.EU4resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A static class that encompasses utility calls for EU4 game files connection.
 * <p>
 * @author LuisArturo
 */
public class EU4Directories {

    private static HashMap<String, String> localization = null;

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

    public static File getCountryTagsFile() {
	return gamePath.resolve("common\\country_tags\\00_countries.txt").toFile();
    }

    public static File getFlagsFile() {
	return gamePath.resolve("gfx\\flags").toFile();
    }

    public static String getLocalization(String s) throws IOException {
	if (localization == null) {
	    localization = new HashMap<>();
	    File[] files = getLocalizationFiles();
	    BufferedReader r;
	    for (File file : getLocalizationFiles()) {
		r = Files.newBufferedReader(file.toPath());
		for (String aux = r.readLine(); aux != null; aux = r.readLine()) {
		    String[] strings = aux.split(":");
		    if (strings.length == 2) {
			strings[0] = strings[0].trim();
			if (strings[1].contains("\"") && strings[1].split("\"").length > 1)
			    strings[1] = strings[1].split("\"")[1];
			if (strings[0] != null && strings[1] != null)
			    localization.put(strings[0], strings[1]);
		    }
		}
	    }
	}
	return localization.get(s);
    }

    public static File[] getLocalizationFiles() throws IOException {
	Vector<File> auxV = new Vector<>();
	File aux = gamePath.resolve("localisation\\").toFile();
	for (File auxF : aux.listFiles())
	    if (auxF.toString().contains("english.yml"))
		auxV.add(auxF);
	return auxV.toArray(new File[auxV.size()]);
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
}
