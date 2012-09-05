package breakout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ball extends Base implements MyObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean onFire = false;
	private int xVelocity = 0;
	private int yVelocity = 0;
	private Ellipse2D.Double ball;
	double ballCenterX,ballCenterY;
	boolean isBallOutOfBrick = true;
	MyObservable board;
	
	// Constructors 
	public Ball(double xLocation, double yLocation, int width, int height, MyObservable board){
		super(xLocation, yLocation, width, height);
		
		this.board = board;
		ball = new Ellipse2D.Double(xLocation, yLocation, width, height);
		
	//board.registerObserver(this);
	}
	
	public Ball(Board board){
		super();
		
		this.board = board;
		ball = new Ellipse2D.Double();
		
		//board.registerObserver(this);
	}
	
	//Setters
	@Override
	void setX(double x) {
		// TODO Auto-generated method stub
		this.x = x;
		ball.x = x;
	}

	@Override
	void setY(double y) {
		// TODO Auto-generated method stub
		this.y = y;
		ball.y = y;
	}
	
	public void setXVelocity(int vx){
		this.xVelocity = vx;
	}
	
	public void setYVelocity(int vy){
		this.yVelocity = vy;
	}
	
	@Override
	void setWidth(int width) {
		// TODO Auto-generated method stub
		this.width = width;
		ball.width = width;
	}

	@Override
	void setHeight(int height) {
		// TODO Auto-generated method stub
		this.height = height;
		ball.height = height;
	}
	
	
	//getters
	
	public int getXVelocity(){
		return this.xVelocity;
	}
	
	public int getYVelocity(){
		return this.yVelocity;
	}
	/*public void notifyTimerFired(Paddle paddle, Brick brick, int frameWidth, int farmeHeight)
	{
		this.update( paddle,  brick,  frameWidth,  farmeHeight);
		
	}*/
	
	@Override
	public void update(Ball ball, Paddle paddle, Brick brick, int frameWidth, int farmeHeight)
	{
		//ball.x += xVelocity;
		//ball.y += yVelocity;
		this.setX(getX()+xVelocity);
		this.setY(getY()+yVelocity);
		
		
		checkCollision(paddle, brick, frameWidth, farmeHeight);
	}
	
	public void checkCollision( Paddle paddle, Brick brick, int frameWidth, int farmeHeight)
	{
		ballCenterX = this.x + this.getWidth()/2;
		ballCenterY = this.y + this.getHeight()/2;
		
		this.checkBound(frameWidth, farmeHeight);
		this.checkPaddleCollision(paddle);
		this.checkBrickColliosion(brick);
		
	}
	private void checkBrickColliosion(Brick brick) {
		// TODO Auto-generated method stub
		
		if(brick.get_life()>0 && this.intersects(brick)){
			if(ballCenterX>=brick.x && ballCenterX<=(brick.x+brick.getWidth())){
				this.invertYVelocity();
			}
			else if(ballCenterY>=brick.y && ballCenterY<=(brick.y+brick.getHeight())){
				this.invertXVelocity();
			}
			else {
				this.invertXVelocity();
				this.invertYVelocity();
			}
		}
		
	}

	private void checkPaddleCollision(Paddle paddle) 
	{
		if(this.intersects(paddle))
		{			
			
			if(isBallOutOfBrick){
				if(ballCenterX>=paddle.x && ballCenterX<=(paddle.x + paddle.getWidth())){
					this.invertYVelocity();
				}
				else if(ballCenterY>=paddle.y && ballCenterY<=(paddle.y  + paddle.getHeight())){
					this.invertXVelocity();
				}
				else {
					this.invertXVelocity();
					this.invertYVelocity();
				}
			}
			
			isBallOutOfBrick = false;
		} else{
			isBallOutOfBrick = true;
		}
	}

	private void checkBound(int frameWidth, int farmeHeight) {
		// TODO Auto-generated method stub
		if(this.x>=frameWidth || this.x<=0){
			this.invertXVelocity();
		}
		if(this.y+this.getHeight()>=farmeHeight ||this.y<=0){
			this.invertYVelocity();
		}
	}

	public void invertXVelocity(){
		this.xVelocity *= -1;
	}
	
	public void invertYVelocity(){
		this.yVelocity *= -1;
	}
	
	
	
	//Nikhil: for effecting intersection
	public boolean intersects(Base shape){
		return ball.intersects((Rectangle2D) shape.getRectangularShapeElement());
	}

	
	@Override
	void draw(Graphics2D g){		
		g.fill(ball);
	}

	@Override
	public void update(int frameWidth, int farmeHeight) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void update() {
//		// TODO Auto-generated method stub
//		
//	}	
}
