package Memory.GUI.Helpers;

import Memory.Debug.Bug;
import Memory.Debug.BugList;
import Memory.Game.Card;
import org.newdawn.easyogg.OggClip;

import java.awt.*;
import javax.swing.*;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class SoundTester extends JFrame implements WindowListener {
	private static final int WIDTH = 555, HEIGHT = 940;
	private static final String TITLE = "Minecraft Memory - Sound Tester";
	private Container pane;
	private JPanel mobPanel, clipPanel;
	
	// Only allow single instance open
	private static SoundTester instance;
	public static SoundTester getThis() { return instance; }
	
	public SoundTester() {
		// Set JFrame properties
		this.setTitle(TITLE);
		this.setResizable(false);
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		// get content pane
		pane = this.getContentPane();
		pane.setLayout(new FlowLayout());
		
		// Create mob Panel
		mobPanel = new JPanel();
		mobPanel.setLayout(new GridLayout(0, 4));
		
		// Add the mob sounds to the testing window
		pane.add(new JLabel("Mob Sounds"));
		pane.add(mobPanel);
		
		// Add the sound buttons
		for (Card c : Card.Collection) {
			mobPanel.add(
				new JLabel(
					c.getID() + " - " +
					c.getImageName().substring(0, c.getImageName().indexOf('.')) + ": "
				)
			);
			mobPanel.add(
				new sButton(
					"Reveal",
					c.getRevealSound(),
					BugList.exists(new Bug("Sound", "Mob", c.getID().toString(), "Reveal"))
				)
			);
			mobPanel.add(
				new sButton(
					"MisMatch",
					c.getMisMatchSound(),
					BugList.exists(new Bug("Sound", "Mob", c.getID().toString(), "MisMatch"))
				)
			);
			mobPanel.add(
				new sButton(
					"Match",
					c.getMatchSound(),
					BugList.exists(new Bug("Sound", "Mob", c.getID().toString(), "Match"))
				)
			);
		}
		
		// Create clip panel
		clipPanel = new JPanel();
		clipPanel.setLayout(new GridLayout(1, 0));
		
		// Add the clip sounds to the testing window
		pane.add(new JLabel("Misc. Clips"));
		pane.add(clipPanel);
		
		// Add the sound buttons
		for (Map.Entry<String, OggClip> c : Card.Clips.entrySet()) {
			clipPanel.add(
				new sButton(
					c.getKey(),
					c.getValue(),
					BugList.exists(new Bug("Sound", "Clip", c.getKey()))
				)
			);
		}
		
		// Set the instance variable
		instance = this;
		
		// Show our new amazing window!
		this.setVisible(true);
	}
	
	private class sButton extends JButton implements ActionListener {
		private OggClip sound;
		
		public sButton(String text, OggClip toPlay, boolean bugged) {
			this.setText(text);
			this.sound = toPlay;
			this.addActionListener(this);
			
			// Set all missing sounds to red
			if (this.sound == null) {
				this.setBackground(Color.RED);
			}
			// And all bugged sounds to yellow
			else if(bugged) {
				this.setBackground(Color.YELLOW);
			}
		}
		
		// Play the sound if it exists
		public void actionPerformed(ActionEvent e) {
			if (sound != null) {
				sound.play();
			}
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
