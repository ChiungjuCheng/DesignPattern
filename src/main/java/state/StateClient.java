package state;

public class StateClient {

	public static void main(String[] args) {
		WashingMachine washingMachine = new WashingMachine();
		washingMachine.on();
		washingMachine.start();
		washingMachine.setDoorIsOpen(false);
		washingMachine.start();
		washingMachine.pause();
		washingMachine.start();
		washingMachine.off();
//		washingMachine.off();
		System.out.println("==========================");
		washingMachine.on();
		washingMachine.on();
		washingMachine.on();
		washingMachine.on();
		
	}
}
