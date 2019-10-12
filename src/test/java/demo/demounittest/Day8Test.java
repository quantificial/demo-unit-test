package demo.demounittest;

import org.junit.Test;

import demo.demounittest.Issue.ActiveState;

/**
 * create test data
 * @author Johnson Fu
 *
 */
public class Day8Test {

	/**
	 * not a good approach to code the test data in the test method
	 */
	@Test
	public void test() {
		Issue issue = new Issue();
		issue.setCreator("JF");
		issue.setActiveState(ActiveState.PROGRESS);
	}
}
