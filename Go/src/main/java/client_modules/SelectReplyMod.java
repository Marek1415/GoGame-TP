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

	//label
	private InfoLabel infoLabel;
	
	//panels
	private JPanel panel;
	private JScrollPane scrollPane;
	
	//button
	private ActionButton gameButton;
	
	//games
	List<Game> games;
	
	public SelectReplyMod() {
		
		//label
		infoLabel = new InfoLabel(STR_INFO, DIM_INFO);
		
		//gridBagLayout, gridBagConstraint
		GridBagLayout layout = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(layout);
		
		//gbc init
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		//label
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10,10,10,10);
		add(infoLabel, gbc);
		
		//games
		panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		//panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//panel.setLayout(new ScrollPaneLayout());
		games = DatabaseConnector.getGames();
		for(int i = 0; i < games.size(); i++) {
			gameButton = new ActionButton(games.get(i));
			panel.add(gameButton);
			System.out.println(games.get(i).getId());
		}
		scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(DIM_PANE);
		
		//panel
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(scrollPane, gbc);
		
		pack();
		setVisible(true);
		//setResizable(false);
		//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/** Called when gameButton is pressed, must be override by parent. */ 
	public void selectGame(int id) {
		System.out.println("selected: " + id);
	}
	
    /**Performs action on parent. */
	private class ActionButton extends JButton {
    	
    	private ActionButton(Game game) {
    		
    		super("Buttonik");
    		setPreferredSize(DIM_BUTTON);
    		setForeground(COL_FOREGROUND);
    		setFont(FONT_BUTTON);
    		setBorderPainted(false);
    		
    		addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent event) {
        			selectGame(5);
      			}
    		});
    	}
	}
	
    /** Label for displaying info about dialog. */
    private class InfoLabel extends JLabel {
    	
    	private InfoLabel(String text, Dimension dim) {
    		super(text);
    		setFont(FONT);
    		setPreferredSize(dim);
    		setHorizontalAlignment(JLabel.CENTER);
    	    setVerticalAlignment(JLabel.CENTER);
    	}
    }

	public static void main(String[] args) {
		//TODO delete main method
		new SelectReplyMod();
	}
}
