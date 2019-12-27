package test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private final Logger LOG = LogManager.getLogger(BaseTest.class);

	@Rule
	public final TestWatcher watcher = getWatcher();
	
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
	
	protected TestWatcher getWatcher() {
		return new TestWatcher() {
			@Override
			protected void succeeded(final Description description) {
				LOG.info("Test '{}' - PASSED\n", description.getMethodName());
				super.succeeded(description);
			}

			@Override
			protected void failed(final Throwable e, final Description description) {
				LOG.error("Test '{}' - FAILED\nReason: {}\n", description.getMethodName(), e.getMessage());
				super.failed(e, description);
			}

			@Override
			protected void starting(final Description description) {
				LOG.info("Test '{}' - starting...", description.getMethodName());
				super.starting(description);
			}
		};
	}
}
