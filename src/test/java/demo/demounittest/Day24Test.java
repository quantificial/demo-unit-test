package demo.demounittest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring testing
 * 
 * get big issue test
 * 
 * @author Johnson Fu
 *
 */
@Category(UnitTest.class)
@RunWith(SpringJUnit4ClassRunner.class) // use spring supported JUnit Runner
@ContextConfiguration(classes = {IssueContext.class}) // apply the context configuration
public class Day24Test {

	// since the context has been setup in the annotation,
	// the required bean could be injected to the overwired fields.
    @Autowired
    private IssueServiceImp issueService;
    
    @Test
    public void shouldReturnBigIssue() {
        Issue bigIssue = issueService.getBigIssue();
        String title = bigIssue.getTitle();
        assertThat(title).isEqualTo("BIG");
    }
}
