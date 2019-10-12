package demo.demounittest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import demo.demounittest.Issue.ActiveState;

public class Day9Test {

	/**
	 * use method to create data object
	 * @return
	 */
	public Issue createIssue() {
		Issue issue = new Issue();
		
		issue.setCreator("JF");
		issue.setActiveState(ActiveState.PROGRESS);
		
		return issue;
	}
	
	@Test
	public void test() {
		
		Issue issue = this.createIssue();
		
		assertThat(issue.getCreator()).isEqualTo("JF");
	}
	
	/**
	 * use factory to create data object
	 */
	@Test
	public void test2() {
		Issue issue = FactoryIssue.createIssueWithCreatorId("JF");
		
		assertThat(issue.getCreator()).isEqualTo("JF");
	}
	
}
