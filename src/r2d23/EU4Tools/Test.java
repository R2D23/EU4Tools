package r2d23.EU4Tools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import r2d23.EU4resources.Country;
import static r2d23.EU4resources.EU4Directories.*;

public class Test {

    public static JFrame frame;
    public static JPanel panel;

    public static void main(String[] args) {
	preparePanel();
	prepareFrame();
	javax.swing.SwingUtilities.invokeLater(() -> {
	    frame.setVisible(true);
	});
    }

    private static void preparePanel() {
	panel = new JPanel() {
	    @Override
	    public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		try {
		    String tag = "NED";
		    File f = getPath().resolve(Paths.get("gfx\\flags\\" + tag + ".tga")).toFile();
		    g2.drawImage(net.npe.tga.TGAReader.read(f, net.npe.tga.TGAReader.ARGB), 0, 0, this.getWidth(), this.getHeight(), null);
		    Country c = new Country(tag);
		    System.out.println(c);
		} catch (Exception ex) {
		    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	};
	panel.setSize(600, 400);
    }

    private static void prepareFrame() {
	frame = new JFrame("Test");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(600, 400);
	frame.setLocationByPlatform(true);
	frame.add(panel);
    }
}
