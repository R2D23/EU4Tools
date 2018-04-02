package r2d23.EU4Tools;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import r2d23.EU4resources.Country;

public class FlagChecker {
    public static JFrame frame;
    public static JPanel panel;
    public static Country country;
    public static JComboBox<Country> jcb;

    public static void main(String[] args) {
	preparePanel();
	prepareFrame();
	try {
	    Country[] countries = Country.getAllCountries();
	    ArrayList<JLabel> labels = new ArrayList<>();
	    for (Country country : countries)
		labels.add(new JLabel(country.getName(), new ImageIcon(country.getFlag(), country.getTag()), JLabel.CENTER));
	    jcb = new JComboBox<>(countries);
	    frame.add(jcb);
	    frame.add(panel);
	    frame.setLayout(new GridLayout());
	    jcb.addItemListener((ItemEvent e) -> {
		panel.repaint();
	    });
	} catch (IOException ex) {
	    Logger.getLogger(FlagChecker.class.getName()).log(Level.SEVERE, null, ex);
	}
	javax.swing.SwingUtilities.invokeLater(() -> {
	    frame.setVisible(true);
	});
    }

    private static void preparePanel() {
	panel = new JPanel() {
	    @Override
	    public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(((Country) jcb.getSelectedItem()).getFlag(), 0, 0, this.getWidth(), this.getHeight(), null);
	    }
	};
	panel.setSize(600, 400);
    }

    private static void prepareFrame() {
	frame = new JFrame("Flag Checker");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(600, 400);
	frame.setLocationByPlatform(true);
	frame.add(panel);
    }
}
