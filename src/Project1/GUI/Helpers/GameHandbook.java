package Project1.GUI.Helpers;

import Project1.GUI.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class GameHandbook extends JFrame implements ActionListener, WindowListener {
	// GUI-related members
	private static final int WIDTH = 325, HEIGHT = 180;
	private static final String TITLE = "Minecraft Memory Handbook";
	private Container pane;
	
	// GUI components
	JButton Next, Previous;
	
	// Only allow single instance open
	private static SoundTester instance;
	public static SoundTester get() { return instance; }
	
	public GameHandbook() {
		// Set standard JFrame properties
		pane = this.getContentPane();
		pane.setLayout(null);
		
		this.setTitle(TITLE);
		this.setResizable(false);
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(MainFrame.get());
		
		// Initialize components
		
		// Add components to the content pane
		
		// Show the dialog
		this.setVisible(true);
	}
	
	// ActionListener
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Next) {
			
		}
		else if (e.getSource() == Previous) {
			
		}
	}

	// WindowListener
	public void windowClosed(WindowEvent e) { instance = null; }
	public void windowOpened(WindowEvent e) { }
	public void windowClosing(WindowEvent e) { }
	public void windowIconified(WindowEvent e) { }
	public void windowDeiconified(WindowEvent e) { }
	public void windowActivated(WindowEvent e) { }
	public void windowDeactivated(WindowEvent e) { }
}
