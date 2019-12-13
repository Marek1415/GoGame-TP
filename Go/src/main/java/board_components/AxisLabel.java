package board_components;

import static constants_panels.BoardPanelConstants.*;

import java.awt.Dimension;
import javax.swing.JLabel;

/** Single label for axis. */
public class AxisLabel extends JLabel {
	
	public AxisLabel(int number) {
		super(Integer.toString(number));
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
		setFont(FONT_AXIS);
		setForeground(COL_AXIS);
		//getInsets(new Insets(0,0,0,0));
		setPreferredSize(new Dimension(1,1));
	}
}