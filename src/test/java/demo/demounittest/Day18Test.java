package demo.demounittest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * custom Runner
 * 
 * the runner is used to define how to run test, such as the method annotated with Test or Before etc..
 * 
 * normal...
 * 
 * in before class...
 * in before....
 * in test1 method...
 * in before....
 * in test2 method...
 * 
 * 
 * with custom runner
 * 
 * running the tests from MyRunner. class demo.demounittest.Day18Test
 * in test2 method...
 * in test1 method...
 * 
 * @author Johnson Fu
 *
 */
@RunWith(MyRunner.class)
public class Day18Test {

	@Before
	public void before() {
		System.out.println("in before....");
	}
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("in before class...");
	}
	
	@Test
	public void test1() {
		System.out.println("in test1 method...");
	}

	@Test
	public void test2() {
		System.out.println("in test2 method...");
	}

	
}
