package districts.maps;

import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import districts.json.JSONSupport;

public class Map {
	
	//id of the map data set
	private String _dataSetId;
	
	//record time stamp
	private String _timeStamp;
	
	//an internal latitude
	private double _internalLat;
	
	//an internal longitude
	private double _internalLon;
	
	//base name
	private String _name;
	
	//name with "county" or "city"
	private String _namelsad;
	
	//state name
	public String _stateName;

	public Map(JSONObject jobj) {
		
		Set keys = jobj.keySet();
		Iterator iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = (String)iterator.next();
			
			Object obj = jobj.get(key);
			String className = obj.getClass().getName();
			
			System.out.println(String.format("[%s] [%s]", key, className));
		}
		
		
		_dataSetId = JSONSupport.getString(jobj, "datasetid");
		_timeStamp = JSONSupport.getString(jobj, "record_timestamp");
		
		JSONObject fields = (JSONObject) jobj.get("fields");
		parseFields(fields);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("name: %s\n", _name));
		sb.append(String.format("namelsad: %s\n", _namelsad));
		sb.append(String.format("state name %s\n", _stateName));
		sb.append(String.format("data set id: %s\n", _dataSetId));
		sb.append(String.format("time stamp: %s\n", _timeStamp));
		sb.append(String.format("internal lat, lon: (%9.5f, %9.5f)\n", _internalLat, _internalLon));
		return sb.toString();
		
	}
	
	//parse the fields json object from the main object
	private void parseFields(JSONObject jobj) {
				
		Set keys = jobj.keySet();
		Iterator iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = (String)iterator.next();
			
			Object obj = jobj.get(key);
			String className = obj.getClass().getName();
			
			System.out.println(String.format("   [%s] [%s]", key, className));
		}
	
		
		_name = JSONSupport.getString(jobj, "name");
		_namelsad = JSONSupport.getString(jobj, "namelsad");
		_stateName = JSONSupport.getString(jobj, "state_name");

		_internalLat = JSONSupport.getDouble(jobj, "intptlat");
		_internalLon = JSONSupport.getDouble(jobj, "intptlon");
	}
	

}
