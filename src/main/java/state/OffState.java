package state;

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
