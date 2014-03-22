package Project1.Game.Modifiers;

public class Player {
	private int Wins, Score, Turns;
	private String Name;
	
	public Player(String n) {
		Name = n;
		Turns = 0;
		Score = 0;
		Wins = 0;
	}
	
	// Mutators
	public void giveTurn() { ++Turns; }
	public void giveWin() { ++Wins; }
	public void newGame() { Score = 0; Turns = 0; }
	public void givePoints(int p) { Score += p; }
	
	// Accessors
	public String getName() { return Name; }
	public int getTurns() { return Turns; }
	public int getScore() { return Score; }
	public int getWins() { return Wins; }
}
