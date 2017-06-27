package common.enums;

public enum TestEnvironment {
    STAGING(""),
    PRODUCTION("");

    String baseURL;

    TestEnvironment(String baseURL) { this.baseURL = baseURL; }

    public String getBaseURL() { return baseURL; }
}