package demo.demounittest;

import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

/**
 * this custom runner is only used to execute the method with @Test
 * 
 * @author Johnson Fu
 *
 */
public class MyRunner extends Runner {
	
	  private Class testClass;
	  public MyRunner(Class testClass) {
	      this.testClass = testClass;
	  }

	  @Override
	  public Description getDescription() {
	      return Description.createTestDescription(testClass, "My runner description");
	  }

	  @Override
	  public void run(RunNotifier notifier) {
	      System.out.println("running the tests from MyRunner. " + testClass);
	      try {
	          Object testObject = testClass.newInstance();
	          for (Method method : testClass.getMethods()) {
	              if (method.isAnnotationPresent(Test.class)) {
	                  notifier.fireTestStarted(Description.EMPTY);
	                  method.invoke(testObject);
	                  notifier.fireTestFinished(Description.EMPTY);
	              }
	          }
	      } catch (Exception e) {
	          throw new RuntimeException(e);
	      }

	  }
}
