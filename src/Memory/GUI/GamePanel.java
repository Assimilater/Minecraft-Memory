package Memory.GUI;

import Memory.Game.Game;
import Memory.Program;

import javax.swing.*;

public class GamePanel extends JSplitPane {
	// Constants
	private static final ImageIcon PlayerImage =
		new ImageIcon(
			new ImageIcon("src/Memory/img/Player.png")
				.getImage()
				.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)
		);
	
	// GUI Components for showing the score
	private JPanel Scores, Cards;
	private JLabel
		P1_Name, P2_Name,
		P1_Score, P2_Score,
		P1_Turns, P2_Turns,
		P1_Wins, P2_Wins,
		Consecutive,
		ImgPanel;
	
	// Statically track the instantiated GamePanel
	private static GamePanel instance;
	public static GamePanel get() { return instance; }
	
	public GamePanel() {
		// Set the instance variable
		instance = this;
		
		// Use fixed sizing on the split pane
		this.setDividerLocation(new Integer(225));
		this.setResizeWeight(0);
		this.setDividerSize(0);
		this.setEnabled(false);
		
		// Initialize panels
		Scores = new JPanel();
		Scores.setLayout(null);
		this.setLeftComponent(Scores);
		
		Cards = new JPanel();
		Cards.setLayout(null);
		this.setRightComponent(Cards);
		
		// Initialize player's turn Image
		ImgPanel = new JLabel(PlayerImage);
		ImgPanel.setVisible(false);
		ImgPanel.setSize(50, 100);
		
		// Initialize consecutive count labels
		Consecutive = new JLabel();
		Consecutive.setFont(Program.Display);
		Consecutive.setSize(190, 45);
		
		// Initialize Player 1 Labels
		P1_Name = new JLabel();
		P1_Name.setFont(Program.Display);
		P1_Name.setLocation(60, 5);
		P1_Name.setSize(160, 20);
		
		P1_Score = new JLabel();
		P1_Score.setFont(Program.Display);
		P1_Score.setLocation(60, 30);
		P1_Score.setSize(160, 20);
		
		P1_Turns = new JLabel();
		P1_Turns.setFont(Program.Display);
		P1_Turns.setLocation(60, 55);
		P1_Turns.setSize(160, 20);
		
		P1_Wins = new JLabel();
		P1_Wins.setFont(Program.Display);
		P1_Wins.setLocation(60, 80);
		P1_Wins.setSize(160, 20);
		
		// Initialize Player 2 Labels
		P2_Name = new JLabel();
		P2_Name.setFont(Program.Display);
		P2_Name.setSize(160, 20);
		
		P2_Score = new JLabel();
		P2_Score.setFont(Program.Display);
		P2_Score.setSize(160, 20);
		
		P2_Turns = new JLabel();
		P2_Turns.setFont(Program.Display);
		P2_Turns.setSize(160, 20);
		
		P2_Wins = new JLabel();
		P2_Wins.setFont(Program.Display);
		P2_Wins.setSize(160, 20);
		
		// Add objects to Scores panel
		Scores.add(Consecutive);
		Scores.add(ImgPanel);
		
		Scores.add(P1_Name);
		Scores.add(P1_Score);
		Scores.add(P1_Wins);
		Scores.add(P1_Turns);
		
		Scores.add(P2_Name);
		Scores.add(P2_Score);
		Scores.add(P2_Wins);
		Scores.add(P2_Turns);
	}
	
	// newGame() - Update the labels for player names
	public void newGame() {
		// Set the label's text that represent the player names
		P1_Name.setText(Game.get().Player1.getName());
		P2_Name.setText(Game.get().Player2.getName());
		
		// Clear the Cards JPanel
		this.clearBoard();
		
		// Add the new buttons to the Cards JPanel
		final int SIZE = Game.get().BoardSize;
		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				Cards.add(Game.get().Board[i][j]);
			}
		}
		
		// Show who's turn it is and make final touches on bounding
		ImgPanel.setVisible(true);
		this.resizePanel();
	}
	
	// updateLabels - Update the labels score, win count, turn count, and also the turn identifying image
	public void updateLabels() {
		if (Game.get() == null) { return; }
		
		// Consecutive Matches
		Consecutive.setText(
			"<html><div style=\"color: blue; font-weight: bold;\">" +
				"Consecutive<br/>" +
				"Match Bonus: " +Game.get().ConsecutiveRun +
			"</div></html>"
		);
		
		// Turns
		P1_Turns.setText("Turns Taken: " + Game.get().Player1.getTurns());
		P2_Turns.setText("Turns Taken: " + Game.get().Player2.getTurns());
		
		// Scores
		P1_Score.setText("Points: " + Game.get().Player1.getScore());
		P2_Score.setText("Points: " + Game.get().Player2.getScore());
		
		// Wins
		P1_Wins.setText("Games Won: " + Game.get().Player1.getWins());
		P2_Wins.setText("Games Won: " + Game.get().Player2.getWins());
		
		// Turn
		if (Game.get().thisTurn == Game.get().Player1) {
			ImgPanel.setLocation(5, 5);
		}
		else {
			ImgPanel.setLocation(5, this.getHeight() - 105);
		}
		
		MainFrame.get().repaint();
	}
	
	// drawBoard - Resize and position all CardButton objects in the Cards panel
	public void drawBoard() {
		if (Game.get() == null || Game.get().Board == null) { return; }
		
		final int PADDING = 5, SIZE = Game.get().BoardSize;
		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				Game.get().Board[i][j].setBounds(
					(j * ((Cards.getWidth() - PADDING) / SIZE)) + PADDING,		// x
					(i * ((Cards.getHeight() - PADDING) / SIZE)) + PADDING,		// y
					((Cards.getWidth() - PADDING) / SIZE) - PADDING,			// width
					((Cards.getHeight() - PADDING) / SIZE) - PADDING			// height
				);
				Game.get().Board[i][j].resize();
			}
		}
		
		MainFrame.get().repaint();
	}
	
	// resizePanel - Update the position of key labels and call other redraw methods
	public void resizePanel() {
		this.updateLabels();
		this.drawBoard();
		
		// Update the position of the consecutive match label
		Consecutive.setLocation(5, (Scores.getHeight() - Consecutive.getHeight()) / 2);
		
		// Update the position of Player 2 labels
		P2_Name.setLocation(60, Scores.getHeight() - 105);
		P2_Score.setLocation(60, Scores.getHeight() - 80);
		P2_Turns.setLocation(60, Scores.getHeight() - 55);
		P2_Wins.setLocation(60, Scores.getHeight() - 30);
		
		MainFrame.get().repaint();
	}
	
	// clearBoard - Remove added components before starting a new game
	public void clearBoard() {
		Cards.removeAll();
		ImgPanel.setVisible(false);
		MainFrame.get().repaint();
	}
}
