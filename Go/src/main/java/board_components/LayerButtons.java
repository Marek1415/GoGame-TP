package board_components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import static constants_panels.BoardPanelConstants.getLength;

import javax.swing.JPanel;

/** Buttons layer for board panel.*/
public class LayerButtons extends JPanel {
	
	private BoardButton button;
	
	public LayerButtons(int size) {
		super();
		//setLayout(new GridLayout(size,size));
		
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		//gbc init
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0,0,0,0);
		
		int k = 0;
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++) {
				button = new BoardButton(this, k);
				gbc.gridx = j;
				gbc.gridy = i;
				add(button, gbc);
				k++;
			}
	}
	
	/** Repaints part of area, must be override by parent. */
	public void repaintNow(Rectangle rec) {
	}
	
	/** Sends signal, must be override by parent. */
	public void sendSignal(int number) {
	}
}