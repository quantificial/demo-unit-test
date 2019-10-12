package demo.demounittest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * test service and catch exception
 * 
 * 1. use test
 * 2. use catchThrowable
 * 3. ExpectedException Rule
 *
 */
public class Day6Test {

    private static final long ID = 1L;

    private IssueService issueService;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup () {
        issueService = new IssueService();
    }

    @Test(expected = EntityNotFoundException.class)
    public void queryById_shouldThrowException () {
        issueService.queryById(ID);
    }
    
    @Test
    public void queryById_shouldThrowException2 () {
        Throwable throwable = catchThrowable(() -> issueService.queryById(ID));
        assertThat(throwable)
                .isExactlyInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("cannot find the instance");
    }
    
    @Test
    public void queryById_ShouldThrowException3() {
        thrown.expect(EntityNotFoundException.class);
        issueService.queryById(ID);
    }
    
    /**
     * assert the exception thrown contain the field ID which is the same as our expectation
     */
    @Test
    public void queryById_ShouldThrowException4() {
        thrown.expect(hasProperty("id", is(ID)));
        issueService.queryById(ID);
    }
}
