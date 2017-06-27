package tests.priority;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RunHigh.class,
        RunMedium.class,
        RunLow.class
})
public class RunAll {}