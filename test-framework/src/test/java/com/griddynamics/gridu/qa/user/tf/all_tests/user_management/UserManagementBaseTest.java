package com.griddynamics.gridu.qa.user.tf.all_tests.user_management;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.griddynamics.gridu.qa.user.UserManagement;
import com.griddynamics.gridu.qa.user.tf.client.UserManagementClient;
import com.griddynamics.gridu.qa.user.tf.conf.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@ContextConfiguration(classes = Config.class)
public class UserManagementBaseTest extends AbstractTestNGSpringContextTests {
    protected static final int wiremockPort = 9999;
    @Autowired
    protected UserManagementClient client;
    protected WireMockServer wireMockServer;


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
