package client.panels;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import client.interfaces.PawnOperations;

import static constants.PanelsConstants.*;
import static constants.PawnColors.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/*
 * Panel for displaying board.
 */
@SuppressWarnings("serial")
public class BoardPanel extends JFrame
implements PawnOperations {
	//TODO make class abstract

	//components
	private Board board;
	private XAxis xAxis;
	private YAxis yAxis;
	
	public BoardPanel() {
		
		super();
		//setPreferredSize(DIM_BOARDINIT);
		setBackground(COL_BOARDINIT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/** Initializes the board panel.*/
	public void init(int size) {
		
		//components
		xAxis = new XAxis(size);
		yAxis = new YAxis(size);
		board = new Board(size);
		
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
		//gbc.weightx = size;
		//gbc.weighty = 1;
		//gbc.weighty = 0.5;
		add(xAxis, gbc);
		
		//y axis
		gbc.gridx = 0;
		gbc.gridy = 1;
		//gbc.weightx = 1.5;
		//gbc.weightx = 0.5;
		//gbc.weighty = size;
		add(yAxis, gbc);
		
		//board
		gbc.gridx = 1;
		gbc.gridy = 1;
		//gbc.weightx = size;
		//gbc.weighty = size;
		add(board, gbc);
		
		setVisible(true);
		setBackground(Color.WHITE);
		pack();
	}
	
	public static void main(String [] args) {
		//TODO delete main method
		BoardPanel boardPanel = new BoardPanel();
		boardPanel.init(7);
		boardPanel.addPawn(5, PAWN_WHITE);
		boardPanel.addPawn(15, PAWN_WHITE);
		boardPanel.addPawn(24, PAWN_BLACK);
		boardPanel.removePawn(5);
	}
	
	/** Returns the number of pressed button, must by override by parent*/
	public void buttonPressed(int number) {
		//TODO make this abstract
		System.out.println("Pressed :" + number);
	}
	
	/** Adds a pawn with specific color on specific position.*/
	public void addPawn(int number, int color) {
		board.addPawn(number, color);
	}
	
	/** Remove a pawn from specific position.*/
	public void removePawn(int number) {
		board.removePawn(number);
	}
	
	/*
	 * Panel with layers of buttons and grid
	 */
	private class Board extends JLayeredPane
	implements PawnOperations {
		
		//components
		private LayerGrid layerGrid;
		private LayerButtons layerButtons;
		
		public Board(int size) {
			super();
			
			//components
			layerButtons = new LayerButtons(size);
			layerGrid = new LayerGrid(size);
			
			setLayout(new OverlayLayout(this));
			add(layerGrid, 0);
			add(layerButtons, 1);
		}
		
		public void repaintNow(Rectangle rec) {
			layerGrid.repaint(rec);
		}
		
		/** Adds a pawn with specific color on specific position.*/
		public void addPawn(int number, int color) {
			layerGrid.addPawn(number, color);
		}
		
		/** Remove a pawn from specific position.*/
		public void removePawn(int number) {
			layerGrid.removePawn(number);
		}
		
		/*
		 * Panel for board grid.
		 */
		private class LayerGrid extends JPanel
		implements PawnOperations {
			
			int size;
			int [][] pawns;
			
			final Image WHITE;
			final Image BLACK;
			
			public LayerGrid(int newSize) {
				super();
				this.size = newSize;
				pawns = new int[size][size];
				
				WHITE = getImage("images/white.png");
				BLACK = getImage("images/black.png");
				
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
		        
		        //pawns
		        for(int i = 0; i < size; i++)
		        	for(int j = 0; j < size; j++)
		        		if(pawns[i][j] == PAWN_WHITE)
		        			g.drawImage(WHITE, j*step, i*step, null);
		        		else if(pawns[i][j] == PAWN_BLACK)
		        			g.drawImage(BLACK, j*step, i*step, null);
		    }
			
			/** Return scaled icon.*/
			public Image getImage(String path) {
				
				Image image = null;
				try {
					image = ImageIO.read(new File(path));
				} catch (IOException e) {}

				return image.getScaledInstance((int)LENGTH_BOARD/size, (int)LENGTH_BOARD/size,  java.awt.Image.SCALE_SMOOTH);
			}
			
			/** Adds a pawn with specific color on specific position.*/
			public void addPawn(int number, int color) {
				System.out.println("New pawn!");
				switch(color) {
				case PAWN_WHITE: 
					pawns[number/size][number%size] = PAWN_WHITE;
					break;
				case PAWN_BLACK:
					pawns[number/size][number%size] = PAWN_BLACK;
					break;
				}
				repaint();
			}
			
			/** Remove a pawn from specific position.*/
			public void removePawn(int number) {
				System.out.println("Remove pawn!");
				pawns[number/size][number%size] = NONE;
			}
		}
		
		/*
		 * Panel for displaying buttons.
		 */
		private class LayerButtons extends JPanel {
			
			AbstractButton[][] buttons;
			
			public LayerButtons(int size) {
				
				buttons = new BoardButton[size][size];
				setLayout(new GridLayout(size,size));
				
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
		 * Single button for buttons panel.
		 */
		private class BoardButton extends JButton {
			
			public BoardButton(final int number) {
				super();
				//setOpaque(false);
				//setContentAreaFilled(false);
				setBorderPainted(false);
				setVisible(true);
				setPreferredSize(new Dimension(10,10));
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
	    				buttonPressed(number);
	    			}
	    		});
			}
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
			//getInsets(new Insets(0,0,0,0));
			setPreferredSize(new Dimension(1,1));
		}
	}
}