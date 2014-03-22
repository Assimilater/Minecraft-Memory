package Project1.GUI;

import Project1.GUI.Helpers.GameHandbook;
import Project1.GUI.Helpers.SoundTester;
import Project1.GUI.Modifiers.OptionsDialog;
import Project1.Game.Game;
import Project1.MemoryGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener, WindowListener, HierarchyBoundsListener {
	// Frame-Related Members
	private static final int WIDTH = 1250, HEIGHT = 750, MIN_WIDTH = 500, MIN_HEIGHT = 450;
	private static final String TITLE = "Minecraft Memory";
	private Container pane;
	
	// MainFrame Component Members
	private JMenuBar MenuBar; JMenu GameMenu, HelpMenu;
	private JMenuItem NewGameMenuItem, OptionsMenuItem, ExitMenuItem, RulesMenuItem, SoundMenuItem, AboutMenuItem;
	
	// Only allow single instance open
	private static MainFrame instance;
	public static MainFrame get() { return instance; }
	
	public MainFrame() {
		// Set the instance variable
		instance = this;
		
		// Set standard JFrame properties
		pane = this.getContentPane();
		pane.addHierarchyBoundsListener(this);
		
		this.setTitle(TITLE);
		this.setSize(WIDTH, HEIGHT);
		this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.addWindowListener(this);
		
		// Initialize the menu and corresponding items
		NewGameMenuItem = new JMenuItem("New Game");
		NewGameMenuItem.addActionListener(this);
		NewGameMenuItem.setFont(MemoryGame.Display);
		NewGameMenuItem.setMnemonic(KeyEvent.VK_N);
		
		OptionsMenuItem = new JMenuItem("Options");
		OptionsMenuItem.addActionListener(this);
		OptionsMenuItem.setFont(MemoryGame.Display);
		OptionsMenuItem.setMnemonic(KeyEvent.VK_O);
		
		ExitMenuItem = new JMenuItem("Exit");
		ExitMenuItem.addActionListener(this);
		ExitMenuItem.setFont(MemoryGame.Display);
		ExitMenuItem.setMnemonic(KeyEvent.VK_X);
		
		RulesMenuItem = new JMenuItem("Rules - How to Play");
		RulesMenuItem.addActionListener(this);
		RulesMenuItem.setFont(MemoryGame.Display);
		RulesMenuItem.setMnemonic(KeyEvent.VK_R);
		
		SoundMenuItem = new JMenuItem("Sound Tester");
		SoundMenuItem.addActionListener(this);
		SoundMenuItem.setFont(MemoryGame.Display);
		SoundMenuItem.setMnemonic(KeyEvent.VK_S);
		
		AboutMenuItem = new JMenuItem("About");
		AboutMenuItem.addActionListener(this);
		AboutMenuItem.setFont(MemoryGame.Display);
		AboutMenuItem.setMnemonic(KeyEvent.VK_A);
		
		GameMenu = new JMenu("Game");
		GameMenu.setFont(MemoryGame.Display);
		GameMenu.add(NewGameMenuItem);
		GameMenu.add(OptionsMenuItem);
		GameMenu.add(ExitMenuItem);
		
		HelpMenu = new JMenu("Help");
		HelpMenu.setFont(MemoryGame.Display);
		HelpMenu.add(RulesMenuItem);
		HelpMenu.add(SoundMenuItem);
		HelpMenu.add(AboutMenuItem);
		
		MenuBar = new JMenuBar();
		MenuBar.setFont(MemoryGame.Display);
		MenuBar.add(GameMenu);
		MenuBar.add(HelpMenu);
		
		// Initialize the GamePanel
		new GamePanel();
		
		// Add the menu and GamePanel to the JFrame
		pane.add(MenuBar, BorderLayout.NORTH);
		pane.add(GamePanel.get(), BorderLayout.CENTER);
		
		// Show our new amazing window!
		this.setVisible(true);
	}
	
	// boolean confirmExit() show a confirmation if a game is active before closing the program
	private boolean confirmQuit(String title) {
		return 
			Game.get() == null ||
			JOptionPane.NO_OPTION != JOptionPane.showConfirmDialog(this, "Quit the Current Game?", "Confirm " + title, JOptionPane.YES_NO_OPTION);
	}
	
	// ActionListener
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == NewGameMenuItem) {
			if (confirmQuit("New Game")) {
				new Game();
			}
		}
		else if (e.getSource() == OptionsMenuItem) {
			new OptionsDialog();
		}
		else if (e.getSource() == ExitMenuItem) {
			if (confirmQuit("Exit")) {
				System.exit(0);
			}
		}
		else if (e.getSource() == RulesMenuItem) {
			if (GameHandbook.get() == null) {
				new GameHandbook();
			}
			else {
				GameHandbook.get().setVisible(true);
			}
		}
		else if (e.getSource() == SoundMenuItem) {
			if (SoundTester.get() == null) {
				new SoundTester();
			}
			else {
				SoundTester.get().setVisible(true);
			}
		}
		else if (e.getSource() == AboutMenuItem) {
			JOptionPane.showMessageDialog(this,
				"Minecraft Memory\n\n" +
					"Student Name:  John Call\n" +
					"Student A#:        A01283897\n" +
					"CS2410 ~ USU\n" +
					"\n" +
					"All Images and Sounds are\n" +
					"Copyrighted by MojangAB\n" +
					"\n" +
					"They can be found on:\n" +
					"minecraft.gamepedia.com",
				"About Minecraft Memory",
				JOptionPane.INFORMATION_MESSAGE
			);
		}
	}
	
	// WindowListener
	public void windowClosing(WindowEvent e) {
		if (Game.get() == null) {
			System.exit(0);
		}
		else if (confirmQuit("Exit")) {
			System.exit(0);
		}
	}
	// Unused WindowListener methods
	public void windowClosed(WindowEvent e) { }
	public void windowIconified(WindowEvent e) { }
	public void windowDeiconified(WindowEvent e) { }
	public void windowActivated(WindowEvent e) { }
	public void windowOpened(WindowEvent e) { }
	public void windowDeactivated(WindowEvent e) { }
	
	// HierarchyBoundsListener
	public void ancestorResized(HierarchyEvent e) {
		if (GamePanel.get() != null) {
			GamePanel.get().resizePanel();
		}
	}
	public void ancestorMoved(HierarchyEvent e) { }
}
