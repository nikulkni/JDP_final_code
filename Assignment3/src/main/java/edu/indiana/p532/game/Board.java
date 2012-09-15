package edu.indiana.p532.game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import edu.indiana.p532.game.ControlClass.PaddleMovementListener;

/*
 * This class initialises the main board interface of
 * the game. All the constructors are called in. The repaint
 * function the does the job of repainting the board
 * to display the updated contents.
 * @param bricks Array of Brick class
 * @param ball Object of the Ball
 * @param paddle Object of the paddle
 */

public class Board {
	private List<Brick> bricksList;
	private Ball ball;
	private Paddle paddle;
	private JPanel boardPanel;
	
	
	public Board(List<Brick> argBricksList, Ball argBall, Paddle argPaddle) {
		
		boardPanel = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -7299643961607392206L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				
				if(bricksList == null){
					System.out.println("Hello world");
				}
				
				for (Brick brick : bricksList) {
					if (!brick.isHit()) {
						g.drawImage(brick.gettingImage(), brick.getX(),
								brick.getY(), null);
					}
				}

				paddle.draw(g);
				//g.drawImage(paddle.gettingImage(), paddle.getX(), paddle.getY(), null);

				ball.draw(g);
				//g.drawImage(ball.gettingImage(), ball.getX(), ball.getY(), null);

			}
		};
		
		boardPanel.setSize(new Dimension(Constants.BOARD_LENGTH,
				Constants.BOARD_WIDTH));

		this.bricksList = argBricksList;
		this.ball = argBall;
		this.paddle = argPaddle;

		boardPanel.setBackground(Color.WHITE);

		boardPanel.setFocusable(true);
		boardPanel.requestFocusInWindow();

	}

//	@Override
//	public void paint(Graphics g) {
//		super.paint(g);
//		for (Brick brick : bricksList) {
//			if (!brick.isHit()) {
//				g.drawImage(brick.gettingImage(), brick.getX(),
//						brick.getY(), null);
//			}
//		}
//
//		paddle.draw(g);
//		//g.drawImage(paddle.gettingImage(), paddle.getX(), paddle.getY(), null);
//
//		ball.draw(g);
//		//g.drawImage(ball.gettingImage(), ball.getX(), ball.getY(), null);
//
//	}

	public void draw() {
		boardPanel.requestFocus(true);
		boardPanel.repaint();
	}

	public void addKeyListenerToPanelElement(PaddleMovementListener paddleMovementListener) {	
		boardPanel.addKeyListener(paddleMovementListener);
	}
	
	public void addToContainer(Container container,String constraints){
		container.add(boardPanel, constraints);
	}

	public void addToContainer(Container container){
		container.add(boardPanel);
	}
}
