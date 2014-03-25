package Memory.GUI.Modifiers;

import Memory.GUI.MainFrame;
import Memory.Game.Game;
import Memory.Game.Modifiers.GameSize;
import Memory.Game.Modifiers.Options;
import Memory.Program;

import javax.swing.*;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsDialog extends JDialog implements ActionListener {
	// GUI-related members
	private static final int WIDTH = 325, HEIGHT = 180;
	private static final String TITLE = "Minecraft Memory Options";
	private Container pane;
	
	// GUI components
	private static final int RADIO_WIDTH = 200, RADIO_HEIGHT = 20;
	JRadioButton R6, R7, R8;
	JButton Ok, Cancel;
	JLabel LblSize;
	
	public OptionsDialog() {
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
		LblSize = new JLabel("Game Size:");
		LblSize.setBounds(5, 5, 200, 15);
		LblSize.setFont(Program.Display);
		
		R6 = new JRadioButton("6x6 (36 Cards)");
		R6.setBounds(10, 30, RADIO_WIDTH, RADIO_HEIGHT);
		R6.setMnemonic(KeyEvent.VK_6);
		R6.setFont(Program.Display);
		
		R7 = new JRadioButton("7x7 (49 Cards)");
		R7.setBounds(10, 55, RADIO_WIDTH, RADIO_HEIGHT);
		R7.setMnemonic(KeyEvent.VK_4);
		R7.setFont(Program.Display);
		
		R8 = new JRadioButton("8x8 (64 Cards)");
		R8.setBounds(10, 80, RADIO_WIDTH, RADIO_HEIGHT);
		R8.setMnemonic(KeyEvent.VK_8);
		R8.setFont(Program.Display);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(R6);
		radioGroup.add(R7);
		radioGroup.add(R8);
		
		Ok = new JButton("Ok");
		Ok.setBounds(5, HEIGHT - 68, (WIDTH - 20) / 2, 30);
		Ok.setMnemonic(KeyEvent.VK_O);
		Ok.setFont(Program.Display);
		Ok.addActionListener(this);
		
		Cancel = new JButton("Cancel");
		Cancel.setBounds(10 + ((WIDTH - 20) / 2), HEIGHT - 68, (WIDTH - 20) / 2, 30);
		Cancel.setMnemonic(KeyEvent.VK_C);
		Cancel.setFont(Program.Display);
		Cancel.addActionListener(this);
		
		// Add components to the content pane
		pane.add(LblSize);
		
		pane.add(R6);
		pane.add(R7);
		pane.add(R8);
		
		pane.add(Ok);
		pane.add(Cancel);
		
		// Show the dialog
		resetArgs();
		this.setVisible(true);
	}
	
	// {update|reset}Args() - make sure the GUI reflects the appropriate settings
	private void resetArgs() {
		if (Options.Size == GameSize.C8) {
			R8.setSelected(true);
		}
		else if (Options.Size == GameSize.C7) {
			R7.setSelected(true);
		}
		else {
			R6.setSelected(true);
		}
	}
	private void updateArgs() {
		GameSize newSize;
		if (R8.isSelected()) {
			newSize = GameSize.C8;
		}
		else if (R7.isSelected()) {
			newSize = GameSize.C7;
		}
		else {
			newSize = GameSize.C6;
		}
		
		// Check if settings have changed and there is still a game active
		if (Game.get() != null) {
			if (Options.Size != newSize) {
				int Confirmation = JOptionPane.showConfirmDialog(this,
					"Changes will not affect the current game. Quit the current game and start a new one?",
					"Options Confirmation",
					JOptionPane.YES_NO_CANCEL_OPTION
				);
				
				if (JOptionPane.NO_OPTION == Confirmation) {
					Options.Size = newSize;
				}
				else if (JOptionPane.YES_OPTION == Confirmation) {
					Options.Size = newSize;
					new Game();
				}
			}
		}
		else {
			Options.Size = newSize;
			int Confirmation = JOptionPane.showConfirmDialog(this, "Start a new game?", "Options Confirmation", JOptionPane.YES_NO_OPTION);
			
			if (JOptionPane.YES_OPTION == Confirmation) {
				new Game();
			}
		}
	}
	
	// ActionListener
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Cancel) {
			this.dispose();
		}
		else if (e.getSource() == Ok) {
			updateArgs();
			this.dispose();
		}
	}
}
