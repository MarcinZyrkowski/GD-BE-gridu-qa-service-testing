package com.griddynamics.gridu.qa.user.steps;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.CreateUserResponse;
import com.griddynamics.gridu.qa.user.NewAddress;
import io.qameta.allure.Step;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserManagementSteps {

    @Step("Verify user is created correctly")
    public static void verifyBasicUserData(CreateUserRequest createUserRequest, CreateUserResponse createUserResponse) {
        assertThat(createUserResponse)
                .as("Response should not be null")
                .isNotNull();
        assertThat(createUserResponse.getUserDetails())
                .as("User Details should not be null")
                .isNotNull();
        assertThat(createUserResponse.getUserDetails())
                .as("User Details should not be null")
                .isNotNull();
        assertThat(createUserResponse.getUserDetails().getId())
                .as("User Details id should not be null")
                .isNotNull();
        assertThat(createUserResponse.getUserDetails().getName())
                .as("User Details name should be as in request").
                isEqualTo(createUserRequest.getName());
        assertThat(createUserResponse.getUserDetails().getLastName())
                .as("User Details lastname should be as in request")
                .isEqualTo(createUserRequest.getLastName());
        assertThat(createUserResponse.getUserDetails().getEmail())
                .as("User Details email should be as in request")
                .isEqualTo(createUserRequest.getEmail());

        XMLGregorianCalendar birthdayResponse = createUserResponse.getUserDetails().getBirthday();
        XMLGregorianCalendar birthdayRequest = createUserRequest.getBirthday();
        LocalDate birthdayResponseLocalDate =
                LocalDate.of(birthdayRequest.getYear(), birthdayResponse.getMonth(), birthdayRequest.getDay());
        LocalDate birthdayRequestLocalDate =
                LocalDate.of(birthdayRequest.getYear(), birthdayRequest.getMonth(), birthdayRequest.getDay());
        assertThat(birthdayResponseLocalDate)
                .as("User Details birthday should be as in request")
                .isEqualTo(birthdayRequestLocalDate);
    }

    @Step("Verify Addresses list")
    public static void verifyAddressesList(CreateUserRequest createUserRequest, CreateUserResponse createUserResponse) {
        createUserResponse.getUserDetails().getAddresses().getAddress()
                .forEach(address -> assertThat(address.getId()).isNotNull());

        // add validation
    }

}
