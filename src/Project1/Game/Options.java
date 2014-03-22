package Project1.Game;

public class Options {
	// Members pertaining to the board size, set by OptionsDialog
	public static GameSize Size = GameSize.C6;
	public static int getGameSize() { return toInt(Size); }
	public static int toInt(GameSize a) {
		switch (a) {
			case C8:
				return 8;
			case C7:
				return 7;
			default:
				return 6;
		}
	}
	
	// Members pertaining to the Players, set by PlayersDialog
	public static Player Player1 = new Player("Player 1"), Player2 = new Player("Player 2");
}
