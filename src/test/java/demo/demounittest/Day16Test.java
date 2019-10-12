package demo.demounittest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * use fake repository to test
 * 
 * the fake one is similar to the real one..
 * 
 * @author Johnson Fu
 *
 */
public class Day16Test {
	
    private static final Long ID = 1L;
    private static final String TITLE = "ISSUE";

    private IssueNewRepository repository = new IssueRepositoryFake();

    
    @Before
    public void setup() {
    }

    @Test
    public void saveAndFindIssue() {
        Issue issue = new Issue();
        issue.setTitle(TITLE);

        Issue saved = repository.save(issue);

        assertThat(saved.getId()).isEqualTo(ID);
        assertThat(saved.getTitle()).isEqualTo(TITLE);

        Issue found = repository.findById(saved.getId());

        assertThat(found.getId()).isEqualTo(ID);
        assertThat(found.getTitle()).isEqualTo(TITLE);
    }

}
