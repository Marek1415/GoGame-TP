package client.panels;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static constants.PanelsConstants.*;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Panel for displaying board.
 */
public class BoardPanel extends JFrame {

	//components
	//private BoardCanvas boardCanvas;
	private BoardButtons boardButtons;
	private XAxis xAxis;
	private YAxis yAxis;
	
	public BoardPanel() {
		super();
		setPreferredSize(DIM_BOARD);
		setBackground(COL_BOARDINIT);
		setVisible(true);
	}
	
	/** Initializes the board panel.*/
	public void init(int size) {
		
		//components
		xAxis = new XAxis(size);
		yAxis = new YAxis(size);
		boardButtons = new BoardButtons(size);
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		//gbc init
		//gbc.weightx = 1;
		//gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		
		//x axis
		gbc.gridx = 1;
		gbc.weightx = 1 - 1/size;
		gbc.weighty = 1/size;
		gbc.gridy = 0;
		add(xAxis, gbc);
		
		//y axis
		gbc.weightx = 1/size;
		gbc.weighty = 1 - 1/size;
		add(yAxis, gbc);

		//board buttons
		gbc.weightx = 1 - 1/size;
		gbc.weighty = 1 - 1/size;
		add(boardButtons, gbc);
		
		setVisible(true);
		pack();
	}
	
	public static void main(String [] args) {
		BoardPanel boardPanel = new BoardPanel();
		boardPanel.init(12);
	}
	
	public void buttonPressed(int number) {
		System.out.println("Pressed :" + number);
	}
	
	/*
	 * Panel for displaying buttons.
	 */
	private class BoardButtons extends JPanel {
		
		AbstractButton[][] buttons;
		
		public BoardButtons(int size) {
			
			buttons = new BoardButton[size][size];
			setLayout(new GridLayout(size,size));
			
			int k = 0;
			for(int i = 0; i < size; i++)
				for(int j = 0; j < size; j++) {
					buttons[i][j] = new BoardButton(k);
					add(buttons[i][j], i, j);
					k++;
				}
				
		}
	}
	
	/*
	 * Single button for buttons panel.
	 */
	private class BoardButton extends JButton {
		final int number;
		
		public BoardButton(final int number) {
			super();
			this.number =  number;
			
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			buttonPressed(number);
      			}
    		});
		}
	}
	
	/*
	 * Panel for displaying x axis.
	 */
	private class XAxis extends JPanel {
		
		public XAxis(int size) {
			
			setLayout(new GridLayout(1,0));
			
			for(int i = 0; i < size; i++)
				add(new JLabel(Integer.toString(i+1)));
		}
	}
	
	/*
	 * Panel for displaying y axis.
	 */
	private class YAxis extends JPanel {
		
		public YAxis(int size) {
			
			setLayout(new GridLayout(0,1));
			
			for(int i = 0; i < size; i++)
				add(new BoardLabel(i+1));
		}
	}
	
	/*
	 * Single label for axis.
	 */
	private class BoardLabel extends JLabel {
		
		public BoardLabel(int number) {
			super(Integer.toString(number));
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
		}
	}
}
