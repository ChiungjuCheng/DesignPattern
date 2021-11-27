package flyweight;

import java.util.ArrayList;
import java.util.List;

public class NoFlyweightClient {

	public static void main(String[] args) {
		long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		
		List<Vehicle> vehicleList = new ArrayList<>();
		
		for(int i = 0; i < 1000000; i++) {
			vehicleList.add(new Vehicle(new BigEngine(), String.valueOf(i)));
		}
		long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		
		long actualMemUsed = afterUsedMem-beforeUsedMem;
		System.out.println( actualMemUsed); // byte
		
	}
}
