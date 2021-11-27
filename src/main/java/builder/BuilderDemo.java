package builder;

public class BuilderDemo {


	public static void main(String[] args) {
		// before
		Weat weat = new Weat();
		TastyBeer tastyBeer = new TastyBeer("Taiwain", 550, weat, 20, 6000);
		
		// using builder
		Builder builder = new TastyBearBuilder();
		builder.setFermentation(1000);
		builder.setWheat(new Weat());
		builder.setName("1664");
		TastyBeer tastyBeerByBuilder = builder.getResult();
		
		// using builder with director
		Builder builder2 = new TastyBearBuilder();
		Director director = new Director();
		
		TastyBeer tastyBeer2  =builder2.getResult();

	}

}

// 美味的啤酒
class TastyBeer {

	private String name;

	private int volume;

	private Weat wheat;

	private int hops;

	private int fermentation;

	public TastyBeer(String name, int volume, Weat wheat, int hops, int fermentation) {
		this.name = name;
		this.volume = volume;
		this.wheat = wheat;
		this.hops = hops;
		this.fermentation = fermentation;
	}

}

// 小麥
class Weat {
	private String breed;
	private String quality;
	
	public Weat() {}
	
	
	public Weat(String breed, String quality) {
		super();
		this.breed = breed;
		this.quality = quality;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
		
	
}

class TastyBearBuilder implements Builder{
	private String name;

	private int volume;

	private Weat wheat;

	private int hops;

	private int fermentation;

	public TastyBearBuilder() {

	}

	public void setName(String name) {
		this.name = name;

	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public void setWheat(Weat wheat) {
		this.wheat = wheat;
	}

	public void setHops(int hops) {
		this.hops = hops;
	}

	public void setFermentation(int fermentation) {
		this.fermentation = fermentation;
	}

	public TastyBeer getResult() {
		return new TastyBeer(name, volume, wheat, hops, fermentation);
	}
}

interface Builder {
	public void setName(String name);

	public void setVolume(int volume);

	public void setWheat(Weat wheat);

	public void setHops(int hops);

	public void setFermentation(int fermentation);

	public TastyBeer getResult();

}

class Director{

	public void constructorBear(Builder builder) {
		builder.setFermentation(10);
		builder.setHops(20);
		builder.setName("green label");
		builder.setWheat(new Weat());
		builder.getResult();
	} 
}