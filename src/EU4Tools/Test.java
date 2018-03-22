package EU4Tools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test {
    public static Path path = Paths.get("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Europa Universalis IV");
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
		    File f = path.resolve(Paths.get("gfx\\flags\\NAT.tga")).toFile();
		    g2.drawImage(net.npe.tga.TGAReader.read(f, net.npe.tga.TGAReader.ARGB), 0, 0, this.getWidth(), this.getHeight(), null);
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
