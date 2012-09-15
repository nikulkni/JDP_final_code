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
public class BallChangeXDirectionCommandTest {

	/**
	 * Test method for {@link edu.indiana.p532.command.BallChangeXDirectionCommand#perform()}.
	 */
	@Test
	public void testPerform() {
		Ball ball = new Ball(10,20);
		ball.setVelocityX(3);
		ball.setVelocityY(3);
		BallChangeXDirectionCommand command = new BallChangeXDirectionCommand(ball);
		command.perform();
		assertNotNull(ball);
		assertEquals(ball.getX(),10);
		assertEquals(ball.getY(),20);
		assertEquals(ball.getVelocityX(),-3);
		assertEquals(ball.getVelocityY(),3);
	}

	/**
	 * Test method for {@link edu.indiana.p532.command.BallChangeXDirectionCommand#revert()}.
	 */
	@Test
	public void testRevert() {
		Ball ball = new Ball(10,20);
		ball.setVelocityX(-3);
		ball.setVelocityY(3);
		BallChangeXDirectionCommand command = new BallChangeXDirectionCommand(ball);
		command.revert();
		assertNotNull(ball);
		assertEquals(ball.getX(),10);
		assertEquals(ball.getY(),20);
		assertEquals(ball.getVelocityX(),3);
		assertEquals(ball.getVelocityY(),3);
	}

}
