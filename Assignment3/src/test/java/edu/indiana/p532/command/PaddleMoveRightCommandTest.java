/**
 * 
 */
package edu.indiana.p532.command;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.indiana.p532.game.Paddle;

/**
 * @author Viknes
 *
 */
public class PaddleMoveRightCommandTest {

	/**
	 * Test method for {@link edu.indiana.p532.command.PaddleMoveRightCommand#perform()}.
	 */
	@Test
	public void testPerform() {
		Paddle paddle = new Paddle(50,100);
		PaddleMoveRightCommand command = new PaddleMoveRightCommand(paddle);
		command.perform();
		assertNotNull(paddle);
		assertEquals(paddle.getX(),80);
		assertEquals(paddle.getY(),100);
	}

	/**
	 * Test method for {@link edu.indiana.p532.command.PaddleMoveRightCommand#revert()}.
	 */
	@Test
	public void testRevert() {
		Paddle paddle = new Paddle(80,100);
		PaddleMoveRightCommand command = new PaddleMoveRightCommand(paddle);
		command.revert();
		assertNotNull(paddle);
		assertEquals(paddle.getX(),50);
		assertEquals(paddle.getY(),100);
	}

}
