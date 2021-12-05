package flyweight;

public class SmallEngine extends Engine{

	
	public SmallEngine() {
		this.type = "Small";
	}
	
	@Override
	public String toString() {
		return "SmallEngine";
	}

}
