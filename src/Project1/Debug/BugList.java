package Project1.Debug;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;

public class BugList {
	private static LinkedList<Bug> Bugs;
	public static void Init(JSONObject obj) throws JSONException {
		Bugs = new LinkedList<Bug>();
		Iterator<?> Types = obj.keys();
		
		// Loop through each Type
		while(Types.hasNext()){
			String Type = (String) Types.next();
			if(obj.get(Type) instanceof JSONObject){
				JSONObject typeObj = obj.getJSONObject(Type);
				
				// Loop through each Category
				Iterator<?> Categories = typeObj.keys();
				while (Categories.hasNext()) {
					String Category = (String) Categories.next();
					if (typeObj.get(Category) instanceof JSONArray) {
						JSONArray bugList = typeObj.getJSONArray(Category);
						
						// Loop through each bug
						for (int i = 0; i < bugList.length(); ++i) {
							JSONObject bug = bugList.getJSONObject(i);
							
							// Add appropriately
							if (bug.has("value")) {
								Bugs.add(
									new Bug(
										Type,
										Category,
										bug.getString("id"),
										bug.getString("value")
									)
								);
							}
							else {
								Bugs.add(
									new Bug(
										Type,
										Category,
										bug.getString("id")
									)
								);
							}
						}
					}
				}
			}
		}
	}
	public static boolean exists(Bug bug) {
		for (Bug b : Bugs) {
			if (b.equals(bug)) { return true; }
		}
		return false;
	}
}
