package Memory.Game;

import Memory.Game.Modifiers.Matching;
import Memory.Program;
import org.json.JSONException;
import org.json.JSONObject;
import org.newdawn.easyogg.OggClip;

import javax.swing.ImageIcon;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class Card {
	// Global Constants
	public static final int HEROBRINE = 31, CREEPER = 16;
	
	// Static Utilities used globally
	public static Map<String, OggClip> Clips;
	public static OggClip makeOGG(String name) throws IOException{
		return new OggClip(
			Program.getAssetAsStream(
				"ogg/" + name
			)
		);
	}
	
	// Static Utilities populated by Program.main()
	public static LinkedList<Card> Collection;
	public static Card getByID(int id) {
		// Realistically this could probably just return Collection.get(id) so long as id < Collection.size() ...
		// But ... this removes the requirement that the JSON data file be ordered perfectly, so why not? (though it is anyways...)
		for (Card c : Collection) {
			if (c.getID() == id) {
				return c;
			}
		}
		return null;
	}

	/***  Non-Static
	 *     Members
	 *     Methods
	 */
	
	// Card Properties
	private int ID, Points;
	private String sImage, sMatch, sMisMatch, sReveal;
	
	// Card Data-Object Instances
	OggClip MatchSound, MisMatchSound, RevealSound;
	ImageIcon Image;
	
	/*** JSON Format
	 * {
	 *   "id": x,
	 *   "points": y,
	 *   "src": "*.png",
	 *   "match": "[*.ogg]",
	 *   "mismatch": "[*.ogg]",
	 *   "reveal": "[*.ogg]"
	 * }
	 */
	
	public Card(JSONObject data) throws JSONException, IOException {
		// Populate object properties
		ID = data.getInt("id");
		Points = data.getInt("points");
		sImage = data.getString("src");
		sMatch =  data.getString("match");
		sMisMatch = data.getString("mismatch");
		sReveal = data.getString("reveal");
		
		MatchSound = sMatch.equals("") ? null : makeOGG("mob/" + sMatch);
		MisMatchSound = sMisMatch.equals("") ? null : makeOGG("mob/" + sMisMatch);
		RevealSound = sReveal.equals("") ? null : makeOGG("mob/" + sReveal);
		
		Image = new ImageIcon(Program.getAsset("img/mob/" + sImage));
	}
	
	// Accessors
	public Integer getID() { return ID; }
	public Integer getPoints() { return Points; }
	public ImageIcon getImage() { return Image; }
	public String getImageName() { return sImage; }
	public OggClip getRevealSound() { return RevealSound; }
	public OggClip getMatchSound() { return MatchSound; }
	public OggClip getMisMatchSound() { return MisMatchSound; }
	public void playSound(Matching type) {
		if (type == Matching.Reveal && RevealSound != null) {
			RevealSound.play();
		}
		else if (type == Matching.Match && MatchSound != null) {
			MatchSound.play();
		}
		else if (type == Matching.MisMatch && MisMatchSound != null) {
			MisMatchSound.play();
		}
	}
}
