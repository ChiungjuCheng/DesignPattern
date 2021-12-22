# State 狀態模式
能夠讓物件隨著自身狀態改變而更換行為。

# 問題
1. 一個物件的行為在run-time的時候會因為自身狀態不同而改變，每一次呼叫方法都要確認一次物件的狀態，造成確認的狀態的程式重複
2. 有大量的判斷條件依賴著物件的狀態，當要增加新的狀態時，變得難以維護
3. 物件目前的狀態可能無法轉換成某些狀態

# UML
![State UML](/picture/state.png)

**Context**  
擁有一個state物件，負責藉由state interface調用state的方法。有一個public的setter，可以傳入新的state物件來改變狀態。
  
**Concrete States**  
可以擁有一個Context物件參考，以便拿到需要的資訊來初始化state transitions。

**Transitions**  
轉換狀態的規則，也是屬於有限和可以預先決定的，context和states都可以設定context的下一個狀態和執行Transitions。

# Demo  
洗衣機有三種狀態，分別是開機、關機和運轉。
**狀態**  
```java
public interface WashingMachineState {

	void off();

	void on();

	void start();

	void pause();
}

/**
 * 運轉狀態
 *
 */
public class RunningState implements WashingMachineState {

	private WashingMachine washingMachine;

	public RunningState(WashingMachine washingMachine) {
		this.washingMachine = washingMachine;
	}

	@Override
	public void start() {
		off();
	}

	@Override
	public void off() {
		System.out.println("Shut down the washing machine");
		washingMachine.setState(new OffState(washingMachine));
	}

	@Override
	public void on() {
		System.out.println("Shut down the washing machine");
		washingMachine.setState(new OffState(washingMachine));
	}

	@Override
	public void pause() {
		System.out.println("Pause the washing machine");
		washingMachine.setState(new OnState(washingMachine));
	}

}

/**
 * 開機狀態
 *
 */
public class OnState implements WashingMachineState {

	private WashingMachine washingMachine;

	public OnState(WashingMachine washingMachine) {
		this.washingMachine = washingMachine;
	}

	@Override
	public void start() {
		if (washingMachine.isDoorIsOpen()) {
			System.out.println("The door is open");
			return;
		}
		System.out.println("Start!");
		washingMachine.setState(new RunningState(washingMachine));
	}

	@Override
	public void pause() {
		System.out.println("OnState already!");
	}

	@Override
	public void off() {
		System.out.println("Shut down the washing machine");
		washingMachine.setState(new OffState(washingMachine));
	}

	@Override
	public void on() {
		off();
	}

}

/**
 * 關機狀態
 *
 */
public class OffState implements WashingMachineState {

	private WashingMachine washingMachine;

	public OffState(WashingMachine washingMachine) {
		this.washingMachine = washingMachine;
	}

	@Override
	public void start() {
		System.out.println("OffState now.");
	}

	@Override
	public void off() {
		System.out.println("OffState now.");

	}

	@Override
	public void pause() {
		System.out.println("OffState now.");

	}

	@Override
	public void on() {
		System.out.println("Turn on the washing machine");
		washingMachine.setState(new OnState(washingMachine));
	}

}

```

**Context 洗衣機**  
```java
/**
 * Context
 *
 */
public class WashingMachine {

	private WashingMachineState washingMachineState;

	private boolean doorIsOpen = true;

	public WashingMachine() {
		WashingMachineState offState = new OffState(this);
		setState(offState);
	}

	public void setState(WashingMachineState washingMachineState) {
		this.washingMachineState = washingMachineState;
	}

	public boolean isDoorIsOpen() {
		return doorIsOpen;
	}

	public void setDoorIsOpen(boolean doorIsOpen) {
		this.doorIsOpen = doorIsOpen;
	}

	public void off() {
		washingMachineState.off();
	}

	public void on() {
		washingMachineState.on();
	}

	public void start() {
		washingMachineState.start();
	}

	public void pause() {
		washingMachineState.pause();
	}

}
```
**Client**
```java
		WashingMachine washingMachine = new WashingMachine();
		washingMachine.on(); // Turn on the washing machine
		washingMachine.start(); // The door is open
		washingMachine.setDoorIsOpen(false);
		washingMachine.start(); // Start!
		washingMachine.pause(); // Pause the washing machine
		washingMachine.start(); // Start!
		washingMachine.off(); // Shut down the washing machine
		System.out.println("==========================");
		washingMachine.on(); // Turn on the washing machine
		washingMachine.on(); // Shut down the washing machine
		washingMachine.on(); // Turn on the washing machine
		washingMachine.on(); // Shut down the washing machine

```

# 優點
1. 消除龐大的條件分支敘述
2. 將特定的狀態相關行為都放入一個物件中，由於所有和狀態相館的程式碼都存在於某個ConcreteState中，因此透過新的子類別，能夠容易的增加新的狀態和轉換。

# 參考資料  
大話設計模式  
[State](https://refactoring.guru/design-patterns/state)