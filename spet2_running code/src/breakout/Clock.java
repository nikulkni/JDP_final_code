package breakout;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class Clock implements MyObserver
{
	//Time time ;
	
	private Date date; 
	private Format format;
	private String string;
	
	long seconds;
	MyObservable board;
	long addSeconds = 2;
	
	public Clock(MyObservable board)
	{
	//	time = new Time(0);
		date = new Date();
		seconds = 0;
		date.setTime(seconds);			
		
		//formatter = new SimpleDateFormat("HH.mm.ss");
		
		this.board = board;
		board.registerObserver(this);
	}
	
	public String getString(){
		return this.string;
	}
	
		
	@Override
	public void update( Ball ball, Paddle paddle, Brick brick, int frameWidth,
			int farmeHeight) 
	{
		seconds += addSeconds;
		
		//TODO: Check time for TimeStamp
			date.setTime(seconds);
			format = new SimpleDateFormat("mm"+":"+"ss");
			string = format.format(date);
			//where do we paint the timer?
			//System.out.println("aaaa"+timeString);
	}

	@Override
	public void update(int frameWidth, int farmeHeight) 
	{
		
	}

	public void setString(String clockString) {
		// TODO Auto-generated method stub
		this.string = clockString;
		
	}

	

}