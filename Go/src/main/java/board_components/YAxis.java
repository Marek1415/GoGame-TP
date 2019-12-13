package board_components;

import static constants_panels.BoardPanelConstants.*;

import java.awt.GridLayout;
import javax.swing.JPanel;


/** Displays Y axis. */
public class YAxis extends JPanel {
	
	public YAxis(int size) {
		
		setLayout(new GridLayout(0,1));
		setPreferredSize(DIM_YAXIS);
		setBackground(BACKGROUND);
		
		for(int i = 0; i < size; i++)
			add(new AxisLabel(i+1));
	}
}