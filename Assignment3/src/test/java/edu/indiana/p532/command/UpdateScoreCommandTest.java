/**
 * 
 */
package edu.indiana.p532.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import edu.indiana.p532.game.Score;

/**
 * @author Viknes
 *
 */
public class UpdateScoreCommandTest {

	/**
	 * Test method for {@link edu.indiana.p532.command.UpdateScoreCommand#perform()}.
	 */
	@Test
	public void testPerform() {
		Score score = new Score(50);
		UpdateScoreCommand command = new UpdateScoreCommand(score);
		command.perform();
		assertNotNull(score);
		assertEquals(score.getScore(),60);
	}

	/**
	 * Test method for {@link edu.indiana.p532.command.UpdateScoreCommand#revert()}.
	 */
	@Test
	public void testRevert() {
		Score score = new Score(60);
		UpdateScoreCommand command = new UpdateScoreCommand(score);
		command.revert();
		assertNotNull(score);
		assertEquals(score.getScore(),50);
	}

}
