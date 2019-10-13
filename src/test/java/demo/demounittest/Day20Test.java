package demo.demounittest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;

/**
 * parameter test
 * 
 * @author Johnson Fu
 *
 */
@RunWith(JUnitParamsRunner.class)
public class Day20Test {

	@Test
	@Parameters(value = {
	        "0, 0, 0, print1, true",
	        "1| 1| 2| print2| false"
	})
	@TestCaseName("test case 1 -> first: {0}, second: {1}, expect: {2}, print: {3}, something: {4}")
	public void testCase1(int first, int second, int expectedSum, String print, boolean trueFalse) {
	    Calculator calculator = new Calculator();

	    int actualSum = calculator.add(first, second);

	    System.out.println(print + ", " + trueFalse);

	    assertThat(actualSum).isEqualByComparingTo(expectedSum);
	}
	
	@Test
	@Parameters(method = "testAddData")
	public void testCase2(Issue issue, int second, long expectedSum) {
	    Calculator calculator = new Calculator();

	    long actualSum = calculator.add(issue.getId(), (long)second);

	    assertThat(actualSum).isEqualByComparingTo(expectedSum);
	}

	private Object[] testAddData() {
	    return new Object[]{
	            new Object[]{Issue.builder().id(1L).build(), 2, 3},
	            new Object[]{Issue.builder().id(5L).build(), 10, 15}
	    };
	}
	
	@Test
	@FileParameters("src/test/resources/parameters.csv")
	public void testCase3(int first, int second, int expectedSum) {
	    Calculator calculator = new Calculator();

	    int actualSum = calculator.add(first, second);

	    assertThat(actualSum).isEqualByComparingTo(expectedSum);
	}
	

}
