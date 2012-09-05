package breakout;
/*
 * Code adapted from http://zetcode.com/tutorials/javagamestutorial/basics/
 */

import javax.swing.JFrame;

public class Breakout{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	public Breakout(){
		
		JFrame gameFrame = new JFrame();
		Board board = new Board(gameFrame);
		//gameFrame.add(new Board());
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(800,580);
		gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setTitle("Breakout");
		gameFrame.setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Breakout();
	}

}
