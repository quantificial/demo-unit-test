package demo.demounittest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Spring Environment Unit Test
 * 
 * SpringRunner Example
 * 
 * SpringRunner is alias to SpringJUnit4ClassRunner
 * 
 * @author Johnson Fu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoUnitTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class DayAfter01Test {
	
	@Autowired
	private IssueService issueService;
	
    @Test
    public void mustEqualTest() {
        assertThat("abc123").isEqualTo("abc123");
    }
    
    @Test
    public void checkIssueService() {
    	
    	Issue issue = issueService.create(new Issue());
    	assertThat(issue).isNotNull();
    
    }
}
