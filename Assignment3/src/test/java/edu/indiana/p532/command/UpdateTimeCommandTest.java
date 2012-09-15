/**
 * 
 */
package edu.indiana.p532.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.indiana.p532.game.TimePanel;

/**
 * @author Viknes
 *
 */
public class UpdateTimeCommandTest {

	/**
	 * Test method for {@link edu.indiana.p532.command.UpdateTimeCommand#perform()}.
	 */
	@Test
	public void testPerform() {
		TimePanel timePanel = new TimePanel();
		assertEquals(timePanel.getTimeField().getText(),"0:0");
		UpdateTimeCommand command = new UpdateTimeCommand(timePanel);
		command.perform();
		timePanel.update();
		assertEquals(timePanel.getTimeField().getText(),"0:1");
	}

	/**
	 * Test method for {@link edu.indiana.p532.command.UpdateTimeCommand#revert()}.
	 */
	@Test
	public void testRevert() {
		TimePanel timePanel = new TimePanel();
		UpdateTimeCommand command = new UpdateTimeCommand(timePanel);
		command.perform();
		timePanel.update();
		assertEquals(timePanel.getTimeField().getText(),"0:1");
		command.revert();
		timePanel.update();
		assertEquals(timePanel.getTimeField().getText(),"0:0");
	}

}
