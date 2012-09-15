package edu.indiana.p532.saver;

import java.util.List;

import edu.indiana.p532.game.Ball;
import edu.indiana.p532.game.Brick;
import edu.indiana.p532.game.Paddle;
import edu.indiana.p532.game.Score;
import edu.indiana.p532.game.TimePanel;

public class GameState {

	private List<Brick> bricksList;
	private Ball ball;
	private Paddle paddle;
	private Score score;
	private TimePanel timePanel;
	private int time;
	
	public GameState() {
		
	}
	
	public GameState(List<Brick> bricksList, Ball ball, Paddle paddle,
			Score score, int time) {
		this.bricksList = bricksList;
		this.ball = ball;
		this.paddle = paddle;
		this.score = score;
		//this.timePanel = timePanel;
		this.time = time;
	}
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public List<Brick> getBricksList() {
		return bricksList;
	}
	public void setBricksList(List<Brick> bricksList) {
		this.bricksList = bricksList;
	}
	public Ball getBall() {
		return ball;
	}
	public void setBall(Ball ball) {
		this.ball = ball;
	}
	public Paddle getPaddle() {
		return paddle;
	}
	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	public TimePanel getTimePanel() {
		return timePanel;
	}
	public void setTimePanel(TimePanel timePanel) {
		this.timePanel = timePanel;
	}
}
