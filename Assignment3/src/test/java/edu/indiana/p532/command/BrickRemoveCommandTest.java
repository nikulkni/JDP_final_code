/**
 * 
 */
package edu.indiana.p532.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import edu.indiana.p532.game.Brick;

/**
 * @author Viknes
 *
 */
public class BrickRemoveCommandTest {

	/**
	 * Test method for {@link edu.indiana.p532.command.BrickRemoveCommand#perform()}.
	 */
	@Test
	public void testPerform() {
		Brick brick = new Brick(10,20);
		BrickRemoveCommand command = new BrickRemoveCommand(new ArrayList<Brick>(),brick);
		command.perform();
		assertNotNull(brick);
		assertTrue(brick.isHit());
	}

	/**
	 * Test method for {@link edu.indiana.p532.command.BrickRemoveCommand#revert()}.
	 */
	@Test
	public void testRevert() {
		Brick brick = new Brick(10,20);
		brick.setHit(true);
		BrickRemoveCommand command = new BrickRemoveCommand(new ArrayList<Brick>(),brick);
		command.revert();
		assertNotNull(brick);
		assertFalse(brick.isHit());
	}

}
