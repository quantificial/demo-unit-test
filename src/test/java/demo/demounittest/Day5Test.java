package demo.demounittest;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import demo.demounittest.Issue.ActiveState;

/**
 * check object states and also create assert object to verify the test instead of coding all
 * the assertion in the test method
 *
 */
public class Day5Test {
	
	@Test
	public void test() { 
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setActiveState(ActiveState.DONE);
        issue.setCreator("JF");
        issue.setLog("this is the root cause");

        SoftAssertions assertions = new SoftAssertions();

        assertions.assertThat(issue.getActiveState())
                .overridingErrorMessage(
                        "solved issue's active state must be done, but it is: %s",
                        issue.getActiveState()
                )
                .isEqualTo(ActiveState.DONE);

        assertions.assertThat(issue.getLog())
                .overridingErrorMessage(
                        "solved issue's log must contain data: %s",
                        issue.getLog()
                )
                .isNotNull();

        assertions.assertAll();
	}
	
	@Test
	public void testIssue1() {
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setActiveState(ActiveState.DONE);
        issue.setCreator("JF");

        MyIssueAssert.assertThatIssue(issue).isResolved();		
	}
	
	@Test
	public void testIssue2() {
        Issue issue = new Issue();
        issue.setId(2L);
        issue.setActiveState(ActiveState.DONE);
        issue.setCreator("JF");
        issue.setLog("everything is ok.");

        MyIssueAssert.assertThatIssue(issue).isResolved();		
	}

}
