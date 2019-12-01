package client.panels;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static constants.PanelsConstants.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
	//TODO make class abstract

	//components
	private LayerGrid layerGrid;
	private LayerButtons layerButtons;
	private XAxis xAxis;
	private YAxis yAxis;
	
	public BoardPanel() {
		super();
		//setPreferredSize(DIM_BOARDINIT);
		setBackground(COL_BOARDINIT);
		setVisible(true);
	}
	
	/** Initializes the board panel.*/
	public void init(int size) {
		
		//components
		xAxis = new XAxis(size);
		yAxis = new YAxis(size);
		layerButtons = new LayerButtons(size);
		layerGrid = new LayerGrid(size);
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		//gbc init
		gbc.fill = GridBagConstraints.BOTH;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.insets = new Insets(0,0,0,0);
		
		//x axis
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = size;
		//gbc.weighty = 1;
		gbc.weighty = 0.5;
		add(xAxis, gbc);
		
		//y axis
		gbc.gridx = 0;
		gbc.gridy = 1;
		//gbc.weightx = 1.5;
		gbc.weightx = 0.5;
		gbc.weighty = size;
		add(yAxis, gbc);

		//layer buttons
		//bc.gridx = 1;
		//gbc.gridy = 1;
		//gbc.weightx = size;
		//gbc.weighty = size;
		//add(layerButtons, gbc);
		
		//layer grid
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = size;
		gbc.weighty = size;
		add(layerGrid, gbc);
		
		setVisible(true);
		setBackground(Color.WHITE);
		pack();
	}
	
	public static void main(String [] args) {
		//TODO delete main method
		BoardPanel boardPanel = new BoardPanel();
		boardPanel.init(7);
	}
	
	public void buttonPressed(int number) {
		System.out.println("Pressed :" + number);
	}
	
	/*
	 * Panel for displaying buttons.
	 */
	private class LayerButtons extends JPanel {
		
		AbstractButton[][] buttons;
		
		public LayerButtons(int size) {
			
			buttons = new BoardButton[size][size];
			setLayout(new GridLayout(size,size));
			//setPreferredSize(DIM_BOARD);
			
			int k = 0;
			for(int i = 0; i < size; i++)
				for(int j = 0; j < size; j++) {
					buttons[i][j] = new BoardButton(k);
					add(buttons[i][j]);
					k++;
				}
				
		}
	}
	
	/*
	 * Panel for board grid.
	 */
	private class LayerGrid extends JPanel {
		
		int size;
		
		public LayerGrid(int newSize) {
			super();
			this.size = newSize;
			//setPreferredSize(DIM_BOARD);
			setBackground(Color.RED);
			repaint();
		}
		
		@Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        
	        g.setColor(COL_GRID);
	        int step = (int)LENGTH_BOARD/(size);
	        int step2 = (int)step/2;
	        int length = LENGTH_BOARD - step;
	        
	        //columns
	        for(int i = 0; i < size; i++)
	        	g.fillRect(step2 + i*step, step2, THICKNESS, length);
	        
	        //rows
	        for(int i = 0; i < size; i++)
	        	g.fillRect(step2, step2 + i*step, length, THICKNESS);
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
			setPreferredSize(DIM_XAXIS);
			
			for(int i = 0; i < size; i++)
				add(new BoardLabel(i+1));
		}
	}
	
	/*
	 * Panel for displaying y axis.
	 */
	private class YAxis extends JPanel {
		
		public YAxis(int size) {
			
			setLayout(new GridLayout(0,1));
			setPreferredSize(DIM_YAXIS);
			
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
			setFont(FONT_AXIS);
			setForeground(COL_AXIS);
			//setOpaque(true);
		}
	}
}
