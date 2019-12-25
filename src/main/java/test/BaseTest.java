package test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by Vladimir Trandafilov on 29.11.2019.
 */
@RunWith(JUnit4.class)
public abstract class BaseTest {

	@Rule
	public final TestWatcher watcher = new TestWatcher() {
		@Override
		protected void succeeded(final Description description) {
			System.out.printf("Test '%s' - PASSED\n", description.getMethodName());
			super.succeeded(description);
		}

		@Override
		protected void failed(final Throwable e, final Description description) {
			System.out.printf("Test '%s' - FAILED\nReason: '%s'\n", description.getMethodName(), e.getMessage());
			super.failed(e, description);
		}

		@Override
		protected void starting(final Description description) {
			System.out.printf("Test '%s' - starting...", description.getMethodName());
			super.starting(description);
		}
	};
	
	protected void assertAll(Consumer<Boolean>... assertions) {
		List<AssertionError> errors = new ArrayList<>();
		
		for(Consumer<Boolean> assertion : assertions) {
			try {
				assertion.accept(true);
			} catch (AssertionError ae) {
				errors.add(ae);
			}
		}
		
		assert errors.isEmpty() : errors
				.stream()
				.map(assertionError -> assertionError.getMessage().replace("java.lang.AssertionError:", ""))
				.collect(Collectors.toList())
				.toString();
	}
	
	
}
