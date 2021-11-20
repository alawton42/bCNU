package districts.maps.drawing;

import cnuphys.bCNU.item.PathBasedItem;
import cnuphys.bCNU.layer.LogicalLayer;
import districts.maps.Map;

public class MapItem extends PathBasedItem {
	
	//the underlying map
	private Map _map;

	public MapItem(LogicalLayer layer) {
		super(layer);
	}

}
