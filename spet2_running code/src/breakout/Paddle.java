package breakout;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle /*extends Rectangle*/ extends Base implements MyObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int dy;
	public int screen_height;
	private double springFactor = 1; //how much does it change the speed of the ball
	private Rectangle paddleRectangle;
	MyObservable board;
	
	public Paddle(int x, int y, int w, int h, MyObservable board){
		super(x, y, w, h);
		
		this.board = board;
		paddleRectangle = new Rectangle(x, y, w, h);
		this.setRectangularShapeElement(paddleRectangle);
		
		//board.registerObserver(this);
	}
	public Paddle(int w, int h, MyObservable board){
		//super(w, h);
		paddleRectangle = new Rectangle();
		this.setRectangularShapeElement(paddleRectangle);
		
		//board.registerObserver(this);
	}
	
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			
			if(paddleRectangle.y<0)
				dy=0;
			else
				dy = -1;
				
        }
		if (key == KeyEvent.VK_DOWN) {
            
			if(paddleRectangle.y+paddleRectangle.height>screen_height)
				dy=0;
			else
				dy = 1;
        }
	}
	public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
		
	}
	
	@Override
	public void update(int frameWidth, int farmeHeight){
		
		/*
		paddleRectangle.y+= dy;
		if(paddleRectangle.y<=0)
			paddleRectangle.y=0;
		if(paddleRectangle.y+paddleRectangle.height>=screen_height)
			paddleRectangle.y=screen_height-paddleRectangle.height;
			*/
		screen_height = farmeHeight;
		this.setY(this.getY()+dy);
		if(this.getY() <= 0){
			this.setY(0);
		}
		if((this.getY() + this.getHeight()) >= screen_height){
			this.setY(screen_height - this.getHeight());
		}
		
	}
	public void set_springFactor(double sf){
		this.springFactor = sf;
	}
	public double get_springFactor(){
		return this.springFactor;
	}
	
	@Override
	void setX(double x) {
		// TODO Auto-generated method stub
		this.x = x;
		paddleRectangle.x = (int) x;
	}

	@Override
	void setY(double y) {
		// TODO Auto-generated method stub
		this.y = y;
		paddleRectangle.y = (int) y;
	}

	@Override
	void setWidth(int width) {
		// TODO Auto-generated method stub
		this.width = width;
		paddleRectangle.width = width;
	}

	@Override
	void setHeight(int height) {
		// TODO Auto-generated method stub
		this.height = height;
		paddleRectangle.height = height;
	}
//	@Override
//	public void update(Ball ball, Paddle paddle, Brick brick, int frameWidth,
//			int farmeHeight) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void update(Paddle paddle, Brick brick, int frameWidth,
//			int farmeHeight) {
//		// TODO Auto-generated method stub
//		
//	}
	@Override
	public void update(Ball ball, Paddle paddle, Brick brick, int frameWidth,
			int farmeHeight) {
		// TODO Auto-generated method stub
		
	}
	
		
}
