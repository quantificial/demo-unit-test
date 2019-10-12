package demo.demounittest;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

/**
 * demo of using soft assertion that keep the assertion within 
 * the test method continue to run even hit assertion failure
 *
 */
public class Day4Test {
	
	// if one assert failed, the execution will stop
    @Test
    public void testHardAssertion() {
        int actual1 = 5;

        String actual2 = "10";

        assertThat(actual1).isLessThan(4);

        assertThat(actual2).isEqualTo("11");
    }
    
    @Test
    public void softTest() {
        int actual1 = 5;
        String actual2 = "10";

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actual1).isLessThan(4);

        softAssertions.assertThat(actual2).isEqualTo("11");

        softAssertions.assertAll();
    }
}
