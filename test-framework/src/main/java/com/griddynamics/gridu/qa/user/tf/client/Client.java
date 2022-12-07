package com.griddynamics.gridu.qa.user.tf.client;

import com.griddynamics.gridu.qa.user.*;
import com.griddynamics.gridu.qa.user.tf.Environment;
import io.qameta.allure.Step;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class Client extends WebServiceGatewaySupport {

    public Client(int workingPort) {
        Jaxb2Marshaller marshaller = marshaller();
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
        this.setDefaultUri(Environment.LOCAL_HOST + workingPort + "/ws");
    }

    private Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(Environment.SCHEMA_DERIVED_CLASSES_PATH);
        return marshaller;
    }

    @Step("Create new user request")
    public CreateUserResponse createUser(CreateUserRequest request) {
        return (CreateUserResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    @Step("Get user's details")
    public GetUserDetailsResponse getUserDetails(GetUserDetailsRequest request) {
        return (GetUserDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    @Step("Update user's details")
    public UpdateUserResponse updateUser(UpdateUserRequest request) {
        return (UpdateUserResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    @Step("Delete user")
    public DeleteUserResponse deleteUser(DeleteUserRequest request) {
        return (DeleteUserResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

}
