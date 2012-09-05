package breakout;
import java.awt.Color;


public class MoveBall implements Command{

	private Ball ball;
	private float ballInitialX, ballInitialY , ballXLocation, BallYLocation, ballWidth, ballHeight;
	private Color ballColor;
	
	public MoveBall(Ball ball)
	{
		this.ball = ball;
	}
	
	@Override
	public void execute(Ball ball, Paddle paddle, Brick brick,int frameWidth, int frameHeight)
	{		
		ball.update(ball, paddle, brick, frameWidth, frameHeight);
	}

	/*@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}*/
	
	public void unDo() {
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void move() {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void execute(int frameWidth, int farmeHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(Ball ball) {
		// TODO Auto-generated method stub
		
	}
	
}
