package demo.demounittest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Day2Test {

	/**
	 * before all the testing
	 */
    @BeforeClass
    public static void beforeClass() {
        System.out.println("before all the testing...");
    }

    @Before
    public void before() {
        System.out.println("before each test");
    }

    @After
    public void after() {
        System.out.println("after each test");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("after all the testing...");
    }

    @Test
    public void Case1() {
        System.out.println("test case 1");
    }

    @Test
    public void Case2() {
        System.out.println("test case 2");
    }

    @Test
    public void Case3() {
        System.out.println("test case 3");
    }
}
