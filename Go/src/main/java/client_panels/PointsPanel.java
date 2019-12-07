package client_panels;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static constants.PanelsConstants.LENGTH_BOARD;
import static constants.PointsPanelConstants.*;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

/** Shows current points and player turn. */
public class PointsPanel extends JPanel {
	
	//components
	private PointsLabel pointsLabel;
	private TurnLabel turnLabel;
	private PointsNoumber pointsNumber;
	private DotLabel redDot;
	private DotLabel greenDot;
	
	//images
	final ImageIcon redOFF;
	final ImageIcon redON;
	final ImageIcon greenOFF;
	final ImageIcon greenON;
	
	public PointsPanel() {
		
		//images
		redOFF = getIcon("images/redOFF.png");
		redON = getIcon("images/redON.png");
		greenOFF = getIcon("images/greenOFF.png");
		greenON = getIcon("images/greenON.png");
		
		//components
		pointsLabel = new PointsLabel(STR_POINTS);
		turnLabel = new TurnLabel(STR_TURN_ON, STR_TURN_OFF);
		pointsNumber = new PointsNoumber("0");
		redDot = new DotLabel(redOFF, redON);
		greenDot = new DotLabel(greenOFF, greenON);
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		//gbc init
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 0;
		gbc.weighty = 0;
		//gbc.fill = GridBagConstraints.BOTH;
		
		//points label
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(pointsLabel, gbc);
		
		//points number
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(pointsNumber, gbc);
		
		//green dot
		gbc.gridx = 2;
		gbc.gridy = 0;
		add(greenDot, gbc);
		
		//red dot
		gbc.gridx = 3;
		gbc.gridy = 0;
		add(redDot, gbc);
		
		//turn label
		gbc.gridx = 4;
		gbc.gridy = 0;
		add(turnLabel, gbc);
		
		//pack();
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(COL_BACKGROUND);
		setVisible(true);
	}
	
	/** Sets the number of points. */
	public void setPoints(String points) {
		pointsNumber.setText(points);
	}
	
	/** Sets the turn mode ON. */
	public void turnON() {
		greenDot.setON();
		redDot.setOFF();
		turnLabel.setON();
	}
	
	/** Sets the turn mode OFF. */
	public void turnOFF() {
		greenDot.setOFF();
		redDot.setON();
		turnLabel.setOFF();
	}
	
	/** Return scaled image.*/
	public ImageIcon getIcon(String path) {
		
		Image image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {}

		return new ImageIcon(image.getScaledInstance
				(DOT_LENGTH, DOT_LENGTH,  java.awt.Image.SCALE_SMOOTH));
	}
	
	private class DotLabel extends JLabel {
		
		private final ImageIcon imgON;
		private final ImageIcon imgOFF;
		
		public DotLabel(ImageIcon imgOFF, ImageIcon imgON) {
			super(imgOFF);
			this.imgOFF = imgOFF;
			this.imgON = imgON;
			setPreferredSize(DIM_DOT);
		}
		
		/** Sets the icon OFF mode. */
		public void setOFF() {
			setIcon(imgOFF);
		}
		
		/** Sets the icon OF mode. */
		public void setON() {
			setIcon(imgON);
		}
	}
	
	private class PointsLabel extends JLabel {
		public PointsLabel(String text) {
			super(text);
			setFont(FONT_LABEL);
		}
	}
	
	private class PointsNoumber extends JLabel {
		public PointsNoumber(String text) {
			super(text);
			setFont(FONT_POINTS_NOUMBER);
			setPreferredSize(DIM_POINTS);
		}
	}
	
	private class TurnLabel extends JLabel {
		
		private final String textON;
		private final String textOFF;
		
		public TurnLabel(String textON, String textOFF) {
			super(STR_TURN_INIT);
			this.textOFF = textOFF;
			this.textON = textON;
			setFont(FONT_LABEL);
		}
		
		/** Sets the text OFF mode. */
		public void setOFF() {
			setText(textOFF);
			setForeground(COL_OFF);
		}
		
		/** Sets the text OF mode. */
		public void setON() {
			setText(textON);
			setForeground(COL_ON);
		}
	}
	
	public static void main(String [] args) {
		//TODO delete main method
		PointsPanel pointsPanel = new PointsPanel();
	}
}
