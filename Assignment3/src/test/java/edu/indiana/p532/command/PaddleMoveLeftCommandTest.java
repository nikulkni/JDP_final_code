/**
 * 
 */
package edu.indiana.p532.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import edu.indiana.p532.game.Paddle;

/**
 * @author Viknes
 *
 */
public class PaddleMoveLeftCommandTest {

	/**
	 * Test method for {@link edu.indiana.p532.command.PaddleMoveLeftCommand#perform()}.
	 */
	@Test
	public void testPerform() {
		Paddle paddle = new Paddle(50,100);
		PaddleMoveLeftCommand command = new PaddleMoveLeftCommand(paddle);
		command.perform();
		assertNotNull(paddle);
		assertEquals(paddle.getX(),20);
		assertEquals(paddle.getY(),100);
	}

	/**
	 * Test method for {@link edu.indiana.p532.command.PaddleMoveLeftCommand#revert()}.
	 */
	@Test
	public void testRevert() {
		Paddle paddle = new Paddle(20,100);
		PaddleMoveLeftCommand command = new PaddleMoveLeftCommand(paddle);
		command.revert();
		assertNotNull(paddle);
		assertEquals(paddle.getX(),50);
		assertEquals(paddle.getY(),100);
	}

}
