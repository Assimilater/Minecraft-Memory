package Project1.GUI.Modifiers;

import Project1.GUI.MainFrame;
import Project1.MemoryGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class PlayersDialog extends JDialog implements ActionListener {
	// GUI-related members
	private static final int WIDTH = 325, HEIGHT = 200;
	private static final String TITLE = "Select Players";
	private Container pane;
	
	// GUI components
	
	JButton Ok, Cancel;

	public PlayersDialog() {
		// Set standard JFrame properties
		pane = this.getContentPane();
		pane.setLayout(null);
		
		this.setTitle(TITLE);
		this.setResizable(false);
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(MainFrame.get());
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		// Initialize components
		Ok = new JButton("Ok");
		Ok.setBounds(5, HEIGHT - 78, (WIDTH - 20) / 2, 40);
		Ok.setMnemonic(KeyEvent.VK_O);
		Ok.setFont(MemoryGame.Display);
		Ok.addActionListener(this);
		
		Cancel = new JButton("Cancel");
		Cancel.setBounds(10 + ((WIDTH - 20) / 2), HEIGHT - 78, (WIDTH - 20) / 2, 40);
		Cancel.setMnemonic(KeyEvent.VK_C);
		Cancel.setFont(MemoryGame.Display);
		Cancel.addActionListener(this);
		
		// Add components to the content pane
		pane.add(Ok);
		pane.add(Cancel);
		
		// Show the dialog
		this.setVisible(true);
	}


	public void actionPerformed(ActionEvent e) {
		
	}
}
