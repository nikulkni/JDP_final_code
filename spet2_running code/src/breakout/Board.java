package breakout;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Formatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.Timer;

public class Board implements MyObservable
{
	
	//private static final long serialVersionUID = 1L;
	//private Thread animator;
	private Timer animationTimer;
	private Timer replayTimer;
	//LinkedList<State> stateList = new LinkedList<State>();
	static LinkedList<MyObserver> observerList = new LinkedList<MyObserver>();
	private final int TIMER_INTERVAL = 10;
	private final boolean YES = true, NO = false;
	
	private boolean isGamePaused = NO;
	
	Clock clock = new Clock(this);
	
	//Iterator<State> itr;
	//private final int DELAY = 50;

	private Paddle paddle;
	private Brick brick;
	private Ball ball;
	private int w=800;
	private int h=560;
	private boolean isPaused, isGameOver=NO, isReplayOver = NO, isReplaying=NO;
	private boolean isBallOutOfBrick = YES;
	
	JPanel boardPanel;
	JPanel menuPanel;
	
	BoardState boardState;
	
	public Board(JFrame applicationFrame){
		
		boardPanel = new JPanel(){
			
			@Override
			public void addNotify() 
			{
				super.addNotify();
				TimerTask taskAnimate = new TimerTask() 
				{
					@Override
					public void run() {
						animateGame();
					}
				};
				
				animationTimer = new Timer();
				animationTimer.scheduleAtFixedRate(taskAnimate, 0, TIMER_INTERVAL);
			}
			
			@Override
			public void paint(Graphics g)
			{
				
				super.paint(g);
				Graphics2D g2 = (Graphics2D) g;
			    g2.setStroke(new BasicStroke(1));
			    g2.setColor(Color.gray);
			    
			    if (isReplaying)
			    {
			    	  
			    	//replay();

			    }
			    
			    ball.draw(g2);
			    g2.setColor(Color.cyan);
			    paddle.draw(g2);
			    g2.setColor(Color.red);
			    
			    if(brick.get_life()>0){
			    	brick.draw(g2);
			    }
			    
			    Toolkit.getDefaultToolkit().sync();
			    g.setFont(new Font("Courier New", Font.PLAIN, 30));
			    StringBuilder sb = new StringBuilder();
			    Formatter formatter = new Formatter(sb);
			    //g.drawString(clock.getString(), (int)w*3/4, (int)h/8);
			    
			      if(isGameOver && !isReplaying)
			      {
			    	  formatter.format("Game Over");
			    	  formatter.close();
			    	 
			    	  animationTimer.cancel();
			    	  //animationTimer.purge();
			    	  isReplaying = YES;
			    	  
						TimerTask taskReplay = new TimerTask() {
							@Override
							public void run() {
								//replayGame();
							}
						};	
					  replayTimer = new Timer();
			    	  replayTimer.scheduleAtFixedRate(taskReplay, 0, 3*TIMER_INTERVAL);
			      }
			      
			      //Nikhil: moved code for stopping replayTimer to replayGame()
			      /*
			      if(isReplayOver)
			      {
			    	  replayTimer.cancel();
			    	  replayTimer.purge();
			      }
			      */
			      g.drawString(sb.toString(), w/2 - 150, h/2-22);
		        g.dispose();
			}
		};
		
		applicationFrame.add(boardPanel);
        //boardPanel.addKeyListener(new TAdapter());
        boardPanel.setFocusable(YES);
        boardPanel.setBackground(Color.black);
        boardPanel.setDoubleBuffered(YES);
        boardPanel.setFocusable(true);
        
        menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		applicationFrame.add(menuPanel,BorderLayout.NORTH);
		menuPanel.addKeyListener(new TAdapter());
        menuPanel.setBackground(Color.black);
        menuPanel.setFocusable(YES);
        
        
        
        final JButton resumeButton = new JButton("Pause");
        menuPanel.add(resumeButton,BorderLayout.EAST);
        resumeButton.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		if(!isGameOver && !isGamePaused)
        		{	 
        			animationTimer.cancel();
        			//animationTimer.purge();
        			animationTimer = null;
        			isGamePaused = YES;
        		}
        	}	
    	});
        
        JButton startButton = new JButton("Play");
        menuPanel.add(startButton,BorderLayout.WEST);
        startButton.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e)
            {   
        		if(!isGameOver && isReplaying == NO){
        			menuPanel.addKeyListener(new TAdapter());
        			animationTimer = new Timer();
    				TimerTask resumeTask = new TimerTask() 
    				{
    					@Override
    					public void run() {
    						animateGame();
    					}
    				};						
    				animationTimer.scheduleAtFixedRate(resumeTask, 0, TIMER_INTERVAL);
    				isGamePaused = NO;
        		}

        		menuPanel.requestFocusInWindow();
            }
        });
        
        
        JButton replayButton = new JButton("Replay");
        menuPanel.add(replayButton,BorderLayout.WEST);
        replayButton.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e)
            {
        		if((isGameOver || isGamePaused) && !boardState.isHistoryEmpty()){
        			        			
        			animationTimer = new Timer();
    				TimerTask resumeTask = new TimerTask() 
    				{
    					@Override
    					public void run() {    					    					
    						replayGame();
    					}
    				};						
  				animationTimer.scheduleAtFixedRate(resumeTask, 0, TIMER_INTERVAL);    				
        		}
            }
        });
               
        final int numberOfFrames = 10;
        
        final JButton undoButton = new JButton("undo");
        menuPanel.add(undoButton,BorderLayout.EAST);
        undoButton.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		boardState.undoAndExecute(numberOfFrames, ball, paddle, brick, w, h);
        		boardPanel.repaint();
        	}	
    	});
        
        initialize();
        boardState = new BoardState(ball, paddle, brick);
	}
	//amruta
	
//	public void replay()
//	{
//	if (!boardState.isHistoryEmpty())
//	  {			    	  
//		  State state = stateList.pop();
//		  //ball = new Ball(state.ballX, state.ballY, state.ballW, state.ballH);
//		  ball.setX(state.ballX);
//		  ball.setY(state.ballY);
//		  ball.setWidth(state.ballW);
//		  ball.setHeight(state.ballH);
//		  						  
////		  paddle = new Paddle((int)state.paddleX, (int)state.paddleY, state.paddleW, state.paddleH);
//		  paddle.setX(state.paddleX);
//		  paddle.setY(state.paddleY);
//		  paddle.setWidth(state.paddleW);
//		  paddle.setHeight(state.paddleH);
//		  
////		  brick = new Brick((int)state.brickX, (int)state.brickY, state.brickW, state.brickH);
//		  brick.setX(state.brickX);
//		  brick.setY(state.brickY);
//		  brick.setWidth(state.brickW);
//		  brick.setHeight(state.brickH);
//		  brick.setLife(state.brickLife);
//		  
//		  clock.setString(state.clockString);
//	  }
//	  else
//	  {
//		  isReplayOn = false;
//		  replayover = true;
//	  }
//	}
	
	
	
	//Amruta: added initialize
	public void initialize()
	{
		int paddlew= 20;
		int paddleh=200;
		int paddlex=(int) (w/2-paddlew/2);
		int paddley=(int) (h/2-paddleh/2);
		   
		  
		paddle = new Paddle(paddlex, paddley, paddlew, paddleh,this);
		paddle.screen_height=h;
		brick = new Brick(35, 55, this);
		
		ball = new Ball(this);
		//ball.width = 20;
		ball.setWidth(20);
		//ball.height = 20;
		ball.setHeight(20);
		//ball.x = 70;
		ball.setX(70);
		//ball.y = 30;
		ball.setY(30);
		
		ball.setXVelocity(1);
		ball.setYVelocity(1);
		//brick.x = w*3/4;
		brick.setX(w*3/4);
		//brick.y = h/2-brick.height/2;
		brick.setY(h/2-brick.getHeight()/2);
		
		//Clock clock = new Clock(this);
	}
	
	//Nikhil: created a separate method to access boardPanel
	void animateGame()
	{
		//checkCollisions();
		isGameOver = brick.isDead(ball);
		boardState.storeAndExecute(ball, paddle, brick, w, h);
		//notifyAllObservers();
		//saveState();
		boardPanel.repaint();
	}
	
	void replayGame(){
		  if (!boardState.isReplayOver()){
//			  State state = stateList.pop();
//			  ball = new Ball(state.ballX, state.ballY, state.ballW, state.ballH);
//			  paddle = new Paddle((int)state.paddleX, (int)state.paddleY, state.paddleW, state.paddleH);
//			  brick = new Brick((int)state.brickX, (int)state.brickY, state.brickW, state.brickH);
//			  System.out.println(brick.getX());			  			  
			  
			  boardState.replayAndExecute(ball, paddle, brick, w, h);
			  boardPanel.repaint();
		  }
		  else{
			  isReplaying = NO;
			  
			  animationTimer.cancel();
			  //animationTimer.purge();
			  animationTimer = null;
		  }	  	 	  	  	  	 
	}
	
	@Override
	public void notifyAllObservers(){
		Iterator<MyObserver> observerListIterator = observerList.iterator();
		
		while(observerListIterator.hasNext()){
			MyObserver observer = (MyObserver) observerListIterator.next();
			observer.update(ball, paddle, brick, w, h);
		}
	}	
	
/*		private class State{
		public double ballX, ballY, paddleX, paddleY, brickX, brickY;
		public int ballW, ballH, paddleW, paddleH, brickW, brickH;
		public int brickLife = 1;
		public String clockString;
		
		public State(Ball ball, Brick brick, Paddle paddle, Clock clock){
			ballX = ball.getX();
			ballY = ball.getY();
			ballW = ball.getWidth();
			ballH = ball.getHeight();
			paddleX = paddle.getX();
			paddleY = paddle.getY();
			//System.out.println(paddleY);
			
			paddleW = paddle.getWidth();
			paddleH = paddle.getHeight();
			brickX = brick.getX();
			brickY = brick.getY();
			brickW = brick.getWidth();
			brickH = brick.getHeight();
			brickLife = brick.get_life();
			
			clockString = clock.getString();
		}
	}*/
	


	
//	public void saveState(){
		
//		stateList.add(new State(ball,brick,paddle, clock));
		
//	}
	public void checkCollisions() /*throws InterruptedException*/{
		//double ballCenterx = ball.x + ball.width/2;
		//double ballCentery = ball.y + ball.height/2;
		
		double ballX = ball.getX();
		double ballY = ball.getY();
		int ballWidth = ball.getWidth();
		int ballHeight = ball.getHeight();
		
		double brickX = brick.getX();
		double brickY = brick.getY();
		int brickWidth = brick.getWidth();
		int brickHeight = brick.getHeight();
		
		double paddleX = paddle.getX();
		double paddleY = paddle.getY();
		int paddleWidth = ball.getWidth();
		int paddleHeight = ball.getHeight();
		
		double ballCenterX = ballX + ball.getWidth()/2;
		double ballCenterY = ballY + ball.getHeight()/2;
		
		/*if(ballX>=w||ballX<=0){
			ball.invertXVelocity();
		}
		if(ballY+ball.getHeight()>=h||ballY<=0){
			ball.invertYVelocity();
		}
		if(ball.intersects(paddle)){			
			
			if(isBallOutOfBrick){
				if(ballCenterX>=paddleX && ballCenterX<=(paddleX + paddle.getWidth())){
					ball.invertYVelocity();
				}
				else if(ballCenterY>=paddleY && ballCenterY<=(paddleY + paddle.getHeight())){
					ball.invertXVelocity();
				}
				else {
					ball.invertXVelocity();
					ball.invertYVelocity();
				}
			}
			
			isBallOutOfBrick = false;
		} else{
			isBallOutOfBrick = true;
		}*/
		if(brick.get_life()>0 && ball.intersects(brick)){
			/*if(ballCenterX>=brickX && ballCenterX<=(brickX+brick.getWidth())){
				ball.invertYVelocity();
			}
			else if(ballCenterY>=brickY && ballCenterY<=(brickY+brick.getHeight())){
				ball.invertXVelocity();
			}
			else {
				ball.invertXVelocity();
				ball.invertYVelocity();
			}
			
//			Thread.sleep(100);
			*/brick.dec_life();
			if(brick.get_life()==0)
				isGameOver=YES;
		}
	}
	private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }
	
	public  void registerObserver(MyObserver observer){
		observerList.add(observer);				
	}
	
	public  void unRegisterObserver(MyObserver observer){
		observerList.remove(observer);
	}

	
	
}
