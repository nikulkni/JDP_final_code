package edu.indiana.p532.command;

public class CommandImpl {

	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}

	public void perform() {
		command.perform();
	}

	public void revert() {
		command.revert();
	}

}
