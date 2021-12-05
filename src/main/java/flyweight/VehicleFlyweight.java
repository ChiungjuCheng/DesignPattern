package flyweight;

public class VehicleFlyweight {
	
	private Engine engine;
	
	/**
	 * 
	 * @param engine intrinsic state
	 */
	public VehicleFlyweight(Engine engine) {
		this.engine = engine;
	}
	
	public void operation(String moving) {
		System.out.println("Vehicle with "+ engine + " moves "+ moving + " steps.");
	}

}
