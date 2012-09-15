/**
 * 
 */
package edu.indiana.p532.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import edu.indiana.p532.game.Ball;

/**
 * @author Viknes
 *
 */
public class BallChangeYDirectionCommandTest {

	/**
	 * Test method for {@link edu.indiana.p532.command.BallChangeYDirectionCommand#perform()}.
	 */
	@Test
	public void testPerform() {
		Ball ball = new Ball(10,20);
		ball.setVelocityX(3);
		ball.setVelocityY(3);
		BallChangeYDirectionCommand command = new BallChangeYDirectionCommand(ball);
		command.perform();
		assertNotNull(ball);
		assertEquals(ball.getX(),10);
		assertEquals(ball.getY(),20);
		assertEquals(ball.getVelocityX(),3);
		assertEquals(ball.getVelocityY(),-3);
	}

	/**
	 * Test method for {@link edu.indiana.p532.command.BallChangeYDirectionCommand#revert()}.
	 */
	@Test
	public void testRevert() {
		Ball ball = new Ball(10,20);
		ball.setVelocityX(3);
		ball.setVelocityY(-3);
		BallChangeYDirectionCommand command = new BallChangeYDirectionCommand(ball);
		command.revert();
		assertNotNull(ball);
		assertEquals(ball.getX(),10);
		assertEquals(ball.getY(),20);
		assertEquals(ball.getVelocityX(),3);
		assertEquals(ball.getVelocityY(),3);
	}

}
