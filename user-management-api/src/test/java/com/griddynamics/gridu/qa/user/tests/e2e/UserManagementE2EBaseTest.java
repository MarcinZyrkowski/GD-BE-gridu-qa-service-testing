package com.griddynamics.gridu.qa.user.tests.e2e;

import com.griddynamics.gridu.qa.user.client.UserManagementClient;
import org.testng.annotations.BeforeMethod;

public class UserManagementE2EBaseTest {

    public static final int USER_MANAGEMENT_PORT = 8080;
    protected UserManagementClient userManagementClient;

    @BeforeMethod
    public void setUp() {
        userManagementClient = new UserManagementClient();
    }

}
