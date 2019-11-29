package goGameThroughServer;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
import java.util.*;
class GoFrame extends JFrame // pierwszy popup z podstawowymi wyborami
{
	GoFrame()
	{
		super("Go - choose options" );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		setVisible(true);
	}

}
public class Client
{
	public static void main()
	{
		GoFrame goframe = new GoFrame();
	}

}