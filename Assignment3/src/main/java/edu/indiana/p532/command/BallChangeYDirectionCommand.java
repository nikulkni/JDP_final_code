package edu.indiana.p532.command;

import edu.indiana.p532.game.Ball;

public class BallChangeYDirectionCommand implements Command{

	Ball ball;

	public BallChangeYDirectionCommand(Ball ball) {
		this.ball = ball;
	}

	public void perform() {
		ball.setVelocityY(-1 * ball.getVelocityY());
	}

	public void revert() {
		ball.setVelocityY(-1 * ball.getVelocityY());
	}
}
