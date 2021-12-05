package flyweight;

/**
 * 拆成兩個部份的Vehicle
 * @author user
 *
 */
public class VehicleContext {
	
	private String moving;
	
	private VehicleFlyweight vehicleFlyweight;
	
	public VehicleContext(Engine engine, String moving) {
		this.vehicleFlyweight = VehicleFlyweightFactory.getVehicleFlyweight(engine);
		this.moving = moving;
	}
	
	public void operation() {
		vehicleFlyweight.operation(moving);
	}
	
}
