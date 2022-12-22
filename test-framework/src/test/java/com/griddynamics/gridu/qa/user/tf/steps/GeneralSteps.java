package com.griddynamics.gridu.qa.user.tf.steps;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.CreateUserResponse;
import com.griddynamics.gridu.qa.user.UpdateUserRequest;
import com.griddynamics.gridu.qa.user.UpdateUserResponse;
import io.qameta.allure.Step;

import static com.griddynamics.gridu.qa.user.tf.steps.AddressSteps.verifyUpdatedAddressLists;
import static com.griddynamics.gridu.qa.user.tf.steps.PaymentSteps.verifyUpdatedPaymentLists;

public class GeneralSteps {

    @Step("Verify user data, addresses and payments")
    public static void verifyAllUsersData(CreateUserRequest createUserRequest, CreateUserResponse createUserResponse) {
        UserSteps.verifyBasicUserData(createUserRequest, createUserResponse);
        AddressSteps.verifyAddressLists(createUserResponse, createUserRequest);
        PaymentSteps.verifyUpdatedPaymentLists(createUserResponse, createUserRequest);
    }

    @Step("Verify user updated data, addresses and payments")
    public static void verifyAllUsersUpdatedData(UpdateUserRequest updateUserRequest,
            UpdateUserResponse updateUserResponse) {
        UserSteps.verifyUpdatedUserDetails(updateUserRequest, updateUserResponse);
        verifyUpdatedPaymentLists(updateUserResponse.getUserDetails(), updateUserRequest.getUserDetails());
        verifyUpdatedAddressLists(updateUserResponse.getUserDetails(), updateUserRequest.getUserDetails());
    }

}
