package client.panels;

import javax.swing.JPanel;

import java.awt.Color;
import static constants.PanelsConstants.*;

/*
 * Panel for displaying messenger.
 */
public class MessengerPanel extends JPanel {

	public MessengerPanel() {
		super();
		setPreferredSize(DIM_MESSENGER);
		setBackground(Color.RED);
		setVisible(true);
	}
}
