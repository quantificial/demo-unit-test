package demo.demounittest;

import org.junit.Test;

import demo.demounittest.Issue.ActiveState;

/**
 * use builder to create test data
 * 
 * @author Johnson Fu
 *
 */
public class Day10Test {
	
	
	@Test
	public void test() {
		Issue issue = Issue.builder().creator("JF").activeState(ActiveState.DONE).build();
		
		
	}

}
