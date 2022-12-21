package com.griddynamics.gridu.qa.user.tf.steps;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.CreateUserResponse;
import io.qameta.allure.Step;

public class GeneralSteps {

    @Step("Verify user data, addresses and payments")
    public static void verifyAllUsersData(CreateUserRequest createUserRequest, CreateUserResponse createUserResponse) {
        UserSteps.verifyBasicUserData(createUserRequest, createUserResponse);
        AddressSteps.verifyAddressLists(createUserResponse, createUserRequest);
        PaymentSteps.verifyPaymentLists(createUserResponse, createUserRequest);
    }

}
