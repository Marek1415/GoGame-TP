package agreemod_components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import static constants_modules.AgreeModConstants.*;

/** Action Button for performing action on parent. */
public abstract class ActionButton extends JButton {

	Color color;
	Color colorDisabled;
	
	public ActionButton(String text, Dimension dim, Color col) {

		super(text);
		setPreferredSize(dim);
		this.color = col;
		this.colorDisabled = COL_DISABLED;
		setEnabledStatus(true);
		setForeground(COL_FOREGROUND);
		setFont(FONT);
		setBorderPainted(false);

		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				action();
			}
		});
	}

	/** action method, must be override by parent. */
	public abstract void action();
	
	public void setEnabledStatus(boolean status) {
		if(status) {
			setBackground(color);
			setEnabled(true);
		}
		else {
			setBackground(colorDisabled);
			setEnabled(false);
		}
	}
}