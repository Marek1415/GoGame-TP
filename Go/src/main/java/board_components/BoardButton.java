package board_components;

import static constants.Signals.CL_PUT;
import static constants_panels.BoardPanelConstants.LENGTH_BOARD;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/** Single button for buttons layer. */
public class BoardButton extends JButton {
	
	LayerButtons parent;
	private final ImageIcon CROSS;
	
	public BoardButton(LayerButtons parent, final int number) {
		super();
		this.parent = parent;

		setBorderPainted(false);
		setPreferredSize(new Dimension(1,1));
		setBackground(new Color(0,0,0,0));
		repaintNow(getBounds());
		repaint();
		
		CROSS = getImage("images/ring.png");
		
		//setOpaque(false);
		//setContentAreaFilled(false);
		//setVisible(false);
		//repaintNow(getBounds());
		//setBackground(new Color(0,0,0,125));
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.YELLOW);
				//System.out.println(getBounds());
				//setIcon(CROSS);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(new Color(0,0,0,0));
				//setIcon(null);
				repaintNow(getBounds());
				repaint();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				sendSignal(CL_PUT + " " + number);
			}
		});
	}
	
	/** Return scaled image.*/
	public ImageIcon getImage(String path) {
		
		Image image = null;
		try {
			image = ImageIO.read(new File(path));
		} 
		catch (IOException e) {}
		
		return new ImageIcon(image.getScaledInstance((int)(LENGTH_BOARD/10), (int)(LENGTH_BOARD/10),  java.awt.Image.SCALE_SMOOTH));
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