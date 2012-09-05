package breakout;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public abstract class Base {
	protected double x,y;
	protected int width, height;
	protected RectangularShape r;
	
	
	public abstract void update(Ball ball, Paddle paddle, Brick brick, int frameWidth, int farmeHeight);
	
	Base(double x, double y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	Base(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	Base() {
	}
	
	void draw(Graphics2D g2){
		g2.fill((Shape) this.getRectangularShapeElement());
	}
		
	protected RectangularShape getRectangularShapeElement(){
		return r;
	}
	
	protected void setRectangularShapeElement(Rectangle2D r){
		this.r = r;
	}	
	
	//Getters
	double getX(){
		return x;
	}
	
	double getY(){
		return y;
	}
	
	int getWidth(){
		return width;
	}
	
	int getHeight(){
		return height;
	}
	
	//Setters
	abstract void setX(double x);
	
	abstract void setY(double y);
	
	abstract void setWidth(int width);
	
	abstract void setHeight(int height);
	
}
