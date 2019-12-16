package agreemod_components;

import static constants_panels.BoardPanelConstants.*;
import static constants_panels.BoardPanelConstants.getLength;

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
import static constants.Territories.*;

/** Grid layer for board panel. */
public class LayerGrid extends JPanel {
	
	//pawns
	final int [][] pawns;
	int [][] territories;
	
    //length
	int size;
    double step;
    double thick;
    double length;
    double initStep;
    
    //colors
    private final Color myTerritory;
    private final Color enemyTerritory;
    private final Color conflict;
	
	private final Image WHITE;
	private final Image BLACK;
	
	public LayerGrid(int newSize, int [][] pawns) {
		super();
		setBorder(null);
		this.size = newSize;
		this.pawns = pawns;
		territories = new int[newSize][newSize];
		
	    step = (double)getLength(size)/size;
	    thick = (double)THICKNESS;
	    length = (double)(step*(size-1) + thick);
	    initStep = (double)(step/2 - thick/2);
	    
		WHITE = getImage("images/white.png");
		BLACK = getImage("images/black.png");
		
	    myTerritory = COL_MYTERRITORY;
	    enemyTerritory = COL_ENEMYTERRITORY;
	    conflict = COL_CONFLICT;
		
		setBackground(BACKGROUND);
		setPreferredSize(new Dimension(getLength(size), getLength(size)));
		repaint();
	}
	
	@Override
    public void paintComponent(Graphics g) {
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
        
        //territories
        for(int i = 0; i < size; i++)
        for(int j = 0; j < size; j++)
        	switch(territories[i][j]) {
        	case ME:
        		g.setColor(myTerritory);
        		g.fillRect((int)(j*step), (int)(i*step), (int)step, (int)step);
        		System.out.println("__print ME");
        		break;
			case ENEMY:
				g.setColor(enemyTerritory);
				g.fillRect((int)(j*step), (int)(i*step), (int)step, (int)step);
				System.out.println("__print ENEMY");
				break;
			case CONFLICT:
				g.setColor(conflict);
				g.fillRect((int)(j*step), (int)(i*step), (int)step, (int)step);
				System.out.println("__print CONFLICT");
				break;	
        	}
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
	
	/** Adds coloured field to board. */
	public void addTerritory(int position, int status) {
		int x = position%size;
		int y = position/size;
		territories[y][x] = status;
	}
}