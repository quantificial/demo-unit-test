package demo.demounittest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * use stub to test with dependency 
 * 
 * @author Johnson Fu
 *
 */
public class Day13Test {
	
    private static final Long ID = 66L;
    private IssueRepository repository;
	
    @Before
	public void setup() {
		Issue configuredIssue = new Issue();
		configuredIssue.setCreator("JF");
		
		// create stub and assign pre-defined object data to the stub
		repository = new IssueRepositoryStub(configuredIssue);
	}
    
    @Test
    public void test() {
    	
    	IssueServiceImp issueServiceImp = new IssueServiceImp();
        issueServiceImp.setRepository(repository);
        Issue issue = new Issue();
        issue.setId(ID);
        
        // it will return the pre-defined data object
        Issue actual = issueServiceImp.find(issue);

        assertThat(actual.getCreator()).isEqualTo("JF");
    	
    }

}
