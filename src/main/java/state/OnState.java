package state;

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
