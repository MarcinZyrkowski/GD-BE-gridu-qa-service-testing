package com.griddynamics.gridu.qa.user.client;

import com.griddynamics.gridu.qa.user.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import static com.griddynamics.gridu.qa.user.Environment.LOCAL_HOST;
import static com.griddynamics.gridu.qa.user.Environment.SCHEMA_DERIVED_CLASSES_PATH;
import static com.griddynamics.gridu.qa.user.tests.e2e.UserManagementE2EBaseTest.USER_MANAGEMENT_PORT;

public class UserManagementClient extends WebServiceGatewaySupport {

    public UserManagementClient() {
        Jaxb2Marshaller marshaller = marshaller();
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
        this.setDefaultUri(LOCAL_HOST + USER_MANAGEMENT_PORT + "/ws");
    }

    private Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(SCHEMA_DERIVED_CLASSES_PATH);
        return marshaller;
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        return (CreateUserResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public GetUserDetailsResponse getUserDetails(GetUserDetailsRequest request) {
        return (GetUserDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public UpdateUserResponse updateUser(UpdateUserRequest request) {
        return (UpdateUserResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public DeleteUserResponse deleteUser(DeleteUserRequest request) {
        return (DeleteUserResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

}
