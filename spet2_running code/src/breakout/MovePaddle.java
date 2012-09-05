package breakout;
import java.awt.Color;
import java.awt.Color;


public class MovePaddle implements Command{

	private Paddle paddle;
	private float paddleInitialX, paddleInitialY , paddleXLocation, paddleYLocation, paddleWidth, paddleHeight;
	private Color paddleColor;
	
	public MovePaddle(Paddle paddle)
	{
		this.paddle = paddle;
	}

	@Override
	public void execute( int frameWidth, int farmeHeight) {
		// TODO Auto-generated method stub
		paddle.update( frameWidth, farmeHeight);
	}

//	@Override
//	public void move() {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void unDo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(Ball ball, Paddle paddle, Brick brick, int frameWidth,
			int frameHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(Ball ball) {
		// TODO Auto-generated method stub
		
	}
	
}
