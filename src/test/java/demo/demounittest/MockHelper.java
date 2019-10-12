package demo.demounittest;

import static org.mockito.Mockito.mock;

public class MockHelper {
	
    public static <T> T stub(Class<T> stubClass) {
        return mock(stubClass);
    }

}
