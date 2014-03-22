package Project1.GUI;

import Project1.Game.*;
import Project1.Game.Modifiers.Matching;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class CardButton extends JButton implements ActionListener {
	private Card Child;
	private ImageIcon back;
	private Timer selfTimer;
	private ImageIcon current;
	
	public CardButton(Card v) {
		Child = v;
		back = new ImageIcon("src/Project1/img/back.jpg");
		selfTimer = new Timer((int) TimeUnit.SECONDS.toMillis(1), this);
		
		this.addActionListener(this);
		this.setContentAreaFilled(false);
		this.setText("");
		this.faceDown();
	}
	
	// Accessors
	public Card getCard() { return Child; }
	
	// clear - Called in the event of a matching set revealed, clears the image and makes the button un-clickable
	public void clear() {
		Child = null;
		this.setIcon(null);
		current = null;
	}
	
	// faceDown - switch the image to the back-facing card
	public void faceDown() {
		this.setIcon(back);
		
		current = back;
	}
	
	// faceUp - on a click, switch the image to the front-facing card and perform any necessary logical operations
	public void faceUp() {
		// Make sure this isn't just a place-holder
		if (this.Child == null) { return; }
		if (Game.get().TimerRunning) { return; }
		
		// Show the face
		this.setIcon(Child.getImage());
		current = Child.getImage();
		
		// Determine the type of match
		Matching MatchType;
		if (Game.get().FlippedCard == null) {
			MatchType = Matching.Reveal;
		}
		else if (Game.get().FlippedCard != this) {
			if (Game.get().FlippedCard.getCard() == this.getCard()) {
				MatchType = Matching.Match;
			}
			else {
				MatchType = Matching.MisMatch;
			}
		}
		else {
			// They clicked the same card twice so do nothing
			return;
		}
		
		// Play the appropriate sound
		Child.playSound(MatchType);
		
		// Give points if appropriate
		if(MatchType == Matching.Reveal) {
			Game.get().FlippedCard = this;
		}
		else {
			if (MatchType == Matching.Match) {
				Game.get().thisTurn.givePoints(Child.getPoints());
				GamePanel.get().updateLabels();
			}
			
			// Increase the turn count for the given player
			Game.get().thisTurn.giveTurn();
			
			// Set a timer to trigger the next event so the frame will render
			selfTimer.start();
			Game.get().TimerRunning = true;
		}
	}
	
	// ActionListener
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this) {
			this.faceUp();
		}
		else {
			// the faceUp() method has a delay so the user can see the card, then this code block executes
			selfTimer.stop();
			Game.get().TimerRunning = false;
			
			// Keep track if Herobrine was revealed before removing stuff
			boolean needShuffle =
				Child.getID() == Card.HEROBRINE ||
				Game.get().FlippedCard.Child.getID() == Card.HEROBRINE;
			
			// Keep track if the user goes again because of a match
			boolean goAgain = false;
			
			// Handle the FlippedCard property appropriately
			if (Game.get().FlippedCard.getCard() == this.getCard()) {
				// Got a match, go again
				goAgain = true;
				
				// Check consecutive point bonus
				if (Game.get().ConsecutiveRun > 0) {
					Game.get().thisTurn.givePoints(1);
				}
				
				// Increase consecutive increment
				Game.get().ConsecutiveRun++;
				
				// Remove matched cards from the game
				Game.get().FlippedCard.clear();
				this.clear();
			}
			else {
				// Reset consecutive count
				Game.get().ConsecutiveRun = 0;
				
				// Flip cards back over
				Game.get().FlippedCard.faceDown();
				this.faceDown();
			}
			Game.get().FlippedCard = null;
			
			// Shuffle if Herobrine was revealed
			if (needShuffle) {
				Game.get().shuffle();
			}
			
			if (!Game.get().gameOver()) {
				if (!goAgain) {
					// Change the player's turn
					Game.get().thisTurn =
						Game.get().thisTurn == Game.get().Player2
							? Game.get().Player1
							: Game.get().Player2;
				}
			}
			
			// Tell the GamePanel to update
			GamePanel.get().updateLabels();
		}
	}
	
	// Scale the icon image
	public void resize() { this.setIcon(current); }
	
	// Auto-scale an image and set the button's icon to it
	public void setIcon(ImageIcon Image) {
		// Exit early
		if (Image == null) {
			super.setIcon(null);
			return;
		}
		
		// Scale the image and add it to the control
		int MaxWidth = 100, MaxHeight = 100, Width = Image.getIconWidth(), Height = Image.getIconHeight();
		
		// If the window is ready update MaxWidth and MaxHeight to the right values
		if (this.getWidth() != 0 && this.getHeight() != 0) {
			MaxWidth = this.getWidth();
			MaxHeight = this.getHeight();
		}
		
		// Scale Width's aspect ratio to fit in the MaxDimension
		if (MaxWidth < Width) {
			Width = MaxWidth;
			Height = Width * Image.getIconHeight() / Image.getIconWidth();
		}
		if (MaxHeight < Height) {
			Height = MaxHeight;
			Width = Height * Image.getIconWidth() / Image.getIconHeight();
		}
		
		super.setIcon(new ImageIcon(
				Image.getImage()
					.getScaledInstance(Width, Height, java.awt.Image.SCALE_SMOOTH)
			)
		);
		
		MainFrame.get().repaint();
	}
}
