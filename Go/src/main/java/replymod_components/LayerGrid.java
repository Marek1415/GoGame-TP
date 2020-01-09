package replymod_components;

import static constants_panels.BoardPanelConstants.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import client_interfaces.PawnOperations;
import constants.PawnColors;

/** Grid layer for board panel. */
public class LayerGrid extends JPanel
implements PawnOperations {
	
	//pawns
	int [][] pawns;
	
    //length
	int size;
    double step;
    double thick;
    double length;
    double initStep;
	
	private final Image WHITE;
	private final Image BLACK;
	
	public LayerGrid(int newSize) {
		super();
		setBorder(null);
		this.size = newSize;
		pawns = new int[size][size];
		
	    step = (double)getLength(size)/size;
	    thick = (double)THICKNESS;
	    length = (double)(step*(size-1) + thick);
	    initStep = (double)(step/2 - thick/2);
	    
		WHITE = getImage("images/white.png");
		BLACK = getImage("images/black.png");
		
		setBackground(BACKGROUND);
		setPreferredSize(new Dimension(getLength(size), getLength(size)));
		repaint();
	}
	
	@Override
    public void paintComponent(Graphics g) {
		System.out.println("repaint");
        super.paintComponent(g);
        
        g.setColor(COL_GRID);
        
        //columns
        for(int i = 0; i < size; i++)
        	g.fillRect((int)(initStep+i*step), (int)initStep, (int)thick, (int)length);

        //rows
        for(int i = 0; i < size; i++)
        	g.fillRect((int)initStep, (int)(initStep+i*step), (int)length, (int)thick);
        
        //pawns
        for(int i = 0; i < size; i++)
        	for(int j = 0; j < size; j++)
        		if(pawns[i][j] == PawnColors.Pawn.WHITE.Symbol())
        			g.drawImage(WHITE, (int)(j*step), (int)(i*step), null);
        		else if(pawns[i][j] == PawnColors.Pawn.BLACK.Symbol())
        			g.drawImage(BLACK, (int)(j*step), (int)(i*step), null);
        //notifyAll();
	}
	
	/** Return scaled image.*/
	public Image getImage(String path) {
		
		Image image = null;
		try {
			image = ImageIO.read(new File(path));
		} 
		catch (IOException e) {}
		
		return image.getScaledInstance((int)(LENGTH_BOARD/size+1), (int)(LENGTH_BOARD/size+1),  java.awt.Image.SCALE_SMOOTH);
	}
	
	/** Adds a pawn with specific color on specific position.*/
	public void addPawn(int number, int color) {
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
		pawns[number/size][number%size] = PawnColors.Pawn.EMPTY.Symbol();
		repaint();
	}
}