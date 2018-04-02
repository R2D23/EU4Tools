package r2d23.EU4resources;

import java.awt.Image;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;
import static r2d23.EU4resources.EU4Directories.*;

/**
 * The <code>Country</code> class that models a tag country from EU4 game meta
 * data.
 * <p>
 * @author R2D2 3
 */
public class Country {

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
     * The tag of the Country. A <code>String</code> that MUST be a sequence of
     * three letters.
     */
    private String tag;
    
    private Country(){
	
    }

    /**
     * The main constructor.
     * <p>
     * This constructor will automatically fill on the flag and name info from
     * the static methods provided by <code>EU4resources.EU4Directories</code>
     * class. As such, the mentioned class must be initiated beforehand.
     * <p>
     * @param tag The tag of the country.
     * <p>
     * @throws java.io.FileNotFoundException
     */
    public Country(String tag) throws FileNotFoundException, IOException {
	this();
	if (!isValideTag(tag))
	    throw new IllegalArgumentException();
	this.tag = tag;
	setNameFromGameData();
	setFlagFromGameData();
    }

    public Image getFlag() {
	return flag;
    }

    public String getName() {
	return name;
    }

    public String getTag() {
	return tag;
    }

    @Override
    public String toString() {
	return tag.toUpperCase() + ": " + name;
    }
    
    public static Country[] getAllCountries() throws IOException{
	Vector<Country> auxA = new Vector<>();
	BufferedReader r = Files.newBufferedReader(Paths.get(getCountryTagsFile().toString()));
	for (String auxS = r.readLine(); auxS != null; auxS = r.readLine()){
	    if(auxS.contains("=") && auxS.trim().charAt(0) != '#'){
		auxS = auxS.split("=")[0];
		auxS = auxS.trim();
		if(!auxS.equals("SOS")) //Specifically SOS.tga crashes TGAReader class. Reason unknown. Readable for PS.
		    auxA.add(new Country(auxS));
	    }
	}
	return auxA.toArray(new Country[auxA.size()]);
    }

    private void setFlagFromGameData() throws IOException {
	File f = Paths.get(getFlagsFile().toString(), tag + ".tga").toFile();
	if(!f.exists())
	    return;
	flag = net.npe.tga.TGAReader.read(f, net.npe.tga.TGAReader.ARGB);
    }

    /**
     * This private method will set both <code>flag</code> and <code>name</code>
     * fields of the new instanced <code>Country</code> object from EU4 game
     * directory using <code>EU4Directories</code> methods.
     */
    private void setNameFromGameData() throws FileNotFoundException, IOException {
	name = getLocalization(tag);
    }

}
