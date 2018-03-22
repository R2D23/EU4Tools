package EU4resources;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EU4Directories {

    private static final String DEFAULT_PATH = "C:\\Program Files (x86)\\Steam\\steamapps\\common\\Europa Universalis IV";
    private static Path gamePath;

    static {
	gamePath = Paths.get(DEFAULT_PATH);
	try {
	    verify();
	} catch (Exception ex) {
	    Logger.getLogger(EU4Directories.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private static void verify() throws Exception {
	if (gamePath == null)
	    gamePath = Paths.get(DEFAULT_PATH);
	if (!gamePath.resolve("eu4.exe").toFile().canRead())
	    throw new Exception();
    }

    public static Path getPath() {
	return gamePath;
    }
}
