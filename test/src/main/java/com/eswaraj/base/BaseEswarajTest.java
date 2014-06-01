package com.eswaraj.base;



import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;

public class BaseEswarajTest extends BaseTest {

	private Mockery mockery;

	/**
	 * Provide access to the Mockery. Ordinarily the subclass won't need access
	 * to this, since it can simply call mock() and expect(). But if the
	 * subclass needs to do something fancy, here you go.
	 */
	protected final Mockery getMockery() {
		return mockery;
	}

	/**
	 * Set up the Mockery, which happens before every test
	 */
	@Before
	public void beforeEveryTest() {
		UNIQUENESS_GROUPS_BY_CLASS.clear();
		mockery = new JUnit4Mockery() {
			{
				// Allows mocking of classes and not just interfaces
				setImposteriser(ClassImposteriser.INSTANCE);
			}
		};
	}

	/**
	 * Assert that any Expectations set up in the Mockery have been satisfied
	 * after every test
	 */
	@After
	public final void afterEveryTest() {
		mockery.assertIsSatisfied();
	}

	/**
	 * Set up expectations to be asserted at the end of the test
	 */
	protected final void expect(Expectations e) {
		mockery.checking(e);
	}

	/**
	 * Create a mock object of the given type
	 */
	protected final <T> T mock(Class<T> clazz, String... name) {
		if (name == null || name.length == 0) {
			return mockery.mock(clazz);
		} else {
			return mockery.mock(clazz, name[0]);
		}
	}


}
