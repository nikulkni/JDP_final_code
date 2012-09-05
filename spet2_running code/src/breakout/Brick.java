package breakout;

import java.awt.Rectangle;

public class Brick /* extends Rectangle */extends Base implements MyObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int life = 1; // number of hits the brick can take
	private Rectangle brickRectangle;
	//private 
	
	MyObservable board;

	public Brick(int x, int y, int w, int h, MyObservable board) {
		super(x, y, w, h);
		
		this.board = board;
		brickRectangle = new Rectangle(x, y, w, h);
		this.setRectangularShapeElement(brickRectangle);
		//board.registerObserver(this);
	}

	public Brick(int w, int h, MyObservable board) {
		// super(w, h);
		
		this.board = board;
		brickRectangle = new Rectangle(w, h);
		this.setX(brickRectangle.x);
		this.setY(brickRectangle.y);
		this.setWidth(brickRectangle.width);
		this.setHeight(brickRectangle.height);
		this.setRectangularShapeElement(brickRectangle);

		//board.registerObserver(this);
	}

	boolean isDead(Ball ball) {
		if (this.get_life() == 0) {
			return true;
		}
		return false;
	}

	public void update(Ball ball) {
		
		//System.out.print("hello world from update Brick");
		
		if (this.get_life() > 0 && ball.intersects(this)) 
		{
			this.dec_life();
			if(this.get_life() < 0)
			{
				this.setLife(0);
			}

		}
	}

	public void dec_life() {
		if (this.life > 0) {
			this.life--;
		}
	}

	public int get_life() {
		return this.life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	@Override
	void setX(double x) {
		// TODO Auto-generated method stub
		this.x = x;
		brickRectangle.x = (int) x;
	}

	@Override
	void setY(double y) {
		// TODO Auto-generated method stub
		this.y = y;
		brickRectangle.y = (int) y;
	}

	@Override
	void setWidth(int width) {
		// TODO Auto-generated method stub
		this.width = width;
		brickRectangle.width = width;
	}

	@Override
	void setHeight(int height) {
		// TODO Auto-generated method stub
		this.height = height;
		brickRectangle.height = height;
	}

//	@Override
//	public void update() {
//		// TODO Auto-generated method stub
//System.out.print("Brick: public void update()  ");
//	}

	@Override
	public void update(Ball ball, Paddle paddle, Brick brick, int frameWidth, int farmeHeight) {
		// TODO Auto-generated method stub
		
		//System.out.print("hello world from update Brick");
		
		if (this.get_life() > 0 && ball.intersects(this)) 
		{
			this.dec_life();
			if(this.get_life() < 0)
			{
				this.setLife(0);
			}

		}
	}

	@Override
	public void update(int frameWidth, int farmeHeight) {
		
			}
}
