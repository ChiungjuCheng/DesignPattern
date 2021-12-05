package flyweight;

public class BigEngine extends Engine {
	
	public BigEngine() {
		this.type = "Big";
	}
	
	@Override
	public String toString() {
		return "BigEngine";
	}
	
}
