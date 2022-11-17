package com.griddynamics.gridu.qa.user.tests;

import com.griddynamics.gridu.qa.user.client.UserManagementClient;
import org.testng.annotations.BeforeSuite;

public class UserManagementBaseTest {

    public static final int USER_MANAGEMENT_PORT = 8080;
    protected UserManagementClient userManagementClient;

    @BeforeSuite
    public void setUp(){
        userManagementClient = new UserManagementClient();
    }

}
