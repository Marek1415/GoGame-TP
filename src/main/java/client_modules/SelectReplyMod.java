package client_modules;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import database.DatabaseConnector;
import database.Game;

import static constants_modules.SelectReplyModConstants.*;

/**
 * @author gumis
 * Displays games contained in database. 
 */
public class SelectReplyMod extends JFrame {

	//panels
	private JPanel panel;
	private JScrollPane scrollPane;
	
	//button
	private ActionButton gameButton;
	
	//games
	List<Game> games;
	
	public void init() {
		
		//games
		games = DatabaseConnector.getGames();
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		
		//gbc init
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = new Insets(0, 0, 4, 0);
		
		//games
		panel = new JPanel();
		panel.setBackground(COL_PANEL);
		panel.setLayout(layout);
		
		for(int i = 0; i < games.size(); i++) {
			if(i == games.size()-1)
				gbc.insets = new Insets(0,0,0,0);
			gameButton = new ActionButton(games.get(i));
			panel.add(gameButton, gbc);
			gbc.gridy += 1;
		}

		//scroll panel
		scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(DIM_PANE);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		add(scrollPane);
		
		pack();
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle(STR_TITLE);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/** Called when gameButton is pressed, must be override by parent. */ 
	public void selectGame(int id) {
		dispose();
	}
	
    /**Performs action on parent. */
	private class ActionButton extends JButton {
    	
		int id;
		
    	private ActionButton(Game game) {
    		
    		super();
    		prepareText(game);
    		setPreferredSize(DIM_BUTTON);
    		setBackground(COL_BUTTON);
    		setForeground(COL_FOREGROUND);
    		setFont(FONT_BUTTON);
    		setBorderPainted(false);
    		
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			selectGame(id);
        			dispose();
      			}
    		});
    	}
    	
    	private void prepareText(Game gm) {
    		this.id = gm.getId();
    		setText(
    				"nr: " + gm.getIdStr() + "  |  " + 
    				"size: " + gm.getSizeStr() + "  |  " + 
    				gm.getDateStr());
    	}
	}

	public static void main(String[] args) {
		//TODO delete main method
		SelectReplyMod replyMod = new SelectReplyMod();
		replyMod.init();
	}
}
