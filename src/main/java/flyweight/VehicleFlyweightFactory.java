package flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleFlyweightFactory {
	
	/**
	 * 以會重複的intrinsic作為key
	 */
	static Map<Engine, VehicleFlyweight> engineTypes = new ConcurrentHashMap<>();
	
	public static VehicleFlyweight  getVehicleFlyweight(Engine engine) {
		VehicleFlyweight vehicleFlyweight = engineTypes.get(engine);
		
		if(vehicleFlyweight == null) {
			vehicleFlyweight = new VehicleFlyweight(engine);
			engineTypes.put(engine,vehicleFlyweight);
		}
		
		return vehicleFlyweight;
	}
	
}
