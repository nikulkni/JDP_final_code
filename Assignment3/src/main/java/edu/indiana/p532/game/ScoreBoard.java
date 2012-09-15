package edu.indiana.p532.game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class to keep the track of the score during the game play and to update
 * it
 * 
 * @param score
 *            Object of the JTextField
 * @param scoreLabel
 *            Object of the Jlabel class
 * @param currentScore
 *            contains the current score in the game
 */

public class ScoreBoard{

	private JTextField score;
	private JLabel scoreLabel;
	private int currentScore = 0;
	private JPanel scoreBoard;
	
	public ScoreBoard() {
		
		//Nikhil: added JPanel as a member rather than making this a JPanel
		scoreBoard = new JPanel();
		
		score = new JTextField(5);
		score.setEditable(false);
		score.setFont(new Font("Times New Roman", Font.BOLD, 18));
		score.setText("0");
		score.setHorizontalAlignment(JTextField.CENTER);
		currentScore = 0;
		
		JPanel scorePanel = new JPanel();
		scoreLabel = new JLabel();
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setText("Score: ");
		scorePanel.add(scoreLabel);
		scorePanel.add(score);
		scorePanel.setBackground(Color.BLACK);

		scoreBoard.setLayout(new GridLayout(1, 1));
		scoreBoard.add(scorePanel);

	}

	public JTextField getScore() {
		return this.score;
	}

	public int getCurrentScore() {
		return this.currentScore;
	}

	public void setScore(String currentScore) {
		this.score.setText("" + currentScore);
	}
	
	public void addToContainer(Container container,String constraints){
		container.add(scoreBoard, constraints);
	}

	public void addToContainer(Container container){
		container.add(scoreBoard);
	}
}
