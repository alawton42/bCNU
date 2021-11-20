package districts.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import cnuphys.bCNU.util.Environment;
import districts.maps.Map;

public class JSONSupport {
	
	//shared json parser
	private static JSONParser _jsonParser;
	
	public static void processMapFile(String mapPath) throws FileNotFoundException {
		File file = new File(mapPath);
		
		if (!file.exists()) {
			throw new FileNotFoundException("Could not find map file: [" + mapPath + "]");
		}
		
		System.out.println("Found map file [" + mapPath + "]");
		
		JSONParser jsonParser = getJSONParser();

		int i = 0;

		try {
			JSONArray a = (JSONArray) jsonParser.parse(new FileReader(file.getPath()));
			Iterator<JSONObject> iterator = a.iterator();
			
			while (iterator.hasNext()) {
				JSONObject jobj = iterator.next();

				
				if (i == 0) {
					Map map = new Map(jobj);
					System.out.println(map.toString());
					i++;
				}
			}
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Get the shared parser
	 * @return the JSON parser
	 */
	public static JSONParser getJSONParser() {
		if (_jsonParser == null) {
			_jsonParser = new JSONParser();
		}
		return _jsonParser;
	}
	
	/**
	 * Get a string value from a json object
	 * @param jobj the json object
	 * @param key the key (field name)
	 * @return a string value
	 */
	public static String getString(JSONObject jobj, String key) {
		String value = (String) jobj.get(key);
		return value;
	}
	
	/**
	 * Get a double value from a json object
	 * @param jobj the json object
	 * @param key the key (field name)
	 * @return a double value
	 */
	public static double getDouble(JSONObject jobj, String key) {
		String value = getString(jobj, key);
		return Double.parseDouble(value);
	}

	/***
	 * main method for testing
	 * @param args the command line arguments (ignored)
	 */
	public static void main(String[] args) {
		Environment env = Environment.getInstance();

		System.out.println("Testing the JSON support.");
		System.out.println(env);
		
		
		//try to read the VA County maps
		String mapPath = env.getCurrentWorkingDirectory() + File.separator + 
				"data" + File.separator + "VACountyBoundaries.json";
		try {
			processMapFile(mapPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
}