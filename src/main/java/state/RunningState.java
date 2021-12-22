package state;

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
