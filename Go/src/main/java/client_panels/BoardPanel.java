package client_panels;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import static constants.PawnColors.*;
import static constants.Signals.*;
import static constants_panels.BoardPanelConstants.*;

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
import client_interfaces.*;

import constants.PawnColors;

/** Displays game board. */
public class BoardPanel extends JPanel implements PawnOperations, SignalSender{

	//components
	private Board board;
	private XAxis xAxis;
	private YAxis yAxis;
	
	public BoardPanel() {
		super();
		
		//setPreferredSize(DIM_BOARDINIT);
		setBackground(COL_BOARDINIT);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false);
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
		//pack();
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String [] args) {
		//TODO delete main method
		BoardPanel boardPanel = new BoardPanel();
		boardPanel.init(20);
		boardPanel.addPawn(5, PawnColors.Pawn.WHITE.Symbol());
		boardPanel.addPawn(15, PawnColors.Pawn.WHITE.Symbol());
		boardPanel.addPawn(24, PawnColors.Pawn.BLACK.Symbol());
		boardPanel.removePawn(5);
	}
	
	public void sendSignal(String signal) {
		System.out.println(signal);
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
			layerGrid = new LayerGrid(size);
			layerButtons = new LayerButtons(size);
			
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
		
		/** Panel for board grid. */
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
				
				setBackground(Color.WHITE);
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
		        		if(pawns[i][j] == PawnColors.Pawn.WHITE.Symbol())
		        			g.drawImage(WHITE, j*step, i*step, null);
		        		else if(pawns[i][j] == PawnColors.Pawn.BLACK.Symbol())
		        			g.drawImage(BLACK, j*step, i*step, null);
		    }
			
			/** Return scaled image.*/
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
				if(color == PawnColors.Pawn.WHITE.Symbol())
				{
					pawns[number/size][number%size] = PawnColors.Pawn.WHITE.Symbol();
				}
				else if(color == PawnColors.Pawn.BLACK.Symbol())
				{
					pawns[number/size][number%size] = PawnColors.Pawn.BLACK.Symbol();
				}
				repaint();
			}
			
			/** Remove a pawn from specific position.*/
			public void removePawn(int number) {
				System.out.println("Remove pawn!");
				pawns[number/size][number%size] = PawnColors.Pawn.EMPTY.Symbol();
			}
		}
		
		/*
		 * Panel for displaying buttons.
		 */
		public class LayerButtons extends JPanel {
			
			public BoardButton[][] buttons;
			
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

				setBorderPainted(false);
				setPreferredSize(new Dimension(10,10));
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
		}
	}
	
	/*
	 * Panel for displaying x axis.
	 */
	private class XAxis extends JPanel {
		
		public XAxis(int size) {
			
			setLayout(new GridLayout(1,0));
			setPreferredSize(DIM_XAXIS);
			setBackground(COL_AXIS_BACKGROUND);
			
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
			setBackground(COL_AXIS_BACKGROUND);
			
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
