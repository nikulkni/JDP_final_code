package edu.indiana.p532.command;
import edu.indiana.p532.game.TimePanel;

public class UpdateTimeCommand implements Command{
	
	TimePanel timePanel;

	public UpdateTimeCommand(TimePanel timePanel) {
		this.timePanel = timePanel;
	}

	public void perform() {
		// Add time
		timePanel.setTimeIncrease();
	}

	public void revert() {
		// Subtract the time
		timePanel.setTimeDecrease();
	}

}
