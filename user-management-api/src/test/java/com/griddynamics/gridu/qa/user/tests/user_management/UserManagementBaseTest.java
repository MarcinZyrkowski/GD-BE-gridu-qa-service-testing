package com.griddynamics.gridu.qa.user.tests.user_management;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.griddynamics.gridu.qa.user.client.UserManagementClient;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.griddynamics.gridu.qa.user.Environment.LOCAL_HOST;

public class UserManagementBaseTest {

    public static final int WIREMOCK_USER_MANAGEMENT_PORT = 8080;
    protected UserManagementClient userManagementClient;
    protected WireMockServer wireMockServer;
    protected int wiremockPort = 9999;
    protected String wiremockUrl = LOCAL_HOST + wiremockPort;

    @BeforeMethod
    public void setUp() {
        userManagementClient = new UserManagementClient(WIREMOCK_USER_MANAGEMENT_PORT);
    }

    @BeforeSuite
    public void startWireMock() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(wiremockPort));
        wireMockServer.start();
    }

    @AfterSuite
    public void stopWireMock() {
        wireMockServer.stop();
    }

}
