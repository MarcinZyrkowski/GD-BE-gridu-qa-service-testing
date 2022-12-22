package com.griddynamics.gridu.qa.user.tf.steps;

import com.griddynamics.gridu.qa.user.*;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Optional;

public class AddressSteps {

    @Step("Verify Address lists")
    public static void verifyAddressLists(CreateUserResponse responseWithActualAddresses,
            CreateUserRequest requestWithExpectedAddresses) {

        if (requestWithExpectedAddresses.getAddresses() == null ||
                requestWithExpectedAddresses.getAddresses().getAddress() == null) {

            Assertions.assertThat(responseWithActualAddresses.getUserDetails().getAddresses().getAddress().isEmpty())
                    .isTrue();
        } else {
            compareAddressLists(responseWithActualAddresses.getUserDetails().getAddresses().getAddress(),
                    requestWithExpectedAddresses.getAddresses().getAddress());
        }
    }

    @Step("Verify updated address lists")
    public static void verifyUpdatedAddressLists(UserDetails userDetailsWithActualAddresses,
            UserDetails userDetailsWithExpectedAddresses) {

        if (userDetailsWithExpectedAddresses.getAddresses() == null ||
                userDetailsWithExpectedAddresses.getAddresses().getAddress() == null) {

            Assertions.assertThat(userDetailsWithActualAddresses.getAddresses().getAddress().isEmpty())
                    .isTrue();
        } else {
            compareExistingAddressLists(userDetailsWithActualAddresses.getAddresses().getAddress(),
                    userDetailsWithExpectedAddresses.getAddresses().getAddress());
        }
    }

    @Step("Compare Address lists")
    public static void compareAddressLists(List<ExistingAddress> actualExistingAddressList,
            List<NewAddress> expectedAddressDataList) {

        if (expectedAddressDataList == null) {
            Assertions.assertThat(actualExistingAddressList).isNull();
        } else {
            actualExistingAddressList.forEach(address -> Assertions.assertThat(address.getId())
                    .as("Created address should have generated id value")
                    .isNotNull());

            expectedAddressDataList.forEach(address ->
                    Assertions.assertThat(
                                    findExistingAddressByNewAddress(actualExistingAddressList, address).isPresent())
                            .as("Existing address corresponding to new address should be found")
                            .isTrue());

            Assertions.assertThat(actualExistingAddressList.size())
                    .as("New address list size and Existing address list size should be the same")
                    .isEqualTo(expectedAddressDataList.size());
        }
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

    @Step("Compare existing address lists")
    public static void compareExistingAddressLists(List<ExistingAddress> actualExistingAddressList,
            List<ExistingAddress> expectedAddressDataList) {

        actualExistingAddressList.forEach(address -> Assertions.assertThat(address.getId())
                .as("Existing address should have generated id value")
                .isNotNull());

        expectedAddressDataList.forEach(address ->
                Assertions.assertThat(findExistingAddressByNewAddress(actualExistingAddressList,
                                address).isPresent())
                        .as("Existing address corresponding to new address should be found")
                        .isTrue());

        Assertions.assertThat(actualExistingAddressList.size())
                .as("New payment list size and Existing address list size should be the same")
                .isEqualTo(expectedAddressDataList.size());
    }

}
