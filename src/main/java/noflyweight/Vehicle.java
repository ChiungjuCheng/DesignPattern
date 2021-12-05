package noflyweight;

/**
 * @author user
 *
 */
public class Vehicle {

	// immutable
	private Engine engine;

	// mutable
	private String moving;

	// 也能用simple factory 產生特定的Engine
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
	
	public void operation() {
		System.out.println(engine + " moves "+moving);
	}

}
