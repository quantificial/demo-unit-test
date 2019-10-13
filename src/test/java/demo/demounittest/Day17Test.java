package demo.demounittest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * demo of writing manual spy object and using mockito spy object
 * 
 * demo of using category and with surefire
 * 
 * configure in the pom file to execute the UnitTest group unit tests only
 */
@Category(UnitTest.class) // group this unit test into UnitTest.class and could be execute and configure on surefire plugin 
public class Day17Test {
	
    private static final String TITLE = "TITLE";
    
    // manual spy object
    private IssueRepositorySpy repository;
    
    // test with mockito spy
    private IssueNewRepository repo;
    
    private IssueNewRepository repo1;

    @Before
    public void setup() {
        repository = new IssueRepositorySpy();
        repo = spy(new IssueNewRepositoryImpl());
        
        // create stub by using mockito stud and then apply spy
        repo1 = MockHelper.stub(IssueNewRepository.class);        
        repo1 = spy(repo1);
    }

    @Test
    public void verifySaveMethod() {

    	Issue issue = new Issue();
        issue.setTitle(TITLE);

        repository.save(issue);

        int executeCount = repository.getSaveExecuteCount();

        assertThat(executeCount).isEqualTo(1);

        List<Issue> savedIssueList = repository.getSavedIssueList();

        assertThat(savedIssueList).containsExactly(issue);

    }
    
    /**
     * spy implemented repo
     */
    @Test
    public void test() {
    	
    	Issue expected = new Issue();
    	
        Issue saved = repo.save(expected);

        verify(repo, times(1)).save(saved);
    }
    
    @Test
    public void testReturnObject() {

        Issue expected = new Issue();
        
        // not using the following as we want to keep it simple and not affect by the repo
        // given(repo.save(any())).willReturn(expected);
        
        willReturn(expected).given(repo).save(any());

        Issue saved2 = repo.save(expected);

        assertThat(saved2).isSameAs(expected);
    }
    
    @Test
    public void test2() {

    	
        Issue expected = new Issue();
        
        BDDMockito.willReturn(expected).given(repo1).save(any());

        Issue saved2 = repo1.save(expected);

        assertThat(saved2).isSameAs(expected);
    }

}
