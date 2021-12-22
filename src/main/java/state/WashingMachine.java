package state;

/**
 * Context
 * @author user
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
