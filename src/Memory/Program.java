package Memory;

import Memory.Debug.BugList;
import Memory.GUI.MainFrame;
import Memory.Game.Card;
import Memory.Game.Game;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.newdawn.easyogg.OggClip;

import javax.swing.*;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;

public class Program {
	// Shared font and data-set
	public static Font displayFont = new Font("Serif", Font.PLAIN, 20);
	
	public static URL getAsset(String name) {
		return Program.class.getClassLoader().getResource("Memory/assets/" + name);
	}
	public static InputStream getAssetAsStream(String name) {
		return Program.class.getClassLoader().getResourceAsStream("Memory/assets/" + name);
	}
	public static String readAsset(String name) throws IOException {
		return Resources.toString(Resources.getResource("Memory/assets/" + name), Charsets.UTF_8);
	}
	
	public static void main(String[] args) {
		try {
			// Load the JSON data files
			JSONObject
				imgMap = new JSONObject(Program.readAsset("_map.json")),
				bugList = new JSONObject(Program.readAsset("_bug.json"));
			
			// Populate a list of Cards with data from the file
			Card.Collection = new LinkedList<Card>();
			JSONArray objects = imgMap.getJSONArray("Objects");
			for (int i = 0; i < objects.length(); ++i) {
				JSONObject obj = objects.getJSONObject(i);
				Card.Collection.add(new Card(obj));
			}
			
			// Load other utility clips
			Card.Clips = new HashMap<String, OggClip>();
			Card.Clips.put("Start", Card.makeOGG("start.ogg"));
			Card.Clips.put("Eat", Card.makeOGG("eat.ogg"));
			Card.Clips.put("Burp", Card.makeOGG("burp.ogg"));
			
			BugList.Init(bugList);
		}
		catch (FileNotFoundException e) { dataError("File Not Found Error", e); }
		catch (JSONException e) { dataError("JSON Parse Error", e); }
		catch (IOException e) { dataError("File Read Error", e); }
		
		// Instantiate the GUI components
		new MainFrame();
		
		// Start a game
		new Game();
	}
	
	// jsonError - Report an error loading the data file to the user and close the program
	private static void dataError(String msg, Exception e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "This program cannot function without valid data files", msg, JOptionPane.ERROR_MESSAGE);
		System.exit(-1);
	}
}
