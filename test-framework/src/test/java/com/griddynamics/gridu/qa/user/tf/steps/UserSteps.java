package com.griddynamics.gridu.qa.user.tf.steps;

import com.griddynamics.gridu.qa.user.*;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;

public class UserSteps {

    @Step("Verify user is created correctly")
    public static void verifyBasicUserData(CreateUserRequest createUserRequest, CreateUserResponse createUserResponse) {
        Assertions.assertThat(createUserResponse)
                .as("Response should not be null")
                .isNotNull();
        Assertions.assertThat(createUserResponse.getUserDetails())
                .as("User Details should not be null")
                .isNotNull();
        Assertions.assertThat(createUserResponse.getUserDetails().getId())
                .as("User Details id should not be null")
                .isNotNull();
        Assertions.assertThat(createUserResponse.getUserDetails().getName())
                .as("User Details name should be as in request").
                isEqualTo(createUserRequest.getName());
        Assertions.assertThat(createUserResponse.getUserDetails().getLastName())
                .as("User Details lastname should be as in request")
                .isEqualTo(createUserRequest.getLastName());
        Assertions.assertThat(createUserResponse.getUserDetails().getEmail())
                .as("User Details email should be as in request")
                .isEqualTo(createUserRequest.getEmail());

        Assertions.assertThat(createUserResponse.getUserDetails().getBirthday())
                .as("User Details birthday should be as in request")
                .isEqualTo(createUserRequest.getBirthday());
    }

    @Step("Verify created user's details")
    public static void verifyCreatedUserDetails(CreateUserResponse createUserResponse,
            GetUserDetailsResponse getUserDetailsResponse) {
        Assertions.assertThat(getUserDetailsResponse)
                .as("Response should not be null")
                .isNotNull();

        Assertions.assertThat(getUserDetailsResponse.getUserDetails())
                .as("User details should not be null")
                .isNotNull();

        verifyBasicUsersDetails(getUserDetailsResponse.getUserDetails(), createUserResponse.getUserDetails());
    }

    @Step("Verify updated user's details")
    public static void verifyUpdatedUserDetails(UpdateUserRequest updateUserRequest,
            UpdateUserResponse updateUserResponse) {
        Assertions.assertThat(updateUserResponse)
                .as("Response should not be null")
                .isNotNull();

        Assertions.assertThat(updateUserResponse.getUserDetails())
                .as("User details should not be null")
                .isNotNull();

        verifyBasicUsersDetails(updateUserResponse.getUserDetails(), updateUserRequest.getUserDetails());
    }

    @Step("Compare user's details to expected")
    public static void verifyBasicUsersDetails(UserDetails userDetailsActual, UserDetails userDetailsExpected) {
        Assertions.assertThat(userDetailsActual.getName())
                .as("Name should be the same as expected")
                .isEqualTo(userDetailsExpected.getName());

        Assertions.assertThat(userDetailsActual.getLastName())
                .as("Last name should be the same as expected")
                .isEqualTo(userDetailsExpected.getLastName());

        Assertions.assertThat(userDetailsActual.getEmail())
                .as("Email should be the same as expected")
                .isEqualTo(userDetailsExpected.getEmail());

        Assertions.assertThat(userDetailsActual.getBirthday())
                .as("Birthday should be the same as expected")
                .isEqualTo(userDetailsExpected.getBirthday());
    }
}
