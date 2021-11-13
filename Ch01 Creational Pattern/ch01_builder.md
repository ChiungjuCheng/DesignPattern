# 建造者模式 (Builder pattern)

讓設計師一步一步的創立複雜的物件

### 問題
1. 當有個物件 (product) 在建立時，有很多的條件需要設定，導致constructor擁有過多的參數，這樣子讓使用者在創立該物件的時候，會搞不清楚第幾個參數該放哪個相對應的變數，這方面也違反了clean code內提到的函示參數。
2. 在java中，方法都可以overloading，因此有時在創造物件時，在不同條件下需要被設定的實體變數的數量會不一樣，而需要宣告多個constructor。
3. 當創立物件非常複雜時，例如對某個物件具有依賴性，也會導致使用者的本身的程式碼與創立物件的過程交雜在一起。

例如在製作一瓶美味的啤酒時，我們需要決定他的名字、容量、小麥品種、啤酒花的數量和發酵時間等等。

```java

class TastyBeer {

   	private String name;

	private int volume;

	private Weat wheat;

	private int hops;

	private int fermentation;

    public TastyBeer(String name,int volume,String wheat,int hops,int fermentation){
        // Assigning to instance variables
    }

    // using default value
    public TastyBeer(String name){
        TastyBeer(name,20,new Weat(),2000);
    }

    // ...some getter and setter methods

}

class Weat {
	private String breed;
	private String quality;
	
	public Weat() {}

	// ...some getter and setter methods	
}


// Creating the object will be tough
public class BuliderDemo {

	public static void main(String[] args) {
		// before
		TastyBeer tastyBeer = new TastyBeer("Taiwain", 550, "A", 20, 6000);
		
	}

}


```

### 解決方式- Builder
將創立的過程從自身的class抽離出來，讓別的類別 (builder)來幫忙實踐，讓設定參數這件事情不會只限定在創造物件的當下。且可以依照自身的需求來設定所需要的參數，這樣子物件也可以減少需要宣告的constructor數量。

首先會先創立一個builder interface，宣告所有builder會有的共有方法，內容需包含創立物件所可能用到的setter以及得到該物件的方法，另外介面可以降低使用者
對某特定builder的依賴性 (dependency inversion)。

接著就創立物件去實作該介面。

``` java
interface Builder {

    public void setName(String name);

	public void setVolume(int volume);

	public void setWheat(String wheat);

	public void setHops(int hops);

	public void setFermentation(int fermentation);

	public TastyBeer getResult();

}

class TastyBearBuilder implements Builder{
	private String name;

	private int volume;

	private Weat wheat;

	private int hops;

	private int fermentation;

	public TastyBearBuilder() {}

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

public class BuilderDemo {

	public static void main(String[] args) {
		// using builder
		Builder builder = new TastyBearBuilder();
		builder.setFermentation(1000);
		builder.setWheat(new Weat());
		builder.setName("1664");
		TastyBeer tastyBeerByBuilder = builder.getResult();
	}
}

```

### 更進一步解決問題- Director 
但有時候使用者根本不需要知道製造物件的過程，且若是使用的物件都來自相同的步驟，這樣會產生許多重複的程式碼，且若建立物件有一定的步驟時，使用者除了自己的商業邏輯外，還要花心思注意這個物件是否正確地被建立。

此時可以再宣告一個類別，負責操作builder內的創建步驟，完全隱藏住該物件的創立過程和所需要的參數，使用要做的只有宣告一個正確的builder (能製造出使用者需要的特定物件的builder)，並將其傳入到director內。

若要取得該物件，通常會從builder拿取，這樣子director就不會與該builder和物件有相依性 (director內沒有bulider的instance variable，且不用特別宣告一個該物件的getter方法)。



```java
class Director{

	public void constructorBear(Builder builder) {
		builder.setFermentation(10);
		builder.setHops(20);
		builder.setName("green label");
		builder.setWheat(new Weat());
		builder.getResult();
	} 

    // Director may know several building recipes.
}

public class BuilderDemo {

	public static void main(String[] args) {
		// using builder with director
		Builder builder2 = new TastyBearBuilder();
		Director director = new Director();
		
        // Constructoring and getting the object which has been given the constructor's arguments by the director
		TastyBeer tastyBeer2 = builder2.getResult();

	}

}

```

若需要用不同的方式創立來自相同類別的物件，就需要新增一個實作的builder類別，另外創立的物件不一定是要來自於相同的類別，或是多型的類別物件，products之間可以完全沒有任何關係，但套用同一個director，只需要新增對應的builder和在dierector內宣告好創建的步驟即可。