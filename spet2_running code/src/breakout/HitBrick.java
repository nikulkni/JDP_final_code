package breakout;
import java.awt.Color;
import java.awt.Color;


public class HitBrick implements Command{

	private Brick brick;
	private float brickInitialX, brickInitialY , brickXLocation, brickYLocation, brickWidth, brickHeight;
	private Color brickColor;
	
	public HitBrick(Brick brick)
	{
		this.brick = brick;
	}

	@Override
	public void execute(Ball ball) {
		brick.update(ball);
		
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
	public void execute(Ball ball, Paddle paddle, Brick brick, int frameWidth,
			int frameHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(int frameWidth, int farmeHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}
