package com.griddynamics.gridu.qa.user.steps;

import com.griddynamics.gridu.qa.user.*;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public
class UserManagementSteps {

    @Step("Verify user is created correctly")
    public static void verifyBasicUserData(CreateUserRequest createUserRequest, CreateUserResponse createUserResponse) {
        assertThat(createUserResponse)
                .as("Response should not be null")
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

        assertThat(createUserResponse.getUserDetails().getBirthday())
                .as("User Details birthday should be as in request")
                .isEqualTo(createUserRequest.getBirthday());
    }

    @Step("Verify Addresses list")
    public static void verifyAddressesList(List<ExistingAddress> actualExistingAddressList,
            List<NewAddress> expectedNewAddressList) {
        actualExistingAddressList.forEach(address -> assertThat(address.getId())
                .as("Created address should have generated id value")
                .isNotNull());

        expectedNewAddressList.forEach(address ->
                assertThat(findExistingAddressByNewAddress(actualExistingAddressList, address).isPresent())
                        .as("Existing address corresponding to new address should be found")
                        .isTrue());

        assertThat(actualExistingAddressList.size())
                .as("New address list size and Existing address list size should be the same")
                .isEqualTo(expectedNewAddressList.size());
    }

    @Step("Find corresponding existing new address")
    public static Optional<ExistingAddress> findExistingAddressByNewAddress(List<ExistingAddress> existingAddressList,
            NewAddress newAddress) {
        return existingAddressList.stream()
                .filter(existingAddress -> existingAddress.getState().equals(newAddress.getState()))
                .filter(existingAddress -> existingAddress.getCity().equals(newAddress.getCity()))
                .filter(existingAddress -> existingAddress.getZip().equals(newAddress.getZip()))
                .filter(existingAddress -> existingAddress.getLine1().equals(newAddress.getLine1()))
                .filter(existingAddress -> existingAddress.getLine2().equals(newAddress.getLine2()))
                .findAny();
    }

    @Step("Verify Payment list")
    public static void verifyPaymentsList(List<ExistingPayment> actualExistingPaymentsList,
            List<NewPayment> expectedNewPaymentsList) {

        actualExistingPaymentsList.forEach(payment -> assertThat(payment.getId())
                .as("Created payment should have generated id value")
                .isNotNull());

        expectedNewPaymentsList.forEach(payment ->
                assertThat(findExistingPaymentByNewPayment(actualExistingPaymentsList, payment).isPresent())
                        .as("Existing payment corresponding to new payment should be found")
                        .isTrue());

        assertThat(actualExistingPaymentsList.size())
                .as("New payment list size and Existing payment list size should be the same")
                .isEqualTo(expectedNewPaymentsList.size());
    }

    @Step("Find corresponding existing new payment")
    public static Optional<ExistingPayment> findExistingPaymentByNewPayment(List<ExistingPayment> existingPaymentList,
            NewPayment newPayment) {
        return existingPaymentList.stream()
                .filter(existingPayment -> existingPayment.getCardNumber().equals(newPayment.getCardNumber()))
                .filter(existingPayment -> existingPayment.getExpiryYear() == newPayment.getExpiryYear())
                .filter(existingPayment -> existingPayment.getExpiryMonth() == newPayment.getExpiryMonth())
                .filter(existingPayment -> existingPayment.getCardholder().equals(newPayment.getCardholder()))
                .filter(existingPayment -> existingPayment.getCvv().equals(newPayment.getCvv()))
                .findAny();
    }

    @Step("Verify created user's details")
    public static void verifyCreatedUserDetails(CreateUserResponse createUserResponse,
            GetUserDetailsResponse getUserDetailsResponse) {
        assertThat(getUserDetailsResponse)
                .as("Response should not be null")
                .isNotNull();

        assertThat(getUserDetailsResponse.getUserDetails())
                .as("User details should not be null")
                .isNotNull();

        verifyBasicUsersDetails(getUserDetailsResponse.getUserDetails(), createUserResponse.getUserDetails());
    }

    @Step("Verify updated user's details")
    public static void verifyUpdatedUserDetails(UpdateUserRequest updateUserRequest,
            UpdateUserResponse updateUserResponse) {
        assertThat(updateUserResponse)
                .as("Response should not be null")
                .isNotNull();

        assertThat(updateUserResponse.getUserDetails())
                .as("User details should not be null")
                .isNotNull();

        verifyBasicUsersDetails(updateUserResponse.getUserDetails(), updateUserRequest.getUserDetails());
    }

    @Step("Compare user's details to expected")
    public static void verifyBasicUsersDetails(UserDetails userDetailsActual, UserDetails userDetailsExpected) {
        assertThat(userDetailsActual.getName())
                .as("Name should be the same as expected")
                .isEqualTo(userDetailsExpected.getName());

        assertThat(userDetailsActual.getLastName())
                .as("Last name should be the same as expected")
                .isEqualTo(userDetailsExpected.getLastName());

        assertThat(userDetailsActual.getEmail())
                .as("Email should be the same as expected")
                .isEqualTo(userDetailsExpected.getEmail());

        assertThat(userDetailsActual.getBirthday())
                .as("Birthday should be the same as expected")
                .isEqualTo(userDetailsExpected.getBirthday());
    }

}
