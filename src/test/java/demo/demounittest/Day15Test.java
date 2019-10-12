package demo.demounittest;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class Day15Test {

	private IssueNewRepository repository;
	
	@Before
	public void setup() {
		repository = MockHelper.stub(IssueNewRepository.class);
	}
	
	@Test
	public void test() {
        repository.findById(1L);

        verify(repository).findById(1L);
	}
	
    @Test
    public void verifyThatMethodWasCalledOnce() {

        repository.findById(1L);
        repository.findById(1L);

        verify(repository, times(2)).findById(1L);
    }	
	
    @Test
    public void verifyThatMethodWasNotInvoked() {

        verify(repository, never()).findById(1L);
    }
    
    @Test
    public void verifyThatNoOtherMethodsWereInvoked() {

        repository.findById(1L);
        repository.findById(1L);

        verify(repository, times(2)).findById(1L);

        // no more action other than call two times findbyId
        verifyNoMoreInteractions(repository);
    }
    
    @Test
    public void verifyThatMethodWasInvokedByPassingAnyLongAsMethodParameter() {

        repository.findById(3L);

        verify(repository).findById(anyLong());
        
        
    }
    
    @Test
    public void verifyThatMethodWasInvokedByPassing1LAsMethodParameter() {

        repository.findById(1L);

        verify(repository).findById(eq(1L));
    }
    
    @Test
    public void verifyThatMethodWasInvokedByUsingArgumentCaptor() {
        repository.findById(1L);

        ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);

        verify(repository).findById(argument.capture());

        Long actualId = argument.getValue();

        assertThat(actualId).isEqualTo(1L);
    }
    
//    @Test
//    public void verifyThatMethodWasInvokedByUsingArgumentCaptorJava8() {
//        repository.findById(1L);
//
//        verify(repository).findById(
//                assertArg(
//                        argument -> assertThat(argument).isEqualTo(1L)
//                )
//        );
//    }
    
	
}
