package board_components;

import static constants_panels.BoardPanelConstants.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;


/** Displays X axis. */
public class XAxis extends JPanel {
	
	public XAxis(int size) {
		
		setLayout(new GridLayout(1,0));
		setPreferredSize(new Dimension(getLength(size), LENGTH_AXIS));
		setBackground(BACKGROUND);
		
		for(int i = 0; i < size; i++)
			add(new AxisLabel(i+1));
	}
}