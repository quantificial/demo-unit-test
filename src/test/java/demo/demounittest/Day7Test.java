package demo.demounittest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

/**
 * 
 * use temporary folder rule
 * 
 * create custom rule
 *
 */
public class Day7Test {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();
    
    @Rule
    public final TestPrinter printer = new TestPrinter();

    private static final Long ID = 1L;
    private IssueService issueService;
    
    @Before
    public void setup() {issueService = new IssueService();}

    @Test
    public void queryById_ShouldThrowException() {
        thrown.expect(EntityNotFoundException.class);
        issueService.queryById(ID);
    }
    
    @Test
    public void testUsingTempFolder() throws IOException {
        File createdFile = folder.newFolder("testFolder");
        assertThat(createdFile).isFile();
    }
    
    @Test
    public void testCase1() {
        System.out.println("test case 11111");
    }

    @Test
    public void testCase2() {
        System.out.println("test case 22222");
    }
}
