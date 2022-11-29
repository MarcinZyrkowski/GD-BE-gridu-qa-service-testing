package com.griddynamics.gridu.qa.user.tests.user_management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.griddynamics.gridu.qa.user.UserManagement;
import com.griddynamics.gridu.qa.user.client.UserManagementClient;
import org.springframework.boot.SpringApplication;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class UserManagementBaseTest {
    protected static final int WIREMOCK_USER_MANAGEMENT_PORT = 9898;
    protected UserManagementClient userManagementClient;
    protected WireMockServer wireMockServer;
    protected static final int wiremockPort = 9999;
    protected ObjectMapper objectMapper = new ObjectMapper();

    @BeforeMethod
    public void setUp() {
        userManagementClient = new UserManagementClient(WIREMOCK_USER_MANAGEMENT_PORT);
    }

    public void startWireMock() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(wiremockPort));
        wireMockServer.start();
    }

    @BeforeSuite(alwaysRun = true)
    public void startAppAndWireMock() {
        startWireMock();

        SpringApplication userManagementService = new SpringApplication(UserManagement.class);
        userManagementService.run();
    }

    @AfterSuite(alwaysRun = true)
    public void stopWireMock() {
        wireMockServer.stop();
    }

}
