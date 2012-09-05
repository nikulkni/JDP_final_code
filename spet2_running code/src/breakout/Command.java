package breakout;
public interface Command 
{

	public void execute();
	
	public void unDo();
	
	//public void move();

	void execute(Ball ball, Paddle paddle, Brick brick, int frameWidth,
			int frameHeight);

	void execute(int frameWidth, int farmeHeight);

	void execute(Ball ball);

	
	
}
