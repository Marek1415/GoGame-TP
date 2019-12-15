package agreemod_components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import static constants_modules.AgreeModConstants.*;

/** Action Button for performing action on parent. */
public abstract class ActionButton extends JButton {

	public ActionButton(String text, Dimension dim, Color col) {

		super(text);
		setPreferredSize(dim);
		setBackground(col);
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
}