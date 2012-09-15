package edu.indiana.p532.game;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * 
 * @param board
 *            Object of class Board
 * @param control
 *            object of the ControlClass
 * @param scorePanel
 *            object of thE score Panel
 * @param bricks
 *            array of Brick objects
 * @param ball
 *            object of the Ball class
 * @param paddle
 *            object of the Paddle class
 */

public class BrickBreaker {

	/**
	 * @param args
	 */
	private Board board;
	private ControlClass control;
	private ScoreBoard scorePanel;
	private TimePanel timePanel;

	private List<Brick> bricksList;
	private Ball ball;
	private Paddle paddle;
	
	private JPanel mainPanel;

	//Nikhil: responder to layoutChange
	private Observer buttonLayoutChangedObserver;
	private int i, x, j;
	
	private static org.apache.log4j.Logger log = Logger.getLogger(BrickBreaker.class);

	public static void main(String[] args) {
		
		log.info("Game Started");
		new BrickBreaker();
	}

	public BrickBreaker() {
		
		buttonLayoutChangedObserver = new Observer(){

			@Override
			public void update() {
				resetPanel();
				
			}
			
		};
		
		PropertyConfigurator.configure(getClass().getClassLoader().getResource("log4j.properties"));
		JFrame guiFrame = new JFrame();
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Kick out Game");
		guiFrame.setSize(Constants.WINDOW_LENGTH, Constants.WINDOW_WIDTH);

		bricksList = new ArrayList<Brick>(Constants.BRICK_COUNT);
		for (i = 0; i < Constants.BRICK_COUNT; i++) {
			if (x > 4) {
				x = 0;
				j++;
			}
			Brick brick = new Brick(20 + (x * 65), 20 + (j * 25));
			bricksList.add(brick);
			x++;
		}

		Constants constant = new Constants();
		constant.generateRandomStartingPositions();
		ball = new Ball(constant.getBall_xvalue(), constant.getBall_yvalue());

		paddle = new Paddle(constant.paddle_xvalue, constant.getPaddle_yvalue());
		board = new Board(bricksList, ball, paddle);
		scorePanel = new ScoreBoard();
		timePanel = new TimePanel();

		control = new ControlClass(scorePanel, board, bricksList, ball, paddle,
				timePanel, constant);
		timePanel.timeRegister(control);

		JPanel infoPanel = new JPanel(new BorderLayout());
		
		//Nikhil: replaced following three lines with appropriate method calls
//		infoPanel.add(control, BorderLayout.SOUTH);
//		infoPanel.add(scorePanel, BorderLayout.NORTH);
//		infoPanel.add(timePanel, BorderLayout.WEST);
		
		//Nikhil: for layout Change feature
		control.registerForLayoutChangeNotification(buttonLayoutChangedObserver);
		
		control.addToContainer(infoPanel, BorderLayout.SOUTH);
		scorePanel.addToContainer(infoPanel, BorderLayout.NORTH);
		timePanel.addToContainer(infoPanel, BorderLayout.WEST);

		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(infoPanel, BorderLayout.EAST);
		
		//Nikhil: added addToContainer to Board and changed to appropriate method call here.
//		mainPanel.add(board, BorderLayout.CENTER);
		board.addToContainer(mainPanel, BorderLayout.CENTER);
		// make sure the program exits when the frame closes
		guiFrame.add(mainPanel, BorderLayout.CENTER);

		guiFrame.setVisible(true);
	}
	
	//Nikhil: for layout change feature
	public void resetPanel(){
		mainPanel.validate();
	}
}
