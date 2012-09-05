package breakout;

import java.util.Iterator;
import java.util.LinkedList;

public class BoardState {
	//private List<State> history = new  ArrayList<State>();
	
	private final boolean YES = true, NO = false;
	
	private LinkedList<State> gameHistory = new  LinkedList<State>();
	private MoveBall commandMoveBall;
	private MovePaddle commandMovePaddle;
	private HitBrick commandHitBrick;
	private boolean isReplayOver = NO;
	private Iterator <State> gameHistoryIterator;

	private boolean isIteratorSet, hasUserStartedPlaying;
	
	//private int frameWidth, frameHeight;
	State state;
	//private MovePaddle  commandMovePaddle;
	public BoardState(Ball ball, Paddle paddle, Brick brick)
	{
		commandMoveBall = new MoveBall(ball);
		commandMovePaddle = new MovePaddle(paddle);
		commandHitBrick = new HitBrick(brick);
		
		
		
	}
	
	public void storeAndExecute(Ball ball, Paddle paddle, Brick brick,int frameWidth, int frameHeight)
	{				
		state = new State(ball,brick,paddle);				
		this.gameHistory.add(state);
		commandMoveBall.execute(ball,paddle,brick,frameWidth,frameHeight );
		commandMovePaddle.execute(frameWidth, frameHeight);
		commandHitBrick.execute(ball);
		
		isReplayOver = NO;
		isIteratorSet = NO;
	}
	
	public void replayAndExecute(Ball ball, Paddle paddle, Brick brick, int framemWidth, int frameHeight){
				
			
			if(isIteratorSet == NO){
				gameHistoryIterator = gameHistory.iterator();
				isIteratorSet = YES;
			}
			
			
				state = gameHistoryIterator.next();
				isReplayOver = !gameHistoryIterator.hasNext();
			
			state.setStateOfObjects(ball, brick, paddle);					
	}
	
	public void undoAndExecute(int numberOfFrames, Ball ball, Paddle paddle, Brick brick, int framemWidth, int frameHeight){
		
		for(;numberOfFrames >=0 || gameHistory.isEmpty();numberOfFrames--){
			state = gameHistory.pollLast();
		}
		
		if(!gameHistory.isEmpty()){					
			state.setStateOfObjects(ball, brick, paddle);
		}else{
			isReplayOver = YES;
		}
	}
	
	private class State{
		public double ballX, ballY, paddleX, paddleY, brickX, brickY;
		public int ballW, ballH, paddleW, paddleH, brickW, brickH;
		public int brickLife = 1;
		
		public State(Ball ball, Brick brick, Paddle paddle){
			ballX = ball.getX();
			ballY = ball.getY();
			ballW = ball.getWidth();
			ballH = ball.getHeight();
			paddleX = paddle.getX();
			paddleY = paddle.getY();
			//System.out.println(paddleY);
			
			paddleW = paddle.getWidth();
			paddleH = paddle.getHeight();
			brickX = brick.getX();
			brickY = brick.getY();
			brickW = brick.getWidth();
			brickH = brick.getHeight();
			brickLife = brick.get_life();
		}
	
		public void setStateOfObjects(Ball ball, Brick brick, Paddle paddle){			
			ball.setX(ballX);
			ball.setY(ballY);
			ball.setWidth(ballW);
			ball.setHeight(ballH);
			
			paddle.setX(paddleX);
			paddle.setY(paddleY);
			paddle.setWidth(paddleW);
			paddle.setHeight(paddleH);
			
			brick.setX(brickX);
			brick.setY(brickY);
			brick.setWidth(brickW);
			brick.setHeight(brickH);
			brick.setLife(brickLife);
		}
	}
	
	public boolean isReplayOver(){
		return isReplayOver;
	}
	
	public boolean isHistoryEmpty(){
		return gameHistory.isEmpty();				
	}
}
