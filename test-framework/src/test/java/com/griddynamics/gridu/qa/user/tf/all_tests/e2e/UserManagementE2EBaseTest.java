package com.griddynamics.gridu.qa.user.tf.all_tests.e2e;

import com.griddynamics.gridu.qa.user.tf.client.UserManagementE2EClient;
import com.griddynamics.gridu.qa.user.tf.conf.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = Config.class)
public class UserManagementE2EBaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected UserManagementE2EClient client;

}
