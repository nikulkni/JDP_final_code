package edu.indiana.p532.command;

import edu.indiana.p532.game.Constants;
import edu.indiana.p532.game.Paddle;

public class PaddleMoveLeftCommand implements Command{

	Paddle paddle;

	public PaddleMoveLeftCommand(Paddle paddle) {
		this.paddle = paddle;
	}

	public void perform() {
		if (paddle.getX() > 0) {
			paddle.setX(paddle.getX() - Constants.PADDLE_PRECISION);
		}
	}

	public void revert() {
		paddle.setX(paddle.getX() + Constants.PADDLE_PRECISION);
	}
}
