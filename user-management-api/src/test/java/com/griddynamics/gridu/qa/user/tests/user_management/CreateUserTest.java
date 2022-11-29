package com.griddynamics.gridu.qa.user.tests.user_management;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.CreateUserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.report.user_management.UserManagementFeatures.*;
import static com.griddynamics.gridu.qa.user.tests.test_data.PaymentTestData.*;
import static com.griddynamics.gridu.qa.user.tests.test_data.UserTestData.prepareBasicCreateUserRequestData;

@Feature(UM_FEATURE_USER)
@Story(UM_PBI_CREATE_USER)
public class CreateUserTest extends UserManagementBaseTest {

    @Test(description = UM_TC_CREATE_USER_ERROR_ON_SAVING_PAYMENT, expectedExceptions = SoapFaultClientException.class,
    expectedExceptionsMessageRegExp = "Can not save user payments!")
    @Description(UM_TC_CREATE_USER_ERROR_ON_SAVING_PAYMENT)
    public void createNewUser() {

        wireMockServer.stubFor(WireMock
                .post(WireMock.urlMatching("\\/payment\\/\\d+"))
                .willReturn(WireMock.serverError()));

        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();
        CreateUserRequest.Payments payments = preparePayments();
        String cardHolder = createUserRequest.getName() + " " + createUserRequest.getLastName();
        addNewPayment(payments, prepareNewPayment(cardHolder));
        createUserRequest.setPayments(payments);

        userManagementClient.createUser(createUserRequest);
    }

}
