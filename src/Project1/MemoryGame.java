package Project1;

import Project1.Debug.BugList;
import Project1.GUI.MainFrame;
import Project1.Game.Card;
import Project1.Game.Game;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.newdawn.easyogg.OggClip;

import javax.swing.*;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

public class MemoryGame {
	// Shared font and data-set
	public static Font Display = new Font("Serif", Font.PLAIN, 20);
	
	public static void main(String[] args) {
		try {
			// Load the JSON data files
			JSONObject
				imgMap = new JSONObject(new String(Files.readAllBytes(Paths.get("src/Project1/img/_map.json")))),
				bugList = new JSONObject(new String(Files.readAllBytes(Paths.get("src/Project1/img/_bug.json"))));
			
			// Populate a list of Cards with data from the file
			Card.Collection = new LinkedList<Card>();
			JSONArray objects = imgMap.getJSONArray("Objects");
			for (int i = 0; i < objects.length(); ++i) {
				JSONObject obj = objects.getJSONObject(i);
				Card.Collection.add(new Card(obj));
			}
			
			// Load other utility clips
			Card.Clips = new HashMap<String, OggClip>();
			Card.Clips.put("Start", Card.makeOGG("ogg/start.ogg"));
			Card.Clips.put("Eat", Card.makeOGG("ogg/eat.ogg"));
			Card.Clips.put("Burp", Card.makeOGG("ogg/burp.ogg"));
			
			BugList.Init(bugList);
		}
		catch (FileNotFoundException e) { dataError("File Not Found Error", e); }
		catch (IOException e) { dataError("File Read Error", e); }
		catch (JSONException e) { dataError("JSON Parse Error", e); }
		
		// Instantiate the GUI components
		new MainFrame();
		
		// Start a game
		new Game();
	}
	
	// jsonError - Report an error loading the data file to the user and close the program
	private static void dataError(String msg, Exception e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "This program cannot function without a valid data files", msg, JOptionPane.ERROR_MESSAGE);
		System.exit(-1);
	}
}
