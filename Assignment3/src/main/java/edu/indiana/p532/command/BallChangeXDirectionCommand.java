package edu.indiana.p532.command;

import edu.indiana.p532.game.Ball;

public class BallChangeXDirectionCommand implements Command{

	Ball ball;

	public BallChangeXDirectionCommand(Ball ball) {
		this.ball = ball;
	}

	public void perform() {
		ball.setVelocityX(-1 * ball.getVelocityX());
	}

	public void revert() {
		ball.setVelocityX(-1 * ball.getVelocityX());
	}
}
