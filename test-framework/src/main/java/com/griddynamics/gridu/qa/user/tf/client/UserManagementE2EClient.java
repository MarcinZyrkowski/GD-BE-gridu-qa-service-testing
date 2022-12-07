package com.griddynamics.gridu.qa.user.tf.client;

import org.springframework.stereotype.Component;

@Component
public class UserManagementE2EClient extends Client {

    private static final int USER_MANAGEMENT_PORT = 8080;

    public UserManagementE2EClient() {
        super(USER_MANAGEMENT_PORT);
    }

}
