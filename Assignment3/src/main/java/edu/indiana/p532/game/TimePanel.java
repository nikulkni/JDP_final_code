package edu.indiana.p532.game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TimePanel implements Observer {

	private JTextField timeField;
	private JLabel timeLabel;

	private int timeDisplay;
	private int min = 0;
	private int sec = 0;
	
	//Nikhil: this has JPanel as a member and does not extend JPanel
	private JPanel timePanelParent;
	
	ControlClass controlClass;

	public TimePanel() {
		timePanelParent = new JPanel();
		
		timeField = new JTextField(5);
		timeField.setEditable(false);
		timeField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		timeField.setText("0:0");
		timeField.setHorizontalAlignment(JTextField.CENTER);

		JPanel timePanel = new JPanel();
		timeLabel = new JLabel();
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setText("Clock: ");
		timePanel.add(timeLabel);
		timePanel.add(timeField);
		timePanel.setBackground(Color.BLACK);

		timePanelParent.setLayout(new GridLayout(1, 1));
		timePanelParent.add(timePanel);
	}

	public void timeRegister(ControlClass controlClass) {
		this.controlClass = controlClass;
		controlClass.registerObserver(this);
	}

	public void setTimeIncrease() {
		this.timeDisplay++;
	}

	public void setTimeDecrease() {
		this.timeDisplay--;
	}

	public void timeReset() {
		this.timeDisplay = 0;
	}

	public void update() {

		min = this.timeDisplay / 60;
		sec = this.timeDisplay % 60;
		timeField.setText(min + ":" + sec);
	}
	
	public JTextField getTimeField() {
		return timeField;
	}

	public void setTimeField(JTextField timeField) {
		this.timeField = timeField;
	}

	//Nikhil:
	public void addToContainer(Container container,String constraints){
		container.add(timePanelParent, constraints);
	}

	public void addToContainer(Container container){
		container.add(timePanelParent);
	}
}
