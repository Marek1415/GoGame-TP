package board_components;

import static constants.Signals.CL_PUT;
import static constants_panels.BoardPanelConstants.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

/** Single button for buttons layer. */
public class BoardButton extends JButton {
	
	//parent
	LayerButtons parent;
	
	//constants
	private final Color active;
	private final Color inactive;
	
	public BoardButton(LayerButtons parent, final int number) {
		super();
		this.parent = parent;

		setBorderPainted(false);
		setPreferredSize(new Dimension(1,1));
		this.active = COL_ACTIVE;
		this.inactive = COL_INACTIVE;
		setBackground(inactive);
		repaintNow(getBounds());
		repaint();
		
		//setOpaque(false);
		//setContentAreaFilled(true);
		//setVisible(false);
		//repaintNow(getBounds());
		//setBackground(new Color(0,0,0,125));
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(active);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(inactive);
				repaintNow(getBounds());
				repaint();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				sendSignal(number);
			}
		});
	}
	
	/** Repaints part of area. */
	public void repaintNow(Rectangle rec) {
		parent.repaintNow(rec);
	}
	
	/** Sends signal to parent. */
	public void sendSignal(int number) {
		parent.sendSignal(number);
	}
}