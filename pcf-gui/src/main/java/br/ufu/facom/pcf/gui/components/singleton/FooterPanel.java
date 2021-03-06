package br.ufu.facom.pcf.gui.components.singleton;

import br.ufu.facom.pcf.gui.Main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FooterPanel extends JPanel {

    private static FooterPanel instance;

    private FooterPanel() {

        final JLabel lblRunning = new JLabel("Running... ", JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        final URL url = Main.class.getClassLoader().getResource("ajax-loader.gif");
        if (url != null) {
            final ImageIcon imgIcon = new ImageIcon(url);
            lblRunning.setIcon(imgIcon);
        }
        lblRunning.setFont(lblRunning.getFont().deriveFont(Font.PLAIN));

        this.add(lblRunning);
        this.setVisible(false);

    }

    public static FooterPanel getInstance() {

        if (instance == null) {
            instance = new FooterPanel();
        }
        return instance;

    }

}
