package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CreateAccountAndPasswordTest.class, CreateMockUser.class,
		TestWeaveDraftModel.class })
public class AllTests {

}
