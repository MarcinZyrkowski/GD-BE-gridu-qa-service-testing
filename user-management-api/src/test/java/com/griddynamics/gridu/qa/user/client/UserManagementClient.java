package com.griddynamics.gridu.qa.user.client;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.CreateUserResponse;
import com.griddynamics.gridu.qa.user.Environment;
import com.griddynamics.gridu.qa.user.tests.UserManagementBaseTest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import static com.griddynamics.gridu.qa.user.Environment.SCHEMA_DERIVED_CLASSES_PATH;

public class UserManagementClient extends WebServiceGatewaySupport {

    public UserManagementClient() {
        Jaxb2Marshaller marshaller = marshaller();
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
        this.setDefaultUri(Environment.LOCAL_HOST + UserManagementBaseTest.USER_MANAGEMENT_PORT + "/ws");
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        return (CreateUserResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    private Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(SCHEMA_DERIVED_CLASSES_PATH);
        return marshaller;
    }

}
