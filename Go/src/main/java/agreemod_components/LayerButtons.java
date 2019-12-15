package agreemod_components;

import static constants_panels.BoardPanelConstants.getLength;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JPanel;

import game.Engine;

/** Buttons layer for board panel.*/
public class LayerButtons extends JPanel {
	
	private BoardButton [][] buttons;
	private Board parent;
	private int size;
	
	public LayerButtons(Board parent, int size) {
		super();
		this.parent = parent;
		this.size = size;
		buttons = new BoardButton[size][size];
		//setLayout(new GridLayout(size,size));
		//setPreferredSize(DIM_BOARDINIT);
		setPreferredSize(new Dimension(getLength(size), getLength(size)));
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		//gbc init
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0,0,0,0);
		
		int k = 0;
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++) {
				buttons[i][j] = new BoardButton(this, k);
				gbc.gridx = j;
				gbc.gridy = i;
				add(buttons[i][j], gbc);
				k++;
			}
	}
	
	/** Repaints part of area, must be override by parent. */
	public void repaintNow(Rectangle rec) {
		parent.repaintNow(rec);
	}
	
	/*
	@Override
    protected void paintComponent(Graphics g)
    {
        g.setColor( getBackground() );
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
    */
	
	/** Sends signal, must be override by parent. */
	public void sendSignal(String signal) {
		parent.sendSignal(signal);
	}
	
	/** Switch on enemy territory field.*/
	public void addEnemy(int number) {
		int x = number%size;
		int y = number/size;
		buttons[y][x].addEnemy(number);
	}
	
	/** Switch off enemy territory field.*/
	public void removeEnemy(int number) {
		int x = number%size;
		int y = number/size;
		buttons[y][x].removeEnemy(number);
	}
}