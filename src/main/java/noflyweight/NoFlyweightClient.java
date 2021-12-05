package noflyweight;

import java.util.ArrayList;
import java.util.List;

public class NoFlyweightClient {

	public static void main(String[] args) {
		
		List<Vehicle> vehicleList = new ArrayList<>();
		
		// operation test
//		vehicleList.add(new Vehicle(new BigEngine(), String.valueOf(1)));
//		vehicleList.forEach(Vehicle::operation);
		
		// Heap test 30000000 is fine.
		long beforeUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		
		for(int i = 0; i < 50000000; i++) {
			vehicleList.add(new Vehicle(new BigEngine(), "1"));
		}
		
		long afterUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		
		long actualMemUsed = afterUsedMem-beforeUsedMem;
		System.out.println( actualMemUsed); // byte
		
	}
}
