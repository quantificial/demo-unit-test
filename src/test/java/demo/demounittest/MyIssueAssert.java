package demo.demounittest;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.SoftAssertions;

import demo.demounittest.Issue.ActiveState;

public class MyIssueAssert extends AbstractAssert<MyIssueAssert, Issue> {

	public MyIssueAssert(Issue actual, Class<?> selfType) {
		super(actual, selfType);
		
	}
	
	private MyIssueAssert(Issue actual) {
		super(actual, MyIssueAssert.class);
	}
	
    public static MyIssueAssert assertThatIssue(Issue actual) {
        return new MyIssueAssert(actual);
    }
    
    public MyIssueAssert isResolved() {
        SoftAssertions assertions = new SoftAssertions();

        assertions.assertThat(actual.getActiveState())
                .overridingErrorMessage(
                		 "solved issue's active state must be done, but it is: %s",
                        actual.getActiveState()
                )
                .isEqualTo(ActiveState.DONE);

        assertions.assertThat(actual.getLog())
                .overridingErrorMessage(
                		"solved issue's log must contain data: %s",
                        actual.getLog()
                )
                .isNotNull();

        assertions.assertAll();
        return this;
    }
}
