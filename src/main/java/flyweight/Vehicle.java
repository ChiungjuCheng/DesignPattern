package flyweight;

public class Vehicle {

	// immutable
	private Engine engine;

	// mutable
	private String moving;

	public Vehicle(Engine engine, String moving) {
		this.engine = engine;
		this.moving = moving;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public String getMoving() {
		return moving;
	}

	public void setMoving(String moving) {
		this.moving = moving;
	}

}
