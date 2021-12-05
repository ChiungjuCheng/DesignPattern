package flyweight;

import java.util.ArrayList;
import java.util.List;

public class FlyweightClient {
	
	public static void main(String[] args) {
		
		// 測試operation
//		VehicleContext vehicleContext = new VehicleContext(new BigEngine(),"1");
//		vehicleContext.operation();
		
		List<VehicleContext> vehicleContextList = new ArrayList<>();
		// 測試heap Heap test 30000000
		long beforeUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		
		for(int i = 0; i < 50000000; i++) {
			vehicleContextList.add(new VehicleContext(new BigEngine(),"1"));
		}
		
		long afterUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		
		long actualMemUsed = afterUsedMem-beforeUsedMem;
		System.out.println( actualMemUsed); // byte
		
		
	}
}
