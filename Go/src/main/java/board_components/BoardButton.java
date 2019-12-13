package board_components;

import static constants.Signals.CL_PUT;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

/** Single button for buttons layer. */
public class BoardButton extends JButton {
	
	LayerButtons parent;
	
	public BoardButton(LayerButtons parent, final int number) {
		super();
		this.parent = parent;

		setBorderPainted(false);
		setPreferredSize(new Dimension(1,1));
		setBackground(new Color(0,0,0,0));
		repaintNow(getBounds());
		repaint();
		
		//setOpaque(false);
		//setContentAreaFilled(false);
		//setVisible(false);
		//repaintNow(getBounds());
		//setBackground(new Color(0,0,0,125));
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.YELLOW);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(new Color(0,0,0,0));
				repaintNow(getBounds());
				repaint();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				sendSignal(CL_PUT + " " + number);
			}
		});
	}
	
	/** Repaints part of area. */
	public void repaintNow(Rectangle rec) {
		parent.repaintNow(rec);
	}
	
	/** Sends signal to parent. */
	public void sendSignal(String signal) {
		parent.sendSignal(signal);
	}
}