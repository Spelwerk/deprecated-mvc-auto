package common;

import common.enums.*;

import java.util.List;

public class TestData extends Core {
    private TestEnvironment testEnvironment;

    public TestData(TestEnvironment testEnvironment) {
        this.testEnvironment = testEnvironment;
    }

    public String getTestEnvironmentURL() {
        return testEnvironment.getBaseURL();
    }

    // PRIVATE

    private String getRandomString(List<String> list) {
        int random = (int)(Math.random()*list.size());
        return list.get(random);
    }
}
