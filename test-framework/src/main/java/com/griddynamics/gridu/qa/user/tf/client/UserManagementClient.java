package com.griddynamics.gridu.qa.user.tf.client;

import org.springframework.stereotype.Component;

@Component
public class UserManagementClient extends Client {

    private static final int WIREMOCK_USER_MANAGEMENT_PORT = 9898;

    public UserManagementClient() {
        super(WIREMOCK_USER_MANAGEMENT_PORT);
    }

}
