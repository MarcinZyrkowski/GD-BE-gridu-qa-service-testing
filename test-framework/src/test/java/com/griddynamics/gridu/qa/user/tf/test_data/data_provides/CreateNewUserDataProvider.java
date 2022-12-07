package com.griddynamics.gridu.qa.user.tf.test_data.data_provides;


import com.griddynamics.gridu.qa.user.CreateUserRequest;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;

import static com.griddynamics.gridu.qa.user.tf.test_data.AddressTestData.*;
import static com.griddynamics.gridu.qa.user.tf.test_data.PaymentTestData.*;
import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareBasicCreateUserRequestData;

public class CreateNewUserDataProvider {

    @DataProvider
    @SneakyThrows
    @Step("Prepare valid user data with address and payment")
    public static Object[][] validUserWithAddressesAndPayments() {
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();

        CreateUserRequest.Addresses addresses = prepareAddresses();
        addNewAddress(addresses, prepareNewAddress());

        CreateUserRequest.Payments payments = preparePayments();
        String cardHolder = createUserRequest.getName() + " " + createUserRequest.getLastName();
        addNewPayment(payments, prepareNewPayment(cardHolder));

        createUserRequest.setAddresses(addresses);
        createUserRequest.setPayments(payments);

        return new Object[][]{
                {
                        createUserRequest
                }
        };
    }

    @DataProvider
    @SneakyThrows
    @Step("Prepare valid user data with payment")
    public static Object[][] validUserWithPayments() {
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();

        CreateUserRequest.Payments payments = preparePayments();
        String cardHolder = createUserRequest.getName() + " " + createUserRequest.getLastName();
        addNewPayment(payments, prepareNewPayment(cardHolder));

        createUserRequest.setPayments(payments);

        return new Object[][]{
                {
                        createUserRequest
                }
        };
    }

    @DataProvider
    @SneakyThrows
    @Step("Prepare valid user data with address")
    public static Object[][] validUserWithAddresses() {
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();

        CreateUserRequest.Addresses addresses = prepareAddresses();
        addNewAddress(addresses, prepareNewAddress());

        createUserRequest.setAddresses(addresses);

        return new Object[][]{
                {
                        createUserRequest
                }
        };
    }

    @DataProvider
    @SneakyThrows
    @Step("Prepare valid user data with mock address and mock payment")
    public static Object[][] validUserWithMockAddressesAndMockPayments() {
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();

        CreateUserRequest.Addresses addresses = prepareAddresses();
        addNewAddress(addresses, prepareMockNewAddress());

        CreateUserRequest.Payments payments = preparePayments();
        String cardHolder = createUserRequest.getName() + " " + createUserRequest.getLastName();
        addNewPayment(payments, prepareMockNewPayment(cardHolder));

        createUserRequest.setAddresses(addresses);
        createUserRequest.setPayments(payments);

        return new Object[][]{
                {
                        createUserRequest
                }
        };
    }

}
