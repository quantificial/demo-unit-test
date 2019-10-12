package demo.demounittest;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * use mock to test
 * @author Johnson Fu
 *
 */
public class Day14Test {

	
	private IssueNewRepository repo;
	
	
	@Before
	public void setup() {
		repo = MockHelper.stub(IssueNewRepository.class);
	}
	
	@Test
	public void test() {
		
		given(repo.count()).willReturn(10L);
		
		long actualCount = repo.count();
		
		assertThat(actualCount).isEqualTo(10L);
		
	}
	
	@Test
	public void test2() {
		
        Issue expected = new Issue();
        given(repo.findById(1L)).willReturn(expected);

        Issue actual = repo.findById(1L);
        //Issue actual = repo.findById(2L);

        assertThat(actual).isSameAs(expected);		
	}
	
	@Test
	public void test3() {
        Issue expected = new Issue();

        given(repo.findById(anyLong())).willReturn(expected);

        Issue actual = repo.findById(99L);

        assertThat(actual).isSameAs(expected);		
	}
	
    @Test(expected = Exception.class)
    public void test4() {

        given(repo.findById(1L)).willThrow(new Exception());
        repo.findById(1L);
        
    }
    
    @Test(expected = Exception.class)
    public void test5() {
    	Issue deleted = new Issue();
    	willThrow(new Exception()).given(repo).delete(deleted);
    	
    	repo.delete(deleted);
    }
    
    @Test
    public void test6() { 
        Issue expected = new Issue();

        given(repo.findById(1L)).willAnswer(new Answer<Issue>() {
            @Override
            public Issue answer(InvocationOnMock invocation) {
                Long idParameter = (Long) invocation.getArguments()[0];
                if (idParameter.equals(1L)) {
                    return expected;
                } else {
                    return null;
                }
            }
        });

        Issue actual = repo.findById(1L);

        assertThat(actual).isSameAs(expected);
    }
	
    
	
}
