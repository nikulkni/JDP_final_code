package edu.indiana.p532.command;

import java.util.List;

import edu.indiana.p532.game.Brick;

public class BrickRemoveCommand implements Command{

	List<Brick> bricksList;
	Brick brick;

	public BrickRemoveCommand(List<Brick> bricksList, Brick brick) {
		this.bricksList = bricksList;
		this.brick = brick;
	}

	public void perform() {
		// Remove brick
		brick.setHit(true);
		//bricksList.remove(brick);
	}

	public void revert() {
		//bricksList.add(brick);
		// Add back the brick
		brick.setHit(false);
	}
}
