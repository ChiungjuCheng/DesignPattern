# Flyweight 享元模式
運用共用技術有效地支援大量細粒度的物件[DP]。  
多個物件共享同一份狀態，利用共享節省RAM的空間。  
這個設計模式僅適用於當有大量物件造成RAM過多消耗時適用，需要確定問題沒有更有意義的解決方式後才適用flyweight。

# 問題
有個簡單的Vehicle，只擁有一個operation說明這個Vehicle用了甚麼樣式的引擎走了幾步路。  

**Vehicle**
```java
public class Vehicle {

	private Engine engine;    
	private String moving;

	public Vehicle(Engine engine, String moving) {
		this.engine = engine;
		this.moving = moving;
	}

        //...... 省略 getter 和 setter

	public void operation() {
		System.out.println(engine + " moves "+moving);
	}

}

```
當有需求產生大量的Vehicle物件時卻發生OutOfMemoryError。  

**Client**
```java
List<Vehicle> vehicleList = new ArrayList<>();

// 創立物件前已使用的heap空間
long beforeUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

// Heap test 30000000 is fine.
for(int i = 0; i < 50000000; i++) {
    vehicleList.add(new Vehicle(new BigEngine(), "1");
}

// 創立物件後已使用的heap空間
long afterUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
// 物件使用的heap空間
long actualMemUsed = afterUsedMem-beforeUsedMem;
System.out.println( actualMemUsed); // byte

// Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
```

仔細看會發現，Vehicle的引擎都長得一樣，因此引擎是可以讓多個Vehicle共用的，只有移動的步數會根據Vehicle不同而有所改變。

# UML
![flyweight](/picture/flyweight.png)


**Flyweight class**  
擁有物件部分的狀態，這些狀態是可以被其他物件共用的，因此同樣一個Flyweight 物件可以被多個Context使用，存在Flyweight中不會變動的狀態稱為<mark>intrinsic</mark>，傳入flyweight方法的可變動的狀態則是<mark>extrinsic</mark>。  

<br>

**Flyweight Factory**  
負責管理flyweight物件群，依照傳入的參數來創造和回傳該物件，或是直接回傳已經創建好的物件。

<br>

**Context**  
擁有extrinsic state(是每個物件自己擁有的)，會和一個flywight物件成對，以成為一個擁有完整state的物件(full state of the original object)。  

**Context operation**
通常original object的行為會包在flyweight方法內，當要使用該方法時只要傳入extrinsic state即可。

**Client端**
負責計算或儲存需要的extrinsic state，對於使用者來說flyweight物件是個在Rumtime中傳入各種狀態(contextual data )到他的方法的template物件。


資料參考  
https://refactoring.guru/design-patterns/flyweight

# Demo
為了將會可以共享的引擎與不能共享的步數從Vehicle中分開，需要宣告一個Flyweight類別包住可以共享的物件，因此將Vehicle改寫為VehicleFlyweight，constructor傳入可共享的Engine。執行方法則改為將不可共享的參數或物件 (extrinsic state)傳入方法中使用。
```java
public class VehicleFlyweight {
	
    private Engine engine;
	
    public VehicleFlyweight(Engine engine) {
        this.engine = engine;
    }
	
    public void operation(String moving) {
        System.out.println("Vehicle with "+ engine + " moves "+ moving + " steps.");
    }

}
```
宣告一個可以放extrinsic state和Flyweight物件的VehicleContext供client端使用。在每次呼叫constructor的時候，都會經由VehicleFlyweightFactory產生包有可共享物件的VehicleFlyweight。
```java
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
```
VehicleFlyweightFactory負責管理flyweights pool，在此處使用ConcurrentHashMap存放。當有目標flyweights存在時就直接回傳VehicleFlyweight，若沒有則建立物件，並加入管理後回傳。
```java
public class VehicleFlyweightFactory {
	
	/**
	 * 以會重複的intrinsic作為key
	 */
	static Map<Engine, VehicleFlyweight> engineTypes = new ConcurrentHashMap<>();
	
	public static VehicleFlyweight  getVehicleFlyweight(Engine engine) {
		VehicleFlyweight vehicleFlyweight = engineTypes.get(engine);
		
		if(vehicleFlyweight == null) {
			vehicleFlyweight = new VehicleFlyweight(engine);
			engineTypes.put(engine,vehicleFlyweight);
		}
		
		return vehicleFlyweight;
	}
	
}
```
Client : 
```java
public class FlyweightClient {
	
	public static void main(String[] args) {
		
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
```


# 優缺點
* 可能在呼叫flyweight method時，有些contextual data需要經過複雜的計算，而造成CPU過量使用。
* CODE變得複雜，讓人難以理解為什麼一個物件要被拆成兩個狀態。 

# Real world example
java.lang.Integer#valueOf(int)

#  Flyweight V.S. Cache
Flyweight目的為降低RAM的使用量，Cache則是為了降低運算時間
