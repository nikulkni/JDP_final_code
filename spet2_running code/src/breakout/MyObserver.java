package breakout;

public interface MyObserver {
	
//	public void update();

	void update(Ball ball, Paddle paddle, Brick brick, int frameWidth, int farmeHeight);

	void update(int frameWidth, int farmeHeight);

}
