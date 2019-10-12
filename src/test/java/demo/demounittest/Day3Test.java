package demo.demounittest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class Day3Test {

	@Test
	public void test1() {
        // 1. Arrange
        Calculator calculator = new Calculator();
        int number1 = 5;
        int number2 = 10;
        int excepted = 15;

        // 2. Act
        int actual = calculator.add(5, 10);

        // 3. Assert
        assertThat(actual).isEqualTo(excepted);
	}
}
