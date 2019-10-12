package demo.demounittest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * use custom builder
 * @author Johnson Fu
 *
 */
public class Day11Test {

	@Test
	public void test() {
		
		Issue issue = new IssueBuilder().withOnlyCreator("JF").build();
		
		assertThat(issue.getCreator()).isEqualTo("JF");
	}
}
