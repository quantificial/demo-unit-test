package demo.demounittest;

import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

/**
 * perform layering unit test
 * 
 * @author Johnson Fu
 *
 */
@RunWith(HierarchicalContextRunner.class)
public class Day19Test {
    private IssueService mockService;
    
    /**
     * first layer to perform the unit test setup
     */
    @BeforeClass
    public static void rootBeforeClass() {
        System.out.println("root before class");
    }

    @Before
    public void rootBefore() {
        System.out.println("root before");
        mockService = mock(IssueService.class);
    }

    @After
    public void rootAfter() {
        System.out.println("root after");
    }

    @AfterClass
    public static void rootAfterClass() {
        System.out.println("root after class");
    }

    public class FindById {

    	/**
    	 * second layer for setup again
    	 */
        public static final long ID = 1L;

        @Before
        public void findByIdBefore() {
            System.out.println("findById before");
        }

        @After
        public void findByIdAfter() {
            System.out.println("findById after");
        }

        public class WhenIssueFound {

        	/**
        	 * third layer for setup and perform the test
        	 */
            @Before
            public void FindByIdWhenIssueFoundBefore() {
                System.out.println("findById when issue found before");
                BDDMockito.given(mockService.queryById(ID)).willReturn(new Issue());
            }

            @Test
            public void testCase(){
                System.out.println("find by id when issue found test case");
            }
        }

        public class WhenIssueNotFound {

            @Before
            public void FindByIdWhenIssueNotFoundBefore() {
                System.out.println("findById when issue found before");
                BDDMockito.given(mockService.queryById(ID)).willReturn(new Issue());
            }

            @Test
            public void testCase(){
                System.out.println("find by id when issue not found test case");
            }
        }
    }
}
