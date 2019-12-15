package agreemod_components;

import static constants.Signals.*;
import static constants_panels.BoardPanelConstants.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import client_interfaces.EnemyOperations;

/** Single button for buttons layer. */
public class BoardButton extends JButton implements EnemyOperations {
	
	//parent
	LayerButtons parent;
	
	//constants
	private final Color myActive;
	private final Color inactive;
	private final Color myTerritory;
	private final Color enemyTerritory;
	private final Color conflict;
	private final int number;
	
	//status
	private boolean myStatus;
	private boolean enemyStatus;
	
	public BoardButton(LayerButtons parent, final int number) {
		super();
		this.parent = parent;
		this.number = number;
		this.myStatus = false;
		this.enemyStatus = false;

		setBorderPainted(false);
		setPreferredSize(new Dimension(1,1));
		this.myActive = COL_MYACTIVE;
		this.inactive = COL_INACTIVE;
		this.myTerritory = COL_MYTERRITORY;
		this.enemyTerritory = COL_ENEMYTERRITORY;
		this.conflict = COL_CONFLICT;
		
		//setBackground(myActive);
		setBackground(Color.RED);
		System.out.println("Button init!");
		repaint();
		setVisible(true);
		//repaintNow(getBounds());
		//repaint();
		//setOpaque(true);
		setEnabled(true);
		//setVisible(true);
		//setFocusable(true);
		//setContentAreaFilled(true);
		//setVisible(false);
		//repaintNow(getBounds());
		//setBackground(new Color(0,0,0,125));
		
		/*
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("Mouse entered!");
				setBackground(myActive);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("Mouse exited!");
				//setBackground(inactive);
				
				
				if(!myStatus && !enemyStatus) {
					setBackground(inactive);
					repaintNow(getBounds());
				}
				else {
					changeBackground();
					repaint();
				}
					
				//setBackground(COL_INACTIVE);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse clicked!");
				changeMyStatus();
				changeBackground();
				//repaintNow(getBounds());
				repaint();
			}
		});
		*/
	}
	
	/** Repaints part of area. */
	public void repaintNow(Rectangle rec) {
		/*
		parent.repaintNow(rec);
		*/
	}
	
	/** Sends signal to parent. */
	public void sendSignal(String signal) {
		parent.sendSignal(signal);
	}
	
	/** Changes the background color. */
	public void changeBackground() {
		/*
		//setBackground(inactive);
		if(myStatus && enemyStatus)
			setBackground(conflict);
		else if(myStatus)
			setBackground(myTerritory);
		else if(enemyStatus)
			setBackground(enemyTerritory);
		//else {
		//	setBackground(inactive);
		//}
		//setVisible(true);
		super.repaint();
		*/
	}
	
	/** Change current button status. */
	public void changeMyStatus() {
		/*
		if(myStatus) {
			myStatus = false;
			sendSignal(CL_MYREMOVE + " " + number);
		}
		else {
			myStatus = true;
			sendSignal(CL_MYADD + " " + number);
		}
		*/
	}
	
	/** Switch on enemy territory field.*/
	public void addEnemy(int number) {
		/*
		System.out.println("enemy put!");
		setBackground(Color.RED);
		//repaint();
		enemyStatus = true;
		
		changeBackground();
		invalidate();
		validate();
		repaint();
		*/
	}
	
	/** Switch off enemy territory field.*/
	public void removeEnemy(int number) {
		/*
		enemyStatus = false;
		changeBackground();
		//repaintNow(getBounds());
		repaint();
		*/
	}
}